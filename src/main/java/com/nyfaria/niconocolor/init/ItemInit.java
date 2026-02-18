package com.nyfaria.niconocolor.init;

import com.nyfaria.niconocolor.NicoNoColor;
import com.nyfaria.niconocolor.item.PaintBrushItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NicoNoColor.MODID);
    public static final RegistryObject<Item> COLORLESS_PAINTBRUSH_1 = ITEMS.register("colorless_paintbrush_1", () -> new PaintBrushItem(getItemProperties(), null,false, false, true));
    public static final RegistryObject<Item> COLORLESS_PAINTBRUSH_2 = ITEMS.register("colorless_paintbrush_2", () -> new PaintBrushItem(getItemProperties(), TagInit.TOTEMS, false, true, false));
    public static final RegistryObject<Item> COLORLESS_PAINTBRUSH_3 = ITEMS.register("colorless_paintbrush_3", () -> new PaintBrushItem(getItemProperties(), null, true, false, false));



    public static Item.Properties getItemProperties() {
        return new Item.Properties().tab(CreativeModeTab.TAB_MISC);
    }
}
