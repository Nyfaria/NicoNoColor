package com.nyfaria.niconocolor;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ModRenderTypes {
    public static final RenderStateShard.ShaderStateShard BW_RENDERTYPE_CUTOUT_SHADER = new RenderStateShard.ShaderStateShard(()-> ModShaders.BW_CUTOUT_SHADER_INSTANCE);
    public static final RenderStateShard.ShaderStateShard BW_RENDERTYPE_ENTITY_CUTOUT_SHADER = new RenderStateShard.ShaderStateShard(()-> ModShaders.BW_ENTITY_CUTOUT_SHADER_INSTANCE);
    public static final RenderStateShard.ShaderStateShard BW_RENDERTYPE_ENTITY_TRANSLUCENT_SHADER = new RenderStateShard.ShaderStateShard(()-> ModShaders.BW_ENTITY_TRANSLUCENT_SHADER_INSTANCE);
    public static final RenderStateShard.ShaderStateShard BW_RENDERTYPE_SOLID_SHADER = new RenderStateShard.ShaderStateShard(()-> ModShaders.BW_SOLID_SHADER_INSTANCE);

    public static final RenderType BW_CUTOUT = RenderType.create("bw_cutout", DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS, 131072, true, false, RenderType.CompositeState.builder().setLightmapState(RenderType.LIGHTMAP).setShaderState(ModRenderTypes.BW_RENDERTYPE_CUTOUT_SHADER).setTextureState(RenderType.BLOCK_SHEET).createCompositeState(true));
    public static final Function<ResourceLocation, RenderType> ENTITY_CUTOUT = Util.memoize((p_173202_) -> {
        RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder().setShaderState(BW_RENDERTYPE_ENTITY_CUTOUT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(p_173202_, false, false)).setTransparencyState(RenderType.NO_TRANSPARENCY).setLightmapState(RenderType.LIGHTMAP).setOverlayState(RenderType.OVERLAY).createCompositeState(true);
        return RenderType.create("bw_entity_cutout", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, false, rendertype$compositestate);
    });
    public static final BiFunction<ResourceLocation, Boolean, RenderType> ENTITY_TRANSLUCENT = Util.memoize((p_173227_, p_173228_) -> {
        RenderType.CompositeState rendertype$compositestate = RenderType.CompositeState.builder().setShaderState(BW_RENDERTYPE_ENTITY_TRANSLUCENT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(p_173227_, false, false)).setTransparencyState(RenderType.TRANSLUCENT_TRANSPARENCY).setCullState(RenderType.NO_CULL).setLightmapState(RenderType.LIGHTMAP).setOverlayState(RenderType.OVERLAY).createCompositeState(p_173228_);
        return RenderType.create("entity_translucent", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, true, rendertype$compositestate);
    });
    public static final RenderType BW_SOLID = RenderType.create("bw_solid",DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS, 2097152, true, false,RenderType.CompositeState.builder().setLightmapState(RenderType.LIGHTMAP).setShaderState(BW_RENDERTYPE_SOLID_SHADER).setTextureState(RenderType.BLOCK_SHEET_MIPPED).createCompositeState(true));

//    public static final RenderStateShard.ShaderStateShard BW_RENDERTYPE_CUTOUT_MIPPED_SHADER = new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeCutoutMippedShader);

}
