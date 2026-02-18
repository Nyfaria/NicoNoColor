package com.nyfaria.niconocolor.init;

import com.nyfaria.niconocolor.NicoNoColor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PotionInit {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, NicoNoColor.MODID);

    public static final RegistryObject<Potion> NICO_POTION = POTIONS.register("colorless", () -> new Potion(new MobEffectInstance(MobEffectInit.NICO_NO_COLOR.get(), 200)));
}
