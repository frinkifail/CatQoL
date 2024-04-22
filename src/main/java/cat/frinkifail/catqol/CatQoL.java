package cat.frinkifail.catqol;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class CatQoL implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("catqol");
    private short flightTimer = 0;
    private short offRemaining = 3;

    @Override
    public void onInitialize() {
        GlobalStaticClass.logger = LOGGER;
        LOGGER.info("Hello, Cats!");

        ClientTickEvents.END_WORLD_TICK.register(world -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            assert player != null;

            if (player.getAbilities().allowFlying) {
                flightTimer++;
                if (flightTimer > 10) {
                    offRemaining--;
                }
                if (offRemaining > 0) {
                    player.getAbilities().allowFlying = false;
                }
                if (offRemaining == 0) {
                    offRemaining = 3;
                    flightTimer = 0;
                    player.getAbilities().allowFlying = true;
                }
            }

            if (player.fallDistance > player.getSafeFallDistance()) {
                player.setOnGround(true);
                player.limitFallDistance();
                player.fallDistance = 0.0F;
            }
        });
    }
}
