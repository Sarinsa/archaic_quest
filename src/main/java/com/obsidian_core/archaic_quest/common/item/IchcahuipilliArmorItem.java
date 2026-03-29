package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.client.ClientRegister;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class IchcahuipilliArmorItem extends ArmorItem {
    
    private static final String ARMOR_TEXTURE = ArchaicQuest.rl( "textures/model/armor/ichcahuipilli_armor.png" ).toString();
    
    
    public IchcahuipilliArmorItem( ArmorMaterial armorMaterial, ArmorItem.Type type ) {
        super( armorMaterial, type, new Item.Properties()
                .stacksTo( 1 ) );
    }
    
    @Override
    @Nullable
    public String getArmorTexture( ItemStack stack, Entity entity, EquipmentSlot slot, String type ) {
        return ARMOR_TEXTURE;
    }
    
    @Override
    public void initializeClient( Consumer<IClientItemExtensions> consumer ) {
        consumer.accept( new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel( LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original ) {
                return equipmentSlot.getType() == EquipmentSlot.Type.ARMOR ? ClientRegister.ICHCAHUIPILLI_ARMOR_MODELS.get( equipmentSlot ) : original;
            }
        } );
    }
}
