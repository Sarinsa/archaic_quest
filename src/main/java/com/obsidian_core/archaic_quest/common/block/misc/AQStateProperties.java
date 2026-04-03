package com.obsidian_core.archaic_quest.common.block.misc;

import com.obsidian_core.archaic_quest.common.block.SpikeTrapBlock;
import com.obsidian_core.archaic_quest.common.block.base.VerticalSlabBlock;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class AQStateProperties {
    
    public static final BooleanProperty MASTER = BooleanProperty.create( "master" );
    public static final BooleanProperty EXTENDED = BooleanProperty.create( "extended" );
    public static final BooleanProperty IS_TOP = BooleanProperty.create( "top" );
    public static final BooleanProperty FILLED = BooleanProperty.create( "filled" );
    
    public static final IntegerProperty ROTATION_8 = IntegerProperty.create( "rotation", 0, 7 );
    
    public static final EnumProperty<SpikeTrapBlock.SpikeMode> SPIKE_MODE = EnumProperty.create( "spike_mode", SpikeTrapBlock.SpikeMode.class );
    public static final EnumProperty<VerticalSlabBlock.SlabState> VERTICAL_SLAB_STATE = EnumProperty.create( "type", VerticalSlabBlock.SlabState.class );
}
