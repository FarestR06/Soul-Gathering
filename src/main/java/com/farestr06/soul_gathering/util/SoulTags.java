package com.farestr06.soul_gathering.util;

import com.farestr06.soul_gathering.component.SoulComponentHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

/**
 * <p>This class contains tags that add Soul Gathering amounts.</p>
 * @see SoulComponentHelper
 * @since 1.1.1
 */
public class SoulTags {
    public static final TagKey<Item> SOUL_GATHERING_ITEMS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("soul_gathering", "soul_gathering_items"));

    public static final TagKey<Enchantment> SOUL_GATHERING_ENCHANTMENTS =
            TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of("soul_gathering", "soul_gathering_enchantments"));
}
