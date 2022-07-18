package dev.quintindunn.hackmore.mods;

import dev.quintindunn.hackmore.Hackmore;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class Speed {

    public static final float maxSpeed = 1.2f;
    public static final float minSpeed = 1.02f;
    private static final double maxSpeedVelocity = 0.3d;
    private final boolean boostY = false;
    private static double speedBoost = 1.0d;
    public void tick(MinecraftClient client) {
        if (client.player != null)
        {
            // If speed hack enabled, the game isn't paused, and the player is sprinting, and player is moving forwards
            if (Hackmore.getInstance().SpeedEnabled && !MinecraftClient.getInstance().isPaused() && client.player.isSprinting())
            {
                // Add player speed boost up to maxSpeedVelocity
                Vec3d playerVelocity = client.player.getVelocity();
                if (boostY)
                {
                    client.player.setVelocity(
                            Math.min(playerVelocity.x * speedBoost, maxSpeedVelocity),
                            Math.min(playerVelocity.y * speedBoost, maxSpeedVelocity),
                            Math.min(playerVelocity.z * speedBoost, maxSpeedVelocity)
                    );
                }
                else
                {
                    client.player.setVelocity(
                            Math.min(playerVelocity.x * speedBoost, maxSpeedVelocity),
                            playerVelocity.y,
                            Math.min(playerVelocity.z * speedBoost, maxSpeedVelocity)
                    );
                }

            }

        }
    }
    public static void setSpeedEnabled(boolean state)
    {
        Hackmore.getInstance().SpeedEnabled = state;
    }

    public static void setSpeed(double value)
    {
        speedBoost = value;
    }
    public static double getSpeed()
    {
        return speedBoost;
    }
}
