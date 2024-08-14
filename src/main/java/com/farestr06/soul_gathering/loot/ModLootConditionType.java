package com.farestr06.soul_gathering.loot;

import com.mojang.serialization.MapCodec;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.farestr06.soul_gathering.SoulGathering.LOGGER;

public class ModLootConditionType {

    public static final LootConditionType SOULS_CHECK = register("souls_check", SoulsCheckLootCondition.CODEC);

    private static LootConditionType register(String id, MapCodec<? extends LootCondition> codec) {
        return Registry.register(Registries.LOOT_CONDITION_TYPE, Identifier.of("soul_gathering", id), new LootConditionType(codec));
    }

    public static void register() {
        LOGGER.info("Registering predicate!");
    }
}
