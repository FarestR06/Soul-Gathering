package com.farestr06.soul_gathering;

import com.farestr06.soul_gathering.command.SoulCommand;
import com.farestr06.soul_gathering.enchantment.SoulEnchantmentEffects;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoulGathering implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("soul_gathering");

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Soul Gathering");
        SoulEnchantmentEffects.init();
        SoulCommand.registerCommand();
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            if (ResourceManagerHelper.registerBuiltinResourcePack(Identifier.of("soul_gathering", "example"),
                    FabricLoader.getInstance().getModContainer("soul_gathering").orElseThrow(), ResourcePackActivationType.ALWAYS_ENABLED)) {
                LOGGER.info("Successfully registered compat pack for mod \"deeperdarker\"!");
            } else {
                LOGGER.error("Couldn't load pack for mod \"deeperdarker\"!");
            }
        }
    }
}
