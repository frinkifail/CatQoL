package cat.frinkifail.catqol;

import net.fabricmc.api.ModInitializer;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class CatQoL implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("catqol");

    @Override
    public void onInitialize() {
        GlobalStaticClass.logger = LOGGER;
        LOGGER.info("Hello, Cats!");
    }
}
