package dev.quintindunn.hackmore;

import net.minecraft.block.BlockState;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

import java.util.HashSet;

public class Xray {
    private HashSet<String> xrayBlocks = new HashSet();
    private static int FullbrightState = 1;

    public Xray() {
        xrayBlocks.add("Block{minecraft:coal_ore}");
        xrayBlocks.add("Block{minecraft:iron_ore}");
        xrayBlocks.add("Block{minecraft:gold_ore}");
        xrayBlocks.add("Block{minecraft:diamond_ore}");
        xrayBlocks.add("Block{minecraft:emerald_ore}");
        xrayBlocks.add("Block{minecraft:lapis_ore}");
        xrayBlocks.add("Block{minecraft:redstone_ore}");
        xrayBlocks.add("Block{minecraft:deepslate_coal_ore}");
        xrayBlocks.add("Block{minecraft:deepslate_iron_ore}");
        xrayBlocks.add("Block{minecraft:deepslate_gold_ore}");
        xrayBlocks.add("Block{minecraft:deepslate_diamond_ore}");
        xrayBlocks.add("Block{minecraft:deepslate_emerald_ore}");
        xrayBlocks.add("Block{minecraft:deepslate_lapis_ore}");
        xrayBlocks.add("Block{minecraft:deepslate_redstone_ore}");
    }

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


    public boolean showBlock(BlockState state) {
        return xrayBlocks.contains(state.getBlock().toString());
    }

    public static void setFullbrightState(boolean state)
    {
        if (state)
            FullbrightState = 1;
        else
            FullbrightState = 0;
    }
}
