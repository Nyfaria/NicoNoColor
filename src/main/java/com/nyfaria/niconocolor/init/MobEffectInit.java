package com.nyfaria.niconocolor.init;

import com.nyfaria.niconocolor.mobeffect.ColorlessEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.nyfaria.niconocolor.NicoNoColor.MODID;

public class MobEffectInit {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);

    public static final RegistryObject<MobEffect> NICO_NO_COLOR = MOB_EFFECTS.register("colorless", ColorlessEffect::new);
}
