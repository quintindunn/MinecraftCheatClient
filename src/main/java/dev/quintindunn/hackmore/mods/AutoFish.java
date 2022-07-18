package dev.quintindunn.hackmore.mods;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;


public class AutoFish {
    public static int recast = -1;
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

    public static void setRecast(int ticks) {
        recast = ticks;
    }
}
