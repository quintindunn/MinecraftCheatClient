package dev.quintindunn.hackmore.mods;

import dev.quintindunn.hackmore.Hackmore;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

import java.util.HashSet;

public class Xray {
    private static final HashSet<String> xrayBlocks = new HashSet<>();
    private static int FullbrightState = 0;

    public Xray() {
        xrayBlocks.add("Block{minecraft:coal_ore}");
        xrayBlocks.add("Block{minecraft:copper_ore}");
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
        xrayBlocks.add("Block{minecraft:chest}");
        xrayBlocks.add("Block{minecraft:mob_spawner}");
        xrayBlocks.add("Block{minecraft:spawner}");
        xrayBlocks.add("Block{minecraft:bookshelf}");
        xrayBlocks.add("Block{minecraft:ancient_debris}");
        xrayBlocks.add("Block{minecraft:nether_gold_ore}");
        xrayBlocks.add("Block{minecraft:nether_quartz_ore}");
        xrayBlocks.add("Block{minecraft:blackstone}");
        xrayBlocks.add("Block{minecraft:glowstone}");
        xrayBlocks.add("Block{minecraft:gold_block}");
        xrayBlocks.add("Block{minecraft:bone_block}");
        xrayBlocks.add("Block{minecraft:obsidian}");
        xrayBlocks.add("Block{minecraft:nether_brick}");
        xrayBlocks.add("Block{minecraft:magma_block}");
        xrayBlocks.add("Block{minecraft:lava}");
        xrayBlocks.add("Block{minecraft:water}");
    }

    private static final SimpleOption<Double> gammaBypass = new SimpleOption<>("options.gamma", SimpleOption.emptyTooltip(), (optionText, value) -> Text.empty(), SimpleOption.DoubleSliderCallbacks.INSTANCE.withModifier(
            d -> 20d, d -> 1
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


    public static boolean showBlock(BlockState state) {
        return xrayBlocks.contains(state.getBlock().toString());
    }

    public static void setFullBrightState(boolean state)
    {
        if (state)
            FullbrightState = 1;
        else
            FullbrightState = 0;
    }

    public static void toggleXray()
    {
        MinecraftClient client = MinecraftClient.getInstance();
        Hackmore.getInstance().XrayEnabled = !Hackmore.getInstance().XrayEnabled;
        setFullBrightState(Hackmore.getInstance().XrayEnabled);
        client.worldRenderer.reload();
    }

    public static boolean shouldDrawSide(BlockState state) {
        return showBlock(state);
    }
}
