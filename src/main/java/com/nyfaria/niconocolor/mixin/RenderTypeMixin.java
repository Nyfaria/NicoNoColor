package com.nyfaria.niconocolor.mixin;

import com.google.common.collect.ImmutableList;
import com.nyfaria.niconocolor.ModRenderTypes;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(RenderType.class)
public class RenderTypeMixin {

    @Inject(method="chunkBufferLayers", at=@At("HEAD"))
    private static void chunkBufferLayers(CallbackInfoReturnable<List<RenderType>> cir) {
        if(RenderType.CHUNK_BUFFER_LAYERS.contains(ModRenderTypes.BW_CUTOUT)) {
            return;
        }
        List<RenderType> chunkBuffers = new ArrayList<>(RenderType.CHUNK_BUFFER_LAYERS);
        chunkBuffers.add(ModRenderTypes.BW_CUTOUT);
        chunkBuffers.add(ModRenderTypes.BW_SOLID);
        RenderType.CHUNK_BUFFER_LAYERS = ImmutableList.copyOf(chunkBuffers);
    }
}
