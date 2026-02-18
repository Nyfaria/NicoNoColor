package com.nyfaria.niconocolor.cap;

import com.nyfaria.niconocolor.NicoNoColor;
import dev._100media.capabilitysyncer.core.CapabilityAttacher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber(modid = NicoNoColor.MODID)
public class BWHolderAttacher extends CapabilityAttacher {
    public static final Capability<BWHolder> CAPABILITY = getCapability(new CapabilityToken<>() {});
    public static final ResourceLocation LOCATION = new ResourceLocation(NicoNoColor.MODID, "bw_holder");
    private static final Class<BWHolder> CAPABILITY_CLASS = BWHolder.class;

    @Nullable
    @SuppressWarnings("ConstantConditions")
    public static BWHolder getUnwrap(Entity player) {
        return get(player).orElse(null);
    }

    public static LazyOptional<BWHolder> get(Entity player) {
        return player.getCapability(CAPABILITY);
    }

    private static void attach(AttachCapabilitiesEvent<Entity> event, Entity entity) {
        genericAttachCapability(event, new BWHolder(entity), CAPABILITY, LOCATION);
    }

    public static void register() {
        CapabilityAttacher.registerCapability(CAPABILITY_CLASS);
        CapabilityAttacher.registerEntityAttacher(Entity.class, BWHolderAttacher::attach, BWHolderAttacher::get);
    }
}
