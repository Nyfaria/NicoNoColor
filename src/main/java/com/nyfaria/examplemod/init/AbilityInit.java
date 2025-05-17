package com.nyfaria.examplemod.init;

import com.nyfaria.examplemod.ExampleMod;
import dev._100media.hundredmediaabilities.ability.Ability;
import dev._100media.hundredmediaabilities.init.HMAAbilityInit;
import net.minecraftforge.registries.DeferredRegister;

public class AbilityInit {
    public static DeferredRegister<Ability> ABILITIES = DeferredRegister.create(HMAAbilityInit.ABILITIES.getRegistryKey(), ExampleMod.MODID);

}
