package com.kielson.item.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

public class HeartOfNether extends Item {
    private static final int ABILITY_DURATION = 60;
    private static final int ABILITY_COOLDOWN = 90;

    public HeartOfNether(Settings settings) {
        super(settings.rarity(Rarity.EPIC)
                .useCooldown(ABILITY_COOLDOWN)
                .repairable(Items.NETHER_STAR)
                .maxDamage(2));
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient) return ActionResult.FAIL;
        ItemStack stack = user.getStackInHand(hand);

        if(stack.getDamage() <= 0){
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, ABILITY_DURATION * 20, 0, false, true));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, ABILITY_DURATION * 20, 2, false, true));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, ABILITY_DURATION * 20, 0, false, true));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, ABILITY_DURATION * 20, 1, false, true));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, ABILITY_DURATION * 20, 1, false, true));
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            stack.setDamage(2);
            user.playSound(SoundEvents.ENTITY_WITHER_DEATH, 0.75f,2f);
            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }
}
