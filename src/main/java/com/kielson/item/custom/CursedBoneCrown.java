package com.kielson.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import static com.kielson.Serwerek.MOD_ID;

public class CursedBoneCrown extends Item {
    private static final double ARMOR_POINTS = 1.0;
    public static final double VILLAGER_DISCOUNT = 33.4;

    public CursedBoneCrown(Settings settings) {
        super(settings.rarity(Rarity.EPIC)
                .component(
                        DataComponentTypes.EQUIPPABLE,
                        EquippableComponent.builder(EquipmentSlot.HEAD).swappable(true).build()
                )
                .maxCount(1)
                .component(
                        DataComponentTypes.ATTRIBUTE_MODIFIERS,
                        AttributeModifiersComponent.builder()
                                .add(EntityAttributes.ARMOR, new EntityAttributeModifier(
                                                Identifier.of(MOD_ID, "cursed_bone_crown"),
                                                ARMOR_POINTS,
                                                EntityAttributeModifier.Operation.ADD_VALUE),
                                        AttributeModifierSlot.forEquipmentSlot(EquipmentSlot.HEAD))
                                .build())
        );
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
