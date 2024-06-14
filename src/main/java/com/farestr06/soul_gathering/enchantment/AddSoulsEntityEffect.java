package com.farestr06.soul_gathering.enchantment;

import com.farestr06.soul_gathering.component.ModComponents;
import com.farestr06.soul_gathering.component.SoulComponent;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

public record AddSoulsEntityEffect(EnchantmentLevelBasedValue minSouls, EnchantmentLevelBasedValue maxSouls) implements EnchantmentEntityEffect {

    public static final MapCodec<AddSoulsEntityEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            (EnchantmentLevelBasedValue.CODEC.fieldOf("min_souls"))
                    .forGetter(AddSoulsEntityEffect::minSouls),
            (EnchantmentLevelBasedValue.CODEC.fieldOf("max_souls"))
                    .forGetter(AddSoulsEntityEffect::maxSouls))
            .apply(instance, AddSoulsEntityEffect::new));

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof PlayerEntity) {
            SoulComponent component = ModComponents.SOUL_COMPONENT.get(user);
            int i = (int) Math.ceil(Random.create().nextFloat());
            if (component.getSoulCount() + i <= 8192) {
                if (Random.create().nextFloat() < 0.33f) {
                    component.addSouls(i);
                }
            } else {
                component.setSoulCount(8192);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }

    public EnchantmentLevelBasedValue minSouls() {
        return this.minSouls;
    }
    public EnchantmentLevelBasedValue maxSouls() {
        return this.maxSouls;
    }

}
