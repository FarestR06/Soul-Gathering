package com.farestr06.soul_gathering.item;

import com.farestr06.soul_gathering.SoulGathering;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;

import java.util.function.UnaryOperator;

public class SoulDataComponentTypes {
    public static final ComponentType<Integer> SOUL_GATHERING = register("gathering", builder -> builder.codec(Codecs.NONNEGATIVE_INT).packetCodec(PacketCodecs.VAR_INT));

    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of("soul_gathering", id), builderOperator.apply(ComponentType.builder()).build());
    }

    public static void init() {
        SoulGathering.LOGGER.info("Initializing ComponentTypes...");
    }
}
