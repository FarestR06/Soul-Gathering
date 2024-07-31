package com.farestr06.soul_gathering.loot;

import com.mojang.serialization.MapCodec;
import net.minecraft.predicate.entity.EntitySubPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModPredicateType {
    public static final MapCodec<SoulsCheckPredicate> SOULS_CHECK_PREDICATE = register("souls_check", SoulsCheckPredicate.CODEC);

    private static <T extends EntitySubPredicate> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(Registries.ENTITY_SUB_PREDICATE_TYPE, Identifier.of("soul_gathering", id), codec);
    }
}
