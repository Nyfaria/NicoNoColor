package com.nyfaria.niconocolor.cap;

import com.nyfaria.niconocolor.NicoNoColor;
import dev._100media.capabilitysyncer.core.CapabilityAttacher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = NicoNoColor.MODID)
public class BWLevelHolderAttacher extends CapabilityAttacher {

    public static final Capability<BWLevelHolder> CAPABILITY = getCapability(new CapabilityToken<>() {
    });
    public static final ResourceLocation LOCATION = new ResourceLocation(NicoNoColor.MODID, "level_bw");
    private static final Class<BWLevelHolder> CAPABILITY_CLASS = BWLevelHolder.class;

    public static BWLevelHolder getUnwrap(Level player) {
        return get(player).orElse(null);
    }

    public static LazyOptional<BWLevelHolder> get(Level player) {
        return player.getCapability(CAPABILITY);
    }

    private static void attach(AttachCapabilitiesEvent<Level> event, Level player) {
        genericAttachCapability(event, new BWLevelHolder(player), CAPABILITY, LOCATION);
    }

    public static void register() {
        CapabilityAttacher.registerCapability(CAPABILITY_CLASS);
        CapabilityAttacher.registerLevelAttacher(Level.class, BWLevelHolderAttacher::attach, BWLevelHolderAttacher::get);
    }

}
