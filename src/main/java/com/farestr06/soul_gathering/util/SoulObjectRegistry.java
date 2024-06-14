package com.farestr06.soul_gathering.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that keeps track of enchantments and their Soul Gathering. It's not elegant by any means, but it should work for now.
 * @deprecated The process is now data-driven.
 */
@Deprecated
public class SoulObjectRegistry {

    public static class Items {
        public static final Map<Item, Integer> ITEM_MAP = new HashMap<>();

        /**
         * @param key The item to add Soul Gathering to.
         * @param value The amount of Soul Gathering
         */
        public static void registerItemSoulGathering(Item key, int value) {
            ITEM_MAP.put(key, value);
        }

        public static int getSoulGatheringFromItem(Item key) {
            return ITEM_MAP.get(key);
        }
    }


    public static class Enchantments {

        public static final Map<Enchantment, Integer> ENCHANTMENT_MAP = new HashMap<>();

        /**
         *
         * @param key The enchantment to add Soul Gathering to.
         * @param value The amount of Soul Gathering
         */
        public static void registerEnchantmentSoulGathering(Enchantment key, int value) {
            ENCHANTMENT_MAP.put(key, value);
        }

        public static int getSoulGatheringFromEnchantment(Enchantment key) {
            return ENCHANTMENT_MAP.get(key);
        }
    }
}
