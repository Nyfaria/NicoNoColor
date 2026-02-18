package com.nyfaria.niconocolor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class RenderLayerHelper {

    protected static <T extends LivingEntity> void coloredCutoutModelCopyLayerRender(EntityModel<T> pModelParent, EntityModel<T> pModel, ResourceLocation pTextureLocation, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, float pPartialTicks, float pRed, float pGreen, float pBlue) {
        if (!pEntity.isInvisible()) {
            pModelParent.copyPropertiesTo(pModel);
            pModel.prepareMobModel(pEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
            pModel.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
            renderColoredCutoutModel(pModel, pTextureLocation, pMatrixStack, pBuffer, pPackedLight, pEntity, pRed, pGreen, pBlue);
        }

    }

    protected static <T extends LivingEntity> void renderColoredCutoutModel(EntityModel<T> pModel, ResourceLocation pTextureLocation, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pEntity, float pRed, float pGreen, float pBlue) {
        VertexConsumer $$9 = pBuffer.getBuffer(ModRenderTypes.ENTITY_CUTOUT.apply(pTextureLocation));
        pModel.renderToBuffer(pMatrixStack, $$9, pPackedLight, LivingEntityRenderer.getOverlayCoords(pEntity, 0.0F), pRed, pGreen, pBlue, 1.0F);
    }
}
