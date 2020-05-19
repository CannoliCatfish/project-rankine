package com.cannolicatfish.rankine.items;

import com.cannolicatfish.rankine.ProjectRankine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ItemBattery extends Item {

    public ItemBattery(String registryName) {
        super(new Item.Properties().group(ProjectRankine.setup.itemGroup).maxStackSize(64));
        setRegistryName(ProjectRankine.MODID, registryName);
    }



}