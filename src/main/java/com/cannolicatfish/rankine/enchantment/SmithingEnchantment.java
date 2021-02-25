package com.cannolicatfish.rankine.enchantment;

import com.cannolicatfish.rankine.items.tools.HammerItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class SmithingEnchantment extends Enchantment {
    public SmithingEnchantment(Enchantment.Rarity p_i46721_1_, EquipmentSlotType... p_i46721_2_) {
        super(p_i46721_1_, EnchantmentType.create("hammer", (itemIn) -> {
            return itemIn instanceof HammerItem; }), p_i46721_2_);
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 1 + 10 * (enchantmentLevel - 1);
    }

    public int getMaxEnchantability(int p_223551_1_) {
        return super.getMinEnchantability(p_223551_1_) + 50;
    }

    public int getMaxLevel() {
        return 5;
    }

}