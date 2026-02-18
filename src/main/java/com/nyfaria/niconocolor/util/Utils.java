package com.nyfaria.niconocolor.util;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.function.Predicate;

public class Utils {
    public static void projectileRotation(Entity projectile, Vec3 deltaMovement) {
        double deltaX = deltaMovement.x;
        double deltaY = deltaMovement.y;
        double deltaZ = deltaMovement.z;
        double deltaDistance = deltaMovement.horizontalDistance();
        projectile.setYRot((float) (Mth.atan2(deltaX, deltaZ) * (double) (180F / (float) Math.PI)));
        projectile.setXRot((float) (Mth.atan2(deltaY, deltaDistance) * (double) (180F / (float) Math.PI)));
        projectile.setXRot(lerpRotation(projectile.xRotO, projectile.getXRot()));
        projectile.setYRot(lerpRotation(projectile.yRotO, projectile.getYRot()));
    }
    public static Vec3 getRandomPointOnSphere(RandomSource random, Vec3 origin, float radius) {
        return new Vec3(random.nextGaussian(), random.nextGaussian(), random.nextGaussian()).normalize().scale(radius).add(origin);
    }
    protected static float lerpRotation(float rot1, float rot2) {
        while (rot2 - rot1 < -180.0F) {
            rot1 -= 360.0F;
        }

        while (rot2 - rot1 >= 180.0F) {
            rot1 += 360.0F;
        }

        return Mth.lerp(0.2F, rot1, rot2);
    }

    public static LivingEntity getTarget(LivingEntity shooter, float range) {
        EntityHitResult ehr = VectorUtils.rayTraceEntities(shooter.level,shooter,range,(e)->true);
        if (ehr != null) {
            if(ehr.getEntity() instanceof LivingEntity livingEntity) {
                return livingEntity;
            }
        }
        return null;
    }

    public static HitResult scanLineOfSightHit(LivingEntity shooter, float range) {
        Predicate<Entity> filter = e -> true;
        Vec3 eyePos = shooter.getEyePosition(1);
        Vec3 lookDirection = shooter.getLookAngle();
        Vec3 traceVec = eyePos.add(lookDirection.scale(range));

        HitResult result = shooter.level.clip(new ClipContext(eyePos, traceVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, shooter));
        Vec3 resultVec = traceVec;
        if (result.getType() != HitResult.Type.MISS) {
            resultVec = result.getLocation();
        }

        AABB box = new AABB(eyePos, resultVec);
        HitResult projectileResult = ProjectileUtil.getEntityHitResult(shooter.level, shooter, eyePos, resultVec, box, filter);
        if (projectileResult != null) {
            result = projectileResult;
        }
        return result;
    }

    public static Vec3 calculateViewVector(float pXRot, float pYRot) {
        float f = pXRot * ((float)Math.PI / 180F);
        float f1 = -pYRot * ((float)Math.PI / 180F);
        float f2 = Mth.cos(f1);
        float f3 = Mth.sin(f1);
        float f4 = Mth.cos(f);
        float f5 = Mth.sin(f);
        return new Vec3((f3 * f4), (-f5), (f2 * f4));
    }

    public static void simulateJump(LivingEntity entity, float modifier) {
        float currentBlockFactor = entity.level.getBlockState(entity.blockPosition()).getBlock().getJumpFactor();
        float belowBlockFactor = entity.level.getBlockState(new BlockPos(entity.position().x, entity.getBoundingBox().minY - 0.5000001D, entity.position().z)).getBlock().getJumpFactor();
        float jumpFactor = currentBlockFactor == 1.0F ? belowBlockFactor : currentBlockFactor;
        double jumpStrength = (0.42F * jumpFactor + entity.getJumpBoostPower()) * modifier;
        Vec3 movement = entity.getDeltaMovement();
        entity.setDeltaMovement(movement.x, jumpStrength, movement.z);
        if (entity.isSprinting()) {
            float speedAdjustment = entity.getYRot() * ((float)Math.PI / 180F);
            entity.setDeltaMovement(entity.getDeltaMovement().add((-Mth.sin(speedAdjustment) * 0.2F), 0.0D, (Mth.cos(speedAdjustment) * 0.2F)));
        }

        entity.hurtMarked = true;
    }












    public static Vec2 randomCircleVector(float radius, Vec2 center, RandomSource random, boolean edgeOnly) {
        if (!edgeOnly) {
            radius = (float)Math.sqrt(random.nextFloat()) * radius;
        }
        double radial = random.nextFloat() * 2 * Math.PI;
        double x = center.x + radius * Math.cos(radial);
        double y = center.y + radius * Math.sin(radial);
        return new Vec2((float)x, (float)y);
    }

//    public static float scaleAbilityDamage(LivingEntity source, float baseDamage) {
//        return baseDamage * (1.0f + (0.05f * MarkerHolderAttacher.getMarkerStackCount(source, HMAMarkerInit.ABILITY_DAMAGE.get())));
//    }
}
