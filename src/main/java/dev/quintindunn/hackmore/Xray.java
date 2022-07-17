package dev.quintindunn.hackmore;

import net.minecraft.block.BlockState;

import java.util.HashSet;

public class Xray {
    private HashSet<String> xrayBlocks = new HashSet();

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

    public boolean showBlock(BlockState state) {
        return xrayBlocks.contains(state.getBlock().toString());
    }

}
