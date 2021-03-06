package com.cannolicatfish.rankine.util.elements;

import com.cannolicatfish.rankine.util.PeriodicTableUtils;
import net.minecraft.enchantment.Enchantment;

public class BariumElement  implements ElementInterface {
    @Override
    public PeriodicTableUtils.Element getReference() {
        return PeriodicTableUtils.Element.BARIUM;
    }

    @Override
    public int getDurabilityFromPercent(int x) {
        return x - 50;
    }

    @Override
    public float getDamageFromPercent(int x) {
        if (x <= 10)
        {
            return x/10f;
        } else {
            return 1f;
        }
    }

    @Override
    public float getAttackSpeedFromPercent(int x) {
        if (x <= 10)
        {
            return x/10f;
        } else {
            return 1f;
        }
    }

    @Override
    public float getMiningSpeedFromPercent(int x) {
        return 0;
    }

    @Override
    public int getMiningLevelFromPercent(int x) {
        return 0;
    }

    @Override
    public int getEnchantabilityFromPercent(int x) {
        return 0;
    }

    @Override
    public float getCorrResistFromPercent(int x) {
        return 0;
    }

    @Override
    public float getHeatResistFromPercent(int x) {
        return 0;
    }

    @Override
    public float getToughnessFromPercent(int x) {
        return 0;
    }

    @Override
    public float getElectrodePotentialFromPercent(int x) {
        return -2.912f;
    }

    @Override
    public Enchantment getEnchantments(int x) {
        return null;
    }
}