package com.farestr06.soul_gathering.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.NbtCompound;

public class EntitySoulComponent implements SoulComponent, AutoSyncedComponent {
    private int soulCount = 0;

    @Override
    public int getSoulCount() {
        return soulCount;
    }

    @Override
    public void addSouls(int amount) {
        if (soulCount >= 8192) {
            soulCount = 8192;
        } else {
            soulCount += amount;
        }
    }

    @Override
    public void removeSouls(int amount) {
        if (soulCount <= 0) {
            soulCount = 0;
        } else {
            soulCount -= amount;
        }
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.soulCount = tag.getInt("soul_count");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putInt("soul_count", this.soulCount);
    }
}
