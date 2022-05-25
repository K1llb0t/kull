package wtf.kull.api.event;

import me.bush.eventbus.event.Event;
import wtf.kull.Kull;

public class TickEvent extends Event {

    public static void call() {
        Kull.BUS.post(new TickEvent());
    }

    @Override
    protected boolean isCancellable() {
        return false;
    }
}
