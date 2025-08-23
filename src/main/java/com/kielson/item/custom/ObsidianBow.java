package com.kielson.item.custom;

import com.kielson.item.CustomBow;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;

public class ObsidianBow extends CustomBow {
    public ObsidianBow(Settings settings) {
        super(9.0,1.0 , 5, settings.rarity(Rarity.EPIC)
                .maxDamage(576));
    }

    @Override
    protected void shoot(LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target) {
        projectile.setNoGravity(true);
        super.shoot(shooter, projectile, index, speed, divergence, yaw, target);
    }
}
