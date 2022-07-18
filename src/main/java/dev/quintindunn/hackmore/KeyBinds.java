package dev.quintindunn.hackmore;

import dev.quintindunn.hackmore.mods.Flight;
import dev.quintindunn.hackmore.mods.FullBright;
import dev.quintindunn.hackmore.mods.Xray;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class KeyBinds implements ClientModInitializer {
    // Set default binds
    private static final int defaultFullBrightBind = 71;  // G
    private static final int defaultFlightBind = 72;  // H
    private static final int defaultXRayBind = 88;  // X

    // Register KeyBinds
    private static final KeyBinding xrayKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle X-Ray", InputUtil.Type.KEYSYM, defaultXRayBind, "key.categories.misc"));
    private static final KeyBinding fullBrightKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle Full Bright", InputUtil.Type.KEYSYM, defaultFullBrightBind, "key.categories.misc"));
    private static final KeyBinding flightKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle Flight", InputUtil.Type.KEYSYM, defaultFlightBind, "key.categories.misc"));


    // Respond to KeyPresses
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (xrayKeybind.wasPressed())
                Xray.toggleXray();

            if (fullBrightKeybind.wasPressed())
                FullBright.toggleFullBright();

            if (flightKeybind.wasPressed())
                Flight.toggleFlight();
        });
    }
}
