package com.nyfaria.niconocolor.mixin;

import com.nyfaria.niconocolor.NicoNoColor;
import com.nyfaria.niconocolor.cap.BWHolderAttacher;
import com.nyfaria.niconocolor.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.GenerationStep;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Painting.class)
public abstract class PaintingMixin extends HangingEntity {
    protected PaintingMixin(EntityType<? extends HangingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public InteractionResult interact(Player pPlayer, InteractionHand pHand) {
        if(pPlayer.getItemInHand(pHand).is(ItemInit.COLORLESS_PAINTBRUSH_1.get())) {
            if(!this.level.isClientSide) {
                BWHolderAttacher.get(this).ifPresent(
                        bwHolder -> {
                            bwHolder.setBlackAndWhite(true);
                        }
                );
                return InteractionResult.SUCCESS;
            }
        }
        return super.interact(pPlayer, pHand);
    }
}
