package com.farestr06.soul_gathering.config;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModConfig {
    public static final ConfigClassHandler<ModConfig> HANDLER = ConfigClassHandler.createBuilder(ModConfig.class)
            .id(Identifier.of("soul_gathering", "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("soul_gathering.json5"))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
                    .setJson5(true)
                    .build()
            ).build();

    @SerialEntry
    public boolean addDefaultSoulGatheringsAsDataComponents = true;

    @SerialEntry
    public List<String> itemsWithOneSoulGathering = new ArrayList<>();

    @SerialEntry
    public List<String> itemsWithThreeSoulGathering = new ArrayList<>();

    @SerialEntry
    public List<String> itemsWithFiveSoulGathering = new ArrayList<>();

    public static final Map<String, Integer> ITEMS_AND_GATHERING = new HashMap<>();

    public static void init() {
        HANDLER.load();
        for (String item : HANDLER.instance().itemsWithOneSoulGathering) {
            ITEMS_AND_GATHERING.put(item, 1);
        }
        for (String item : HANDLER.instance().itemsWithThreeSoulGathering) {
            ITEMS_AND_GATHERING.put(item, 3);
        }
        for (String item : HANDLER.instance().itemsWithFiveSoulGathering) {
            ITEMS_AND_GATHERING.put(item, 5);
        }
    }
}
