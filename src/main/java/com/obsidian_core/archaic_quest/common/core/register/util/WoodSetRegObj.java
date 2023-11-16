package com.obsidian_core.archaic_quest.common.core.register.util;

import com.obsidian_core.archaic_quest.common.block.AQStandingSignBlock;
import com.obsidian_core.archaic_quest.common.block.AQWallSignBlock;
import com.obsidian_core.archaic_quest.common.block.VerticalSlabBlock;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import com.obsidian_core.archaic_quest.common.entity.AQBoat;
import com.obsidian_core.archaic_quest.common.item.AQBoatItem;
import com.obsidian_core.archaic_quest.common.item.AQCreativeTabs;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.extensions.IForgeBoat;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class WoodSetRegObj {

    public static final List<WoodSetRegObj> WOOD_SETS = new ArrayList<>();
    public static final List<RegistryObject<? extends SignBlock>> SIGNS = new ArrayList<>();


    private final List<RegistryObject<? extends Block>> allBlocks;
    private final WoodType woodType;


    private final RegistryObject<SaplingBlock> sapling;
    private final RegistryObject<LeavesBlock> leaves;
    private final RegistryObject<RotatedPillarBlock> wood;
    private final RegistryObject<RotatedPillarBlock> strippedWood;
    private final RegistryObject<RotatedPillarBlock> log;
    private final RegistryObject<RotatedPillarBlock> strippedLog;
    private final RegistryObject<Block> planks;
    private final RegistryObject<SlabBlock> slab;
    private final RegistryObject<VerticalSlabBlock> verticalSlab;
    private final RegistryObject<StairBlock> stairs;
    private final RegistryObject<FenceBlock> fence;
    private final RegistryObject<FenceGateBlock> fenceGate;
    private final RegistryObject<PressurePlateBlock> pressurePlate;
    private final RegistryObject<WoodButtonBlock> button;
    private final RegistryObject<SignBlock> sign;
    private final RegistryObject<WallSignBlock> wallSign;
    private final RegistryObject<TrapDoorBlock> trapDoor;
    private final RegistryObject<DoorBlock> door;

    private final RegistryObject<AQBoatItem> boat;
    private final RegistryObject<AQBoatItem> chestBoat;



    public WoodSetRegObj(String name, BlockBehaviour.Properties properties, AbstractTreeGrower treeGrower) {
        woodType = WoodType.create(ArchaicQuest.MODID + ":" + name);
        WoodType.register(woodType);

        sapling = register(name + "_sapling", () -> new SaplingBlock(treeGrower, BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)), true);
        leaves = register(name + "_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never).isViewBlocking(Blocks::never)), true);
        wood = register(name + "_wood", () -> new RotatedPillarBlock(properties), false);
        strippedWood = register(name + "_stripped_wood", () -> new RotatedPillarBlock(properties), false);
        log = register(name + "_log", () -> new RotatedPillarBlock(properties), false);
        strippedLog = register(name + "_stripped_log", () -> new RotatedPillarBlock(properties), false);
        planks = register(name + "_planks", () -> new Block(properties), false);
        slab = register(name + "_slab", () -> new SlabBlock(properties), false);
        verticalSlab = register(name + "_vertical_slab", () -> new VerticalSlabBlock(properties), false);
        stairs = register(name + "_stairs", () -> new StairBlock(() -> planks.get().defaultBlockState(), properties), false);
        fence = register(name + "_fence", () -> new FenceBlock(properties), true);
        fenceGate = register(name + "_fence_gate", () -> new FenceGateBlock(properties), true);
        pressurePlate = register(name + "_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, properties), true);
        button = register(name + "_button", () -> new WoodButtonBlock(properties), true);
        trapDoor = register(name + "_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(planks.get()).noCollission()), true);
        door = registerDoor(name + "_door", properties);

        sign = AQBlocks.REGISTRY.register(name + "_sign", () -> new AQStandingSignBlock(properties, woodType));
        wallSign = AQBlocks.REGISTRY.register(name + "_wall_sign", () -> new AQWallSignBlock(properties, woodType));

        AQItems.REGISTRY.register(name + "_sign", () -> new SignItem(new Item.Properties().stacksTo(16).tab(AQCreativeTabs.DECORATION), sign.get(), wallSign.get()));
        boat = AQItems.REGISTRY.register(name + "_boat", () -> new AQBoatItem(false, AQBoat.BoatType.AHUEHUETE, new Item.Properties().stacksTo(16).tab(AQCreativeTabs.ITEMS)));
        chestBoat = AQItems.REGISTRY.register(name + "_chest_boat", () -> new AQBoatItem(true, AQBoat.BoatType.AHUEHUETE, new Item.Properties().stacksTo(16).tab(AQCreativeTabs.ITEMS)));


        allBlocks = List.of(
            sapling,
            leaves,
            wood,
            strippedWood,
            log,
            strippedLog,
            planks,
            slab,
            verticalSlab,
            stairs,
            fence,
            fenceGate,
            pressurePlate,
            button,
            sign,
            wallSign,
            trapDoor,
            door
        );

        WOOD_SETS.add(this);
        SIGNS.add(sign);
        SIGNS.add(wallSign);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, boolean decorative) {
        RegistryObject<T> regObj = AQBlocks.REGISTRY.register(name, block);
        AQItems.REGISTRY.register(name, () -> new BlockItem(regObj.get(), new Item.Properties().tab(decorative ? AQCreativeTabs.DECORATION : AQCreativeTabs.BLOCKS)));
        return regObj;
    }

    private static RegistryObject<DoorBlock> registerDoor(String name, BlockBehaviour.Properties properties) {
        RegistryObject<DoorBlock> regObj = AQBlocks.REGISTRY.register(name, () -> new DoorBlock(properties));
        AQItems.REGISTRY.register(name, () -> new DoubleHighBlockItem(regObj.get(), new Item.Properties().stacksTo(16).tab(AQCreativeTabs.DECORATION)));
        return regObj;
    }

    public RegistryObject<RotatedPillarBlock> getWood() {
        return wood;
    }

    public RegistryObject<RotatedPillarBlock> getStrippedWood() {
        return strippedWood;
    }

    public RegistryObject<RotatedPillarBlock> getLog() {
        return log;
    }

    public RegistryObject<RotatedPillarBlock> getStrippedLog() {
        return strippedLog;
    }

    public RegistryObject<Block> getPlanks() {
        return planks;
    }

    public RegistryObject<SlabBlock> getSlab() {
        return slab;
    }

    public RegistryObject<VerticalSlabBlock> getVertSlab() {
        return verticalSlab;
    }

    public RegistryObject<StairBlock> getStairs() {
        return stairs;
    }

    public RegistryObject<FenceBlock> getFence() {
        return fence;
    }

    public RegistryObject<FenceGateBlock> getFenceGate() {
        return fenceGate;
    }

    public RegistryObject<PressurePlateBlock> getPressurePlate() {
        return pressurePlate;
    }

    public RegistryObject<WoodButtonBlock> getButton() {
        return button;
    }

    public RegistryObject<SignBlock> getSign() {
        return sign;
    }

    public RegistryObject<WallSignBlock> getWallSign() {
        return wallSign;
    }

    public RegistryObject<TrapDoorBlock> getTrapdoor() {
        return trapDoor;
    }

    public RegistryObject<DoorBlock> getDoor() {
        return door;
    }

    public RegistryObject<SaplingBlock> getSapling() {
        return sapling;
    }

    public RegistryObject<LeavesBlock> getLeaves() {
        return leaves;
    }

    public RegistryObject<AQBoatItem> getBoat() { return boat; }

    public RegistryObject<AQBoatItem> getChestBoat() { return chestBoat; }

    public Iterable<RegistryObject<? extends Block>> allBlocks() {
        return allBlocks;
    }

    public WoodType getWoodType() {
        return woodType;
    }
}
