package com.obsidian_core.archaic_quest.common.worldgen.feature.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.obsidian_core.archaic_quest.common.core.register.AQTreeDecoratorType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

/**
 * Places hanging trails of custom vines on leaf blocks. Vanilla vine block does not work with this decorator.
 * <br><br>
 * Unlike {@link net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator}, this
 * decorator provides configurable placement probability as well as configurable vine block state,
 * minimum vine length and maximum vine length.
 * <br><br>
 * The only requirement for a block state to qualify as valid "vines" is having the
 * {@link net.minecraft.world.level.block.state.properties.BlockStateProperties#HORIZONTAL_FACING} property.
 */
public class CustomLeavesVineDecorator extends TreeDecorator {
    
    
    public static final Codec<CustomLeavesVineDecorator> CODEC = RecordCodecBuilder.create( inst ->
            inst.group(
                    Codec.floatRange( 0.0F, 1.0F )
                            .fieldOf( "probability" )
                            .forGetter( decorator -> decorator.probability ),
                    UniformInt.codec( 0, 100 )
                            .fieldOf( "length_provider" )
                            .forGetter( decorator -> decorator.lengthProvider ),
                    BlockState.CODEC
                            .fieldOf( "vine" )
                            .forGetter( decorator -> decorator.vine )
            ).apply( inst, CustomLeavesVineDecorator::new )
    );
    
    private final float probability;
    private final BlockState vine;
    private final IntProvider lengthProvider;
    
    
    public CustomLeavesVineDecorator( float probability, IntProvider lengthProvider, BlockState vine ) {
        this.probability = probability;
        this.lengthProvider = lengthProvider;
        this.vine = vine;
        
        // Horizontal facing property is required.
        if( !vine.hasProperty( BlockStateProperties.HORIZONTAL_FACING ) ) {
            throw new IllegalArgumentException( "Vine BlockState must have the horizontal facing property to be used in this decorator!" );
        }
    }
    
    
    @Override
    protected TreeDecoratorType<?> type() {
        return AQTreeDecoratorType.CUSTOM_LEAVES_VINES.get();
    }
    
    @Override
    public void place( TreeDecorator.Context context ) {
        RandomSource random = context.random();
        
        context.leaves().forEach( ( pos ) -> {
            for( Direction dir : Direction.Plane.HORIZONTAL ) {
                if( random.nextFloat() < probability ) {
                    
                    BlockPos offsetPos = pos.relative( dir );
                    if( context.isAir( offsetPos ) ) {
                        addHangingVine( offsetPos, dir, context );
                    }
                }
            }
        } );
    }
    
    private void addHangingVine( BlockPos pos, Direction direction, TreeDecorator.Context context ) {
        DecoratorUtil.placeCustomVine( context, vine, pos, direction );
        int length = lengthProvider.sample( context.random() );
        
        for( BlockPos placePos = pos.below(); context.isAir( placePos ) && length > 0; --length ) {
            DecoratorUtil.placeCustomVine( context, vine, placePos, direction );
            placePos = placePos.below();
        }
    }
}
