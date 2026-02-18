package com.nyfaria.niconocolor.event;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.nyfaria.niconocolor.ModRenderTypes;
import com.nyfaria.niconocolor.ModShaders;
import com.nyfaria.niconocolor.NicoNoColor;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = NicoNoColor.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public static void onRegisterShaders(RegisterShadersEvent event) throws IOException {
        ModShaders.BW_CUTOUT_SHADER_INSTANCE = new ShaderInstance(event.getResourceManager(), new ResourceLocation(NicoNoColor.MODID, "rendertype_cutout"), DefaultVertexFormat.BLOCK);
        ModShaders.BW_ENTITY_CUTOUT_SHADER_INSTANCE = new ShaderInstance(event.getResourceManager(), new ResourceLocation(NicoNoColor.MODID,"rendertype_entity_solid"), DefaultVertexFormat.NEW_ENTITY);
        ModShaders.BW_ENTITY_TRANSLUCENT_SHADER_INSTANCE = new ShaderInstance(event.getResourceManager(), new ResourceLocation(NicoNoColor.MODID,"rendertype_entity_translucent"), DefaultVertexFormat.NEW_ENTITY);
        ModShaders.BW_SOLID_SHADER_INSTANCE = new ShaderInstance(event.getResourceManager(), new ResourceLocation(NicoNoColor.MODID,"rendertype_solid"), DefaultVertexFormat.BLOCK);
        event.registerShader(ModShaders.BW_CUTOUT_SHADER_INSTANCE, shader -> {
            ModShaders.BW_CUTOUT_SHADER_INSTANCE = shader;
        });
        event.registerShader(ModShaders.BW_ENTITY_CUTOUT_SHADER_INSTANCE, shader -> {
            ModShaders.BW_ENTITY_CUTOUT_SHADER_INSTANCE = shader;
        });
        event.registerShader(ModShaders.BW_ENTITY_TRANSLUCENT_SHADER_INSTANCE, shader -> {
            ModShaders.BW_ENTITY_TRANSLUCENT_SHADER_INSTANCE = shader;
        });
        event.registerShader(ModShaders.BW_SOLID_SHADER_INSTANCE, shader -> {
            ModShaders.BW_SOLID_SHADER_INSTANCE = shader;
        });
    }
}
