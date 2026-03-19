package com.obsidian_core.archaic_quest.common.worldgen.feature.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.obsidian_core.archaic_quest.common.core.register.AQTreeDecoratorType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

/**
 * Places custom vines on logs/trunks. Vanilla vine block does not work with this decorator.
 * <br><br>
 * Unlike {@link net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator}, this
 * decorator provides configurable placement probability as well as configurable vine block state.
 * The only requirement for a block state to qualify as "vines" is having the
 * {@link net.minecraft.world.level.block.state.properties.BlockStateProperties#HORIZONTAL_FACING} property.
 */
public class CustomTrunkVineDecorator extends TreeDecorator {
    
    
    public static final Codec<CustomTrunkVineDecorator> CODEC = RecordCodecBuilder.create( inst ->
            inst.group(
                    Codec.floatRange( 0.0F, 1.0F )
                            .fieldOf( "probability" )
                            .forGetter( decorator -> decorator.probability ),
                    BlockState.CODEC
                            .fieldOf( "vine" )
                            .forGetter( decorator -> decorator.vine )
            ).apply( inst, CustomTrunkVineDecorator::new )
    );
    
    private final float probability;
    private final BlockState vine;
    
    
    public CustomTrunkVineDecorator( float probability, BlockState vine ) {
        this.probability = probability;
        this.vine = vine;
    }
    
    
    @Override
    protected TreeDecoratorType<?> type() {
        return AQTreeDecoratorType.CUSTOM_TRUNK_VINES.get();
    }
    
    @Override
    public void place( TreeDecorator.Context context ) {
        RandomSource random = context.random();
        
        context.logs().forEach( ( pos ) -> {
            for( Direction dir : Direction.Plane.HORIZONTAL ) {
                if( random.nextFloat() <= probability ) {
                    BlockPos offsetPos = pos.relative( dir );
                    
                    if( context.isAir( offsetPos ) ) {
                        DecoratorUtil.placeCustomVine( context, vine, offsetPos, dir );
                    }
                }
            }
        } );
    }
}
