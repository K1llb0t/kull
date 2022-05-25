package wtf.kull.api.cache.impl.module;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import me.bush.eventbus.annotation.EventListener;
import org.lwjgl.input.Keyboard;
import wtf.kull.api.cache.BaseCache;
import wtf.kull.api.cache.Caches;
import wtf.kull.api.cache.impl.module.impl.Module;
import wtf.kull.api.event.KeyInputEvent;
import wtf.kull.util.internal.reflect.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter @Log4j2
public class ModuleCache extends BaseCache {
    private final List<Module> modules = new CopyOnWriteArrayList<>();

    @SneakyThrows
    @Override
    public void init() {
        ReflectionUtil.getClasses("wtf.kull.impl.module", Module.class)
                .forEach((clazz) -> {
                    try {
                        Module module = clazz.getConstructor().newInstance();
                        addModule(module);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                });

        log.info("Loaded {} modules!", modules.size());
    }

    @EventListener
    public void onKeyInput(KeyInputEvent event) {
        if (event.isState() && event.getKeyCode() != Keyboard.KEY_NONE && mc.currentScreen == null) {
            modules.forEach((module) -> {
                if (module.getBind().getValue().equals(event.getKeyCode())) {
                    module.setToggled(!module.isToggled());
                }
            });
        }
    }

    public <T extends Module> T getModule(Class<? extends Module> clazz) {
        return (T) objects.getOrDefault(clazz, null);
    }

    private void addModule(Module module) {
        objects.put(module.getClass(), module);
        modules.add(module);
    }

    public static ModuleCache get() {
        return Caches.getCache(ModuleCache.class);
    }
}