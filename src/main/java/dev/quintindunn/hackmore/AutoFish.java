package dev.quintindunn.hackmore;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;


public class AutoFish {
    public int recast = -1;

    public void tick(MinecraftClient client) {
        if (recast>0) {
            recast--;
        }

        if (recast==0) {
            assert client.interactionManager != null;
            assert client.player != null;
            client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
            recast = -1;
        }

    }

    public void setRecast(int ticks) {
        recast = ticks;
    }
}
