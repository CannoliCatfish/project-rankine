package com.cannolicatfish.rankine.items;

import com.cannolicatfish.rankine.blocks.RankineVerticalSlabBlock;
import com.cannolicatfish.rankine.init.RankineBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HerbicideItem extends Item {
    public HerbicideItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World worldIn = context.getWorld();
        BlockPos pos = context.getPos();
        BlockState state = worldIn.getBlockState(pos);
        Block block = state.getBlock();
        BlockState nb = null;
        //plantBlocks.put(Blocks.GRASS_BLOCK, Blocks.DIRT);
        //plantBlocks.put(Blocks.MYCELIUM, Blocks.DIRT);
        //plantBlocks.put(Blocks.PODZOL, Blocks.DIRT);
        //plantBlocks.put(Blocks.DIRT, Blocks.COARSE_DIRT);

        if (block.getTags().contains(new ResourceLocation("forge:dirt"))) {
            nb = Blocks.COARSE_DIRT.getDefaultState();
        } else if (block.getTags().contains(new ResourceLocation("minecraft:leaves"))) {
            nb = Blocks.AIR.getDefaultState();
        }

         if (nb != null) {
             worldIn.setBlockState(pos, nb);
             context.getItem().shrink(1);
             return ActionResultType.SUCCESS;
         }
        return super.onItemUse(context);
    }



    //public static Map<Block, Block> plantBlocks = new HashMap<Block, Block>();


    public static void spawnParticles(IWorld worldIn, BlockPos posIn, int data) {
        if (worldIn.isRemote())
        {
            if (data == 0) {
                data = 15;
            }

            BlockState blockstate = worldIn.getBlockState(posIn);
            if (!blockstate.isAir(worldIn, posIn)) {
                double d0 = 0.5D;
                double d1;
                if (blockstate.matchesBlock(Blocks.WATER)) {
                    data *= 3;
                    d1 = 1.0D;
                    d0 = 3.0D;
                } else if (blockstate.isOpaqueCube(worldIn, posIn)) {
                    posIn = posIn.up();
                    data *= 3;
                    d0 = 3.0D;
                    d1 = 1.0D;
                } else {
                    d1 = blockstate.getShape(worldIn, posIn).getEnd(Direction.Axis.Y);
                }

                worldIn.addParticle(ParticleTypes.WHITE_ASH, (double)posIn.getX() + 0.5D, (double)posIn.getY() + 0.5D, (double)posIn.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);

                for(int i = 0; i < data; ++i) {
                    double d2 = random.nextGaussian() * 0.02D;
                    double d3 = random.nextGaussian() * 0.02D;
                    double d4 = random.nextGaussian() * 0.02D;
                    double d5 = 0.5D - d0;
                    double d6 = (double)posIn.getX() + d5 + random.nextDouble() * d0 * 2.0D;
                    double d7 = (double)posIn.getY() + random.nextDouble() * d1;
                    double d8 = (double)posIn.getZ() + d5 + random.nextDouble() * d0 * 2.0D;
                    if (!worldIn.getBlockState((new BlockPos(d6, d7, d8)).down()).isAir()) {
                        worldIn.addParticle(ParticleTypes.WHITE_ASH, d6, d7, d8, d2, d3, d4);
                    }
                }

            }
        }

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
            tooltip.add(new StringTextComponent("Kills vegetation").mergeStyle(TextFormatting.GRAY));
    }


}
