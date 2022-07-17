package dev.quintindunn.hackmore;

import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;

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
        return Text.literal("Null");
    }

    protected void init() {
        // Autofish
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 90, 200, 20,
                text("autofish"), (button -> {
            Hackmore.getInstance().AutoFishEnabled = !Hackmore.getInstance().AutoFishEnabled;
            button.setMessage(text("autofish"));
        })));
    }
}
