package com.farestr06.soul_gathering.mixin;

import com.farestr06.soul_gathering.component.ModComponents;
import com.farestr06.soul_gathering.component.SoulComponentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * This mixin adds souls to the player whenever it kills an enemy by triggering its component's <code>addSouls</code> method.
 */
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "onKilledOther", at = @At(value = "HEAD"))
    private void injectOnKilledOther(ServerWorld world, LivingEntity other, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity provider = other.getAttacker();
        if (provider instanceof PlayerEntity) {
            ModComponents.SOUL_COMPONENT.get(provider).addSouls(SoulComponentHelper.MathHelper.calcSoulAdderAmount(((PlayerEntity) provider)));
        }
    }
}
