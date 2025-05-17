package com.nyfaria.examplemod.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import dev._100media.hundredmediaabilities.HundredMediaAbilitiesMod;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeModeInventoryScreen.class)
public class CreativeModeTabMixin {
    @Inject(method = "renderTabButton", at = @At("HEAD"), cancellable = true, remap = false)
    private void removeTab(PoseStack pPoseStack, CreativeModeTab pCreativeModeTab, CallbackInfo ci) {
        if (pCreativeModeTab == HundredMediaAbilitiesMod.TAB) {
            ci.cancel();
        }
    }
}
