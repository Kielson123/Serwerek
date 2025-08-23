package com.kielson.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.*;

public class ShatteredMirror extends Item {
    private static final int USE_COOLDOWN = 15;
    private static final int DURABILITY = 152;
    private static final int ABILITY_RADIUS = 10;
    private static final int ABILITY_ITEM_DAMAGE = 1;

    public ShatteredMirror(Settings settings) {
        super(settings.rarity(Rarity.EPIC)
                .maxDamage(DURABILITY)
                .repairable(Items.GILDED_BLACKSTONE)
                .useCooldown(USE_COOLDOWN));
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient) return ActionResult.FAIL;
        ItemStack stack = user.getStackInHand(hand);
        int executions = 0;

        Collection<StatusEffectInstance> userStatusEffects = new ArrayList<>(user.getStatusEffects());
        for (StatusEffectInstance statusEffect : userStatusEffects) {
            if(!isPositiveEffect(statusEffect)){
                executions++;
                user.removeStatusEffect(statusEffect.getEffectType());
                StatusEffectInstance newStatusEffect = reverseStatusEffect(statusEffect, user);
                if(newStatusEffect != null){
                    user.addStatusEffect(newStatusEffect);
                }
            }
        }

        Box box = user.getBoundingBox().expand(ABILITY_RADIUS);
        List<LivingEntity> targets = world.getEntitiesByClass(LivingEntity.class, box, LivingEntity::isAlive);
        for (LivingEntity target : targets) {
            if (target == user) continue;
            Collection<StatusEffectInstance> targetStatusEffects = new ArrayList<>(target.getStatusEffects());
            for (StatusEffectInstance statusEffect : targetStatusEffects) {
                if (isPositiveEffect(statusEffect)) {
                    executions++;
                    target.removeStatusEffect(statusEffect.getEffectType());
                    StatusEffectInstance newStatusEffect = reverseStatusEffect(statusEffect, target);
                    if (newStatusEffect != null) {
                        target.addStatusEffect(newStatusEffect);
                    }
                }
            }
        }

