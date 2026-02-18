package com.nyfaria.niconocolor.event;

import com.nyfaria.niconocolor.ModRenderTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientForgeEvents {
    public static boolean broken = false;
    public static boolean lastBroken = false;
//
//    @OnlyIn(Dist.CLIENT)
//    @SubscribeEvent
//    public static void onBlockBreak(BlockEvent.BreakEvent event) {
//        // Handle block break event
//        if(lastBroken)
//            broken = false;
//        if(broken)return;
//        broken = true;
//        lastBroken = false;
//
//    }
//    @SubscribeEvent
//    public static void onRenderLevel(RenderLevelStageEvent event) {
//        if(event.getStage() == RenderLevelStageEvent.Stage.AFTER_CUTOUT_BLOCKS){
//            if(lastBroken)return;
//            if(broken){
//                Minecraft.getInstance().levelRenderer.allChanged();
//                lastBroken = true;
//            }
//        }
//    }
}
