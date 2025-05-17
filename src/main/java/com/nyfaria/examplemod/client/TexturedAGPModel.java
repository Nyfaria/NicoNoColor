package com.nyfaria.examplemod.client;

import dev._100media.hundredmediageckolib.client.animatable.SimpleAnimatable;
import dev._100media.hundredmediamorphs.client.model.AdvancedGeoPlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class TexturedAGPModel<T extends SimpleAnimatable> extends AdvancedGeoPlayerModel<T> {

    private final ResourceLocation texture;

    public TexturedAGPModel(String namespace, String name) {
        super(namespace, name);
        this.texture = new ResourceLocation(namespace, "textures/entity/" + name + ".png");
    }

    public TexturedAGPModel(String namespace, String name, Properties<?> properties) {
        super(namespace, name, properties);
        this.texture = new ResourceLocation(namespace, "textures/entity/" + name + ".png");
    }



    @Override
    public ResourceLocation getTextureResource(T animatable, @Nullable AbstractClientPlayer player) {
        return texture;
    }
}
