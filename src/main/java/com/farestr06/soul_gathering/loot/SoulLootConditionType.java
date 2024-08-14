package com.farestr06.soul_gathering.loot;

import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.farestr06.soul_gathering.SoulGathering.LOGGER;

public class SoulLootConditionType {

    public static final LootConditionType SOULS_CHECK = create();

    private static LootConditionType create() {
        return Registry.register(Registries.LOOT_CONDITION_TYPE, Identifier.of("soul_gathering", "souls_check"), new LootConditionType(SoulsCheckLootCondition.CODEC));
    }

    public static void register() {
        LOGGER.info("Registering predicate!");
    }
}
