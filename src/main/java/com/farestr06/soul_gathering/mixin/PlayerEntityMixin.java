package com.farestr06.soul_gathering.mixin;

import com.farestr06.soul_gathering.component.ModComponents;
import com.farestr06.soul_gathering.util.SoulGatheringImpl;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements SoulGatheringImpl {

    @Unique
    PlayerEntity provider = (PlayerEntity) (Object)this;

    @Inject(method = "onKilledOther", at = @At(value = "HEAD"))
    private void injected(ServerWorld world, LivingEntity other, CallbackInfoReturnable<Boolean> cir) {
        ModComponents.SOUL_COMPONENT.get(provider).addSouls(Random.create().nextBetweenExclusive(1, 16));
    }
}