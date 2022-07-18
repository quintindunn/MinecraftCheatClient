package dev.quintindunn.hackmore;

import dev.quintindunn.hackmore.mods.FullBright;
import dev.quintindunn.hackmore.mods.Speed;
import dev.quintindunn.hackmore.mods.Xray;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

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

        if (Objects.equals(type, "fullbright"))
        {
            if (Hackmore.getInstance().FullBrightEnabled)
            {
                return Text.literal("FullBright: ON");
            }
            return Text.literal("FullBright: OFF");
        }

        if (Objects.equals(type, "xray"))
        {
            if (Hackmore.getInstance().XrayEnabled)
            {
                return Text.literal("X-Ray: ON");
            }
            return Text.literal("X-Ray: OFF");
        }

        if (Objects.equals(type, "speed"))
        {
            if (Hackmore.getInstance().SpeedEnabled)
            {
                return Text.literal("Speed: " + (float)Speed.getSpeed());
            }
            return Text.literal("Speed: OFF");
        }

        return Text.literal("Null");
    }


    protected void init() {
        // AutoFish
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 60, 200, 20,
                text("autofish"), (button -> {
            Hackmore.getInstance().AutoFishEnabled = !Hackmore.getInstance().AutoFishEnabled;
            button.setMessage(text("autofish"));
        })));

        // FullBright
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 90, 200, 20,
                text("fullbright"), (button -> {
            FullBright.toggleFullBright();
            button.setMessage(text("fullbright"));
        })));

        // Xray
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 120, 200, 20,
                text("xray"), (button -> {
            Xray.toggleXray();
            button.setMessage(text("xray"));
        })));

        // Speed
        SliderWidget button = new SliderWidget(this.width / 2 - 100, this.height / 6 + 150, 200, 20,
                text("speed"), Speed.getSpeed())
        {
            @Override
            protected void updateMessage() {

            }

            @Override
            protected void applyValue() {
                Hackmore.getInstance().SpeedEnabled = this.value > 0;
                if (Hackmore.getInstance().SpeedEnabled)
                {
                    Speed.setSpeed(this.value * (Speed.maxSpeed - Speed.minSpeed) + Speed.minSpeed);
                }
                this.setMessage(text("speed"));
            }
        };
        this.addDrawableChild(button);
    }

    private void addDrawableChild(SliderWidget speed, Object o) {
    }
}
