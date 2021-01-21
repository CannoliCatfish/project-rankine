package com.cannolicatfish.rankine.util.alloys;

import com.cannolicatfish.rankine.init.ModEnchantments;
import com.cannolicatfish.rankine.items.alloys.*;
import com.cannolicatfish.rankine.items.tools.RankineToolMaterials;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;

public class BlackGoldAlloyUtils implements AlloyUtils {
    public RankineToolMaterials material = RankineToolMaterials.BLACK_GOLD;

    @Override
    public int getDurabilityBonus() {
        return 32;
    }

    @Override
    public float getMiningSpeedBonus() {
        return 0;
    }

    @Override
    public float getAttackDamageBonus() {
        return 1;
    }

    @Override
    public int getMiningLevelBonus() {
        return 0;
    }

    @Override
    public int getEnchantabilityBonus() {
        return 0;
    }

    @Override
    public float getCorrResistBonus() {
        return 0;
    }

    @Override
    public float getHeatResistBonus() {
        return 0.2f;
    }

    @Override
    public float getToughnessBonus() {
        return -0.05f;
    }

    @Override
    public Enchantment getEnchantmentBonus(Item item) {
        if (item instanceof AlloyHoe)
        {
            return ModEnchantments.FORAGING;
        } else if (item instanceof AlloySword || item instanceof AlloyAxe)
        {
            return Enchantments.BANE_OF_ARTHROPODS;
        } else if (item instanceof AlloySpear) {
            return ModEnchantments.IMPACT;
        } else if (item instanceof AlloyHammer) {
            return ModEnchantments.EXCAVATE;
        } else {
            return ModEnchantments.QUAKE;
        }

    }

    @Override
    public int getEnchantmentLevel(Enchantment en, int enchantability) {
        if (enchantability >= 25 && en.getMaxLevel() >= 2)
        {
            return 2;
        }
        return 1;
    }

    @Override
    public String getDefComposition() {
        return "75Au-25Co";
    }

    @Override
    public TextFormatting getAlloyGroupColor() {
        return TextFormatting.YELLOW;
    }
}
