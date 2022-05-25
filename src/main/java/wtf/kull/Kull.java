package wtf.kull;

import lombok.extern.log4j.Log4j2;
import me.bush.eventbus.bus.EventBus;
import me.bush.eventbus.handler.handlers.LambdaHandler;
import wtf.kull.api.cache.Caches;
import wtf.kull.api.cache.impl.module.ModuleCache;
import wtf.kull.util.versioning.BuildConfig;

/**
 * The main class, where all interactions will be handled
 *
 * @author aesthetical
 * @since 5/24/22
 */
@Log4j2
public class Kull {
    private static Kull instance;

    public static final String NAME = "Kull";
    public static final String FULL_NAME = NAME + " " + BuildConfig.VERSION + "-" + BuildConfig.HASH;

    public static final EventBus BUS = new EventBus(LambdaHandler.class, log::error);

    private Kull() {
        log.info("Loading {}...", FULL_NAME);

        Caches.registerCache(new ModuleCache());
    }

    /**
     * inits the client
     */
    public static void init() {
        if (instance != null) {
            log.error("Client already initialized??");
            return;
        }

        instance = new Kull();
    }

    public static Kull getInstance() {
        return instance;
    }
}
