package com.cookies250.mixin;

import com.cookies250.PaperDisconnect;
import com.cookies250.widget.CustomButtonWidget;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.cookies250.PaperDisconnect.mc;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {

    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    public void init(CallbackInfo info) {
        if (mc.isInSingleplayer()) return;

        int w = 203;
        int h = 20;

        int x = this.width / 2 - w / 2;
        int y = this.height / 2 + 33;

        CustomButtonWidget button = new CustomButtonWidget(x, y, w, h, Text.literal("Paper Disconnect"), PaperDisconnect::disconnect);
        this.addDrawableChild(button);
    }
}
