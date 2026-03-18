package com.obsidian_core.archaic_quest.common.core.register.util;

import com.obsidian_core.archaic_quest.common.block.VerticalSlabBlock;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.core.register.AQCreativeTabs;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import com.obsidian_core.archaic_quest.common.entity.AQBoat;
import com.obsidian_core.archaic_quest.common.item.AQBoatItem;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * A utility for both creating and holding references to all the block (and item)
 * registry objects belonging to a wood type.
 */
public class WoodSet {
    
    public static final List<WoodSet> WOOD_SETS = new ArrayList<>();
    public static final List<RegistryObject<? extends SignBlock>> SIGNS = new ArrayList<>();
    
    private final List<RegistryObject<? extends Block>> allBlocks;
    private final WoodType woodType;
    private final BlockSetType blockSetType;
    
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
    private final RegistryObject<ButtonBlock> button;
    private final RegistryObject<SignBlock> sign;
    private final RegistryObject<WallSignBlock> wallSign;
    private final RegistryObject<CeilingHangingSignBlock> hangingSign;
    private final RegistryObject<WallHangingSignBlock> wallHangingSign;
    private final RegistryObject<TrapDoorBlock> trapDoor;
    private final RegistryObject<DoorBlock> door;
    
    private final RegistryObject<AQBoatItem> boat;
    private final RegistryObject<AQBoatItem> chestBoat;
    
    
    public WoodSet( String name, BlockBehaviour.Properties properties, @Nullable BlockSetType type, AbstractTreeGrower treeGrower ) {
        blockSetType = type == null ? new BlockSetType( name ) : type;
        woodType = WoodType.register( new WoodType( ArchaicQuest.rl( name ).toString(), blockSetType ) );
        WoodType.register( woodType );
        
        // Blocks
        sapling = register( name + "_sapling", () -> new SaplingBlock( treeGrower, BlockBehaviour.Properties.copy( Blocks.OAK_SAPLING ).noCollission().randomTicks().instabreak().sound( SoundType.GRASS ) ), true );
        leaves = register( name + "_leaves", () -> new LeavesBlock( BlockBehaviour.Properties.copy( Blocks.OAK_LEAVES ).strength( 0.2F ).randomTicks().sound( SoundType.GRASS ).noOcclusion().isValidSpawn( Blocks::ocelotOrParrot ).isSuffocating( Blocks::never ).isViewBlocking( Blocks::never ) ), true );
        wood = register( name + "_wood", () -> new RotatedPillarBlock( properties ), false );
        strippedWood = register( name + "_stripped_wood", () -> new RotatedPillarBlock( properties ), false );
        log = register( name + "_log", () -> new RotatedPillarBlock( properties ), false );
        strippedLog = register( name + "_stripped_log", () -> new RotatedPillarBlock( properties ), false );
        planks = register( name + "_planks", () -> new Block( properties ), false );
        slab = register( name + "_slab", () -> new SlabBlock( properties ), false );
        verticalSlab = register( name + "_vertical_slab", () -> new VerticalSlabBlock( properties ), false );
        stairs = register( name + "_stairs", () -> new StairBlock( () -> planks.get().defaultBlockState(), properties ), false );
        fence = register( name + "_fence", () -> new FenceBlock( properties ), true );
        fenceGate = register( name + "_fence_gate", () -> new FenceGateBlock( properties, woodType ), true );
        pressurePlate = register( name + "_pressure_plate", () -> new PressurePlateBlock( PressurePlateBlock.Sensitivity.MOBS, properties, blockSetType ), true );
        button = register( name + "_button", () -> new ButtonBlock( properties.noCollission().strength( 0.5F ).pushReaction( PushReaction.DESTROY ), blockSetType, 30, true ), true );
        trapDoor = register( name + "_trapdoor", () -> new TrapDoorBlock( BlockBehaviour.Properties.copy( planks.get() ).noCollission(), blockSetType ), true );
        door = registerDoor( name + "_door", blockSetType, properties );
        
        sign = AQBlocks.REGISTRY.register( name + "_sign", () -> new StandingSignBlock( properties, woodType ) );
        wallSign = AQBlocks.REGISTRY.register( name + "_wall_sign", () -> new WallSignBlock( properties, woodType ) );
        hangingSign = AQBlocks.REGISTRY.register( name + "_hanging_sign", () -> new CeilingHangingSignBlock( properties, woodType ) );
        wallHangingSign = AQBlocks.REGISTRY.register( name + "_wall_hanging_sign", () -> new WallHangingSignBlock( properties, woodType ) );
        
        // Block items
        AQItems.registerItem( name + "_sign", AQCreativeTabs.Keys.DECORATION, () -> new SignItem( new Item.Properties().stacksTo( 16 ), sign.get(), wallSign.get() ) );
        AQItems.registerItem( name + "_hanging_sign", AQCreativeTabs.Keys.DECORATION, () -> new HangingSignItem( hangingSign.get(), wallHangingSign.get(), new Item.Properties().stacksTo( 16 ) ) );
        boat = AQItems.registerItem( name + "_boat", AQCreativeTabs.Keys.ITEMS, () -> new AQBoatItem( false, AQBoat.BoatType.AHUEHUETE, new Item.Properties().stacksTo( 16 ) ) );
        chestBoat = AQItems.registerItem( name + "_chest_boat", AQCreativeTabs.Keys.ITEMS, () -> new AQBoatItem( true, AQBoat.BoatType.AHUEHUETE, new Item.Properties().stacksTo( 16 ) ) );
        
        // Collect all blocks
        allBlocks = List.of(
                sapling, leaves, wood,
                strippedWood, log, strippedLog,
                planks, slab, verticalSlab,
                stairs, fence, fenceGate,
                pressurePlate, button,
                sign, wallSign,
                hangingSign, wallHangingSign,
                trapDoor, door
        );
        WOOD_SETS.add( this );
        SIGNS.add( sign );
        SIGNS.add( wallSign );
    }
    
