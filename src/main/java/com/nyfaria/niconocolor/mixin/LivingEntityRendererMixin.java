package com.nyfaria.niconocolor.mixin;

import com.nyfaria.niconocolor.ModRenderTypes;
import com.nyfaria.niconocolor.cap.BWHolderAttacher;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin extends EntityRenderer<LivingEntity> {

    protected LivingEntityRendererMixin(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Inject(method="getRenderType", at=@At("HEAD"), cancellable = true)
    private void onGetRenderType(LivingEntity pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing, CallbackInfoReturnable<RenderType> cir) {
        BWHolderAttacher.get(pLivingEntity).ifPresent(attacher -> {
            if(attacher.isBlackAndWhite()) {
                cir.setReturnValue(ModRenderTypes.ENTITY_TRANSLUCENT.apply(this.getTextureLocation(pLivingEntity), true));
            }
        });
    }
}
