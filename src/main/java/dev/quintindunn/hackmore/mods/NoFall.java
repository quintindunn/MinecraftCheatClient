package dev.quintindunn.hackmore.mods;

import dev.quintindunn.hackmore.Hackmore;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFall {

    private boolean isDamageYVelocity(ClientPlayerEntity player)
    {
        return player.getVelocity().y < -0.5;
    }

    public static void setNoFallState(boolean state)
    {
        Hackmore.getInstance().NoFallEnabled = state;
    }

    public static void toggleNoFall()
    {
        setNoFallState(!Hackmore.getInstance().NoFallEnabled);
    }

    public void tick(MinecraftClient client)
    {
        if (!Hackmore.getInstance().NoFallEnabled)
            return;

        ClientPlayerEntity player = client.player;
        if (player != null)
        {
            if(player.fallDistance <= (player.isFallFlying() ? 1 : 2))
                return;

            if(player.isFallFlying() && player.isSneaking() && !isDamageYVelocity(player))
                return;

            player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
        }

    }


}
