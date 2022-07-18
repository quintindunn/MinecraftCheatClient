package dev.quintindunn.hackmore.mods;

import dev.quintindunn.hackmore.Hackmore;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

public class FullBright {

    private static int FullbrightState = 0;
    private static final SimpleOption<Double> gammaBypass = new SimpleOption<>("options.gamma", SimpleOption.emptyTooltip(), (optionText, value) -> Text.empty(), SimpleOption.DoubleSliderCallbacks.INSTANCE.withModifier(
            d -> (double) 20d, d -> 1
    ), 0.5, value -> {});

    public static SimpleOption<Double> getGammaBypass() {
        // force value
        gammaBypass.setValue(1.0);
        return gammaBypass;
    }

    public static boolean isFullBrightEnabled()
    {
        return FullbrightState == 1;
    }

    public static void setFullBrightState(boolean state)
    {
        MinecraftClient client = MinecraftClient.getInstance();
        if (state)
        {
            FullbrightState = 1;
            client.worldRenderer.reload();
        }
        else
        {
            FullbrightState = 0;
            client.worldRenderer.reload();
        }

    }

    public static void toggleFullBright()
    {
        Hackmore.getInstance().FullBrightEnabled = !Hackmore.getInstance().FullBrightEnabled;
        setFullBrightState(Hackmore.getInstance().FullBrightEnabled);
    }
}
