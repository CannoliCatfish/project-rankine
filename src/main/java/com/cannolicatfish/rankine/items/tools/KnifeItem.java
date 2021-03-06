package com.cannolicatfish.rankine.items.tools;

import com.cannolicatfish.rankine.init.RankineEnchantments;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootTable;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;

public class KnifeItem extends SwordItem {
    private final float attackDamage;
    private final float attackSpeed;

    public KnifeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
        this.attackSpeed = attackSpeedIn;
        this.attackDamage = (float)attackDamageIn + tier.getAttackDamage();
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.matchesBlock(Blocks.COBWEB)) {
            return 20.0F;
        } else if (state.isIn(BlockTags.LEAVES)) {
            return 10.0F;
        } else {
            Material material = state.getMaterial();
            return material != Material.CORAL && material != Material.GOURD ? 1.0F : 1.5F;
        }
    }


    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            int i = this.getUseDuration(stack) - timeLeft;
            if (i < 0) return;
            ((PlayerEntity) entityLiving).getCooldownTracker().setCooldown(this, 10);
        }
        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return ActionResult.resultConsume(itemstack);
    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        applyHitEffects(stack,target,attacker);
        return super.hitEntity(stack, target, attacker);
    }

    protected void applyHitEffects(ItemStack stack, LivingEntity target, LivingEntity attacker) {


        int i = EnchantmentHelper.getEnchantmentLevel(RankineEnchantments.POISON_ASPECT,stack);
        if (i > 0) {
            if (target.getCreatureAttribute() == CreatureAttribute.UNDEAD) {
                target.addPotionEffect(new EffectInstance(Effects.WEAKNESS,i * 60));
            } else {
                target.addPotionEffect(new EffectInstance(Effects.POISON,i * 60));
            }

        }
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if(worldIn.getBlockState(pos).getBlock() instanceof LeavesBlock && EnchantmentHelper.getEnchantmentLevel(RankineEnchantments.GRAFTING,stack) >= 1 && !worldIn.isRemote && worldIn.getGameRules().getBoolean(GameRules.DO_TILE_DROPS)
                && !worldIn.restoringBlockSnapshots) {
            ResourceLocation orig = worldIn.getBlockState(pos).getBlock().getRegistryName();
            if (orig != null) {
                ResourceLocation rs = new ResourceLocation(orig.getNamespace(), orig.getPath().split("_leaves")[0] + "_sapling");
                Block sapling = ForgeRegistries.BLOCKS.getValue(rs);
                if (sapling != null) {
                    double d0 = (double) (worldIn.rand.nextFloat() * 0.5F) + 0.25D;
                    double d1 = (double) (worldIn.rand.nextFloat() * 0.5F) + 0.25D;
                    double d2 = (double) (worldIn.rand.nextFloat() * 0.5F) + 0.25D;
                    ItemEntity itementity = new ItemEntity(worldIn, (double) pos.getX() + d0, (double) pos.getY() + d1, (double) pos.getZ() + d2, new ItemStack(sapling));
                    itementity.setDefaultPickupDelay();
                    worldIn.addEntity(itementity);
                }
            }
        }
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if (enchantment == Enchantments.SWEEPING || enchantment == Enchantments.FIRE_ASPECT || enchantment == Enchantments.KNOCKBACK) {
            return false;
        }
        return super.canApplyAtEnchantingTable(stack,enchantment);
    }
}



