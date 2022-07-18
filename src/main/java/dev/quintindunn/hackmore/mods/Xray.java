package dev.quintindunn.hackmore.mods;

import dev.quintindunn.hackmore.Hackmore;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import java.util.HashSet;

public class Xray implements ClientModInitializer {
    private static KeyBinding hotkey = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle X-Ray", InputUtil.Type.KEYSYM, 88, "key.categories.misc"));

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

    public static void toggleXray()
    {
        MinecraftClient client = MinecraftClient.getInstance();
        Hackmore.getInstance().XrayEnabled = !Hackmore.getInstance().XrayEnabled;
        setFullbrightState(Hackmore.getInstance().XrayEnabled);
        client.worldRenderer.reload();
    }

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (hotkey.wasPressed()) {
                toggleXray();
            }
        });
    }
}