    private static <T extends Block> RegistryObject<T> register( String name, Supplier<T> block, boolean decorative ) {
        RegistryObject<T> regObj = AQBlocks.REGISTRY.register( name, block );
        AQItems.registerItem( name, decorative ? AQCreativeTabs.Keys.DECORATION : AQCreativeTabs.Keys.BLOCKS, () -> new BlockItem( regObj.get(), new Item.Properties() ) );
        return regObj;
    }
    
    private static RegistryObject<DoorBlock> registerDoor( String name, BlockSetType blockSet, BlockBehaviour.Properties properties ) {
        RegistryObject<DoorBlock> regObj = AQBlocks.REGISTRY.register( name, () -> new DoorBlock( properties, blockSet ) );
        AQItems.registerItem( name, AQCreativeTabs.Keys.DECORATION, () -> new DoubleHighBlockItem( regObj.get(), new Item.Properties().stacksTo( 16 ) ) );
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
    
    public RegistryObject<ButtonBlock> getButton() {
        return button;
    }
    
    public RegistryObject<SignBlock> getSign() {
        return sign;
    }
    
    public RegistryObject<WallSignBlock> getWallSign() {
        return wallSign;
    }
    
    public RegistryObject<CeilingHangingSignBlock> getHangingSign() {
        return hangingSign;
    }
    
    public RegistryObject<WallHangingSignBlock> getWallHangingSign() {
        return wallHangingSign;
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
    
    
    /** @return An iterable of all the block registry object held by this wood set. */
    public Iterable<RegistryObject<? extends Block>> allBlocks() {
        return allBlocks;
    }
    
    
    /** @return This wood set's {@link BlockSetType}. */
    public BlockSetType getBlockSetType() {
        return blockSetType;
    }
    
    /** @return This wood set's {@link WoodType}. */
    public WoodType getWoodType() {
        return woodType;
    }
}
