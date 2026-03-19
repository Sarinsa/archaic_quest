package com.obsidian_core.archaic_quest.client.screen;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.inventory.menu.KnappingTableMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class KnappingTableScreen extends AbstractContainerScreen<KnappingTableMenu> {
    
    private static final ResourceLocation texture = ArchaicQuest.rl( "textures/gui/knapping_table.png" );
    
    public KnappingTableScreen( KnappingTableMenu container, Inventory inventory, Component title ) {
        super( container, inventory, title );
    }
    
    @Override
    public void init() {
        super.init();
        imageWidth = 176;
        imageHeight = 166;
        titleLabelX = (imageWidth - font.width( title )) / 2;
    }
    
    @Override
    public void render( GuiGraphics poseStack, int mouseX, int mouseY, float partialTick ) {
        renderBackground( poseStack );
        renderBg( poseStack, partialTick, mouseX, mouseY );
        super.render( poseStack, mouseX, mouseY, partialTick );
        renderTooltip( poseStack, mouseX, mouseY );
    }
    
    @Override
    protected void renderBg( GuiGraphics graphics, float partialTick, int mouseX, int mouseY ) {
        graphics.blit( texture, leftPos, topPos, 0, 0, imageWidth, imageHeight );
    }
}
