package com.kielson.item;

import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import static com.kielson.Serwerek.MOD_ID;
import static net.minecraft.item.equipment.EquipmentAssetKeys.REGISTRY_KEY;

public interface ModArmorMaterials extends ArmorMaterials {

    class ModEquipmentAssetKeys {

        static RegistryKey<EquipmentAsset> register(String name) {
            return RegistryKey.of(REGISTRY_KEY, Identifier.of(MOD_ID, name));
        }
    }
}
