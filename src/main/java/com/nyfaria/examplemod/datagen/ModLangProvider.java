package com.nyfaria.examplemod.datagen;

import com.google.common.collect.ImmutableMap;
import com.nyfaria.examplemod.ExampleMod;
import com.nyfaria.examplemod.init.AbilityInit;
import com.nyfaria.examplemod.init.BlockInit;
import com.nyfaria.examplemod.init.EntityInit;
import com.nyfaria.examplemod.init.ItemInit;
import com.nyfaria.examplemod.init.MorphInit;
import dev._100media.hundredmediaabilities.ability.Ability;
import dev._100media.hundredmediamorphs.init.HMMMorphInit;
import dev._100media.hundredmediamorphs.morph.Morph;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModLangProvider extends LanguageProvider {
    protected static final Map<String, String> REPLACE_LIST = ImmutableMap.of(
            "tnt", "TNT",
            "sus", ""
    );

    public ModLangProvider(DataGenerator gen) {
        super(gen, ExampleMod.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        ItemInit.ITEMS.getEntries().forEach(this::itemLang);
        EntityInit.ENTITIES.getEntries().forEach(this::entityLang);
        BlockInit.BLOCKS.getEntries().forEach(this::blockLang);
        AbilityInit.ABILITIES.getEntries().forEach(e -> abilityLang(e.get()));
        MorphInit.MORPHS.getEntries().forEach(e -> morphLang(e.get()));
    }

    protected void itemLang(RegistryObject<Item> entry) {
        if (!(entry.get() instanceof BlockItem) || entry.get() instanceof ItemNameBlockItem) {
            addItem(entry, checkReplace(entry));
        }
    }

    protected void blockLang(RegistryObject<Block> entry) {
        addBlock(entry, checkReplace(entry));
    }

    protected void entityLang(RegistryObject<EntityType<?>> entry) {
        addEntityType(entry, checkReplace(entry));
    }

    protected String checkReplace(RegistryObject<?> registryObject) {
        return Arrays.stream(registryObject.getId().getPath().split("_"))
                .map(this::checkReplace)
                .filter(s -> !s.isBlank())
                .collect(Collectors.joining(" "))
                .trim();
    }
    protected void abilityLang(Ability entry) {
        List<String> words = new ArrayList<>();
        Arrays.stream(entry.getRegistryName().getPath().split("_")).toList().forEach(e -> {

                    words.add(checkReplace(e));
                }
        );
        add(entry.getDescriptionId(), String.join(" ", words).trim());
    }
    protected void morphLang(Morph entry) {
        List<String> words = new ArrayList<>();
        Arrays.stream(HMMMorphInit.getRegistry().getKey(entry).getPath().split("_")).toList().forEach(e -> {

                    words.add(checkReplace(e));
                }
        );
        add(entry.getDescriptionId(), String.join(" ", words).trim());
    }
    protected String checkReplace(String string) {
        return REPLACE_LIST.containsKey(string) ? REPLACE_LIST.get(string) : StringUtils.capitalize(string);
    }
}
