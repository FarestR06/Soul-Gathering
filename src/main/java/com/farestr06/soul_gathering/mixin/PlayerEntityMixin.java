package com.farestr06.soul_gathering.mixin;

import com.farestr06.soul_gathering.component.ModComponents;
import com.farestr06.soul_gathering.component.SoulComponentHelper;
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
    private void injectOnKilledOther(ServerWorld world, LivingEntity other, CallbackInfoReturnable<Boolean> cir) {
        ModComponents.SOUL_COMPONENT.get(provider).addSouls(
                net.minecraft.util.math.MathHelper.floor(Random.create().nextFloat()
                        * SoulComponentHelper.MathHelper.calcSoulAdderAmount(provider)));
    }

    @Inject(method = "onKilledOther", at = @At(value = "TAIL"))
    private void syncSoulGathering(ServerWorld world, LivingEntity other, CallbackInfoReturnable<Boolean> cir) {
        ModComponents.SOUL_COMPONENT.sync(provider);
    }

}
