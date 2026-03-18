package com.obsidian_core.archaic_quest.common.worldgen.feature.decorators;

import com.mojang.serialization.Codec;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.core.register.AQTreeDecoratorType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class TrunkVineVarDecorator extends TreeDecorator {
    
    public static final Codec<TrunkVineVarDecorator> CODEC = Codec.unit( () -> TrunkVineVarDecorator.INSTANCE );
    public static final TrunkVineVarDecorator INSTANCE = new TrunkVineVarDecorator();
    
    public TrunkVineVarDecorator() { }
    
    @Override
    protected TreeDecoratorType<?> type() {
        return AQTreeDecoratorType.TRUNK_VINE.get();
    }
    
    @Override
    public void place( TreeDecorator.Context context ) {
        RandomSource random = context.random();
        
        context.logs().forEach( ( pos ) -> {
            for( Direction dir : Direction.Plane.HORIZONTAL ) {
                if( random.nextInt( 3 ) > 0 ) {
                    BlockPos offsetPos = pos.relative( dir );
                    
                    if( context.isAir( offsetPos ) ) {
                        placeVineVar( context, offsetPos, dir );
                    }
                }
            }
        } );
    }
    
    private void placeVineVar( TreeDecorator.Context context, BlockPos pos, Direction facing ) {
        context.setBlock( pos, AQBlocks.VINES_1.get().defaultBlockState().setValue( BlockStateProperties.HORIZONTAL_FACING, facing ) );
    }
}
