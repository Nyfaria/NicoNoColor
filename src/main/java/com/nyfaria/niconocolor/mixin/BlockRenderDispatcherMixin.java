package com.nyfaria.niconocolor.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.nyfaria.niconocolor.BWBakedModel;
import com.nyfaria.niconocolor.BWWeightedBakedModel;
import com.nyfaria.niconocolor.ClientHandler;
import com.nyfaria.niconocolor.ModRenderTypes;
import com.nyfaria.niconocolor.ModShaders;
import com.nyfaria.niconocolor.NicoNoColor;
import com.nyfaria.niconocolor.cap.BWLevelHolderAttacher;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.ReportedException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.MultiPartBakedModel;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.client.resources.model.WeightedBakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.extensions.IForgeBakedModel;
import net.minecraftforge.client.model.data.ModelData;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Mixin(BlockRenderDispatcher.class)
public abstract class BlockRenderDispatcherMixin {

    @Shadow public abstract BakedModel getBlockModel(BlockState pState);

    @Shadow @Final private ModelBlockRenderer modelRenderer;

    @WrapOperation(method = "renderBatched(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/BlockAndTintGetter;Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;ZLnet/minecraft/util/RandomSource;Lnet/minecraftforge/client/model/data/ModelData;Lnet/minecraft/client/renderer/RenderType;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/block/BlockRenderDispatcher;getBlockModel(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/client/resources/model/BakedModel;"))
    private BakedModel onGetBlockModel(BlockRenderDispatcher instance, BlockState pState, Operation<BakedModel> original, @Local(ordinal = 0, argsOnly = true) BlockPos blockPos2) {
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
        List<RenderType> type = ItemBlockRenderTypes.getRenderLayers(pState).asList();
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
