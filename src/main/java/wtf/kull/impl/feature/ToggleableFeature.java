package wtf.kull.impl.feature;

import lombok.Getter;
import wtf.kull.Kull;

@Getter
public class ToggleableFeature extends Feature {
    private boolean toggled;

    public ToggleableFeature(String name) {
        super(name);
    }

    protected void onActivated() {
        Kull.BUS.subscribe(this);
    }

    protected void onDeactivated() {
        Kull.BUS.unsubscribe(this);
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;

        if (toggled) {
            onActivated();
        } else {
            onDeactivated();
        }
    }
}