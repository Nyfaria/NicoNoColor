package com.nyfaria.niconocolor.datagen;

import com.nyfaria.niconocolor.NicoNoColor;
import com.nyfaria.niconocolor.init.TagInit;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ModTagProvider {

    public static class Items extends TagsProvider<Item>{

        public Items(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
            super(pGenerator, Registry.ITEM, NicoNoColor.MODID, existingFileHelper);
        }

        @Override
        protected void addTags() {

        }

        public void populateTag(TagKey<Item> tag, Supplier<Item>... items){
            for (Supplier<Item> item : items) {
                tag(tag).add(item.get());
            }
        }
    }

    public static class ModBlockTags extends TagsProvider<Block>{

        public ModBlockTags(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
            super(pGenerator, Registry.BLOCK, NicoNoColor.MODID, existingFileHelper);
        }

        @Override
        protected void addTags() {
            tag(TagInit.TOTEMS)
                    .addTags(
                            BlockTags.LOGS,
                            BlockTags.LEAVES,
                            BlockTags.REPLACEABLE_PLANTS,
                            BlockTags.CORAL_PLANTS,
                            BlockTags.FLOWERS,
                            BlockTags.CROPS
                    );
        }
        public void populateTag(TagKey<Block> tag, Supplier<Block>... items){
            for (Supplier<Block> item : items) {
                tag(tag).add(item.get());
            }
        }
    }
}
