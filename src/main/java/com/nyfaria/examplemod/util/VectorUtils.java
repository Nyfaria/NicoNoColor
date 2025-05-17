package com.nyfaria.examplemod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Predicate;


public class VectorUtils {

    /**
     * returns a Vec3 that is the result of linear interpolation between the two input vectors at a scale of s, where 0 returns start and 1.0 returns end
     */
    public static Vec3 vec3Lerp(Vec3 start, Vec3 end, double s) {
        return start.scale(1.0 - s).add(end.scale(s));
    }

    /**
     * returns a float between 90 (straight down) and -90 (straight up) representing the pitch of the given direction
     * @param direction - a vector representing a direction
     * @return - the pitch of the direction
     */
    public static double findXRot(Vec3 direction) {
        float f = Mth.sqrt((float)(direction.x*direction.x+direction.z*direction.z));
        return -(float)(Mth.atan2(direction.y, f) * (double)(180F / (float)Math.PI));
    }

    /**
     * returns a float between 0 and 360 representing the yaw of the given direction
     * @param direction - a vector representing a direction
     * @return - the yaw of the direction
     */
    public static double findYRot(Vec3 direction) {
        return -(float)(Mth.atan2(direction.x, direction.z) * (double)(180F / (float)Math.PI));
    }

    /**
     * returns the angle between two directional vectors
     */
    public static double vectorAngle(Vec3 one, Vec3 two) {
        return Math.acos(one.dot(two) / (one.length() * two.length()));
    }

    /**
     * returns a magnitude 1 vector representing the direction from a position to a target position
     */
    public static Vec3 vectorDirection(Vec3 current, Vec3 target) {
        return target.subtract(current).normalize();
    }


    public static float interpolate(float floor, float ceiling, float scale) {
        return ((ceiling - floor) * scale) + floor;
    }

    public static Direction findHorizontalDirection(BlockPos pos, Vec3 vector) {
        Vec3 center = Vec3.atCenterOf(pos);
        Vec3 direction = vector.subtract(center);
        boolean eastWest = (Math.abs(direction.x()) > Math.abs(direction.z()));
        if (eastWest) {
            if (direction.x >= 0) {
                return Direction.EAST;
            } else {
                return Direction.WEST;
            }
        } else {
            if (direction.z >= 0) {
                return Direction.SOUTH;
            } else {
                return Direction.NORTH;
            }
        }
    }
    public static BlockHitResult blockTrace(LivingEntity livingEntity, ClipContext.Fluid rayTraceFluid, int range, boolean downOrFace) {
        Level level = livingEntity.level;
        ClipContext context;
        Vec3 start = new Vec3(livingEntity.getX(), livingEntity.getY() + livingEntity.getEyeHeight(), livingEntity.getZ());
        Vec3 look;

        if (!downOrFace) {
            look = livingEntity.getLookAngle();
        } else {
            look = new Vec3(0, -range, 0);
        }
        Vec3 end = new Vec3(livingEntity.getX() + look.x * (double) range, livingEntity.getY() + livingEntity.getEyeHeight() + look.y * (double) range, livingEntity.getZ() + look.z * (double) range);
        context = new ClipContext(start, end, ClipContext.Block.COLLIDER, rayTraceFluid, livingEntity);
        return level.clip(context);
    }
    public static Vec3 fromEntityCenter(Entity e) {
        return new Vec3(e.getX(), e.getY() - e.getMyRidingOffset() + e.getBbHeight() / 2, e.getZ());
    }


    public static EntityHitResult rayTraceEntities(Level level, Entity origin, float range, Predicate<Entity> filter) {
        Vec3 look = origin.getViewVector(0);
        Vec3 startVec = origin.getEyePosition(0);
        Vec3 endVec = startVec.add(look.x * range, look.y * range, look.z * range);
        AABB box = new AABB(startVec, endVec);
        return rayTraceEntities(level,origin,startVec,endVec,box,filter);
    }
    public static EntityHitResult rayTraceEntities(Level level, @Nullable Entity origin, Vec3 startVec, Vec3 endVec, AABB boundingBox, Predicate<Entity> filter) {
        double d0 = Double.MAX_VALUE;
        Entity entity = null;
        for (Entity entity1 : level.getEntities(origin, boundingBox, filter)) {
            if (entity1.isSpectator()) {
                continue;
            }
            AABB aabb = entity1.getBoundingBox();
            if (aabb.getSize() < 0.3) {
                aabb = aabb.inflate(0.3);
            }
            Optional<Vec3> optional = aabb.clip(startVec, endVec);
            if (optional.isPresent()) {
                double d1 = startVec.distanceToSqr(optional.get());
                if (d1 < d0) {
                    entity = entity1;
                    d0 = d1;
                }
            }
        }

        return entity == null ? null : new EntityHitResult(entity);

    }
}