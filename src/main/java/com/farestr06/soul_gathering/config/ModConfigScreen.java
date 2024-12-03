package com.farestr06.soul_gathering.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.StringControllerBuilder;
import net.minecraft.text.Text;

import java.util.List;

public class ModConfigScreen implements ModMenuApi {
    private static final Option<Boolean> ADD_DEFAULT_SOUL_GATHERINGS_AS_DATA_COMPONENTS = Option.<Boolean>createBuilder()
            .name(Text.translatable("config.soul_gathering.default_gatherings_as_components.name"))
            .description(OptionDescription.of(Text.translatable("config.soul_gathering.default_gatherings_as_components.desc")))
            .binding(
                    true,
                    () -> ModConfig.HANDLER.instance().addDefaultSoulGatheringsAsDataComponents,
                    newVal -> ModConfig.HANDLER.instance().addDefaultSoulGatheringsAsDataComponents = newVal
            )
            .controller(opt -> BooleanControllerBuilder.create(opt).yesNoFormatter().coloured(true))
            .build();
    private static final ListOption<String> ONE_SOUL_GATHERING = ListOption.<String>createBuilder()
            .name(Text.translatable("config.soul_gathering.one_soul.name"))
            .description(OptionDescription.of(Text.translatable("config.soul_gathering.one_soul.desc")))
            .binding(
                    List.of(
                            "minecraft:diamond_sword",
                            "minecraft:diamond_axe",
                            "minecraft:diamond_helmet",
                            "minecraft:diamond_chestplate",
                            "minecraft:diamond_leggings",
                            "minecraft:diamond_boots"
                    ), ()-> ModConfig.HANDLER.instance().itemsWithOneSoulGathering,
                    newVal -> ModConfig.HANDLER.instance().itemsWithOneSoulGathering = newVal
            ).controller(StringControllerBuilder::create)
            .initial("foo:bar")
            .build();
    private static final ListOption<String> THREE_SOUL_GATHERING = ListOption.<String>createBuilder()
            .name(Text.translatable("config.soul_gathering.three_souls.name"))
            .description(OptionDescription.of(Text.translatable("config.soul_gathering.three_souls.desc")))
            .binding(
                    List.of(
                            "minecraft:netherite_sword",
                            "minecraft:netherite_axe",
                            "minecraft:netherite_helmet",
                            "minecraft:netherite_chestplate",
                            "minecraft:netherite_leggings",
                            "minecraft:netherite_boots"
                    ), ()-> ModConfig.HANDLER.instance().itemsWithThreeSoulGathering,
                    newVal -> ModConfig.HANDLER.instance().itemsWithThreeSoulGathering = newVal
            ).controller(StringControllerBuilder::create)
            .initial("foo:bar")
            .build();

    private static final ListOption<String> FIVE_SOUL_GATHERING = ListOption.<String>createBuilder()
            .name(Text.translatable("config.soul_gathering.five_souls.name"))
            .description(OptionDescription.of(Text.translatable("config.soul_gathering.five_souls.desc")))
            .binding(
                    List.of(),
                    ()-> ModConfig.HANDLER.instance().itemsWithFiveSoulGathering,
                    newVal -> ModConfig.HANDLER.instance().itemsWithFiveSoulGathering = newVal
            ).controller(StringControllerBuilder::create)
            .initial("foo:bar")
            .build();

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("config.soul_gathering.title"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("config.soul_gathering.group.items"))
                        .option(ADD_DEFAULT_SOUL_GATHERINGS_AS_DATA_COMPONENTS)
                        .option(ONE_SOUL_GATHERING)
                        .option(THREE_SOUL_GATHERING)
                        .option(FIVE_SOUL_GATHERING)
                        .build())
                .save(ModConfig.HANDLER::save)
                .build().generateScreen(screen);
    }
}
