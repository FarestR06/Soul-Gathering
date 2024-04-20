package com.farestr06.soul_gathering.item;

public class SoulItemModifier {
    private final int cost;
    private final int soulGatheringCount;

    public SoulItemModifier(int cost, byte soulGatheringCount) {
        this.cost = cost;
        this.soulGatheringCount = soulGatheringCount;
    }

    public int getCost(){
        return cost;
    }

    public int getSoulGatheringCount(){
        return soulGatheringCount;
    }
}
