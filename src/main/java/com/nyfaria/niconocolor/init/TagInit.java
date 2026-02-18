package com.nyfaria.niconocolor.init;

import com.nyfaria.niconocolor.NicoNoColor;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagInit {

    //example
    public static TagKey<Block> TOTEMS = blockTag("paintbrush_2");



    public static void init() {
        Minecraft.getInstance().getSoundManager().stop();
    }

//    public static TagKey<Morph> morphTag(String path) {
//        return HMMMorphInit.getRegistry().tags().createTagKey(new ResourceLocation(HundredDaysStory.MODID, path));
//    }

    public static TagKey<Block> blockTag(String path) {
        return BlockTags.create(new ResourceLocation(NicoNoColor.MODID, path));
    }

    public static TagKey<Item> itemTag(String path) {
        return ItemTags.create(new ResourceLocation(NicoNoColor.MODID, path));
    }


}
