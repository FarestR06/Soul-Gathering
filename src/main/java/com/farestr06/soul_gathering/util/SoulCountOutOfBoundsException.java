package com.farestr06.soul_gathering.util;

/**
 * This exception class is thrown by {@link SoulGatheringImpl SoulGatheringImpl} when attempting to add or remove souls in a way that exceeds the component's intended limits.
 * @see SoulGatheringImpl
 */
public class SoulCountOutOfBoundsException extends RuntimeException {
    int amount;

    /**
     * The constructor for this class. The amount that is passed in is used to help generate the message sent to the console.
     * @param amount the amount that caused the exception. It should be positive if it was meant to be added or negative if it was meant to be removed.
     */
    public SoulCountOutOfBoundsException(int amount) {
        this.amount = amount;
    }

    @Override
    public String getMessage() {
        if (amount < 0) {
            return ("Tried to remove " + amount + " souls when the minimum soul count is 0");
        }
        return ("Tried to add " + amount + " souls when the maximum soul count is 0");
    }
}
