package com.obsidian_core.archaic_quest.common.block.skull;

import com.obsidian_core.archaic_quest.common.blockentity.SimpleSkullBlockEntity;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

@SuppressWarnings( "deprecation" )
public abstract class SimpleAbstractSkullBlock extends Block implements Equipable, EntityBlock {
    
    private final ResourceLocation texture;
    private final boolean isAnimal;
    
    public SimpleAbstractSkullBlock( Properties properties, boolean animal, String textureName ) {
        super( properties );
        texture = ArchaicQuest.rl( "textures/tile/skull/" + textureName + "_skull.png" );
        isAnimal = animal;
    }
    
    public ResourceLocation getTexture() {
        return texture;
    }
    
    public boolean isAnimal() {
        return isAnimal;
    }
    
    @Override
    public VoxelShape getOcclusionShape( BlockState state, BlockGetter level, BlockPos pos ) {
        return Shapes.empty();
    }
    
    
    @Override
    public boolean isPathfindable( BlockState state, BlockGetter level, BlockPos pos, PathComputationType pathType ) {
        return false;
    }
    
    @Nullable
    @Override
    public BlockEntity newBlockEntity( BlockPos pos, BlockState state ) {
        return new SimpleSkullBlockEntity( pos, state );
    }
    
    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }
}
