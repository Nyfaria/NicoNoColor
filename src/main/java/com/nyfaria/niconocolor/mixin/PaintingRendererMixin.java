package com.nyfaria.niconocolor.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.nyfaria.niconocolor.ModRenderTypes;
import com.nyfaria.niconocolor.cap.BWHolder;
import com.nyfaria.niconocolor.cap.BWHolderAttacher;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.PaintingRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.Painting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PaintingRenderer.class)
public class PaintingRendererMixin {

    @WrapOperation(method="render(Lnet/minecraft/world/entity/decoration/Painting;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",at= @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;entitySolid(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/client/renderer/RenderType;"))
    private RenderType nico$renderPainting(ResourceLocation pLocation, Operation<RenderType> original, @Local(argsOnly = true) Painting pEntity) {
        BWHolder holder = BWHolderAttacher.getUnwrap(pEntity);
        if (holder != null && holder.isBlackAndWhite()) {
            return ModRenderTypes.ENTITY_TRANSLUCENT.apply(pLocation,true);
        }
        return original.call(pLocation);
    }
}
