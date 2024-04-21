package cat.frinkifail.catqol;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class CatMainScreen extends Screen {
    private final Screen parent;

    public CatMainScreen(Screen parent) {
        super(Text.literal("CatQoL Modules"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        ButtonWidget testButton = ButtonWidget.builder(Text.literal("Toggle fly"), button -> {
//                    GlobalStaticClass.logger.info("testing 123 success");
                    ClientPlayerEntity player = Objects.requireNonNull(Objects.requireNonNull(client).player);
                    player.getAbilities().allowFlying = !player.getAbilities().allowFlying;
                })
                .dimensions(width / 2 - 250, 24, 250, 24)
                .tooltip(Tooltip.of(Text.literal("Prints a testing message")))
                .build();

        addDrawableChild(testButton);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(textRenderer, Text.literal(String.format("Can fly: %s", Objects.requireNonNull(Objects.requireNonNull(client).player).getAbilities().allowFlying)), width / 2, 24, 0xFFFFFF);
    }

    @Override
    public void close() {
        Objects.requireNonNull(client).setScreen(parent);
    }
}
