package com.kielson.item.custom;

import com.kielson.item.ModToolMaterials;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Rarity;

public class EchoScythe extends Item {
    private static final float ATTACK_DAMAGE = 5.0f;
    private static final float ATTACK_SPEED = 1.9f;
    private static final float MAX_HEAL_AMOUNT = 8.0f;

    public EchoScythe(Settings settings) {
        super(settings.rarity(Rarity.EPIC)
                .sword(ModToolMaterials.ECHO_SCYTHE, ATTACK_DAMAGE - 1.0f, -(4.0f - ATTACK_SPEED)));
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        float attackerHealth = attacker.getHealth();
        float attackerMaxHealth = attacker.getMaxHealth();
        float attackDamage = (float) attacker.getAttributeValue(EntityAttributes.ATTACK_DAMAGE);

        float healAmount = attackDamage * (0.25f + ((attackerMaxHealth - attackerHealth) / attackerMaxHealth) * 0.667f);

        attacker.heal(Math.min(MAX_HEAL_AMOUNT, healAmount));
        if(healAmount >= 1) target.playSound(SoundEvents.ENTITY_PHANTOM_AMBIENT, 1.5f, 1.75f);
        super.postHit(stack, target, attacker);
    }
}
