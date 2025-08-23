package com.kielson.mixin;

import com.kielson.util.TimeToLive;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DisplayEntity.class)
abstract class DisplayEntityMixin extends Entity implements TimeToLive {
    @Unique
    DisplayEntity display = (DisplayEntity)(Object)this;
    @Unique
    World world = display.getWorld();
    public DisplayEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Unique
    private static final TrackedData<Integer> TIME_TO_LIVE = DataTracker.registerData(DisplayEntityMixin.class, TrackedDataHandlerRegistry.INTEGER);

    @Unique
    private static final String TIME_TO_LIVE_NBT_KEY = "time_to_live";

    @Inject(method = "initDataTracker", at = @At(value = "HEAD"))
    private void dataTracker(DataTracker.Builder builder, CallbackInfo ci){
        builder.add(TIME_TO_LIVE, -1);
    }

    @Inject(method = "writeCustomData", at = @At(value = "TAIL"))
    private void writeTimeToLive(WriteView view, CallbackInfo ci){
        view.putFloat(TIME_TO_LIVE_NBT_KEY, serwerek$getTimeToLive());
    }

    @Inject(method = "readCustomData", at = @At(value = "TAIL"))
    private void readTimeToLive(ReadView view, CallbackInfo ci){
        this.serwerek$setTimeToLive(view.getInt(TIME_TO_LIVE_NBT_KEY, 0));
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void blockOutlinesTick(CallbackInfo ci){
        if(world.isClient()) return;

        if(world.getBlockState(display.getBlockPos()).isOf(Blocks.AIR)){
            dataTracker.set(TIME_TO_LIVE, 0);
        }
        if(dataTracker.get(TIME_TO_LIVE) != -1){
            if(dataTracker.get(TIME_TO_LIVE) == 0){
                this.kill((ServerWorld) world);
            }
            if(dataTracker.get(TIME_TO_LIVE) > 0){
                dataTracker.set(TIME_TO_LIVE, dataTracker.get(TIME_TO_LIVE) - 1);
            }
        }
    }

    @Unique
    @Override
    public void serwerek$setTimeToLive(int ticks){
        dataTracker.set(TIME_TO_LIVE, ticks);
    }

    @Unique
    @Override
    public int serwerek$getTimeToLive(){
        return dataTracker.get(TIME_TO_LIVE);
    }
}
