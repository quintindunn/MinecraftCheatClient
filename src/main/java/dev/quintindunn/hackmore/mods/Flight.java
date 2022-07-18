package dev.quintindunn.hackmore.mods;

import dev.quintindunn.hackmore.Hackmore;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.math.Vec3d;

public class Flight {
    private static int mode = -1; // -1 = Off; 0 = Creative Fly; 1 = Vanilla bypass
    private static final double verticalSpeed = 0.4d;
    private static final float defaultAcceleration = 1.1f;
    private static final double maxVelocity = 0.4;

    private static int upTicks = 0;

    public void tick(MinecraftClient client)
    {
        double acceleration = defaultAcceleration;

        if (!Hackmore.getInstance().FlightEnabled)
            return;

        ClientPlayerEntity player = client.player;
        GameOptions options = client.options;

        KeyBinding jumpKey = options.jumpKey;
        KeyBinding sneakKey = options.sneakKey;
        KeyBinding forwardKey = options.forwardKey;
        KeyBinding backwardKey = options.backKey;
        KeyBinding leftKey = options.leftKey;
        KeyBinding rightKey = options.rightKey;

        if (mode == 0 && player != null)
        {
            Vec3d playerVelocity = player.getVelocity();
            double yVelocity = 0;

            if (jumpKey.isPressed())
                yVelocity += verticalSpeed;
            if (sneakKey.isPressed())
                yVelocity -= verticalSpeed;

            if (!(forwardKey.isPressed() || backwardKey.isPressed() || leftKey.isPressed() || rightKey.isPressed()))
                acceleration = 1;

            player.setVelocity(Math.min(playerVelocity.x * acceleration, maxVelocity), yVelocity, Math.min(playerVelocity.x * acceleration, maxVelocity));
        }
        else if (mode == 1 && player != null) // Vanilla flying disabled bypass
        {
            double yVelocity = -0.03126;
            Vec3d playerVelocity = player.getVelocity();
            if (jumpKey.isPressed())
            {
                upTicks += 1;
                if (upTicks == 25)
                    upTicks = 0;
                else
                    yVelocity += verticalSpeed;
            }
            if (sneakKey.isPressed())
                yVelocity -= verticalSpeed;

            int keysPressed = 0;

            if (forwardKey.isPressed()) keysPressed += 1;
            if (backwardKey.isPressed()) keysPressed += 1;
            if (leftKey.isPressed()) keysPressed += 1;
            if (rightKey.isPressed()) keysPressed += 1;

            if (keysPressed == 0)
                acceleration = 1;

            if (keysPressed == 1) yVelocity -= 0.01;
            else if (keysPressed > 1) yVelocity -= 0.018;
            player.setVelocity(Math.min(playerVelocity.x * acceleration, maxVelocity), yVelocity, Math.min(playerVelocity.x * acceleration, maxVelocity));
        }
    }

    public static void setFlightState(int state)
    {
        mode = state;
        Hackmore.getInstance().FlightEnabled = mode != -1;
    }

    public static void cycleFlight()
    {
        if (mode+1 > 1)
            setFlightState(-1);
        else
            setFlightState(mode + 1);
    }

    public static void toggleFlight()
    {
        Hackmore.getInstance().FlightEnabled = !Hackmore.getInstance().FlightEnabled;
    }

    public static String getMode()
    {
        if (mode == -1)
            return "OFF";
        else if (mode == 0)
            return "CREATIVE";
        else if (mode == 1)
            return "VANILLA BYPASS";
        else
            return "NULL";
    }



}
