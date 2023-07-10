package com.obsidian_core.archaic_quest.client;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class AQModelLayers {

    public static final ModelLayerLocation SKULL = create("skull");
    public static final ModelLayerLocation WALL_SKULL = create("wall_skull");
    public static final ModelLayerLocation ANIMAL_SKULL = create("animal_skull");
    public static final ModelLayerLocation ANIMAL_WALL_SKULL = create("animal_wall_skull");

    public static final ModelLayerLocation AZTEC_CRAFTING_STATION = create("aztec_crafting_station");
    public static final ModelLayerLocation AZTEC_DUNGEON_DOOR = create("aztec_dungeon_door");
    public static final ModelLayerLocation AZTEC_THRONE = create("aztec_throne");
    public static final ModelLayerLocation SPIKE_TRAP = create("spike_trap");
    public static final ModelLayerLocation SPIKE_TRAP_OVERLAY = create("spike_trap_overlay");
    public static final ModelLayerLocation AZTEC_DUNGEON_CHEST = create("aztec_dungeon_chest");

    public static final ModelLayerLocation ICHCAHUIPILLI_ARMOR = create("ichcahuipilli_armor");

    public static final ModelLayerLocation TLATLAOMI = create("tlatlaomi");



    private static ModelLayerLocation create(String path) {
        return create(path, "main");
    }

    private static ModelLayerLocation create(String path, String layerName) {
        return new ModelLayerLocation(ArchaicQuest.resourceLoc(path), layerName);
    }

    public static void init() {}

    private AQModelLayers() {}
}
