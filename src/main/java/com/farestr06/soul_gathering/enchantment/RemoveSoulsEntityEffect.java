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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public record RemoveSoulsEntityEffect(EnchantmentLevelBasedValue minSouls, EnchantmentLevelBasedValue maxSouls) implements EnchantmentEntityEffect {
    public static final MapCodec<RemoveSoulsEntityEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            (EnchantmentLevelBasedValue.CODEC.fieldOf("min_souls"))
                    .forGetter(RemoveSoulsEntityEffect::minSouls),
            (EnchantmentLevelBasedValue.CODEC.fieldOf("max_souls"))
                    .forGetter(RemoveSoulsEntityEffect::maxSouls))
            .apply(instance, RemoveSoulsEntityEffect::new));

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof PlayerEntity) {
            SoulComponent component = ModComponents.SOUL_COMPONENT.get(user);
            int i = MathHelper.nextBetween(user.getRandom(), ((int) this.minSouls.getValue(level)), ((int) this.maxSouls.getValue(level)));
            if (component.getSoulCount() >= 0) {
                component.removeSouls(i);
            } else {
                component.setSoulCount(0);
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
