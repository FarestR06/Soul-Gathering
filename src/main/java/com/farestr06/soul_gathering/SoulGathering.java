package com.farestr06.soul_gathering;

import com.farestr06.soul_gathering.command.SoulCommand;
import com.farestr06.soul_gathering.enchantment.SoulEnchantmentEffects;
import com.farestr06.soul_gathering.item.SoulDataComponentTypes;
import com.farestr06.soul_gathering.item.SoulGatheringComponent;
import com.farestr06.soul_gathering.loot.ModLootConditionType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.ComponentMap;
import net.minecraft.item.Items;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoulGathering implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("soul_gathering");

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Soul Gathering");
        ModLootConditionType.register();
        SoulEnchantmentEffects.register();
        SoulDataComponentTypes.register();
        SoulCommand.registerCommand();

        if (ResourceManagerHelper.registerBuiltinResourcePack(Identifier.of("soul_gathering", "standard_data"),
                FabricLoader.getInstance().getModContainer("soul_gathering").orElseThrow(),
                Text.literal("Soul Gathering Standard Data").setStyle(Style.EMPTY.withFont(Identifier.of("alt")))
                        .formatted(Formatting.AQUA), ResourcePackActivationType.DEFAULT_ENABLED)) {
            LOGGER.info("Standard enchantments loaded!");
        } else {
            LOGGER.warn("Failed to load standard enchantments...");
        }

        DefaultItemComponentEvents.MODIFY.register(context -> {
            context.modify(Items.DIAMOND_SWORD, builder -> builder.addAll(genSoulGatheringForDiamond()));
            context.modify(Items.DIAMOND_SHOVEL, builder -> builder.addAll(genSoulGatheringForDiamond()));
            context.modify(Items.DIAMOND_PICKAXE, builder -> builder.addAll(genSoulGatheringForDiamond()));
            context.modify(Items.DIAMOND_AXE, builder -> builder.addAll(genSoulGatheringForDiamond()));
            context.modify(Items.DIAMOND_HOE, builder -> builder.addAll(genSoulGatheringForDiamond()));
            context.modify(Items.DIAMOND_HELMET, builder -> builder.addAll(genSoulGatheringForDiamond()));
            context.modify(Items.DIAMOND_CHESTPLATE, builder -> builder.addAll(genSoulGatheringForDiamond()));
            context.modify(Items.DIAMOND_LEGGINGS, builder -> builder.addAll(genSoulGatheringForDiamond()));
            context.modify(Items.DIAMOND_BOOTS, builder -> builder.addAll(genSoulGatheringForDiamond()));

            context.modify(Items.NETHERITE_SWORD, builder -> builder.addAll(genSoulGatheringForNetherite()));
            context.modify(Items.NETHERITE_SHOVEL, builder -> builder.addAll(genSoulGatheringForNetherite()));
            context.modify(Items.NETHERITE_PICKAXE, builder -> builder.addAll(genSoulGatheringForNetherite()));
            context.modify(Items.NETHERITE_AXE, builder -> builder.addAll(genSoulGatheringForNetherite()));
            context.modify(Items.NETHERITE_HOE, builder -> builder.addAll(genSoulGatheringForNetherite()));
            context.modify(Items.NETHERITE_HELMET, builder -> builder.addAll(genSoulGatheringForNetherite()));
            context.modify(Items.NETHERITE_CHESTPLATE, builder -> builder.addAll(genSoulGatheringForNetherite()));
            context.modify(Items.NETHERITE_LEGGINGS, builder -> builder.addAll(genSoulGatheringForNetherite()));
            context.modify(Items.NETHERITE_BOOTS, builder -> builder.addAll(genSoulGatheringForNetherite()));
        });
    }

    private ComponentMap genSoulGatheringForDiamond() {
        return ComponentMap.builder().add(
                SoulDataComponentTypes.SOUL_GATHERING, SoulGatheringComponent.of(1)
        ).build();
    }
    private ComponentMap genSoulGatheringForNetherite() {
        return ComponentMap.builder().add(
                SoulDataComponentTypes.SOUL_GATHERING, SoulGatheringComponent.of(3)
        ).build();
    }
}
