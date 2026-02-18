package com.nyfaria.niconocolor;

import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.ChunkRenderTypeSet;
import net.minecraftforge.client.RenderTypeGroup;
import net.minecraftforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;

public class BWBakedModel extends SimpleBakedModel {

    private BlockPos pos;

    public BWBakedModel(SimpleBakedModel bakedModel, BlockPos pos, BlockState blockState) {
        super(bakedModel.unculledFaces,
                bakedModel.culledFaces,
                bakedModel.useAmbientOcclusion(),
                bakedModel.usesBlockLight(),
                bakedModel.isGui3d(),
                bakedModel.getParticleIcon(),
                bakedModel.getTransforms(),
                bakedModel.getOverrides(),
                RenderTypeGroup.EMPTY);
        this.pos = pos;
    }

    @Override
    public ChunkRenderTypeSet getRenderTypes(@NotNull BlockState state, @NotNull RandomSource rand, @NotNull ModelData data) {
        return ChunkRenderTypeSet.of(ModRenderTypes.BW_CUTOUT);
    }


}
