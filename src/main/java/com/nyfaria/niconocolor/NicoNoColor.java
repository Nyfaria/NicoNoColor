package com.nyfaria.niconocolor;

import com.nyfaria.niconocolor.cap.BWHolderAttacher;
import com.nyfaria.niconocolor.cap.BWLevelHolderAttacher;
import com.nyfaria.niconocolor.config.ExampleClientConfig;
import com.nyfaria.niconocolor.config.ExampleConfig;
import com.nyfaria.niconocolor.datagen.ModBlockStateProvider;
import com.nyfaria.niconocolor.datagen.ModItemModelProvider;
import com.nyfaria.niconocolor.datagen.ModLangProvider;
import com.nyfaria.niconocolor.datagen.ModLootTableProvider;
import com.nyfaria.niconocolor.datagen.ModRecipeProvider;
import com.nyfaria.niconocolor.datagen.ModSoundProvider;
import com.nyfaria.niconocolor.datagen.ModTagProvider;
import com.nyfaria.niconocolor.init.AbilityInit;
import com.nyfaria.niconocolor.init.BlockInit;
import com.nyfaria.niconocolor.init.EntityInit;
import com.nyfaria.niconocolor.init.ItemInit;
import com.nyfaria.niconocolor.init.MobEffectInit;
import com.nyfaria.niconocolor.init.PotionInit;
import com.nyfaria.niconocolor.network.NetworkHandler;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(NicoNoColor.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class NicoNoColor {
    public static final String MODID = "niconocolor";
    public static final Logger LOGGER = LogManager.getLogger();
//    private static final RenderType BW_CUTOUT_MIPPED = RenderType.create("cutout_mipped", DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS, 131072, true, false, RenderType.CompositeState.builder().setLightmapState(RenderType.LIGHTMAP).setShaderState(RenderType.RENDERTYPE_CUTOUT_MIPPED_SHADER).setTextureState(RenderType.BLOCK_SHEET_MIPPED).createCompositeState(true));


    public NicoNoColor() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ExampleConfig.CONFIG_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ExampleClientConfig.CLIENT_SPEC);

        ItemInit.ITEMS.register(modBus);
        EntityInit.ENTITIES.register(modBus);
        BlockInit.BLOCKS.register(modBus);
        BlockInit.BLOCK_ENTITIES.register(modBus);
        AbilityInit.ABILITIES.register(modBus);
        MobEffectInit.MOB_EFFECTS.register(modBus);
        PotionInit.POTIONS.register(modBus);
//        MorphInit.MORPHS.register(modBus);

        BWHolderAttacher.register();
        BWLevelHolderAttacher.register();

    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        NetworkHandler.register();
    }

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        boolean includeServer = event.includeServer();
        boolean includeClient = event.includeClient();

        generator.addProvider(includeServer, new ModRecipeProvider(generator));
        generator.addProvider(includeServer, new ModLootTableProvider(generator));
        generator.addProvider(includeServer, new ModSoundProvider(generator, existingFileHelper));
        generator.addProvider(includeServer, new ModTagProvider.ModBlockTags(generator, existingFileHelper));
        generator.addProvider(includeServer, new ModTagProvider.Items(generator, existingFileHelper));
        generator.addProvider(includeClient, new ModItemModelProvider(generator, existingFileHelper));
        generator.addProvider(includeClient, new ModBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(includeClient, new ModLangProvider(generator));
    }
}
