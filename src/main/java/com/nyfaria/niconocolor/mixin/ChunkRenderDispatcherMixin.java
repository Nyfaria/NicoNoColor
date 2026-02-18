package com.nyfaria.niconocolor.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.nyfaria.niconocolor.BWBakedModel;
import com.nyfaria.niconocolor.BWWeightedBakedModel;
import com.nyfaria.niconocolor.ClientHandler;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.MultiPartBakedModel;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.client.resources.model.WeightedBakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Mixin(ChunkRenderDispatcher.RenderChunk.RebuildTask.class)
public class ChunkRenderDispatcherMixin {

//    private static BlockPos pos = BlockPos.ZERO;
//
//    @Inject(method = "compile", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/chunk/RenderChunkRegion;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
//    private void onCompile(float pX, float pY, float pZ, ChunkBufferBuilderPack p_234471_, CallbackInfoReturnable<ChunkRenderDispatcher.RenderChunk.RebuildTask.CompileResults> cir,
//                           ChunkRenderDispatcher.RenderChunk.RebuildTask.CompileResults chunkrenderdispatcher$renderchunk$rebuildtask$compileresults,
//                           int i, BlockPos blockpos, BlockPos blockpos1, VisGraph visgraph, RenderChunkRegion renderchunkregion, PoseStack posestack,
//                           Set set, RandomSource randomsource, BlockRenderDispatcher blockrenderdispatcher, Iterator var15, BlockPos blockpos2) {
//        ChunkRenderDispatcherMixin.pos = blockpos2;
//    }

    @WrapOperation(method = "compile", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/block/BlockRenderDispatcher;getBlockModel(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/client/resources/model/BakedModel;"))
    private BakedModel onGetBlockModel(BlockRenderDispatcher instance, BlockState pState, Operation<BakedModel> original, @Local(ordinal = 2) BlockPos blockPos2) {
        boolean isBW = ClientHandler.BW_BLOCKS.contains(blockPos2);
        if (instance.getBlockModel(pState) instanceof WeightedBakedModel wbm && isBW) {
            List<WeightedEntry.Wrapper<BakedModel>> list = new ArrayList<>();
            for (WeightedEntry.Wrapper<BakedModel> entry : wbm.list) {
                if (entry.getData() instanceof SimpleBakedModel bm) {
                    list.add(WeightedEntry.wrap(new BWBakedModel(bm, blockPos2,pState), entry.getWeight().asInt()));
                } else {
                    list.add(entry);
                }
            }
            return new BWWeightedBakedModel(list);
        }
        if(instance.getBlockModel(pState) instanceof SimpleBakedModel bm && isBW) {
            return new BWBakedModel(bm, blockPos2,pState);
        }
        if(instance.getBlockModel(pState) instanceof MultiPartBakedModel bm) {
            List<Pair<Predicate<BlockState>, BakedModel>> newSelectors = new ArrayList<>();
            bm.selectors.forEach(p->{
                if(p.getRight() instanceof SimpleBakedModel sbm && isBW) {
                    Pair<Predicate<BlockState>, BakedModel> p2 = MutablePair.of(p);
                    p2.setValue(new BWBakedModel(sbm, blockPos2,pState));
                    newSelectors.add(p2);
                } else {
                    newSelectors.add(p);
                }
            });


            return new MultiPartBakedModel(newSelectors);
        }

        return original.call(instance, pState);
    }
}
