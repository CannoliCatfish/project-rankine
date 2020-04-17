package com.cannolicatfish.rankine.blocks.alloyfurnace;

import com.cannolicatfish.rankine.ProjectRankine;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AlloyFurnaceScreen extends ContainerScreen<AlloyFurnaceContainer> {
    private ResourceLocation GUI = new ResourceLocation(ProjectRankine.MODID, "textures/gui/alloy_furnace.png");
    public AlloyFurnaceScreen(AlloyFurnaceContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        drawCenteredString(Minecraft.getInstance().fontRenderer, "Alloy Furnace", 92, 10, 0xffffff);
        drawCenteredString(Minecraft.getInstance().fontRenderer, this.container.getPercentSlot1() + "%", 42, 50, 0xffffff);
        drawCenteredString(Minecraft.getInstance().fontRenderer, this.container.getPercentSlot2() +"%", 64, 50, 0xffffff);
        drawCenteredString(Minecraft.getInstance().fontRenderer, this.container.getPercentSlot3() + "%", 86, 50, 0xffffff);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);

        if(this.container.isBurning())
        {

            int k = this.container.getBurnLeftScaled(13);
            this.blit(this.guiLeft + 11, this.guiTop + 21 + 12 - k, 176, 12 - k, 14, k + 1);

        }

        int l = this.container.getCookProgressScaled(24);
        this.blit(this.guiLeft + 98, this.guiTop + 32, 176, 14, l + 1, 16);
    }



}

