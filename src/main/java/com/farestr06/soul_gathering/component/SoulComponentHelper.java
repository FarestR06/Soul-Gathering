package com.farestr06.soul_gathering.component;

import com.farestr06.soul_gathering.util.SoulTags;
import com.farestr06.soul_gathering.util.SoulGatheringImpl;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.random.Random;

import java.util.function.Predicate;

public class SoulComponentHelper {

    public static class MathHelper {

        public static int mappedSoulCount(PlayerEntity provider) {
            float input = ModComponents.SOUL_COMPONENT.get(provider).getSoulCount();
            return Math.round(net.minecraft.util.math.MathHelper.map(input, 0, 8192f, 0f, 100f));
        }

        /**
         * Calculates the total soul gathering of each item; this class is used by {@link com.farestr06.soul_gathering.mixin.PlayerEntityMixin PlayerEntityMixin}
         * to determine how many souls the player should earn.
         * @param provider The player entity to add souls to.
         * @return The amount of souls to add.
         * @throws IllegalStateException If an item implementing {@link SoulGatheringImpl SoulGatheringImpl} is tagged with <code>SOUL_GATHERING_ITEMS</code>. See {@link SoulTags SoulTags}.
         * @see com.farestr06.soul_gathering.mixin.PlayerEntityMixin
         */
        public static int calcSoulAdderAmount(PlayerEntity provider) {
            int amount = 0;
            for (ItemStack itemStack : provider.getArmorItems()) {
                if (itemStack.getItem() instanceof SoulGatheringImpl) {
                    if (TagUtil.isIn(SoulTags.SOUL_GATHERING_ITEMS, itemStack.getItem())) {
                        throw new IllegalStateException("Items implementing SoulGatheringImpl should not be tagged with SOUL_GATHERING_ITEMS!");
                    }                amount += ((SoulGatheringImpl) itemStack.getItem()).getSoulGathering();
                    amount += getSoulGatheringFromEnchantments(itemStack);
                }
                else if (itemStack.streamTags().anyMatch(Predicate.isEqual(SoulTags.SOUL_GATHERING_ITEMS))) {
                    amount += Random.create().nextBetweenExclusive(1, 4);
                }
            }
            for (ItemStack itemStack : provider.getHandItems()) {
                if (itemStack.getItem() instanceof SoulGatheringImpl) {
                    if (TagUtil.isIn(SoulTags.SOUL_GATHERING_ITEMS, itemStack.getItem())) {
                        throw new IllegalStateException("Items implementing SoulGatheringImpl should not be tagged with SOUL_GATHERING_ITEMS!");
                    }
                    amount += ((SoulGatheringImpl) itemStack.getItem()).getSoulGathering();
                    amount += getSoulGatheringFromEnchantments(itemStack);
                }
                else if (TagUtil.isIn(SoulTags.SOUL_GATHERING_ITEMS, itemStack.getItem())) {
                    amount += Random.create().nextBetweenExclusive(2, 8);
                }
            }
            amount = net.minecraft.util.math.MathHelper.floor(amount * Random.create().nextFloat());
            return amount ;
        }

        public static int getSoulGatheringFromEnchantments(ItemStack itemStack) {
            int amount = 0;
            ItemEnchantmentsComponent enchantments = EnchantmentHelper.getEnchantments(itemStack);
            for (RegistryEntry<Enchantment> entry: enchantments.getEnchantments().stream().toList()) {
                Enchantment enchantment = entry.value();
                if (enchantment instanceof SoulGatheringImpl) {
                    if (TagUtil.isIn(SoulTags.SOUL_GATHERING_ENCHANTMENTS, enchantment)) {
                        throw new IllegalStateException("Enchantments implementing SoulGatheringImpl should not be tagged with SOUL_GATHERING_ENCHANTMENTS!");
                    }
                    amount += ((SoulGatheringImpl) enchantment).getSoulGathering();
                    amount = net.minecraft.util.math.MathHelper.floor(amount * (EnchantmentHelper.getLevel(enchantment, itemStack) * 0.1));
                }
                else if (TagUtil.isIn(SoulTags.SOUL_GATHERING_ITEMS, itemStack.getItem())) {
                    amount += Random.create().nextBetweenExclusive(1, 4);
                }
            }
            return amount;
        }
    }
}
