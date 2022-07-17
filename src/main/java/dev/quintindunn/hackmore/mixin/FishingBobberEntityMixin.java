package dev.quintindunn.hackmore.mixin;

import dev.quintindunn.hackmore.Hackmore;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntity.class)
public abstract class FishingBobberEntityMixin {
	@Shadow private  boolean caughtFish;

	@Inject(at = @At("TAIL"), method = "onTrackedDataSet()V")
	public void onTrackedDataSet(TrackedData<?> data, CallbackInfo info) {
		MinecraftClient client = MinecraftClient.getInstance();

		if (caughtFish && Hackmore.getInstance().AutoFishEnabled) {
			Hackmore.LOGGER.info("Fish on!");
			assert client.interactionManager != null;
			client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
			Hackmore.getInstance().setRecast(20);
		}


	}



}
