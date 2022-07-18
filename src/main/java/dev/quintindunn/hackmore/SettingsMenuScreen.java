package dev.quintindunn.hackmore;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.MinecraftClientGame;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.Objects;

public class SettingsMenuScreen extends Screen {

    private final Screen parent;
    private final GameOptions settings;

    public SettingsMenuScreen(Screen parent, GameOptions gameOptions) {
        super(Text.literal("Hackmore Mod"));
        this.parent = parent;
        this.settings = gameOptions;
    }

    public MutableText text(String type){

        if (Objects.equals(type, "autofish"))
        {
            if (Hackmore.getInstance().AutoFishEnabled)
            {
                return Text.literal("AutoFish: ON");
            }
            return Text.literal("AutoFish: OFF");
        }

        if (Objects.equals(type, "xray"))
        {
            if (Hackmore.getInstance().XrayEnabled)
            {
                Xray.setFullbrightState(true);
                return Text.literal("X-Ray: ON");
            }
            Xray.setFullbrightState(false);
            return Text.literal("X-Ray: OFF");
        }

        return Text.literal("Null");
    }


    protected void init() {
        // Autofish
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 60, 200, 20,
                text("autofish"), (button -> {
            Hackmore.getInstance().AutoFishEnabled = !Hackmore.getInstance().AutoFishEnabled;
            button.setMessage(text("autofish"));
        })));

        // Xray
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 90, 200, 20,
                text("xray"), (button -> {
            Hackmore.getInstance().XrayEnabled = !Hackmore.getInstance().XrayEnabled;
            button.setMessage(text("xray"));
            assert client != null;
            client.worldRenderer.reload();
        })));
    }
}
