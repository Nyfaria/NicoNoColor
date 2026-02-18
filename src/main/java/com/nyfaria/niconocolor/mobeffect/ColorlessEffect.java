package com.nyfaria.niconocolor.mobeffect;


import com.mojang.blaze3d.vertex.PoseStack;
import com.nyfaria.niconocolor.cap.BWHolderAttacher;
import com.nyfaria.niconocolor.init.AbilityInit;
import dev._100media.hundredmediaabilities.capability.MarkerHolderAttacher;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ColorlessEffect extends MobEffect {
    public ColorlessEffect() {
        super(MobEffectCategory.BENEFICIAL, 0x000000);
    }

    @Override
    public void addAttributeModifiers(LivingEntity pLivingEntity, AttributeMap pAttributeMap, int pAmplifier) {
        super.addAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
        MarkerHolderAttacher.getMarkerHolder(pLivingEntity).ifPresent(attacher -> {
            attacher.addMarker(AbilityInit.BW_MARKER.get(),true);
        });
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        MarkerHolderAttacher.getMarkerHolder(pLivingEntity).ifPresent(attacher -> {
            attacher.addMarker(AbilityInit.BW_MARKER.get(),true);
        });
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity pSource, @Nullable Entity pIndirectSource, LivingEntity pLivingEntity, int pAmplifier, double pHealth) {
        MarkerHolderAttacher.getMarkerHolder(pLivingEntity).ifPresent(attacher -> {
            attacher.addMarker(AbilityInit.BW_MARKER.get(),true);
        });
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public void initializeClient(Consumer<IClientMobEffectExtensions> consumer) {
        consumer.accept(new IClientMobEffectExtensions() {
            @Override
            public boolean isVisibleInInventory(MobEffectInstance instance) {
                return false;
            }

            @Override
            public boolean isVisibleInGui(MobEffectInstance instance) {
                return false;
            }

            @Override
            public boolean renderInventoryIcon(MobEffectInstance instance, EffectRenderingInventoryScreen<?> screen, PoseStack poseStack, int x, int y, int blitOffset) {
                return false;
            }

            @Override
            public boolean renderInventoryText(MobEffectInstance instance, EffectRenderingInventoryScreen<?> screen, PoseStack poseStack, int x, int y, int blitOffset) {
                return false;
            }

            @Override
            public boolean renderGuiIcon(MobEffectInstance instance, Gui gui, PoseStack poseStack, int x, int y, float z, float alpha) {
                return false;
            }
        });
    }
}
