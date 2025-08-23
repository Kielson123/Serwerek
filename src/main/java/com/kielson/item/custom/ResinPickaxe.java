package com.kielson.item.custom;

import com.kielson.item.ModToolMaterials;
import com.kielson.util.TimeToLive;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UseCooldownComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ResinPickaxe extends Item {
    private static final float ATTACK_DAMAGE = 2.0f;
    private static final float ATTACK_SPEED = 1.2f;
    private static final int USE_COOLDOWN = 150;
    private static final int ABILITY_RADIUS = 20;
    private static final int ABILITY_LENGTH = 10;
    private static final int ABILITY_ITEM_DAMAGE = 15;

    public ResinPickaxe(Settings settings) {
        super(settings
                .rarity(Rarity.EPIC)
                .pickaxe(ModToolMaterials.RESIN_PICKAXE, ATTACK_DAMAGE - 1.0f, -(4.0f - ATTACK_SPEED))
                .component(DataComponentTypes.USE_COOLDOWN, new UseCooldownComponent(USE_COOLDOWN)));
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient) return ActionResult.FAIL;

        BlockPos playerPos = user.getBlockPos();
        BlockPos blockPos;
        for (int y = -ABILITY_RADIUS; y <= ABILITY_RADIUS; y++) {
            for (int z = -ABILITY_RADIUS; z <= ABILITY_RADIUS; z++) {
                for (int x = -ABILITY_RADIUS; x <= ABILITY_RADIUS; x++) {
                    blockPos = playerPos.add(x, y, z);
                    BlockState block = world.getBlockState(blockPos);
                    if (block.isIn(BlockTags.DIAMOND_ORES)){
                        makeBlockOutline(block, blockPos, world, Material.DIAMOND);
                    }if (block.isIn(BlockTags.EMERALD_ORES)){
                        makeBlockOutline(block, blockPos, world, Material.EMERALD);
                    }if (block.isIn(BlockTags.GOLD_ORES)){
                        makeBlockOutline(block, blockPos, world, Material.GOLD);
                    }if (block.isIn(BlockTags.IRON_ORES)){
                        makeBlockOutline(block, blockPos, world, Material.IRON);
                    }if (block.isIn(BlockTags.LAPIS_ORES)){
                        makeBlockOutline(block, blockPos, world, Material.LAPIS);
                    }if (block.isIn(BlockTags.REDSTONE_ORES)){
                        makeBlockOutline(block, blockPos, world, Material.REDSTONE);
                    }if (block.isIn(BlockTags.COPPER_ORES)){
                        makeBlockOutline(block, blockPos, world, Material.COPPER);
                    }if (block.isIn(BlockTags.COAL_ORES)){
                        makeBlockOutline(block, blockPos, world, Material.COAL);
                    }if (block.isOf(Blocks.ANCIENT_DEBRIS)){
                        makeBlockOutline(block, blockPos, world, Material.ANCIENT_DEBRIS);
                    }
                }}}
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        user.getStackInHand(hand).damage(ABILITY_ITEM_DAMAGE, user, hand);
        user.playSound(SoundEvents.ENTITY_ZOMBIE_VILLAGER_CONVERTED, 0.75f,1.75f);
        return ActionResult.SUCCESS;
    }

    private void makeBlockOutline(BlockState blockState, BlockPos blockPos, World world, Material material) {
        DisplayEntity.BlockDisplayEntity blockDisplay = EntityType.BLOCK_DISPLAY.create(world, SpawnReason.COMMAND);
        assert blockDisplay != null;

        blockDisplay.setBlockState(blockState);
        blockDisplay.setGlowing(true);
        blockDisplay.setPosition(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        blockDisplay.setGlowColorOverride(material.color);
        blockDisplay.setDisplayWidth(0.9f);
        blockDisplay.setDisplayHeight(0.9f);
        ((TimeToLive) blockDisplay).serwerek$setTimeToLive(ABILITY_LENGTH * 20);

        world.spawnEntity(blockDisplay);
    }

    private enum Material{
        ANCIENT_DEBRIS(0x66382d),
        REDSTONE(0xff0000),
        DIAMOND(0x1ed0d6),
        LAPIS(0x1044ac),
        EMERALD(0x17c544),
        GOLD(0xfcee4b),
        IRON(0xd8af93),
        COPPER(0xc16746),
        COAL(0x000000);

        public final int color;
        Material(int color) {
            this.color = color;
        }
    }
}

