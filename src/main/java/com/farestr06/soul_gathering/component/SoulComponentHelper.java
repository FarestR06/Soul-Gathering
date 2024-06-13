package com.farestr06.soul_gathering.component;

import com.farestr06.soul_gathering.util.SoulObjectRegistry;
import com.farestr06.soul_gathering.util.SoulTags;
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.random.Random;

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
         * @see com.farestr06.soul_gathering.mixin.PlayerEntityMixin
         */
        public static int calcSoulAdderAmount(PlayerEntity provider) {
            int amount = 0;
            for (ItemStack itemStack : provider.getArmorItems()) {
                if (TagUtil.isIn(SoulTags.SOUL_GATHERING_ITEMS, itemStack.getItem())) {
                    amount += SoulObjectRegistry.Items.getSoulGatheringFromItem(itemStack.getItem());
                    amount += getSoulGatheringFromEnchantments(itemStack);
                }
            }
            for (ItemStack itemStack : provider.getHandItems()) {
                if (TagUtil.isIn(SoulTags.SOUL_GATHERING_ITEMS, itemStack.getItem())) {
                    amount += SoulObjectRegistry.Items.getSoulGatheringFromItem(itemStack.getItem());
                    amount += getSoulGatheringFromEnchantments(itemStack);

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
                if (TagUtil.isIn(SoulTags.SOUL_GATHERING_ITEMS, itemStack.getItem()) && TagUtil.isIn(SoulTags.SOUL_GATHERING_ENCHANTMENTS, enchantment)) {
                    amount += SoulObjectRegistry.Enchantments.getSoulGatheringFromEnchantment(enchantment);
                    amount = net.minecraft.util.math.MathHelper.floor(amount * (EnchantmentHelper.getLevel(entry, itemStack) * 0.1));
                }
            }
            return amount;
        }
    }
}
