package dev.quintindunn.hackmore;

import dev.quintindunn.hackmore.mods.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;

public class Hackmore implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("hackmore");
	private static Hackmore instance;

	// Cheats
	private AutoFish fisher;
	private Speed speed;
	private static Flight flight;
	private static NoFall nofall;

	// Cheat (enabled) booleans
	public boolean AutoFishEnabled = false;
	public boolean SpeedEnabled = false;
	public boolean XrayEnabled = false;
	public boolean FullBrightEnabled = false;
	public boolean FlightEnabled = false;
	public boolean NoFallEnabled = false;


	@Override
	public void onInitialize() {
		if (instance==null) instance = this;
		fisher = new AutoFish();
		flight = new Flight();
		speed = new Speed();
		nofall = new NoFall();
		ClientTickEvents.END_CLIENT_TICK.register(this::tick);

	}

	public static Hackmore getInstance() {
		return instance;
	}



	public void tick(MinecraftClient client) {
		fisher.tick(client);
		nofall.tick(client);
		speed.tick(client);
		flight.tick(client);
	}

	public int getEnabledCheatsCount(){
		return getEnabledCheats().size();
	}
	public HashSet<String> getEnabledCheats(){
		HashSet<String> cheatsEnabled = new HashSet<String>();
		int x = 0;
		if (AutoFishEnabled)
			cheatsEnabled.add("AutoFish");
		if (XrayEnabled)
			cheatsEnabled.add("X-Ray");
		if (FullBrightEnabled)
			cheatsEnabled.add("FullBright");

		return cheatsEnabled;
	}

}
