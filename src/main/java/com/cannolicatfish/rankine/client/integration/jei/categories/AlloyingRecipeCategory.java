package com.cannolicatfish.rankine.client.integration.jei.categories;

import com.cannolicatfish.rankine.ProjectRankine;
import com.cannolicatfish.rankine.init.RankineBlocks;
import com.cannolicatfish.rankine.recipe.AlloyingRecipe;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.ITooltipCallback;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AlloyingRecipeCategory implements IRecipeCategory<AlloyingRecipe> {

    public static ResourceLocation UID = new ResourceLocation(ProjectRankine.MODID, "alloying");
    private final IDrawable background;
    private final String localizedName;
    private final IDrawable overlay;
    private final IDrawable icon;

    public AlloyingRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createBlankDrawable(145, 140);
        localizedName = I18n.format("rankine.jei.alloy");
        overlay = guiHelper.createDrawable(new ResourceLocation(ProjectRankine.MODID, "textures/gui/alloying_jei.png"),
                0, 15, 140, 110);
        icon = guiHelper.createDrawableIngredient(new ItemStack(RankineBlocks.ALLOY_FURNACE.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends AlloyingRecipe> getRecipeClass() {
        return AlloyingRecipe.class;
    }

    @Override
    public String getTitle() {
        return localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void draw(AlloyingRecipe recipe, MatrixStack ms, double mouseX, double mouseY) {
        FontRenderer font = Minecraft.getInstance().fontRenderer;
        RenderSystem.enableAlphaTest();
        RenderSystem.enableBlend();
        overlay.draw(ms, 0, 4);
        RenderSystem.disableBlend();
        RenderSystem.disableAlphaTest();
        String s = "Made in:";
        int tier = recipe.getTier();

        int ymod = 0;
        font.drawString(ms, s, (float)(90 - font.getStringWidth(s) / 2), ymod, 0x000000);
        if ((tier & 1) != 0) {
            ymod += 10;
            s = "Alloy Furnace";
            font.drawString(ms, s, (float)(90 - font.getStringWidth(s) / 2), ymod, 0x000000);
        }
        if ((tier & 2) != 0) {
            ymod += 10;
            s = "Induction Furnace";
            font.drawString(ms, s, (float)(90 - font.getStringWidth(s) / 2), ymod, 0x000000);
        }
    }

    @Override
    public void setIngredients(AlloyingRecipe recipe, IIngredients iIngredients) {
        ImmutableList.Builder<List<ItemStack>> builder = ImmutableList.builder();
        for (Ingredient i : recipe.getIngredients()) {
            builder.add(Arrays.asList(i.getMatchingStacks()));
        }
        iIngredients.setInputLists(VanillaTypes.ITEM, builder.build());
        iIngredients.setOutputs(VanillaTypes.ITEM, Collections.singletonList(recipe.getRecipeOutput()));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, AlloyingRecipe recipe, IIngredients ingredients) {
        int index = 0;
        List<Integer> reqIndex = recipe.getIndexList(true);
        List<Integer> nonreqIndex = recipe.getIndexList(false);
        for (List<ItemStack> o : ingredients.getInputs(VanillaTypes.ITEM)) {
            if (index < 2)
            {
                recipeLayout.getItemStacks().init(index, true, 33 + index * 64, 39);
            } else {
                if (index < 10)
                {
                    recipeLayout.getItemStacks().init(index, true, (index - 2) * 18, 64);
                } else if (index < 18) {
                    recipeLayout.getItemStacks().init(index, true, (index - 10) * 18, 80);
                } else
                {
                    recipeLayout.getItemStacks().init(index, true, (index - 18) * 18, 96);
                }
            }
            recipeLayout.getItemStacks().set(index, o);

            index++;

        }

        int endIndex = index;
        recipeLayout.getItemStacks().addTooltipCallback((i, b, stack, list) -> {
            if (i != endIndex) {
                list.add(new StringTextComponent("Min: " + Math.round(recipe.getMins().get(i) * 100) + "%"));
                list.add(new StringTextComponent("Max: " + Math.round(recipe.getMaxes().get(i) * 100) + "%"));
            }

        });

        for (int i = 0; i < ingredients.getOutputs(VanillaTypes.ITEM).size(); i++) {
            List<ItemStack> stacks = ingredients.getOutputs(VanillaTypes.ITEM).get(i);
            recipeLayout.getItemStacks().init(index + i, false, 19, 10);
            recipeLayout.getItemStacks().set(index + i, stacks);
        }
        ResourceLocation recipeId = recipe.getId();
        recipeLayout.getItemStacks().addTooltipCallback((slotIndex, input, ingredient, tooltip) -> {
            if (slotIndex >= endIndex) {
                if (Minecraft.getInstance().gameSettings.advancedItemTooltips || Screen.hasShiftDown()) {
                    tooltip.add(new TranslationTextComponent("jei.tooltip.recipe.id", recipeId).mergeStyle(TextFormatting.DARK_GRAY));
                }
            }
        });
    }
}