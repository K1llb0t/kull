package wtf.kull.api.cache.impl.module.impl;

import lombok.Getter;
import lombok.Setter;
import org.lwjgl.input.Keyboard;
import wtf.kull.impl.feature.ToggleableFeature;
import wtf.kull.impl.setting.Bind;
import wtf.kull.impl.setting.Setting;
import wtf.kull.util.internal.reflect.ReflectionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter @Setter
public class Module extends ToggleableFeature {
    private final ModuleCategory category;
    private final String description;

    private final Map<String, Setting> settingsMap = new HashMap<>();
    private final List<Setting> settings = new CopyOnWriteArrayList<>();

    protected final Bind bind = new Bind("Bind", Keyboard.KEY_NONE);

    public Module(String name, ModuleCategory category, String description) {
        super(name);

        this.category = category;
        this.description = description;

        addSetting(bind);
    }

    public void loadSettings() {
        ReflectionUtil.allFieldsWithType(this, Setting.class)
                .forEach((field) -> {
                    field.setAccessible(true);

                    try {
                        addSetting((Setting) field.get(this));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }

    protected void addSetting(Setting setting) {
        settingsMap.put(setting.getName(), setting);
        settings.add(setting);
    }
}