package wtf.kull.impl.module.movement;

import me.bush.eventbus.annotation.EventListener;
import wtf.kull.api.cache.impl.module.impl.Module;
import wtf.kull.api.cache.impl.module.impl.ModuleCategory;
import wtf.kull.api.event.TickEvent;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", ModuleCategory.MOVEMENT, "Makes you automatically sprint");
    }

    @Override
    protected void onDeactivated() {
        super.onDeactivated();

        if (!nullCheck() && mc.thePlayer.isSprinting() && !mc.gameSettings.keyBindSprint.isKeyDown()) {
            mc.thePlayer.setSprinting(false);
        }
    }

    @EventListener
    public void onTick(TickEvent event) {
        if (!mc.thePlayer.isSprinting()) {
            mc.thePlayer.setSprinting(mc.thePlayer.movementInput.moveForward > 0.0f &&
                    !mc.thePlayer.isSneaking() &&
                    !mc.thePlayer.isUsingItem() &&
                    mc.thePlayer.getFoodStats().getFoodLevel() > 6 &&
                    !mc.thePlayer.isCollidedHorizontally);
        }
    }
}
