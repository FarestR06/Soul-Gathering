package com.farestr06.soul_gathering.item;

import com.farestr06.soul_gathering.SoulGathering;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class SoulDataComponentTypes {
    public static final ComponentType<SoulGatheringComponent> SOUL_GATHERING = register("gathering", builder -> builder
            .codec(SoulGatheringComponent.CODEC).packetCodec(SoulGatheringComponent.PACKET_CODEC));

    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of("soul_gathering", id), builderOperator.apply(ComponentType.builder()).build());
    }

    public static void register() {
        SoulGathering.LOGGER.info("Registering DataComponentTypes!");
    }
}
