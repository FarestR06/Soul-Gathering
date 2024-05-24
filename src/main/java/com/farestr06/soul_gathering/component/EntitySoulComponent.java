package com.farestr06.soul_gathering.component;

import net.minecraft.registry.RegistryWrapper;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

public class EntitySoulComponent implements SoulComponent, AutoSyncedComponent {

    private final PlayerEntity provider;

    public EntitySoulComponent(PlayerEntity player) {
        this.provider = player;
    }
    private int soulCount = 0;

    public void setSoulCount(int soulCount) {
        this.soulCount = soulCount;
        ModComponents.SOUL_COMPONENT.sync(provider);
    }

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
        ModComponents.SOUL_COMPONENT.sync(provider);
    }

    @Override
    public void removeSouls(int amount) {
        if (soulCount <= 0) {
            soulCount = 0;
        } else {
            soulCount -= amount;
        }
        ModComponents.SOUL_COMPONENT.sync(provider);
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        this.soulCount = tag.getInt("soul_count");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("soul_count", this.soulCount);
    }

    @java.lang.Override
    public boolean equals(java.lang.Object o) {
        return false;
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return player == this.provider; // only sync with the provider itself
    }

    @Override
    public void tick() {

    }
}
