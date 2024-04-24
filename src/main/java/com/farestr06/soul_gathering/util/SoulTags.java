package com.farestr06.soul_gathering.util;

import com.farestr06.soul_gathering.component.SoulComponentHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

/**
 * <p>This class contains tags that add Soul Gathering amounts.</p>
 * <p>These tags <strong>should not</strong> be applied to objects that implement {@link SoulGatheringImpl SoulGatheringImpl}, as the interface already adds SoulGathering.</p>
 * @see SoulComponentHelper SoulComponentHelper
 * @since 1.1.1
 */
public class SoulTags {
    public static final TagKey<Item> SOUL_GATHERING_ITEMS = createItemTag("soul_gathering_items");
    public static final TagKey<Enchantment> SOUL_GATHERING_ENCHANTMENTS = createEnchantmentTag("soul_gathering_items");

    private static TagKey<Item> createItemTag(String name) {
        return TagKey.of(RegistryKeys.ITEM, new Identifier("soul_gathering", name));
    }
    private static TagKey<Enchantment> createEnchantmentTag(String name) {
        return TagKey.of(RegistryKeys.ENCHANTMENT, new Identifier("soul_gathering", name));
    }
}
