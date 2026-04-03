package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.item.misc.IMacheteCuttable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;


public class MacheteItem extends SimpleWeaponItem {
    
    public MacheteItem( Tier itemTier, int durability, int damage, float attackSpeed ) {
        super( itemTier, durability, damage, attackSpeed );
    }
    
    @Override
    public InteractionResult useOn( UseOnContext context ) {
        final Level level = context.getLevel();
        final BlockState state = level.getBlockState( context.getClickedPos() );
        
        if( state.getBlock() instanceof IMacheteCuttable macheteCuttable ) {
            return macheteCuttable.onCut( context ) ? InteractionResult.sidedSuccess( level.isClientSide ) : InteractionResult.FAIL;
        }
        return InteractionResult.FAIL;
    }
}
