package com.kielson.item.custom;

import com.kielson.item.ModToolMaterials;
import com.kielson.util.ModComponents;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.*;

public class AmethystAxe extends Item {
    private static final float ATTACK_DAMAGE = 9.0f;
    private static final float ATTACK_SPEED = 1.2f;

    private static final Direction[] DIRS = {
            Direction.UP, Direction.DOWN, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST
    };

    public AmethystAxe(Settings settings) {
        super(settings.rarity(Rarity.EPIC)
                .axe(ModToolMaterials.AMETHYST_AXE, ATTACK_DAMAGE - 1.0f, -(4.0f - ATTACK_SPEED))
                .component(ModComponents.ACTIVE, false));
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient) return ActionResult.FAIL;

        ItemStack stack = user.getStackInHand(hand);
        boolean isActive = Boolean.TRUE.equals(stack.get(ModComponents.ACTIVE));
        stack.set(ModComponents.ACTIVE, !isActive);

        if(isActive){
            user.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.75f, 2f);
        }else {
            user.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 0.75f, 0.1f);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if(world.isClient) return false;
        if(!isWood(state)) return false;
        if(!(miner instanceof PlayerEntity player)) return false;

        List<BlockPos> blocksToBreak = getBlocksToBreak(world, state, pos);;

        if(Boolean.TRUE.equals(stack.get(ModComponents.ACTIVE)) && !blocksToBreak.isEmpty()){
            for (BlockPos blockPos : blocksToBreak) {
                world.breakBlock(blockPos, true, player);
            }
        }

        if(state.getHardness(world, pos) > 0.0f){
            stack.damage(blocksToBreak.size(), player, player.getMainHandStack() == stack ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        }
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return true;
    }

    private ArrayList<BlockPos> getBlocksToBreak(World world, BlockState startState, BlockPos start) {
        final Block target = startState.getBlock();
        final int LIMIT = 128;

        Set<BlockPos> result = new HashSet<>(LIMIT * 2);
        Set<BlockPos> seen = new HashSet<>(LIMIT * 2);
        ArrayDeque<BlockPos> queue = new ArrayDeque<>();

        seen.add(start);
        queue.add(start);

        while (!queue.isEmpty() && result.size() < LIMIT) {
            BlockPos curr = queue.removeFirst();

            if (world.getBlockState(curr).getBlock() == target) {
                result.add(curr);
            }

            for (Direction d : DIRS) {
                BlockPos n = curr.offset(d);
                if (seen.add(n)) {
                    if (world.getBlockState(n).getBlock() == target) {
                        if (result.size() < LIMIT) {
                            queue.addLast(n);
                        }
                    }
                }
            }
        }

        return new ArrayList<>(result);
    }

    private static boolean isWood(BlockState state){
        return state.isOf(Blocks.OAK_LOG) || state.isOf(Blocks.ACACIA_LOG) || state.isOf(Blocks.DARK_OAK_LOG) || state.isOf(Blocks.BIRCH_LOG) || state.isOf(Blocks.CHERRY_LOG) ||
                state.isOf(Blocks.JUNGLE_LOG) || state.isOf(Blocks.MANGROVE_LOG) || state.isOf(Blocks.PALE_OAK_LOG) || state.isOf(Blocks.SPRUCE_LOG) || state.isOf(Blocks.CRIMSON_HYPHAE) ||
                state.isOf(Blocks.WARPED_HYPHAE) || state.isOf(Blocks.MUSHROOM_STEM);
    }
}
