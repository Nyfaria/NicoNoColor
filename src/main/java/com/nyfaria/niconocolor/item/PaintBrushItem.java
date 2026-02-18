package com.nyfaria.niconocolor.item;

import com.nyfaria.niconocolor.NicoNoColor;
import com.nyfaria.niconocolor.cap.BWHolderAttacher;
import com.nyfaria.niconocolor.cap.BWLevelHolderAttacher;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class PaintBrushItem extends Item {
    private final TagKey<Block> blocks;
    private final boolean canEntity;
    private final boolean canPainting;
    private final boolean isAllBlocks;

    public PaintBrushItem(Properties pProperties, TagKey<Block> blocks) {
        this(pProperties, blocks, false, false, false);
    }

    public PaintBrushItem(Properties pProperties, TagKey<Block> blocks, boolean canEntity) {
        this(pProperties, blocks, false, canEntity, false);
    }

    public PaintBrushItem(Properties pProperties, TagKey<Block> blocks, boolean isAllBlocks, boolean canEntity, boolean canPainting) {
        super(pProperties);
        this.blocks = blocks;
        this.canEntity = canEntity;
        this.canPainting = canPainting;
        this.isAllBlocks = isAllBlocks;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos pos = pContext.getClickedPos();
        BlockState state = pContext.getLevel().getBlockState(pos);
        if (blocks!= null || isAllBlocks) {
//            pContext.getPlayer().displayClientMessage(pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock().getName(),true);
            if (isAllBlocks || (blocks != null && pContext.getLevel().getBlockState(pContext.getClickedPos()).is(blocks))) {
                if (!pContext.getLevel().isClientSide) {
                    BWLevelHolderAttacher.get(pContext.getLevel()).ifPresent(
                            bwHolder -> {
                                bwHolder.add(pContext.getClickedPos());
//                                pContext.getPlayer().displayClientMessage(pContext.getLevel().getBlockState(pContext.getClickedPos()).getBlock().getName().append(" added to list"),true);
                            }

                    );
                    pContext.getLevel().players().forEach(player -> ((ServerPlayer)player).connection.send(new ClientboundBlockUpdatePacket(pos,state)));
                }
                return InteractionResult.SUCCESS;
            }
        }
        return super.useOn(pContext);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        if (!pPlayer.level.isClientSide) {
            if (canEntity) {
                BWHolderAttacher.get(pInteractionTarget).ifPresent(
                        bwHolder -> bwHolder.setBlackAndWhite(true)
                );
                return InteractionResult.SUCCESS;
            }
        }
        return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
    }
}
