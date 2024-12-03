package com.farestr06.soul_gathering.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;

public record SoulsCheckLootCondition(SoulsCheckPredicate soulsCheckPredicate, LootContext.EntityTarget entity) implements LootCondition {
    public static final MapCodec<SoulsCheckLootCondition> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            SoulsCheckPredicate.CODEC.fieldOf("predicate")
                    .forGetter(SoulsCheckLootCondition::soulsCheckPredicate),
            LootContext.EntityTarget.CODEC.fieldOf("entity").forGetter(SoulsCheckLootCondition::entity)).apply(instance, SoulsCheckLootCondition::new));



    @Override
    public LootConditionType getType() {
        return SoulLootConditionType.SOULS_CHECK;
    }

    @Override
    public boolean test(LootContext ctx) {
        Entity entity = ctx.get(this.entity.getParameter());
        ServerWorld world = ctx.getWorld();
        return soulsCheckPredicate.test(entity, world, null);
    }
}
