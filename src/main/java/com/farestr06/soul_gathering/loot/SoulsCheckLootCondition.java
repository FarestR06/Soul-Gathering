package com.farestr06.soul_gathering.loot;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.Set;

public record SoulsCheckLootCondition(SoulsCheckPredicate soulsCheckPredicate, LootContext.EntityTarget entity) implements LootCondition {
    public static final MapCodec<SoulsCheckLootCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            SoulsCheckPredicate.CODEC.fieldOf("predicate")
                    .forGetter(SoulsCheckLootCondition::soulsCheckPredicate),
            LootContext.EntityTarget.CODEC.fieldOf("entity").forGetter(SoulsCheckLootCondition::entity)).apply(instance, SoulsCheckLootCondition::new));

    @Override
    public Set<LootContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(LootContextParameters.ORIGIN, this.entity.getParameter());
    }

    @Override
    public LootConditionType getType() {
        return ModLootConditionType.SOULS_CHECK;
    }

    @Override
    public boolean test(LootContext ctx) {
        Entity entity = ctx.get(this.entity.getParameter());
        ServerWorld world = ctx.getWorld();
        Vec3d vec3d = ctx.get(LootContextParameters.ORIGIN);
        return soulsCheckPredicate.test(entity, world, vec3d);
    }
}
