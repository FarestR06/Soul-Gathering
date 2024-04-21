package com.farestr06.soul_gathering.component;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;

public class SoulComponentHelper {
    public static int mappedSoulCount(PlayerEntity provider) {
        float input = ModComponents.SOUL_COMPONENT.get(provider).getSoulCount();
        return Math.round(MathHelper.map(input, 0, 8192f, 0f, 20f));
    }
}
