package com.cannolicatfish.rankine.blocks.alloys;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;

import static com.cannolicatfish.rankine.init.RankineBlocks.ALLOY_BLOCK_TILE;

public class AlloyBlockTile extends TileEntity {
    public String comp = "";
    public AlloyBlockTile() {
        super(ALLOY_BLOCK_TILE);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.comp = nbt.getString("comp");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound.putString("comp", this.comp);
        return compound;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String s) {
        this.comp = s;
    }
}
