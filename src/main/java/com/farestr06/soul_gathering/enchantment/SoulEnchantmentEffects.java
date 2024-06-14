package com.farestr06.soul_gathering.enchantment;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SoulEnchantmentEffects {

    public static void init() {
        Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of("soul_gathering", "remove_souls"), RemoveSoulsEntityEffect.CODEC);
    }

}
