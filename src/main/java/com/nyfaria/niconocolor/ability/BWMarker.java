package com.nyfaria.niconocolor.ability;

import com.nyfaria.niconocolor.cap.BWLevelHolderAttacher;
import dev._100media.hundredmediaabilities.marker.Marker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class BWMarker extends Marker {
    @Override
    public void onTick(Level level, Player player, int stackCount) {
        super.onTick(level, player, stackCount);
        BWLevelHolderAttacher.get(level).ifPresent(attacher -> {
           if(!attacher.isBW(player.getOnPos())){
                attacher.add(player.getOnPos());
           }
        });
    }
}
