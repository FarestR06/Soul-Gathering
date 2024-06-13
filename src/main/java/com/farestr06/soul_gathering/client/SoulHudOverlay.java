package com.farestr06.soul_gathering.client;

import com.farestr06.soul_gathering.component.ModComponents;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SoulHudOverlay implements HudRenderCallback {
    static int x = 0;
    static int xb = 0;
    static int h = 0;
    private static final Identifier SOUL_EMPTY = Identifier.of("soul_gathering",
            "hud/soul_empty");
    private static final Identifier SOUL_HALF = Identifier.of("soul_gathering",
            "hud/soul_half");
    private static final Identifier SOUL_FULL = Identifier.of("soul_gathering",
            "hud/soul_full");

    static private int y(MinecraftClient minecraft) {
        int height = minecraft.getWindow().getScaledHeight();
        assert minecraft.player != null;
        if (!minecraft.player.isSubmergedIn(FluidTags.WATER)) {
            return height + 9;
        } else {
            return height;
        }
    }

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        MinecraftClient minecraft = MinecraftClient.getInstance();

        assert minecraft != null;

        int width = minecraft.getWindow().getScaledWidth();

        h = Math.round(MathHelper.map(ModComponents.SOUL_COMPONENT.get(MinecraftClient.getInstance().player).getSoulCount(),
                0, 8192f, 0f, 20f));

        x = width / 2 + 91;

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        if (minecraft.interactionManager.hasStatusBars()) {
            for (int i = 0; i < 10; ++i) {
                xb = x - i * 8 - 9;
                drawContext.drawGuiTexture(SOUL_EMPTY, xb, y(minecraft) - 59, 9, 9);
                if (i * 2 + 1 < h) {
                    drawContext.drawGuiTexture(SOUL_FULL, xb, y(minecraft) - 59, 9, 9);
                }
                if (i * 2 + 1 != h) continue;
                drawContext.drawGuiTexture(SOUL_HALF, xb, y(minecraft) - 59 , 9, 9);
            }
        }
    }
}
