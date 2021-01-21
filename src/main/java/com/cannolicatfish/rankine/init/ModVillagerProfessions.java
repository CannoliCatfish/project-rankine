package com.cannolicatfish.rankine.init;

import com.cannolicatfish.rankine.ProjectRankine;
import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModVillagerProfessions {

    public static final VillagerProfession METALLURGIST = new VillagerProfession("metallurgist", ModPOIs.TEMPLATE_TABLE_POI, ImmutableSet.of(), ImmutableSet.of(), SoundEvents.BLOCK_ANVIL_HIT);
}
