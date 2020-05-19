package com.cannolicatfish.rankine.items.alloys;

import com.cannolicatfish.rankine.ProjectRankine;
import com.cannolicatfish.rankine.items.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistry;

import javax.annotation.Nullable;
import javax.xml.soap.Text;
import java.util.List;

public class AlloyItem extends Item {
    String registryName;
    public AlloyItem(String registryName) {
        super(new Item.Properties().maxStackSize(64));
        setRegistryName(ProjectRankine.MODID, registryName);
        this.registryName = registryName;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (getComposition(stack).size() != 0)
        {
            tooltip.add((new StringTextComponent(getComposition(stack).get(0).toString())).applyTextStyle(TextFormatting.GRAY));
        }
    }

    public static ListNBT getComposition(ItemStack stack) {
        CompoundNBT compoundnbt = stack.getTag();
        return compoundnbt != null ? compoundnbt.getList("StoredComposition", 10) : new ListNBT();
    }

    public static void addAlloy(ItemStack p_92115_0_, AlloyData stack) {
        ListNBT listnbt = getComposition(p_92115_0_);
        boolean flag = true;


        if (flag) {
            CompoundNBT compoundnbt1 = new CompoundNBT();
            compoundnbt1.putString("comp", stack.alloyComposition);
            listnbt.add(compoundnbt1);
        }

        p_92115_0_.getOrCreateTag().put("StoredComposition", listnbt);
    }

    /**
     * Returns the ItemStack of an enchanted version of this item.
     */
    public static ItemStack getAlloyItemStack(AlloyData p_92111_0_) {
        ItemStack itemstack = new ItemStack(ModItems.BRONZE_ALLOY);
        addAlloy(itemstack, p_92111_0_);
        return itemstack;
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */

    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (group == ItemGroup.SEARCH || group == ProjectRankine.setup.itemGroup) {
            items.add(getAlloyItemStack(new AlloyData("80Cu-20Sn")));
        }
    }


}