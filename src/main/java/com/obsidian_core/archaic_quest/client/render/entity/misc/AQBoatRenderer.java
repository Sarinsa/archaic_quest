package com.obsidian_core.archaic_quest.client.render.entity.misc;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.entity.AQBoat;
import com.obsidian_core.archaic_quest.common.entity.AQChestBoat;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;
import java.util.stream.Stream;

public class AQBoatRenderer extends BoatRenderer {

    private final Map<AQBoat.BoatType, Pair<ResourceLocation, BoatModel>> boatResources;

    public AQBoatRenderer(EntityRendererProvider.Context context, boolean hasChest) {
        super(context, false);
        this.boatResources = Stream.of(AQBoat.BoatType.values()).collect(ImmutableMap.toImmutableMap((key) -> key,
                (boatType) -> Pair.of(new ResourceLocation(ArchaicQuest.MODID, getTextureLocation(boatType, hasChest)), createBoatModel(context, boatType, hasChest))));
    }

    @Override
    public Pair<ResourceLocation, BoatModel> getModelWithLocation(Boat boat) {
        if (boat instanceof AQChestBoat)
            return this.boatResources.get(((AQChestBoat)boat)._getBoatType());
        else
            return this.boatResources.get(((AQBoat)boat)._getBoatType());
    }

    private static String getTextureLocation(AQBoat.BoatType model, boolean hasChest)
    {
        return hasChest ? "textures/entity/chest_boat/" + model.getName() + ".png" : "textures/entity/boat/" + model.getName() + ".png";
    }

    private static ModelLayerLocation createLocation(String name, String layer)
    {
        return new ModelLayerLocation(new ResourceLocation(ArchaicQuest.MODID, name), layer);
    }

    public static ModelLayerLocation createBoatModelName(AQBoat.BoatType model)
    {
        return createLocation("boat/" + model.getName(), "main");
    }

    public static ModelLayerLocation createChestBoatModelName(AQBoat.BoatType model)
    {
        return createLocation("chest_boat/" + model.getName(), "main");
    }

    private BoatModel createBoatModel(EntityRendererProvider.Context context, AQBoat.BoatType model, boolean hasChest)
    {
        ModelLayerLocation modellayerlocation = hasChest ? createChestBoatModelName(model) : createBoatModelName(model);
        ModelPart baked = context.bakeLayer(modellayerlocation);
        return hasChest ? new BoatModel(baked, true) : new BoatModel(baked, false);
    }
}
