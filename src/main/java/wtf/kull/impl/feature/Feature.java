package wtf.kull.impl.feature;

import lombok.Getter;
import wtf.kull.util.internal.Globals;

/**
 * Represents a base feature
 *
 * @author aesthetical
 * @since 5/22/22
 */
@Getter
public class Feature implements Globals {
    private final String name;

    public Feature(String name) {
        this.name = name;
    }
}