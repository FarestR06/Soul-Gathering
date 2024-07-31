package com.farestr06.soul_gathering.loot;

import com.farestr06.soul_gathering.component.ModComponents;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntitySubPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public record SoulsCheckPredicate(NumberRange.IntRange soulCount) implements EntitySubPredicate {
    public static final MapCodec<SoulsCheckPredicate> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            NumberRange.IntRange.CODEC.optionalFieldOf("level", NumberRange.IntRange.between(0, 8192)).forGetter(
                    SoulsCheckPredicate::soulCount
            )).apply(instance, SoulsCheckPredicate::new)
    );

    @Override
    public MapCodec<? extends EntitySubPredicate> getCodec() {
        return ModPredicateType.SOULS_CHECK_PREDICATE;
    }

    @Override
    public boolean test(Entity entity, ServerWorld world, @Nullable Vec3d pos) {
        if (!(entity instanceof ServerPlayerEntity serverPlayerEntity)) {
            return false;
        }
        return soulCount.test(ModComponents.SOUL_COMPONENT.get(serverPlayerEntity).getSoulCount());
    }
}
