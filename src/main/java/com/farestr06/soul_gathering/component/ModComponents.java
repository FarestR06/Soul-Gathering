package com.farestr06.soul_gathering.component;

import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.util.Identifier;

public class ModComponents implements EntityComponentInitializer {

    public static final ComponentKey<SoulComponent> SOUL_COMPONENT = ComponentRegistry.getOrCreate(Identifier.of("soul_gathering", "soul"), SoulComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(SOUL_COMPONENT, EntitySoulComponent::new, RespawnCopyStrategy.INVENTORY);
    }
}
