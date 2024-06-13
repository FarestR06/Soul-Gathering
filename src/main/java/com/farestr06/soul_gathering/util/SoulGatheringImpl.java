package com.farestr06.soul_gathering.util;

import com.farestr06.soul_gathering.component.ModComponents;
import net.minecraft.entity.player.PlayerEntity;

/**
 * The <code>SoulGatheringImpl</code> interface provided easy access to the {@link com.farestr06.soul_gathering.component.EntitySoulComponent EntitySoulComponent} class.
 * Any game element that accessed a player's soul count would do so by implementing this interface.<br>
 * <br>
 * @apiNote Any object that implemented this interface should not have been tagged under any of the tags in the {@link SoulTags SoulTags} class, as this interface provided a way to give items soul gathering.
 * @see com.farestr06.soul_gathering.component.EntitySoulComponent
 * @deprecated This class has been deprecated in favor of {@link SoulObjectRegistry}, as it is more flexible.
 */
@Deprecated(forRemoval = true)
public interface SoulGatheringImpl {
    int soulGathering = 0;

    /**
     * This method would take a {@link PlayerEntity playerEntity} and attempted to add souls to it. If the provided
     * amount exceeded the maximum soul count, a {@link SoulCountOutOfBoundsException soulParamOutOfBoundsException} would be thrown.
     * @param provider the {@link PlayerEntity playerEntity} to add souls to
     * @param amount the amount of souls to add to the provider.
     * @throws SoulCountOutOfBoundsException if an attempt is made to add more souls than the player can hold.
     */
    default void addSouls(PlayerEntity provider, int amount) {
        if (!canAddSouls(provider, amount)) {
            throw new SoulCountOutOfBoundsException(amount);
        }
        ModComponents.SOUL_COMPONENT.get(provider).addSouls(amount);
    }

    /**
     * This method would take a {@link PlayerEntity playerEntity} and attempted to remove souls from it. If the provided
     * amount exceeded the minimum soul count, a {@link SoulCountOutOfBoundsException soulParamOutOfBoundsException} would be thrown.
     * @param provider the playerEntity to remove souls from
     * @param amount the amount of souls to remove from the provider.
     * @throws SoulCountOutOfBoundsException if an attempt is made to remove more souls than the player already has.
     */
    default void removeSouls(PlayerEntity provider, int amount) {
        if (!canRemoveSouls(provider, amount)) {
            throw new SoulCountOutOfBoundsException(-amount);
        }
        ModComponents.SOUL_COMPONENT.get(provider).removeSouls(amount);
    }

    /**
     * This method would take a {@link PlayerEntity playerEntity} and return the number of souls they held.
     * @param provider the playerEntity whose soul count should be returned
     * @return the amount of souls the provided playerEntity holds
     */
    default int getSouls(PlayerEntity provider) {
        return ModComponents.SOUL_COMPONENT.get(provider).getSoulCount();
    }

    /**
     * This method would test if the provided amount of souls can be removed from the provided player.
     * @param provider the {@link PlayerEntity playerEntity} to test the removal on
     * @param amount the number of souls to test the removal with
     * @return if the provided amount of souls can be removed from the player
     */
    default boolean canRemoveSouls(PlayerEntity provider, int amount) {
        return !(ModComponents.SOUL_COMPONENT.get(provider).getSoulCount() - amount < 0);
    }

    /**
     * This method would test if the provided amount of souls could be added to the provided player.
     * @param provider the {@link PlayerEntity playerEntity} to test the addition on
     * @param amount the number of souls to test the addition with
     * @return if the provided amount of souls can be added to the player
     */
    default boolean canAddSouls(PlayerEntity provider, int amount) {
        return !(ModComponents.SOUL_COMPONENT.get(provider).getSoulCount() - amount > 8192);
    }

    /**
     * This method was used internally to determine how many souls a player should receive upon killing an enemy.
     * @return the implementing object's soul gathering
     */
    default int getSoulGathering() {
        return soulGathering;
    }
}