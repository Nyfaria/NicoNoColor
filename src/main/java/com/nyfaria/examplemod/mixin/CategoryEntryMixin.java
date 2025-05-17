package com.nyfaria.examplemod.mixin;

import net.minecraft.client.gui.screens.controls.KeyBindsList;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBindsList.CategoryEntry.class)
public class CategoryEntryMixin {

    @Shadow @Final @Mutable
    Component name;

    @Inject(method="<init>", at=@At("RETURN"))
    public void onInit(KeyBindsList this$0, Component pName, CallbackInfo ci) {
        if(pName.getString().equals("100 Media Abilities")){
            this.name = Component.literal("Abilities");
        }
    }
}
