package dev.quintindunn.hackmore;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hackmore implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("hackmore");
	private static Hackmore instance;
	private AutoFish fisher;
	private static Xray xray;

	// Cheat (enabled) booleans
	public boolean AutoFishEnabled = false;
	public boolean XrayEnabled = false;


	@Override
	public void onInitialize() {
		if (instance==null) instance = this;
		this.fisher = new AutoFish();
		this.xray = new Xray();

		ClientTickEvents.END_CLIENT_TICK.register(this::tick);

	}

	public static Hackmore getInstance() {
		return instance;
	}

	public void setRecast(int ticks){
		fisher.setRecast(ticks);
	}
	public static boolean shouldDrawSide(BlockState state) {
		return xray.showBlock(state);
	}

	public void tick(MinecraftClient client) {
		fisher.tick(client);
	}

	public int getEnabledCheatsCount(){
		int count = 0;
		if (AutoFishEnabled) count++;
		if (XrayEnabled) count++;
		return count;
	}
	public String[] getEnabledCheats(){
		String[] cheats = new String[getEnabledCheatsCount()];
		int x = 0;
		if (AutoFishEnabled) {cheats[x] = "AutoFish";x++;}
		if (XrayEnabled) {cheats[x] = "X-Ray";x++;}

		return cheats;
	}

}
