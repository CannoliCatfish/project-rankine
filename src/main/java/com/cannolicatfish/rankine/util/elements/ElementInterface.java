package com.cannolicatfish.rankine.util.elements;

import com.cannolicatfish.rankine.util.PeriodicTableUtils;
import net.minecraft.enchantment.Enchantment;
public interface ElementInterface {

    PeriodicTableUtils.Element getReference();

    int getDurabilityFromPercent(int x);

    float getDamageFromPercent(int x);

    float getAttackSpeedFromPercent(int x);

    float getMiningSpeedFromPercent(int x);

    int getMiningLevelFromPercent(int x);

    int getEnchantabilityFromPercent(int x);

    float getCorrResistFromPercent(int x);

    float getHeatResistFromPercent(int x);

    float getToughnessFromPercent(int x);

    float getElectrodePotentialFromPercent(int x);

    Enchantment getEnchantments(int x);


}
