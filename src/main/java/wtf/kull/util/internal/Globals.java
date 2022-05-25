package wtf.kull.util.internal;

import net.minecraft.client.Minecraft;

/**
 * The globals class, can be implemented into any class that need base features
 *
 * @author aesthetical
 * @since 5/24/22
 */
public interface Globals {
    Minecraft mc = Minecraft.getMinecraft();

    default boolean nullCheck() {
        return mc.thePlayer == null || mc.theWorld == null;
    }
}
