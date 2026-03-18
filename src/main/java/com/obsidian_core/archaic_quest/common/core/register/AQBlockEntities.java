package com.obsidian_core.archaic_quest.common.core.register;

import com.obsidian_core.archaic_quest.common.blockentity.*;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Objects;

@SuppressWarnings( "ConstantConditions" )
public class AQBlockEntities {
    
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create( ForgeRegistries.BLOCK_ENTITY_TYPES, ArchaicQuest.MODID );
    
    
    public static final RegistryObject<BlockEntityType<SimpleSkullBlockEntity>> SIMPLE_SKULL = register( "simple_skull", SimpleSkullBlockEntity::new, List.of(
            AQBlocks.CRYSTAL_SKULL.getFirst(),
            AQBlocks.CRYSTAL_SKULL.getSecond(),
            AQBlocks.STONE_SKULL.getFirst(),
            AQBlocks.STONE_SKULL.getSecond(),
            AQBlocks.JAGUAR_SKULL.getFirst(),
            AQBlocks.JAGUAR_SKULL.getSecond(),
            AQBlocks.OLD_SKULL.getFirst(),
            AQBlocks.OLD_SKULL.getSecond()
    ) );
    public static final RegistryObject<BlockEntityType<VaseBlockEntity>> VASE = register( "vase", VaseBlockEntity::new, List.of(
            AQBlocks.AZTEC_VASE
    ) );
    public static final RegistryObject<BlockEntityType<AztecPoisonTrapBlockEntity>> POISON_TRAP = register( "aztec_poison_trap", AztecPoisonTrapBlockEntity::new, List.of(
            AQBlocks.AZTEC_POISON_TRAP
    ) );
    public static final RegistryObject<BlockEntityType<SpikeTrapBlockEntity>> SPIKE_TRAP = register( "spike_trap", SpikeTrapBlockEntity::new, List.of(
            AQBlocks.AZTEC_ANDESITE_SPIKE_TRAP
    ) );
    public static final RegistryObject<BlockEntityType<AztecWorktableBlockEntity>> AZTEC_CRAFTING_STATION = register( "aztec_crafting_station", AztecWorktableBlockEntity::new, List.of(
            AQBlocks.AZTEC_WORKTABLE
    ) );
    public static final RegistryObject<BlockEntityType<AztecDungeonDoorBlockEntity>> AZTEC_DUNGEON_DOOR = register( "aztec_dungeon_door", AztecDungeonDoorBlockEntity::new, List.of(
            AQBlocks.AZTEC_DUNGEON_DOOR_0,
            AQBlocks.AZTEC_DUNGEON_DOOR_1,
            AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_0,
            AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_1
    ) );
    public static final RegistryObject<BlockEntityType<AztecThroneBlockEntity>> AZTEC_THRONE = register( "aztec_throne", AztecThroneBlockEntity::new, List.of(
            AQBlocks.AZTEC_THRONE,
            AQBlocks.MOSSY_AZTEC_THRONE
    ) );
    public static final RegistryObject<BlockEntityType<AztecDungeonChestBlockEntity>> AZTEC_DUNGEON_CHEST = register( "aztec_dungeon_chest", AztecDungeonChestBlockEntity::new, List.of(
            AQBlocks.AZTEC_DUNGEON_CHEST
    ) );
    public static final RegistryObject<BlockEntityType<FoundryBlockEntity>> FOUNDRY = register( "foundry", FoundryBlockEntity::new, List.of( AQBlocks.FOUNDRY ) );
    
    
    @SuppressWarnings( "ConstantConditions" )
    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register( String name, BlockEntityType.BlockEntitySupplier<T> supplier, List<RegistryObject<? extends Block>> block ) {
        return REGISTRY.register( name, () -> BlockEntityType.Builder.of( supplier, toBlockArray( block ) ).build( null ) );
    }
    
    /** Convenience method for returning a list of block registry objects as an array of blocks. */
    private static Block[] toBlockArray( List<RegistryObject<? extends Block>> blocks ) {
        // Sanity checks
        Objects.requireNonNull( blocks );
        if( blocks.isEmpty() ) {
            throw new IllegalArgumentException( "Attempted to convert empty list of block registry objects into block array! Boo." );
        }
        // Collect in array and return
        Block[] blockArray = new Block[blocks.size()];
        for( int i = 0; i < blocks.size(); i++ ) {
            blockArray[i] = blocks.get( i ).get();
        }
        return blockArray;
    }
}
