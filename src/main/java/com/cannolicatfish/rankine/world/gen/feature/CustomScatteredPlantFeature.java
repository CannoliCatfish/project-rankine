package com.cannolicatfish.rankine.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class CustomScatteredPlantFeature extends Feature<NoFeatureConfig> {
    protected final BlockState plant;
    private final List<Block> blocks;
    public CustomScatteredPlantFeature(Codec<NoFeatureConfig> configFactoryIn, BlockState plantIn, List<Block> blocks) {
        super(configFactoryIn);
        this.plant = plantIn;
        this.blocks = blocks;
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int i = 0;

        for (int j = 0; j < 64; ++j) {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (reader.isAirBlock(blockpos) && blocks.contains(reader.getBlockState(blockpos.down()).getBlock())) {
                reader.setBlockState(blockpos, this.plant, 2);
                ++i;
            }
        }

        return i > 0;
    }
}