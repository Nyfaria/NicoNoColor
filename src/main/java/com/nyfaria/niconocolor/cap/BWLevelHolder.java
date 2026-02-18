package com.nyfaria.niconocolor.cap;

import com.nyfaria.niconocolor.ClientHandler;
import com.nyfaria.niconocolor.NicoNoColor;
import com.nyfaria.niconocolor.network.NetworkHandler;
import dev._100media.capabilitysyncer.core.LevelCapability;
import dev._100media.capabilitysyncer.network.LevelCapabilityStatusPacket;
import dev._100media.capabilitysyncer.network.SimpleLevelCapabilityStatusPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.ArrayList;
import java.util.List;

public class BWLevelHolder extends LevelCapability {

    public List<BlockPos> bwList = new ArrayList<>();

    protected BWLevelHolder(Level level) {
        super(level);
    }

    public void add(BlockPos pos) {
        if (!bwList.contains(pos)) {
            bwList.add(pos);
            level.blockUpdated(pos, level.getBlockState(pos).getBlock());
            updateTracking();
        }
    }

    @Override
    public CompoundTag serializeNBT(boolean savingToDisk) {
        CompoundTag tag = new CompoundTag();
        bwList.forEach(pos -> {
            CompoundTag posTag = new CompoundTag();
            posTag.putInt("x", pos.getX());
            posTag.putInt("y", pos.getY());
            posTag.putInt("z", pos.getZ());
            tag.put(pos.toString(), posTag);
        });
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt, boolean readingFromDisk) {
        bwList.clear();
        nbt.getAllKeys().forEach(key -> {
            CompoundTag posTag = nbt.getCompound(key);
            int x = posTag.getInt("x");
            int y = posTag.getInt("y");
            int z = posTag.getInt("z");
            bwList.add(new BlockPos(x, y, z));
        });
        if(level.isClientSide){
            bwList.forEach(pos -> {
                if(level.isLoaded(pos)){
                    ClientHandler.extracted(level,pos);
                }
            });
            ClientHandler.BW_BLOCKS = bwList;
        }
    }



    @Override
    public LevelCapabilityStatusPacket createUpdatePacket() {
        return new SimpleLevelCapabilityStatusPacket(BWLevelHolderAttacher.LOCATION, this);
    }

    @Override
    public SimpleChannel getNetworkChannel() {
        return NetworkHandler.INSTANCE;
    }

    public boolean isBW(BlockPos pos) {
//        boolean isBw = bwList.contains(pos);
        return bwList.contains(pos);

    }
}
