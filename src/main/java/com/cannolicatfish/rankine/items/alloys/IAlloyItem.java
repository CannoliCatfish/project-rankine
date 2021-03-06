package com.cannolicatfish.rankine.items.alloys;

import com.cannolicatfish.rankine.init.RankineRecipeTypes;
import com.cannolicatfish.rankine.recipe.AlloyingRecipe;
import com.cannolicatfish.rankine.recipe.ElementRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface IAlloyItem {

    default void createAlloyNBT(ItemStack stack, World worldIn, String composition, @Nullable ResourceLocation alloyRecipe, @Nullable String nameOverride) {
        ListNBT alloyData = getAlloyNBT(stack);

        CompoundNBT listnbt = new CompoundNBT();

        listnbt.putString("comp",composition);
        if (alloyRecipe != null) {
            listnbt.putString("recipe",alloyRecipe.toString());
        }
        alloyData.add(listnbt);
        stack.getOrCreateTag().put("StoredAlloy", listnbt);

        if (nameOverride != null && stack.getTag() != null) {
            stack.getTag().putString("nameAdd",nameOverride);
        }
    }

    default ListNBT getAlloyNBT(ItemStack stack) {
        CompoundNBT compoundnbt = stack.getTag();
        return compoundnbt != null ? compoundnbt.getList("StoredAlloy", 10) : new ListNBT();
    }

    default boolean isAlloyInit(ItemStack stack) {

        return stack.getTag() != null && !stack.getTag().getCompound("StoredAlloy").isEmpty();
    }

    default String getAlloyComposition(ItemStack stack)
    {
        if (stack.getTag() != null) {
            return stack.getTag().getCompound("StoredAlloy").getString("comp");
        } else {
            return "80Hg-20Au";
        }
    }

    default ResourceLocation getAlloyRecipe(ItemStack stack)
    {
        if (stack.getTag() != null && !stack.getTag().getCompound("StoredAlloy").getString("recipe").isEmpty()) {
            return new ResourceLocation(stack.getTag().getCompound("StoredAlloy").getString("recipe"));
        } else {
            return null;
        }
    }

    default List<ElementRecipe> getElementRecipes(String c, @Nullable World worldIn) {
        if (worldIn != null) {
            String[] comp = c.split("-");
            List<ElementRecipe> list = new ArrayList<>();
            for (String e: comp)
            {
                String str = e.replaceAll("[^A-Za-z]+", "");
                worldIn.getRecipeManager().getRecipesForType(RankineRecipeTypes.ELEMENT).stream().filter(elementRecipe -> elementRecipe.getSymbol().equals(str)).findFirst().ifPresent(list::add);
            }
            return list;
        } else {
            return Collections.emptyList();
        }

    }

    default List<Integer> getPercents(String c)
    {
        String[] comp = c.split("-");
        List<Integer> list = new ArrayList<>();
        for (String e: comp)
        {
            String str = e.replaceAll("\\D+", "");
            list.add(Integer.parseInt(str));
        }
        return list;
    }

    default ItemStack createAlloyItemStack(Item item, World worldIn, String composition, @Nullable ResourceLocation alloyRecipe, @Nullable String nameOverride) {
        ItemStack itemstack = new ItemStack(item);
        this.createAlloyNBT(itemstack,worldIn,composition,alloyRecipe,nameOverride);
        return itemstack;
    }
}
