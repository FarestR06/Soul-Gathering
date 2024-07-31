package com.farestr06.soul_gathering.enchantment;

import com.farestr06.soul_gathering.SoulGathering;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.effect.EnchantmentEffectEntry;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.UnaryOperator;

public class SoulEnchantmentEffects {
    public static final ComponentType<List<EnchantmentEffectEntry<RemoveSoulsEntityEffect>>> REMOVE_SOULS =
            register("remove_souls", builder -> builder.codec(
                    EnchantmentEffectEntry.createCodec(RemoveSoulsEntityEffect.CODEC.codec(), LootContextTypes.EMPTY).listOf()
            ));
    public static final ComponentType<List<EnchantmentEffectEntry<AddSoulsEntityEffect>>> ADD_SOULS =
            register("add", builder -> builder.codec(
                    EnchantmentEffectEntry.createCodec(AddSoulsEntityEffect.CODEC.codec(), LootContextTypes.EMPTY).listOf()
            ));

    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, Identifier.of("soul_gathering", id), builderOperator.apply(ComponentType.builder()).build());
    }

    public static void init() {
        SoulGathering.LOGGER.info("Initializing Enchantment Effects...");
        Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of("soul_gathering", "remove_souls"), RemoveSoulsEntityEffect.CODEC);
        Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of("soul_gathering", "add_souls"), AddSoulsEntityEffect.CODEC);
    }
}
