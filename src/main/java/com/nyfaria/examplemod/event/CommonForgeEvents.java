package com.nyfaria.examplemod.event;

import com.nyfaria.examplemod.ExampleMod;
import dev._100media.hundredmediaabilities.HundredMediaAbilitiesMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {
    @SubscribeEvent
    public static void onFMLCommon(FMLCommonSetupEvent event) {
        List<CreativeModeTab> tabs = new ArrayList<>();
        for(CreativeModeTab tab : CreativeModeTab.TABS){
            if((tab == HundredMediaAbilitiesMod.TAB)){
                tab = new CreativeModeTab(tab.getId(),"abilities") {
                    @Override
                    public ItemStack makeIcon() {
                        return Items.DIRT.getDefaultInstance();
                    }
                };
            }
            tabs.add(tab);
        }

        CreativeModeTab[] newTabs = tabs.toArray(new CreativeModeTab[0]);
        CreativeModeTab.TABS = newTabs;
    }
}
