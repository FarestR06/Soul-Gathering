package com.farestr06.soul_gathering.util;

import com.farestr06.soul_gathering.component.ModComponents;
import net.minecraft.entity.player.PlayerEntity;

public interface SoulGatheringObject {
    default void addSouls(PlayerEntity provider, int amount) {
        ModComponents.SOUL_COMPONENT.get(provider).addSouls(amount);
    }
    default void removeSouls(PlayerEntity provider, int amount) {
        ModComponents.SOUL_COMPONENT.get(provider).removeSouls(amount);
    }
    default int getSouls(PlayerEntity provider) {
        return ModComponents.SOUL_COMPONENT.get(provider).getSoulCount();
    }
    default boolean canSpendSouls(PlayerEntity provider, int amount) {
        return !(ModComponents.SOUL_COMPONENT.get(provider).getSoulCount() - amount <= 0);
    }
}
