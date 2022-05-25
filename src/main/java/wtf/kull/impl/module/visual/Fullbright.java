package wtf.kull.impl.module.visual;

import me.bush.eventbus.annotation.EventListener;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import wtf.kull.api.cache.impl.module.impl.ModuleCategory;
import wtf.kull.api.cache.impl.module.impl.Module;
import wtf.kull.api.event.TickEvent;
import wtf.kull.impl.setting.Setting;

public class Fullbright extends Module {
    public Fullbright() {
        super("Fullbright", ModuleCategory.VISUAL, "Makes your game brighter");
    }

    public final Setting<Mode> mode = new Setting<>("Mode", Mode.GAMMA);
    public final Setting<Float> light = new Setting<>(() -> !mode.getValue().equals(Mode.POTION),
            "Light", 1.0f, 0.1f, 1.0f);

    private float oldGamma = -1.0f;
    private boolean gavePotion = false;

    @Override
    protected void onActivated() {
        super.onActivated();
        oldGamma = mc.gameSettings.gammaSetting;
    }

    @Override
    protected void onDeactivated() {
        super.onDeactivated();

        if (nullCheck()) {
            return;
        }

        if (oldGamma != -1.0f) {
            mc.gameSettings.gammaSetting = oldGamma;
            oldGamma = -1.0f;
        }

        if (gavePotion) {
            gavePotion = false;
            mc.thePlayer.removePotionEffect(Potion.nightVision.id);
        }
    }

    @EventListener
    public void onTick(TickEvent event) {
        switch (mode.getValue()) {
            case GAMMA:
                mc.gameSettings.gammaSetting = light.getValue() * 10.0f;
                break;

            case POTION: {
                if (!mc.thePlayer.isPotionActive(Potion.nightVision)) {
                    gavePotion = true;
                    mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, Integer.MAX_VALUE));
                }
            }
        }
    }

    public enum Mode {
        GAMMA, POTION
    }
}
