package com.farestr06.soul_gathering.item;

import com.farestr06.soul_gathering.component.ModComponents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SoulItem extends Item {

    private final SoulItemModifier canMineModifier;
    private final SoulItemModifier useOnBlockModifier;
    private final SoulItemModifier onStackClickedModifier;
    private final SoulItemModifier onClickedModifier;
    private final SoulItemModifier postHitModifier;
    private final SoulItemModifier postMineModifier;
    private final SoulItemModifier useOnEntityModifier;

    public SoulItem(Settings settings, @Nullable SoulItemModifier canMineModifier, @Nullable SoulItemModifier useOnBlockModifier,
                    @Nullable SoulItemModifier onStackClickedModifier, @Nullable SoulItemModifier onClickedModifier, @Nullable SoulItemModifier postHitModifier,
                    @Nullable SoulItemModifier postMineModifier, @Nullable SoulItemModifier useOnEntityModifier) {
        super(settings);
        this.canMineModifier = canMineModifier;
        this.useOnBlockModifier = useOnBlockModifier;
        this.onStackClickedModifier = onStackClickedModifier;
        this.onClickedModifier = onClickedModifier;
        this.postHitModifier = postHitModifier;
        this.postMineModifier = postMineModifier;
        this.useOnEntityModifier = useOnEntityModifier;
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0x55FFFF;
    }

    public int getPlayerSoulCount(PlayerEntity provider) {
       return ModComponents.SOUL_COMPONENT.get(provider).getSoulCount();
    }

    private boolean canUseSoulItem(PlayerEntity provider, SoulItemModifier modifier) {
        return getPlayerSoulCount(provider) - modifier.getCost() >= 0;
    }

    private void decrementSoulCount(PlayerEntity provider, SoulItemModifier modifier) {
        ModComponents.SOUL_COMPONENT.get(provider).removeSouls(modifier.getCost());
    }


    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        if (!(canMineModifier == null)) {
            if (canUseSoulItem(miner, canMineModifier)) {
                decrementSoulCount(miner, canMineModifier);
                return super.canMine(state, world, pos, miner);
            }
        }
        return false;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!(useOnBlockModifier == null)) {
            if (canUseSoulItem(context.getPlayer(), useOnBlockModifier)) {
                decrementSoulCount(context.getPlayer(), useOnBlockModifier);
                return super.useOnBlock(context);
            }
        }
        return ActionResult.FAIL;
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (!(onClickedModifier == null)) {
            if (canUseSoulItem(player, onClickedModifier)) {
                decrementSoulCount(player, onClickedModifier);
                return super.onClicked(stack, otherStack, slot, clickType, player, cursorStackReference);
            }
        }
        return false;
    }
}
