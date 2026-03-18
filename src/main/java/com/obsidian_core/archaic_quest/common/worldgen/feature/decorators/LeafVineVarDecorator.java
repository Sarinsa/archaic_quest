package com.obsidian_core.archaic_quest.common.worldgen.feature.decorators;

import com.mojang.serialization.Codec;
import com.obsidian_core.archaic_quest.common.core.register.AQTreeDecoratorType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class LeafVineVarDecorator extends TreeDecorator {
    
    public static final Codec<LeafVineVarDecorator> CODEC = Codec.floatRange( 0.0F, 1.0F )
            .fieldOf( "probability" )
            .xmap( LeafVineVarDecorator::new, ( decorator ) -> decorator.probability )
            .codec();
    
    private final float probability;
    
    @Override
    protected TreeDecoratorType<?> type() {
        return AQTreeDecoratorType.LEAF_VINE.get();
    }
    
    public LeafVineVarDecorator( float probability ) {
        this.probability = probability;
    }
    
    @Override
    public void place( TreeDecorator.Context context ) {
        RandomSource random = context.random();
        
        context.leaves().forEach( ( pos ) -> {
            for( Direction dir : Direction.Plane.HORIZONTAL ) {
                if( random.nextFloat() < probability ) {
                    
                    BlockPos offsetPos = pos.relative( dir );
                    if( context.isAir( offsetPos ) ) {
                        addHangingVine( offsetPos, Direction.WEST, context );
                    }
                }
            }
        } );
    }
    
    private static void addHangingVine( BlockPos pos, Direction direction, TreeDecorator.Context context ) {
        DecoratorUtil.placeVineVar( context, pos, direction );
        int i = 4;
        
        for( BlockPos blockpos = pos.below(); context.isAir( blockpos ) && i > 0; --i ) {
            DecoratorUtil.placeVineVar( context, blockpos, direction );
            blockpos = blockpos.below();
        }
    }
}
