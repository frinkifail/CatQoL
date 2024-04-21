package cat.frinkifail.catqol.mixin;

import cat.frinkifail.catqol.CatMainScreen;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(GameMenuScreen.class)
public class PauseMenuMixin extends Screen {
    protected PauseMenuMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "initWidgets")
    private void addCatButton(CallbackInfo ci) {
        ButtonWidget catButton = ButtonWidget.builder(
                Text.literal("CatQoL"),
                button -> Objects.requireNonNull(client).setScreen(new CatMainScreen(this))
        )
                .dimensions(width - 50, height - 24, 50, 24)
                .build();

        this.addDrawableChild(catButton);
    }
}
