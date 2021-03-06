package com.cannolicatfish.rankine.items;

import com.cannolicatfish.rankine.entities.ReactiveItemEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class ReactiveItem extends Item {
    private final float radius;
    private boolean canBreakBlocks;
    public ReactiveItem(Properties properties) {
        super(properties);
        this.radius = 1f;
        this.canBreakBlocks = false;
    }

    public ReactiveItem(Properties properties, float radius, boolean canBreakBlocks) {
        super(properties);
        this.radius = radius;
        this.canBreakBlocks = canBreakBlocks;
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    @Override
    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        ReactiveItemEntity result = new ReactiveItemEntity(location.world,location.getPosX(),location.getPosY(),location.getPosZ(), this.radius, this.canBreakBlocks, itemstack);
        result.setPickupDelay(40);
        result.setMotion(location.getMotion());
        return result;
    }
}
