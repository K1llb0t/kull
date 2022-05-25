package wtf.kull.impl.setting;

import java.util.function.Supplier;

public class Bind extends Setting<Integer> {
    public Bind(String name, Integer value) {
        super(name, value);
    }

    public Bind(Setting parent, String name, Integer value) {
        super(parent, name, value);
    }

    public Bind(Supplier<Boolean> visibility, String name, Integer value) {
        super(visibility, name, value);
    }
}