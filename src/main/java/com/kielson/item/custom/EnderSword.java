package com.kielson.item.custom;

import com.kielson.item.ModToolMaterials;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UseCooldownComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

public class EnderSword extends Item {
    private static final float ATTACK_DAMAGE = 8.0f;
    private static final float ATTACK_SPEED = 1.6f;
    private static final int USE_COOLDOWN = 10;
    private static final int ABILITY_DISTANCE = 10;
    private static final int ABILITY_ITEM_DAMAGE = 5;
    public static final double EXPERIENCE_GAIN = 15;

    public EnderSword(Settings settings) {
        super(settings.sword(ModToolMaterials.ENDER_SWORD, ATTACK_DAMAGE - 1.0f, -(4.0f - ATTACK_SPEED))
                .component(DataComponentTypes.USE_COOLDOWN, new UseCooldownComponent(USE_COOLDOWN))
                .rarity(Rarity.EPIC));
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient) return ActionResult.FAIL;

        Vec3d look = user.getRotationVec(1.0F);

        double targetX = user.getX() + look.x * ABILITY_DISTANCE;
        double targetZ = user.getZ() + look.z * ABILITY_DISTANCE;

        if (world instanceof ServerWorld serverWorld) {
            int topY = serverWorld.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPos(MathHelper.floor(targetX), 0, MathHelper.floor(targetZ))).getY();
            double targetY = topY + 0.1;

            user.requestTeleport(targetX, targetY, targetZ);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        user.getStackInHand(hand).damage(ABILITY_ITEM_DAMAGE, user, hand);
        user.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 0.75f, 1.5f);
        return ActionResult.SUCCESS;
    }
}
