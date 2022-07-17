package dev.quintindunn.hackmore.mixin;

import dev.quintindunn.hackmore.SettingsMenuScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class SettingsMixin extends Screen
{
    protected SettingsMixin(Text text) {
        super(text);
    }
    @Inject(at = @At("HEAD"), method = "initWidgets")
    private void initWidgets(CallbackInfo ci) {
        this.addDrawableChild(new ButtonWidget(10, 10, 100, 20, Text.literal("Hackmore Settings"), (button -> {
            this.client.setScreen(new SettingsMenuScreen(this, this.client.options));
        })));
    }
}
