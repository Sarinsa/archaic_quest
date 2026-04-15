package com.obsidian_core.archaic_quest.datagen.blockstate;

import com.obsidian_core.archaic_quest.common.block.*;
import com.obsidian_core.archaic_quest.common.block.base.BaseDoubleCropBlock;
import com.obsidian_core.archaic_quest.common.block.multiblock.AztecDungeonDoorBlock;
import com.obsidian_core.archaic_quest.common.block.multiblock.AztecThroneBlock;
import com.obsidian_core.archaic_quest.common.block.multiblock.AztecWorktableBlock;
import com.obsidian_core.archaic_quest.common.core.register.util.WoodSet;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import static com.obsidian_core.archaic_quest.common.core.register.AQBlocks.*;


public class AQBlockStateProvider extends AbstractBlockStateProvider {
    
    public AQBlockStateProvider( PackOutput packOutput, ExistingFileHelper exFileHelper ) {
        super( packOutput, exFileHelper );
    }
    
    @Override
    protected void registerStatesAndModels() {
        // Simple full cube blocks
        SIMPLE_BLOCKS.forEach( ( block ) -> simpleBlockAndItem( block.get() ) );
        
        // Slab variants and stairs
        VERT_SLAB_VARIANTS.forEach( ( block, vertSlab ) -> simpleVerticalSlab( vertSlab.get(), block.get() ) );
        SLAB_VARIANTS.forEach( ( block, slab ) -> slab( slab.get(), block.get() ) );
        STAIRS_VARIANTS.forEach( ( block, stairs ) -> {
            stairsBlock( stairs.get(), blockTexture( block.get() ) );
            ModelFile model = models().withExistingParent( name( stairs.get() ), mcLoc( "block/stairs" ) );
            simpleBlockItem( stairs.get(), model );
        } );
        // Wood sets
        WoodSet.WOOD_SETS.forEach( this::woodSet );
        
        // Special blocks
        for( RegistryObject<Block> regObject : REGISTRY.getEntries() ) {
            Block block = regObject.get();
            
            if( block instanceof BaseDoubleCropBlock doubleCropBlock ) {
                doubleCrop( doubleCropBlock );
            }
            else if( block instanceof CuttableVinesBlock vine ) {
                cuttableVine( vine );
            }
            else if( block instanceof AztecDungeonDoorBlock ) {
                blockNoModel( block, blockTexture( ANDESITE_AZTEC_BRICKS_16.get() ) );
            }
            else if( block instanceof AztecThroneBlock ) {
                blockNoModel( block, blockTexture( ANDESITE_AZTEC_BRICKS_16.get() ) );
            }
            else if( block instanceof AztecDungeonChestBlock ) {
                blockNoModel( block, blockTexture( ANDESITE_AZTEC_BRICKS_16.get() ) );
            }
            else if( block instanceof AztecWorktableBlock ) {
                blockNoModel( block, blockTexture( ANDESITE_AZTEC_BRICKS_16.get() ) );
            }
            else if( block instanceof SpearTrapBlock spearTrap ) {
                spearTrap( spearTrap );
            }
            else if( block instanceof AztecWoodPillarBlock woodPillar ) {
                woodPillar( woodPillar );
            }
            else if( block instanceof SkullGobletBlock skullGoblet ) {
                skullGoblet( skullGoblet );
            }
        }
    }
}
