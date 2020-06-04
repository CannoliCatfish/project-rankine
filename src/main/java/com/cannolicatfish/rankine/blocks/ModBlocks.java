package com.cannolicatfish.rankine.blocks;

import com.cannolicatfish.rankine.ProjectRankine;
import com.cannolicatfish.rankine.blocks.alloyfurnace.AlloyFurnace;
import com.cannolicatfish.rankine.blocks.alloyfurnace.AlloyFurnaceContainer;
import com.cannolicatfish.rankine.blocks.alloyfurnace.AlloyFurnaceTile;
import com.cannolicatfish.rankine.blocks.coalforge.CoalForge;
import com.cannolicatfish.rankine.blocks.coalforge.CoalForgeContainer;
import com.cannolicatfish.rankine.blocks.coalforge.CoalForgeTile;
import com.cannolicatfish.rankine.blocks.beehiveoven.BeehiveOvenPit;
import com.cannolicatfish.rankine.blocks.crucible.Crucible;
import com.cannolicatfish.rankine.blocks.fineryforge.FineryForge;
import com.cannolicatfish.rankine.blocks.fineryforge.FineryForgeContainer;
import com.cannolicatfish.rankine.blocks.fineryforge.FineryForgeTile;
import com.cannolicatfish.rankine.blocks.pistoncrusher.PistonCrusher;
import com.cannolicatfish.rankine.blocks.pistoncrusher.PistonCrusherContainer;
import com.cannolicatfish.rankine.blocks.pistoncrusher.PistonCrusherTile;
import com.cannolicatfish.rankine.fluids.ModFluids;
import com.cannolicatfish.rankine.items.*;
import com.cannolicatfish.rankine.setup.ModSetup;
import com.cannolicatfish.rankine.world.trees.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.trees.OakTree;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> REGISTRY = new DeferredRegister<>(ForgeRegistries.BLOCKS, ProjectRankine.MODID);

    //Register a block without an item, add("name", new Block(...))
    private static <T extends Block> T add(String name, T block) {
        return add(name, block, null);
    }

    //Register a block with an item, add("name", new Block(...), new Item.Properties())
    private static <T extends Block> T add(String name, T block, Item.Properties properties) {
        return add(name, block, properties, BlockItem::new);
    }

    //Register a block with a custom item, add("name", new Block(...), new Item.Properties(), ItemName::new)
    private static <T extends Block> T add(String name, T block, Item.Properties properties, BiFunction<Block, Item.Properties, BlockItem> itemConstructor) {
        REGISTRY.register(name, () -> block);
        if (itemConstructor != null && properties != null) ModItems.REGISTRY.register(name, () -> itemConstructor.apply(block, properties));
        return block;
    }

    //Register a block with a FuelBlockItem, add("name", new Block(...), new Item.Properties(), fuelValue)
    private static <T extends Block> T add(String name, T block, Item.Properties properties, int fuelValue) {
        REGISTRY.register(name, () -> block);
        if (properties != null) ModItems.REGISTRY.register(name, () -> new FuelBlockItem(block, properties, fuelValue));
        return block;
    }

    //Register a block with a BlockNamedItem, add("name", "itemName", new Block(...), new Item.Properties(), BlockNamedItem::new)
    private static <T extends Block> T add(String name, String itemName, T block, Item.Properties properties, BiFunction<Block, Item.Properties, BlockItem> itemConstructor) {
        REGISTRY.register(name, () -> block);
        if (itemConstructor != null && properties != null) ModItems.REGISTRY.register(itemName, () -> itemConstructor.apply(block, properties));
        return block;
    }


    //Creative Tabs
    public static Item.Properties METALS = new Item.Properties().group(ProjectRankine.setup.rankineMetals);
    public static Item.Properties MAIN = new Item.Properties().group(ProjectRankine.setup.itemGroup);


    //MAIN CREATIVE TAB------------------------------------------------------------------------------------------------------------------------------------------------------------------------



    //EARTHY BLOCKS
    public static final Block SANDY_DIRT = add("sandy_dirt", new SandyDirtBlock(Block.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(0.5F).sound(SoundType.GROUND)), MAIN);
    //public static final Block MUDDY_DIRT = add("muddy_dirt", new  Block(Block.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(0.5F).sound(SoundType.GROUND)), MAIN);
    //public static final Block MUDDY_GRASS = add("muddy_grass", new MuddyGrassBlock(Block.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(0.5F).sound(SoundType.GROUND)), MAIN);
    public static final Block PERMAFROST = add("permafrost", new PermafrostBlock(Block.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(1.5F).sound(SoundType.GROUND)), MAIN);
    //public static final Block VEGETATED_PERMAFROST = add("vegetated_permafrost", new VegetatedPermafrostBlock(Block.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(1.5F).sound(SoundType.GROUND)), MAIN);
    public static final Block GRAVEL_CONCRETE = add("gravel_concrete", new EntityModifierBlock(Block.Properties.create(Material.SAND).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), MAIN);


    //STONE BLOCKS
    public static final Block GRANITE = add("granite", new RankineStone(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_GRANITE = add("smooth_granite", new Block(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);
    public static final Block GRANITE_BRICKS = add("granite_bricks", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(0)), MAIN);
    public static final Block DIORITE = add("diorite", new RankineStone(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_DIORITE = add("smooth_diorite", new Block(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);
    public static final Block DIORITE_BRICKS = add("diorite_bricks", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(0)), MAIN);
    public static final Block ANDESITE = add("andesite", new RankineStone(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_ANDESITE = add("smooth_andesite", new Block(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);
    public static final Block ANDESITE_BRICKS = add("andesite_bricks", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(0)), MAIN);
    public static final Block LIMESTONE = add("limestone", new RankineStone(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_LIMESTONE = add("smooth_limestone", new Block(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);
    public static final Block LIMESTONE_BRICKS = add("limestone_bricks", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(0)), MAIN);
    public static final Block BASALT = add("basalt", new RankineStone(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_BASALT = add("smooth_basalt", new Block(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(2)), MAIN);
    public static final Block BASALT_BRICKS = add("basalt_bricks", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(1)), MAIN);
    public static final Block RHYOLITE = add("rhyolite", new RankineStone(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_RHYOLITE = add("smooth_rhyolite", new Block(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(2)), MAIN);

    public static final Block MARBLE = add("marble", new RankineStone(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_MARBLE = add("smooth_marble", new Block(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(2)), MAIN);
    public static final Block MARBLE_BRICKS = add("marble_bricks", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(2)), MAIN);
    public static final Block GNEISS = add("gneiss", new RankineStone(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_GNEISS = add("smooth_gneiss", new Block(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(2)), MAIN);
    public static final Block GNEISS_BRICKS = add("gneiss_bricks", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(2)), MAIN);
    public static final Block SHALE = add("shale", new RankineStone(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_SHALE = add("smooth_shale", new Block(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);
    public static final Block SHALE_BRICKS = add("shale_bricks", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(0)), MAIN);
    public static final Block IRONSTONE = add("ironstone", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_IRONSTONE = add("smooth_ironstone", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block IRONSTONE_BRICKS = add("ironstone_bricks", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block PERIDOTITE = add("peridotite", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_PERIDOTITE = add("smooth_peridotite", new Block(Block.Properties.create(Material.ROCK).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(3)), MAIN);

    public static final Block RINGWOODITE = add("ringwoodite", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_RINGWOODITE = add("smooth_ringwoodite", new Block(Block.Properties.create(Material.ROCK).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(3)), MAIN);

    public static final Block WADSLEYITE = add("wadsleyite", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_WADSLEYITE = add("smooth_wadsleyite", new Block(Block.Properties.create(Material.ROCK).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(3)), MAIN);

    public static final Block BRIDGMANITE = add("bridgmanite", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_BRIDGMANITE = add("smooth_bridgmanite", new Block(Block.Properties.create(Material.ROCK).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(3)), MAIN);

    public static final Block KOMATIITE = add("komatiite", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_KOMATIITE = add("smooth_komatiite", new Block(Block.Properties.create(Material.ROCK).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(3)), MAIN);

    public static final Block KIMBERLITE = add("kimberlite", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_KIMBERLITE = add("smooth_kimberlite", new Block(Block.Properties.create(Material.ROCK).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(3)), MAIN);

    public static final Block FERROPERICLASE = add("ferropericlase", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_FERROPERICLASE = add("smooth_ferropericlase", new Block(Block.Properties.create(Material.ROCK).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(3)), MAIN);

    public static final Block PEROVSKITE = add("perovskite", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_PEROVSKITE = add("smooth_perovskite", new Block(Block.Properties.create(Material.ROCK).harvestLevel(3).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(3)), MAIN);

    public static final Block CLAY_BRICKS = add("clay_bricks", new Block(Block.Properties.create(Material.CLAY)), MAIN);
    public static final Block REFRACTORY_BRICKS = add("refractory_bricks", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F)), MAIN);
    public static final Block MAGNESIUM_REFRACTORY_BRICKS = add("magnesium_refractory_bricks", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F)), MAIN);

    public static final Block GRANITE_SLAB = add("granite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_GRANITE_SLAB = add("smooth_granite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block DIORITE_SLAB = add("diorite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_DIORITE_SLAB = add("smooth_diorite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block ANDESITE_SLAB = add("andesite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_ANDESITE_SLAB = add("smooth_andesite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block LIMESTONE_SLAB = add("limestone_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_LIMESTONE_SLAB = add("smooth_limestone_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block BASALT_SLAB = add("basalt_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_BASALT_SLAB = add("smooth_basalt_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block RHYOLITE_SLAB = add("rhyolite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_RHYOLITE_SLAB = add("smooth_rhyolite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block MARBLE_SLAB = add("marble_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_MARBLE_SLAB = add("smooth_marble_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block GNEISS_SLAB = add("gneiss_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_GNEISS_SLAB = add("smooth_gneiss_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block SHALE_SLAB = add("shale_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_SHALE_SLAB = add("smooth_shale_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block IRONSTONE_SLAB = add("ironstone_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_IRONSTONE_SLAB = add("smooth_ironstone_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block PERIDOTITE_SLAB = add("peridotite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_PERIDOTITE_SLAB = add("smooth_peridotite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block RINGWOODITE_SLAB = add("ringwoodite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_RINGWOODITE_SLAB = add("smooth_ringwoodite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block WADSLEYITE_SLAB = add("wadsleyite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_WADSLEYITE_SLAB = add("smooth_wadsleyite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block BRIDGMANITE_SLAB = add("bridgmanite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_BRIDGMANITE_SLAB = add("smooth_bridgmanite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block KOMATIITE_SLAB = add("komatiite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_KOMATIITE_SLAB = add("smooth_komatiite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block KIMBERLITE_SLAB = add("kimberlite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_KIMBERLITE_SLAB = add("smooth_kimberlite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block FERROPERICLASE_SLAB = add("ferropericlase_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_FERROPERICLASE_SLAB = add("smooth_ferropericlase_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);

    public static final Block PEROVSKITE_SLAB = add("perovskite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_PEROVSKITE_SLAB = add("smooth_perovskite_slab", new RankineSlab(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(0)), MAIN);


    public static final Block CLAY_BRICKS_SLAB = add("clay_bricks_slab", new RankineSlab(Block.Properties.create(Material.CLAY)), MAIN);
    public static final Block REFRACTORY_BRICKS_SLAB = add("refractory_bricks_slab", new RankineSlab(Block.Properties.create(Material.CLAY)), MAIN);
    public static final Block MAGNESIUM_REFRACTORY_BRICKS_SLAB = add("magnesium_refractory_bricks_slab", new RankineSlab(Block.Properties.create(Material.CLAY)), MAIN);

    public static final Block GRANITE_STAIRS = add("granite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_GRANITE_STAIRS = add("smooth_granite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block DIORITE_STAIRS = add("diorite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_DIORITE_STAIRS = add("smooth_diorite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block ANDESITE_STAIRS = add("andesite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_ANDESITE_STAIRS = add("smooth_andesite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block LIMESTONE_STAIRS = add("limestone_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_LIMESTONE_STAIRS = add("smooth_limestone_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block BASALT_STAIRS = add("basalt_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_BASALT_STAIRS = add("smooth_basalt_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block RHYOLITE_STAIRS = add("rhyolite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_RHYOLITE_STAIRS = add("smooth_rhyolite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block MARBLE_STAIRS = add("marble_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_MARBLE_STAIRS = add("smooth_marble_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block GNEISS_STAIRS = add("gneiss_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_GNEISS_STAIRS = add("smooth_gneiss_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block SHALE_STAIRS = add("shale_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_SHALE_STAIRS = add("smooth_shale_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block IRONSTONE_STAIRS = add("ironstone_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_IRONSTONE_STAIRS = add("smooth_ironstone_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block PERIDOTITE_STAIRS = add("peridotite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_PERIDOTITE_STAIRS = add("smooth_peridotite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block RINGWOODITE_STAIRS = add("ringwoodite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_RINGWOODITE_STAIRS = add("smooth_ringwoodite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block WADSLEYITE_STAIRS = add("wadsleyite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_WADSLEYITE_STAIRS = add("smooth_wadsleyite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block BRIDGMANITE_STAIRS = add("bridgmanite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_BRIDGMANITE_STAIRS = add("smooth_bridgmanite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block KIMBERLITE_STAIRS = add("kimberlite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_KIMBERLITE_STAIRS = add("smooth_kimberlite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block KOMATIITE_STAIRS = add("komatiite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_KOMATIITE_STAIRS = add("smooth_komatiite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block FERROPERICLASE_STAIRS = add("ferropericlase_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_FERROPERICLASE_STAIRS = add("smooth_ferropericlase_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block PEROVSKITE_STAIRS = add("perovskite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_PEROVSKITE_STAIRS = add("smooth_perovskite_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).hardnessAndResistance(2.0F, 3.0F)), MAIN);


    public static final Block CLAY_BRICKS_STAIRS = add("clay_bricks_stairs", new RankineStairs(Block.getStateById(0), Block.Properties.create(Material.CLAY)), MAIN);
    public static final Block REFRACTORY_BRICKS_STAIRS = add("refractory_bricks_stairs", new RankineStairs(Block.getStateById(0), Block.Properties.create(Material.CLAY)), MAIN);
    public static final Block MAGNESIUM_REFRACTORY_BRICKS_STAIRS = add("magnesium_refractory_bricks_stairs", new RankineStairs(Block.getStateById(0), Block.Properties.create(Material.CLAY)), MAIN);


    public static final Block GRANITE_WALL = add("granite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_GRANITE_WALL = add("smooth_granite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block DIORITE_WALL = add("diorite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_DIORITE_WALL = add("smooth_diorite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block ANDESITE_WALL = add("andesite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_ANDESITE_WALL = add("smooth_andesite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block LIMESTONE_WALL = add("limestone_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_LIMESTONE_WALL = add("smooth_limestone_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block BASALT_WALL = add("basalt_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_BASALT_WALL = add("smooth_basalt_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block RHYOLITE_WALL = add("rhyolite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_RHYOLITE_WALL = add("smooth_rhyolite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block MARBLE_WALL = add("marble_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_MARBLE_WALL = add("smooth_marble_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block GNEISS_WALL = add("gneiss_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_GNEISS_WALL = add("smooth_gneiss_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block SHALE_WALL = add("shale_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_SHALE_WALL = add("smooth_shale_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block IRONSTONE_WALL = add("ironstone_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_IRONSTONE_WALL = add("smooth_ironstone_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block PERIDOTITE_WALL = add("peridotite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_PERIDOTITE_WALL = add("smooth_peridotite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block RINGWOODITE_WALL = add("ringwoodite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_RINGWOODITE_WALL = add("smooth_ringwoodite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block WADSLEYITE_WALL = add("wadsleyite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_WADSLEYITE_WALL = add("smooth_wadsleyite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block BRIDGMANITE_WALL = add("bridgmanite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_BRIDGMANITE_WALL = add("smooth_bridgmanite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block KOMATIITE_WALL = add("komatiite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_KOMATIITE_WALL = add("smooth_komatiite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block KIMBERLITE_WALL = add("kimberlite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_KIMBERLITE_WALL = add("smooth_kimberlite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block FERROPERICLASE_WALL = add("ferropericlase_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_FERROPERICLASE_WALL = add("smooth_ferropericlase_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);

    public static final Block PEROVSKITE_WALL = add("perovskite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);
    public static final Block SMOOTH_PEROVSKITE_WALL = add("smooth_perovskite_wall", new RankineWall(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F, 2.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), MAIN);


                /*
            static final Block PEROVSKITE = add("perovskite", new PipeBlock(Block.Properties.create(Material.IRON)),"brass_pipe"));
            static final Block PEROVSKITE = add("perovskite", new PipeBlock(Block.Properties.create(Material.ROCK)),"concrete_pipe"));
            static final Block PEROVSKITE = add("perovskite", new PipeBlock(Block.Properties.create(Material.ROCK)),"concrete_pipe_junction"));
            static final Block PEROVSKITE = add("perovskite", new PipeBlock(Block.Properties.create(Material.IRON)),"cast_iron_pipe"));
            static final Block PEROVSKITE = add("perovskite", new PipeBlock(Block.Properties.create(Material.IRON)),"cast_iron_pipe_junction"));
            static final Block PEROVSKITE = add("perovskite", new SupportBlock(Block.Properties.create(Material.IRON)),"cast_iron_beam"));
             */



    //WOOD BLOCKS
    public static final Block CEDAR_LOG = add("cedar_log", new LogBlock(MaterialColor.AIR,Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block PINYON_PINE_LOG = add("pinyon_pine_log", new LogBlock(MaterialColor.AIR,Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block JUNIPER_LOG = add("juniper_log", new LogBlock(MaterialColor.AIR,Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block COCONUT_PALM_LOG = add("coconut_palm_log", new LogBlock(MaterialColor.AIR,Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block BALSAM_FIR_LOG = add("balsam_fir_log", new LogBlock(MaterialColor.AIR,Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block CEDAR_WOOD = add("cedar_wood", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block PINYON_PINE_WOOD = add("pinyon_pine_wood", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block JUNIPER_WOOD = add("juniper_wood", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block COCONUT_PALM_WOOD = add("coconut_palm_wood", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block BALSAM_FIR_WOOD = add("balsam_fir_wood", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block STRIPPED_CEDAR_LOG = add("stripped_cedar_log", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block STRIPPED_PINYON_PINE_LOG = add("stripped_pinyon_pine_log", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block STRIPPED_JUNIPER_LOG = add("stripped_juniper_log", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block STRIPPED_COCONUT_PALM_LOG = add("stripped_coconut_palm_log", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block STRIPPED_BALSAM_FIR_LOG = add("stripped_balsam_fir_log", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block STRIPPED_CEDAR_WOOD = add("stripped_cedar_wood", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block STRIPPED_PINYON_PINE_WOOD = add("stripped_pinyon_pine_wood", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block STRIPPED_JUNIPER_WOOD = add("stripped_juniper_wood", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block STRIPPED_COCONUT_PALM_WOOD = add("stripped_coconut_palm_wood", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block STRIPPED_BALSAM_FIR_WOOD = add("stripped_balsam_fir_wood", new RotatedPillarBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F)), MAIN);
    public static final Block CEDAR_PLANKS = add("cedar_planks", new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)), MAIN);
    public static final Block PINYON_PINE_PLANKS = add("pinyon_pine_planks", new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)), MAIN);
    public static final Block JUNIPER_PLANKS = add("juniper_planks", new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)), MAIN);
    public static final Block COCONUT_PALM_PLANKS = add("coconut_palm_planks", new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)), MAIN);
    public static final Block BALSAM_FIR_PLANKS = add("balsam_fir_planks", new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block CEDAR_SLAB = add("cedar_slab", new RankineSlab(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)), MAIN);
    public static final Block PINYON_PINE_SLAB = add("pinyon_pine_slab", new RankineSlab(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)), MAIN);
    public static final Block JUNIPER_SLAB = add("juniper_slab", new RankineSlab(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)), MAIN);
    public static final Block COCONUT_PALM_SLAB = add("coconut_palm_slab", new RankineSlab(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)), MAIN);
    public static final Block BALSAM_FIR_SLAB = add("balsam_fir_slab", new RankineSlab(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block CEDAR_STAIRS = add("cedar_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)), MAIN);
    public static final Block PINYON_PINE_STAIRS = add("pinyon_pine_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)), MAIN);
    public static final Block JUNIPER_STAIRS = add("juniper_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)), MAIN);
    public static final Block COCONUT_PALM_STAIRS = add("coconut_palm_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)), MAIN);
    public static final Block BALSAM_FIR_STAIRS = add("balsam_fir_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F)), MAIN);

    public static final Block CEDAR_FENCE = add("cedar_fence", new RankineWoodFence(), MAIN);
    public static final Block PINYON_PINE_FENCE = add("pinyon_pine_fence", new RankineWoodFence(), MAIN);
    public static final Block JUNIPER_FENCE = add("juniper_fence", new RankineWoodFence(), MAIN);
    public static final Block COCONUT_PALM_FENCE = add("coconut_palm_fence", new RankineWoodFence(), MAIN);
    public static final Block BALSAM_FIR_FENCE = add("balsam_fir_fence", new RankineWoodFence(), MAIN);

    public static final Block CEDAR_FENCE_GATE = add("cedar_fence_gate", new RankineWoodFenceGate(), MAIN);
    public static final Block PINYON_PINE_FENCE_GATE = add("pinyon_pine_fence_gate", new RankineWoodFenceGate(), MAIN);
    public static final Block JUNIPER_FENCE_GATE = add("juniper_fence_gate", new RankineWoodFenceGate(), MAIN);
    public static final Block COCONUT_PALM_FENCE_GATE = add("coconut_palm_fence_gate", new RankineWoodFenceGate(), MAIN);
    public static final Block BALSAM_FIR_FENCE_GATE = add("balsam_fir_fence_gate", new RankineWoodFenceGate(), MAIN);

    public static final Block CEDAR_PRESSURE_PLATE = add("cedar_pressure_plate", new RankineWoodPressurePlate(), MAIN);
    public static final Block PINYON_PINE_PRESSURE_PLATE = add("pinyon_pine_pressure_plate", new RankineWoodPressurePlate(), MAIN);
    public static final Block JUNIPER_PRESSURE_PLATE = add("juniper_pressure_plate", new RankineWoodPressurePlate(), MAIN);
    public static final Block COCONUT_PALM_PRESSURE_PLATE = add("coconut_palm_pressure_plate", new RankineWoodPressurePlate(), MAIN);
    public static final Block BALSAM_FIR_PRESSURE_PLATE = add("balsam_fir_pressure_plate", new RankineWoodPressurePlate(), MAIN);

    public static final Block CEDAR_BUTTON = add("cedar_button", new RankineWoodButton(), MAIN);
    public static final Block PINYON_PINE_BUTTON = add("pinyon_pine_button", new RankineWoodButton(), MAIN);
    public static final Block JUNIPER_BUTTON = add("juniper_button", new RankineWoodButton(), MAIN);
    public static final Block COCONUT_PALM_BUTTON = add("coconut_palm_button", new RankineWoodButton(), MAIN);
    public static final Block BALSAM_FIR_BUTTON = add("balsam_fir_button", new RankineWoodButton(), MAIN);

    public static final Block CEDAR_DOOR = add("cedar_door", new RankineWoodDoor(), MAIN);

    public static final Block CEDAR_TRAPDOOR = add("cedar_trapdoor", new RankineWoodTrapDoor(), MAIN);

    public static final Block STICK_BLOCK = add("stick_block", new StickBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(1.2F)), MAIN);
    public static final Block BAMBOO_BLOCK = add("bamboo_block", new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F).harvestLevel(0).harvestTool(ToolType.AXE)), MAIN);
    public static final Block BAMBOO_SLAB = add("bamboo_slab", new RankineSlab(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F).harvestLevel(0).harvestTool(ToolType.AXE)), MAIN);
    public static final Block BAMBOO_STAIRS = add("bamboo_stairs", new RankineStairs(Block.getStateById(0),Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2.0F, 3.0F).harvestLevel(0).harvestTool(ToolType.AXE)), MAIN);

    public static final Block CEDAR_LEAVES = add("cedar_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid()), MAIN);
    public static final Block COCONUT_PALM_LEAVES = add("coconut_palm_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid()), MAIN);
    public static final Block PINYON_PINE_LEAVES = add("pinyon_pine_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid()), MAIN);
    public static final Block JUNIPER_LEAVES = add("juniper_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid()), MAIN);
    public static final Block BALSAM_FIR_LEAVES = add("balsam_fir_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid()), MAIN);
    public static final Block MAGNOLIA_LEAVES = add("magnolia_leaves", new LeavesBlock(Block.Properties.create(Material.LEAVES).tickRandomly().hardnessAndResistance(0.2F).sound(SoundType.PLANT).notSolid()), MAIN);

    private static final Block.Properties sapling = Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.PLANT);
    public static final Block CEDAR_SAPLING = add("cedar_sapling", new RankineSapling(new CedarTree(), sapling, 3), MAIN, 200);
    public static final Block COCONUT_PALM_SAPLING = add("coconut_palm_sapling", new RankineSapling(new CoconutPalmTree(), sapling, 2), MAIN, 200);
    public static final Block PINYON_PINE_SAPLING = add("pinyon_pine_sapling", new RankineSapling(new PinyonPineTree(), sapling, 2), MAIN, 200);
    public static final Block JUNIPER_SAPLING = add("juniper_sapling", new RankineSapling(new JuniperTree(), sapling, 2), MAIN, 200);
    public static final Block BALSAM_FIR_SAPLING = add("balsam_fir_sapling", new RankineSapling(new BalsamFirTree(), sapling, 3), MAIN, 200);
    public static final Block MAGNOLIA_SAPLING = add("magnolia_sapling", new RankineSapling(new MagnoliaTree(), sapling, 3), MAIN, 200);
    public static final FlowerPotBlock POTTED_CEDAR_SAPLING = add("potted_cedar_sapling", new FlowerPotBlock(null, () -> CEDAR_SAPLING, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0f).notSolid()));
    public static final FlowerPotBlock POTTED_PINYON_PINE_SAPLING = add("potted_pinyon_pine_sapling", new FlowerPotBlock(null, () -> PINYON_PINE_SAPLING, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0f).notSolid()));
    public static final FlowerPotBlock POTTED_JUNIPER_SAPLING = add("potted_juniper_sapling", new FlowerPotBlock(null, () -> JUNIPER_SAPLING, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0f).notSolid()));
    public static final FlowerPotBlock POTTED_COCONUT_PALM_SAPLING = add("potted_coconut_palm_sapling", new FlowerPotBlock(null, () -> COCONUT_PALM_SAPLING, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0f).notSolid()));
    public static final FlowerPotBlock POTTED_BALSAM_FIR_SAPLING = add("potted_balsam_fir_sapling", new FlowerPotBlock(null, () -> BALSAM_FIR_SAPLING, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0f).notSolid()));
    public static final FlowerPotBlock POTTED_MAGNOLIA_SAPLING = add("potted_magnolia_sapling", new FlowerPotBlock(null, () -> MAGNOLIA_SAPLING, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0f).notSolid()));


    public static final Block ALUMINUM_LADDER = add("aluminum_ladder", new MetalLadder(Block.Properties.create(Material.CARPET)), MAIN);
    public static final RopeBlock ROPE = add("rope", new RopeBlock(Block.Properties.create(Material.CARPET).doesNotBlockMovement()), MAIN, RopeItem::new);
    public static final SphagnumMossBlock SPHAGNUM_MOSS = add("sphagnum_moss", new SphagnumMossBlock(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.NETHER_WART)), MAIN);
    public static final SwampGrassBlock SWAMP_GRASS = add("swamp_grass", new SwampGrassBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().sound(SoundType.PLANT).hardnessAndResistance(0.0f)), MAIN);
    public static final DuckweedBlock DUCKWEED = add("duckweed", new DuckweedBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().sound(SoundType.PLANT).hardnessAndResistance(0.0f)), MAIN, DuckweedItem::new);

    //PROCESSING--------------------------------------------------------------------------------------------------------------------------------------------------------------

    //ORES
    public static final Block METEORIC_IRON_ORE = add("meteoric_iron_ore", new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE)), METALS);
    public static final RankineOre NATIVE_COPPER_ORE = add("native_copper_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(0).harvestTool(ToolType.PICKAXE), ModItems.COPPER_NUGGET), METALS);
    public static final RankineOre NATIVE_TIN_ORE = add("native_tin_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(0).harvestTool(ToolType.PICKAXE), ModItems.TIN_NUGGET), METALS);
    public static final RankineOre NATIVE_GOLD_ORE = add("native_gold_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE)), METALS);
    public static final RankineOre MALACHITE_ORE = add("malachite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE), ModItems.COPPER_NUGGET), METALS);
    public static final RankineOre CASSITERITE_ORE = add("cassiterite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE), ModItems.TIN_NUGGET), METALS);
    public static final RankineOre BAUXITE_ORE = add("bauxite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE), ModItems.ALUMINUM_NUGGET), METALS);
    public static final RankineOre SPHALERITE_ORE = add("sphalerite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE), ModItems.ZINC_NUGGET), METALS);
    public static final RankineOre MAGNETITE_ORE = add("magnetite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(2).harvestTool(ToolType.PICKAXE), Items.IRON_NUGGET), METALS);
    public static final RankineOre CINNABAR_ORE = add("cinnabar_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE), Items.REDSTONE), METALS);
    public static final RankineOre PENTLANDITE_ORE = add("pentlandite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(2).harvestTool(ToolType.PICKAXE), ModItems.NICKEL_NUGGET), METALS);
    public static final RankineOre MAGNESITE_ORE = add("magnesite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(2).harvestTool(ToolType.PICKAXE), ModItems.MAGNESIUM_NUGGET), METALS);
    public static final RankineOre GALENA_ORE = add("galena_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(2).harvestTool(ToolType.PICKAXE), ModItems.LEAD_NUGGET), METALS);
    public static final RankineOre VANADINITE_ORE = add("vanadinite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(2).harvestTool(ToolType.PICKAXE)), METALS);
    public static final RankineOre BISMITE_ORE = add("bismite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(2).harvestTool(ToolType.PICKAXE), ModItems.BISMUTH_NUGGET), METALS);
    public static final RankineOre ACANTHITE_ORE = add("acanthite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(2).harvestTool(ToolType.PICKAXE), ModItems.SILVER_NUGGET), METALS);
    public static final RankineOre PYROLUSITE_ORE = add("pyrolusite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(2).harvestTool(ToolType.PICKAXE), ModItems.MANGANESE_NUGGET), METALS);
    public static final RankineOre CHROMITE_ORE = add("chromite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE), ModItems.CHROMIUM_NUGGET), METALS);
    public static final RankineOre MOLYBDENITE_ORE = add("molybdenite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE), ModItems.MOLYBDENUM_NUGGET), METALS);
    public static final RankineOre WOLFRAMITE_ORE = add("wolframite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE), ModItems.TITANIUM_NUGGET), METALS);
    public static final RankineOre ILMENITE_ORE = add("ilmenite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE), ModItems.TITANIUM_NUGGET), METALS);
    public static final RankineOre COLUMBITE_ORE = add("columbite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE), ModItems.NIOBIUM_NUGGET), METALS);
    public static final RankineOre TANTALITE_ORE = add("tantalite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE), ModItems.TANTALUM_NUGGET), METALS);
    public static final RankineOre PLUMBAGO_ORE = add("plumbago_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE)), METALS);
    public static final RankineOre MOISSANITE_ORE = add("moissanite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE)), METALS);
    public static final RankineOre SPERRYLITE_ORE = add("sperrylite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE)), METALS);
    public static final RankineOre LIGNITE_ORE = add("lignite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(0).harvestTool(ToolType.PICKAXE)), METALS);
    public static final RankineOre SUBBITUMINOUS_ORE = add("subbituminous_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE)), METALS);
    public static final RankineOre BITUMINOUS_ORE = add("bituminous_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(2).harvestTool(ToolType.PICKAXE)), METALS);
    public static final RankineOre ANTHRACITE_ORE = add("anthracite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE)), METALS);
    public static final RankineOre REDSTONE_ORE = add("redstone_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(2).harvestTool(ToolType.PICKAXE)), METALS);
    public static final RankineOre LAZURITE_ORE = add("lazurite_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(2).harvestTool(ToolType.PICKAXE)), METALS);
    public static final RankineOre DIAMOND_ORE = add("diamond_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE)), METALS);
    public static final RankineOre EMERALD_ORE = add("emerald_ore", new RankineOre(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(2).harvestTool(ToolType.PICKAXE)), METALS);
    public static final Block OPAL_ORE = add("opal_ore", new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE)), METALS);
    public static final NoduleBlock LIMESTONE_NODULE = add("limestone_nodule", new NoduleBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE)), METALS);


    //metal blocks
    public static final Block ROSE_GOLD_BLOCK = add("rose_gold_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block WHITE_GOLD_BLOCK = add("white_gold_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block GREEN_GOLD_BLOCK = add("green_gold_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block BLUE_GOLD_BLOCK = add("blue_gold_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block PURPLE_GOLD_BLOCK = add("purple_gold_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block CHROMIUM_BLOCK = add("chromium_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block TITANIUM_BLOCK = add("titanium_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block SILVER_BLOCK = add("silver_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block PLATINUM_BLOCK = add("platinum_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block OSMIUM_BLOCK = add("osmium_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block IRIDIUM_BLOCK = add("iridium_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block ZINC_BLOCK = add("zinc_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block MAGNESIUM_BLOCK = add("magnesium_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block MANGANESE_BLOCK = add("manganese_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block MOLYBDENUM_BLOCK = add("molybdenum_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block NICKEL_BLOCK = add("nickel_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block COBALT_BLOCK = add("cobalt_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block TUNGSTEN_BLOCK = add("tungsten_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block INVAR_BLOCK = add("invar_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block BRONZE_BLOCK = add("bronze_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block ALUMINUM_BRONZE_BLOCK = add("aluminum_bronze_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block STEEL_BLOCK = add("steel_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block STAINLESS_STEEL_BLOCK = add("stainless_steel_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block PIG_IRON_BLOCK = add("pig_iron_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block ALNICO_BLOCK = add("alnico_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block MAGNALIUM_BLOCK = add("magnalium_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block DURALUMIN_BLOCK = add("duralumin_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block BRASS_BLOCK = add("brass_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block CUPRONICKEL_BLOCK = add("cupronickel_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block NICHROME_BLOCK = add("nichrome_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block NICKEL_SILVER_BLOCK = add("nickel_silver_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block NITINOL_BLOCK = add("nitinol_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block AMALGAM_BLOCK = add("amalgam_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block ROSE_METAL_BLOCK = add("rose_metal_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block WROUGHT_IRON_BLOCK = add("wrought_iron_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block CAST_IRON_BLOCK = add("cast_iron_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block COPPER_BLOCK = add("copper_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block TIN_BLOCK = add("tin_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block NIOBIUM_BLOCK = add("niobium_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block ALUMINUM_BLOCK = add("aluminum_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block VANADIUM_BLOCK = add("vanadium_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block LEAD_BLOCK = add("lead_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block BISMUTH_BLOCK = add("bismuth_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block TANTALUM_BLOCK = add("tantalum_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block GRAPHITE_BLOCK = add("graphite_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block OPAL_BLOCK = add("opal_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block GARNET_BLOCK = add("garnet_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block FELDSPAR_BLOCK = add("feldspar_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block DOLOMITE_BLOCK = add("dolomite_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block CALCITE_BLOCK = add("calcite_block", new RankineTransparent(Block.Properties.create(Material.IRON).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1).notSolid()), METALS);
    public static final Block OLIVINE_BLOCK = add("olivine_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block PERIDOT_BLOCK = add("peridot_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block PYROXENE_BLOCK = add("pyroxene_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block INCONEL_BLOCK = add("inconel_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block SALT_BLOCK = add("salt_block", new RankineTransparent(Block.Properties.create(Material.SAND).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1).notSolid()), METALS);
    public static final Block CALCIUM_SILICATE_BLOCK = add("calcium_silicate_block", new RankineTransparent(Block.Properties.create(Material.EARTH).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1).notSolid()), METALS);
    public static final Block SILICON_BLOCK = add("silicon_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block SULFUR_BLOCK = add("sulfur_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block SILICON_CARBIDE_BLOCK = add("silicon_carbide_block", new Block(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final Block COKE_BLOCK = add("coke_block", new Block(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS, 2400*9);
    public static final Block LIGNITE_BLOCK = add("lignite_block", new Block(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS, 1200*9);
    public static final Block BITUMINOUS_COAL_BLOCK = add("bituminous_coal_block", new Block(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS, 2000*9);
    public static final Block ANTHRACITE_COAL_BLOCK = add("anthracite_coal_block", new Block(Block.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS, 2400*9);
    public static final CrystalBlock NITER = add("niter", new CrystalBlock(Block.Properties.create(Material.IRON).sound(SoundType.GLASS).harvestLevel(1).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);




    public static final Block COPPER_SHEETMETAL = add("copper_sheetmetal", new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE)), METALS);
    public static final Block TIN_SHEETMETAL = add("tin_sheetmetal", new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE)), METALS);
    public static final Block BRONZE_SHEETMETAL = add("bronze_sheetmetal", new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2).harvestLevel(1).harvestTool(ToolType.PICKAXE)), METALS);
    public static final Block WROUGHT_IRON_SHEETMETAL = add("wrought_iron_sheetmetal", new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2).harvestLevel(2).harvestTool(ToolType.PICKAXE)), METALS);
    public static final Block STEEL_SHEETMETAL = add("steel_sheetmetal", new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2).harvestLevel(3).harvestTool(ToolType.PICKAXE)), METALS);

    public static final BlastingPowderBlock BLASTING_POWDER = add("blasting_powder", new BlastingPowderBlock(Block.Properties.create(Material.SAND).sound(SoundType.SAND).harvestTool(ToolType.SHOVEL).hardnessAndResistance(1)), METALS);
    public static final MantleTeleporterBlock HEART_OF_THE_MANTLE = add("heart_of_the_mantle", new MantleTeleporterBlock(Block.Properties.create(Material.IRON).harvestLevel(2).harvestTool(ToolType.PICKAXE).hardnessAndResistance(2).harvestLevel(1)), METALS);
    public static final BeehiveOvenPit BEEHIVE_OVEN_PIT = add("beehive_oven_pit", new BeehiveOvenPit(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0F)), METALS);
    public static final Crucible CRUCIBLE = add("crucible", new Crucible(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F).lightValue(7)), METALS);
    public static final AlloyFurnace ALLOY_FURNACE = add("alloy_furnace", new AlloyFurnace(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0f).lightValue(13)), METALS);
    public static final CoalForge COAL_FORGE = add("coal_forge", new CoalForge(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0f).lightValue(13)), METALS);
    public static final FineryForge FINERY_FORGE = add("finery_forge", new FineryForge(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0f).lightValue(7)), METALS);
    public static final PistonCrusher PISTON_CRUSHER = add("piston_crusher", new PistonCrusher(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0f).lightValue(7)), METALS);


    public static final RankineBerryBushBlock ELDERBERRY_BUSH = add("elderberry_bush", "elderberries", new RankineBerryBushBlock(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH),0), new Item.Properties().group(ProjectRankine.setup.itemGroup).food(ModFoods.ELDERBERRIES), BlockNamedItem::new);
    public static final RankineBerryBushBlock SNOWBERRY_BUSH = add("snowberry_bush", "snowberries", new RankineBerryBushBlock(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH),1), new Item.Properties().group(ProjectRankine.setup.itemGroup).food(ModFoods.SNOWBERRIES), BlockNamedItem::new);
    public static final RankineBerryBushBlock BANANA_YUCCA_BUSH = add("banana_yucca_bush", "banana_yucca",  new RankineBerryBushBlock(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH),2), new Item.Properties().group(ProjectRankine.setup.itemGroup).food(ModFoods.YUCCA), BlockNamedItem::new);
    public static final RankineBerryBushBlock PINEAPPLE_BUSH = add("pineapple_bush", "pineapple",  new RankineBerryBushBlock(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.SWEET_BERRY_BUSH),3), new Item.Properties().group(ProjectRankine.setup.itemGroup).food(ModFoods.PINEAPPLE), BlockNamedItem::new);


    public static final FlowingFluidBlock LIQUID_PIG_IRON_BLOCK = add("liquid_pig_iron_block", new FlowingFluidBlock(()-> ModFluids.LIQUID_PIG_IRON,Block.Properties.create(Material.LAVA).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));




    //OTHER STUFFS
    @ObjectHolder("rankine:alloy_furnace")
    public static ContainerType<AlloyFurnaceContainer> ALLOY_FURNACE_CONTAINER;

    @ObjectHolder("rankine:alloy_furnace")
    public static TileEntityType<AlloyFurnaceTile> ALLOY_FURNACE_TILE;


    @ObjectHolder("rankine:piston_crusher")
    public static ContainerType<PistonCrusherContainer> PISTON_CRUSHER_CONTAINER;

    @ObjectHolder("rankine:piston_crusher")
    public static TileEntityType<PistonCrusherTile> PISTON_CRUSHER_TILE;


    @ObjectHolder("rankine:coal_forge")
    public static ContainerType<CoalForgeContainer> COAL_FORGE_CONTAINER;

    @ObjectHolder("rankine:coal_forge")
    public static TileEntityType<CoalForgeTile> COAL_FORGE_TILE;


    @ObjectHolder("rankine:finery_forge")
    public static ContainerType<FineryForgeContainer> FINERY_FORGE_CONTAINER;

    @ObjectHolder("rankine:finery_forge")
    public static TileEntityType<FineryForgeTile> FINERY_FORGE_TILE;
}
