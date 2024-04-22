package cat.frinkifail.catqol.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class HUDMixin {
    @Inject(method = "render", at = @At("RETURN"))
    public void render(DrawContext context, float tickDelta, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        assert client.player != null;

        client.player.speed = 0;
        context.drawCenteredTextWithShadow(client.textRenderer, Text.literal(String.format("%s blocks per second", client.player.speed)), context.getScaledWindowWidth() / 2, 24, 0xFFFFFF);
    }
}