        if(executions == 0) return ActionResult.FAIL;
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        stack.damage(ABILITY_ITEM_DAMAGE, user, hand);
        user.playSound(SoundEvents.ENTITY_SKELETON_CONVERTED_TO_STRAY, 0.75f, 1.5f);
        return ActionResult.CONSUME;
    }

    private static boolean isPositiveEffect(StatusEffectInstance statusEffect) {
        RegistryEntry<StatusEffect> effect = statusEffect.getEffectType();
        return effect == StatusEffects.SPEED || effect == StatusEffects.HASTE || effect == StatusEffects.STRENGTH || effect == StatusEffects.INSTANT_HEALTH || effect == StatusEffects.JUMP_BOOST ||
                effect == StatusEffects.REGENERATION || effect == StatusEffects.RESISTANCE || effect == StatusEffects.FIRE_RESISTANCE || effect == StatusEffects.WATER_BREATHING ||
                effect == StatusEffects.INVISIBILITY || effect == StatusEffects.NIGHT_VISION || effect == StatusEffects.HEALTH_BOOST || effect == StatusEffects.ABSORPTION ||
                effect == StatusEffects.SATURATION || effect == StatusEffects.LUCK || effect == StatusEffects.SLOW_FALLING || effect == StatusEffects.CONDUIT_POWER ||
                effect == StatusEffects.DOLPHINS_GRACE || effect == StatusEffects.HERO_OF_THE_VILLAGE;
    }

    private static StatusEffectInstance reverseStatusEffect(StatusEffectInstance original, LivingEntity entity) {
        RegistryEntry<StatusEffect> oldStatusEffect = original.getEffectType();
        RegistryEntry<StatusEffect> newStatusEffect = oldStatusEffect;

        if (oldStatusEffect == StatusEffects.SPEED) newStatusEffect = StatusEffects.SLOWNESS;
        else if (oldStatusEffect == StatusEffects.HASTE) newStatusEffect = StatusEffects.MINING_FATIGUE;
        else if (oldStatusEffect == StatusEffects.STRENGTH) newStatusEffect = StatusEffects.WEAKNESS;
        else if (oldStatusEffect == StatusEffects.INSTANT_HEALTH) newStatusEffect = StatusEffects.INSTANT_DAMAGE;
        else if (oldStatusEffect == StatusEffects.JUMP_BOOST) newStatusEffect = StatusEffects.LEVITATION;
        else if (oldStatusEffect == StatusEffects.REGENERATION) newStatusEffect = StatusEffects.POISON;
        else if (oldStatusEffect == StatusEffects.INVISIBILITY) newStatusEffect = StatusEffects.GLOWING;
        else if (oldStatusEffect == StatusEffects.NIGHT_VISION) newStatusEffect = StatusEffects.BLINDNESS;
        else if (oldStatusEffect == StatusEffects.SATURATION) newStatusEffect = StatusEffects.HUNGER;
        else if (oldStatusEffect == StatusEffects.LUCK) newStatusEffect = StatusEffects.UNLUCK;
        else if (oldStatusEffect == StatusEffects.SLOW_FALLING) newStatusEffect = StatusEffects.LEVITATION;
        else if (oldStatusEffect == StatusEffects.HERO_OF_THE_VILLAGE) newStatusEffect = StatusEffects.BAD_OMEN;
        else if (oldStatusEffect == StatusEffects.GLOWING) newStatusEffect = StatusEffects.INVISIBILITY;
        else if (oldStatusEffect == StatusEffects.BAD_OMEN) newStatusEffect = StatusEffects.HERO_OF_THE_VILLAGE;
        else if (oldStatusEffect == StatusEffects.SLOWNESS) newStatusEffect = StatusEffects.SPEED;
        else if (oldStatusEffect == StatusEffects.MINING_FATIGUE) newStatusEffect = StatusEffects.HASTE;
        else if (oldStatusEffect == StatusEffects.INSTANT_DAMAGE) newStatusEffect = StatusEffects.INSTANT_HEALTH;
        else if (oldStatusEffect == StatusEffects.BLINDNESS) newStatusEffect = StatusEffects.NIGHT_VISION;
        else if (oldStatusEffect == StatusEffects.HUNGER) newStatusEffect = StatusEffects.SATURATION;
        else if (oldStatusEffect == StatusEffects.WEAKNESS) newStatusEffect = StatusEffects.STRENGTH;
        else if (oldStatusEffect == StatusEffects.POISON) newStatusEffect = StatusEffects.REGENERATION;
        else if (oldStatusEffect == StatusEffects.WITHER) newStatusEffect = StatusEffects.REGENERATION;
        else if (oldStatusEffect == StatusEffects.LEVITATION) newStatusEffect = StatusEffects.SLOW_FALLING;
        else if (oldStatusEffect == StatusEffects.UNLUCK) newStatusEffect = StatusEffects.LUCK;
        else if (oldStatusEffect == StatusEffects.DARKNESS) newStatusEffect = StatusEffects.NIGHT_VISION;
        else if (oldStatusEffect == StatusEffects.RESISTANCE) newStatusEffect = null;
        else if (oldStatusEffect == StatusEffects.HEALTH_BOOST) newStatusEffect = null;
        else if (oldStatusEffect == StatusEffects.ABSORPTION) newStatusEffect = null;
        else if (oldStatusEffect == StatusEffects.CONDUIT_POWER) newStatusEffect = null;
        else if (oldStatusEffect == StatusEffects.DOLPHINS_GRACE) newStatusEffect = null;
        else if (oldStatusEffect == StatusEffects.NAUSEA) newStatusEffect = null;
        else if (oldStatusEffect == StatusEffects.WIND_CHARGED) newStatusEffect = null;
        else if (oldStatusEffect == StatusEffects.WEAVING) newStatusEffect = null;
        else if (oldStatusEffect == StatusEffects.OOZING) newStatusEffect = null;
        else if (oldStatusEffect == StatusEffects.INFESTED) newStatusEffect = null;
        else if (oldStatusEffect == StatusEffects.WATER_BREATHING) {
            newStatusEffect = null;
            entity.setAir(0);
        }
        else if (oldStatusEffect == StatusEffects.FIRE_RESISTANCE) {
            newStatusEffect = null;
            entity.setOnFireFor(original.getDuration());
        }

        return newStatusEffect == null ? null : new StatusEffectInstance(newStatusEffect, original.getDuration(), original.getAmplifier(), original.isAmbient(), true);
    }
}
