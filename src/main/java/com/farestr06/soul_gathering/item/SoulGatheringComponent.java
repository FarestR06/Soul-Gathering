package com.farestr06.soul_gathering.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

public record SoulGatheringComponent(int amount) implements TooltipAppender {

    public static final Codec<SoulGatheringComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.optionalFieldOf("amount", 1).forGetter(SoulGatheringComponent::amount)
    ).apply(instance, SoulGatheringComponent::new));

    public static final PacketCodec<ByteBuf, SoulGatheringComponent> PACKET_CODEC = PacketCodecs.INTEGER.xmap(SoulGatheringComponent::new, SoulGatheringComponent::amount);

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> tooltip, TooltipType type) {
        tooltip.accept(Text.translatable("item.soul_gathering", amount).formatted(Formatting.DARK_AQUA));
    }

    public static SoulGatheringComponent of(int amount) {
        return new SoulGatheringComponent(amount);
    }
}
