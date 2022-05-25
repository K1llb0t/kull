package wtf.kull.api.event;

import me.bush.eventbus.event.Event;
import wtf.kull.Kull;

public class KeyInputEvent extends Event {
    private final int keyCode;
    private final boolean state;

    public KeyInputEvent(int keyCode, boolean state) {
        this.keyCode = keyCode;
        this.state = state;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public boolean isState() {
        return state;
    }

    public static void call(int keyCode, boolean state) {
        Kull.BUS.post(new KeyInputEvent(keyCode, state));
    }

    @Override
    protected boolean isCancellable() {
        return false;
    }
}
