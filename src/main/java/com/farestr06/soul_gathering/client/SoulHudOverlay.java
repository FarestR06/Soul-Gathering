package com.farestr06.soul_gathering.client;

import com.farestr06.soul_gathering.component.SoulComponentHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.Identifier;

public class SoulHudOverlay implements HudRenderCallback {
    private static final Identifier SOUL_EMPTY = new Identifier("soul_gathering",
            "hud/soul_empty");
    private static final Identifier SOUL_HALF = new Identifier("soul_gathering",
            "hud/soul_half");
    private static final Identifier SOUL_FULL = new Identifier("soul_gathering",
            "hud/soul_full");
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        int x = 0;
        int xb;
        int h = 0;
        MinecraftClient minecraft = MinecraftClient.getInstance();

        if (minecraft != null) {
            int width = minecraft.getWindow().getScaledWidth();
            int height = minecraft.getWindow().getScaledHeight();
            assert minecraft.player != null;
            h = SoulComponentHelper.mappedSoulCount(minecraft.player);
            x = width / 2 + 91;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        if (minecraft.interactionManager.hasStatusBars()) {
            for (int i = 0; i < 10; i++) {
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
    private int y(MinecraftClient minecraft) {
        int height = minecraft.getWindow().getScaledHeight();
        assert minecraft.player != null;
        if (!minecraft.player.isSubmergedIn(FluidTags.WATER)) {
            return height + 9;
        } else {
            return height;
        }
    }
}
