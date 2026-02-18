package com.nyfaria.niconocolor.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.nyfaria.niconocolor.ModRenderTypes;
import com.nyfaria.niconocolor.cap.BWHolder;
import com.nyfaria.niconocolor.cap.BWHolderAttacher;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RenderLayer.class)
public class RenderLayerMixin {

    @WrapOperation(method = "renderColoredCutoutModel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;entityCutoutNoCull(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/client/renderer/RenderType;"))
    private static RenderType nico$renderColoredCutoutModel(ResourceLocation pLocation, Operation<RenderType> original, @Local(ordinal = 0) LivingEntity pEntity) {
        BWHolder holder = BWHolderAttacher.getUnwrap(pEntity);
        if (holder != null && holder.isBlackAndWhite()) {
            return ModRenderTypes.ENTITY_TRANSLUCENT.apply(pLocation,true);
        }
        return original.call(pLocation);
    }

}
