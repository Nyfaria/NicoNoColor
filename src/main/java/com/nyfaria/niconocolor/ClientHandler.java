package com.nyfaria.niconocolor;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import java.util.List;

public class ClientHandler {
    public static List<BlockPos> BW_BLOCKS = List.of();

    public static void extracted(Level level, BlockPos pos) {
        Minecraft.getInstance().levelRenderer.blockChanged(level, pos,level.getBlockState(pos),level.getBlockState(pos),3);
    }
}
