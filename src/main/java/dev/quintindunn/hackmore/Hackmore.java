package dev.quintindunn.hackmore;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Hackmore implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("hackmore");
	private static Hackmore instance;
	private AutoFish fisher;
	public boolean AutoFishEnabled = false;


	@Override
	public void onInitialize() {
		if (instance==null) instance = this;
		this.fisher = new AutoFish();

		ClientTickEvents.END_CLIENT_TICK.register(this::tick);

	}

	public static Hackmore getInstance() {
		return instance;
	}

	public void setRecast(int ticks){
		fisher.setRecast(ticks);
	}

	public void tick(MinecraftClient client) {
		fisher.tick(client);
	}

	public int getEnabledCheatsCount(){
		int count = 0;
		if (AutoFishEnabled) count++;
		return count;
	}
	public String[] getEnabledCheats(){
		String[] cheats = new String[getEnabledCheatsCount()];
		int x = 0;
		if (AutoFishEnabled) cheats[x] = "AutoFish";
		return cheats;
	}

}
