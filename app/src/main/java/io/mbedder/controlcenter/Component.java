package io.mbedder.controlcenter;

import java.util.HashMap;
import java.util.Map;

/**
 * A switchable component.
 */
public class Component {

    /**
     * The current state of the component. True=on, false=off.
     */
    public boolean state;
    /**
     * The user-facing name of the component.
     */
    public String name;
    /**
     * The desired positions for on and off.
     */
    Map<Boolean, Integer> positions;

    public Component(String name, boolean state, int offPos, int onPos) {
        this.name = name;
        this.state = state;
        this.positions = new HashMap<>(2);
        this.positions.put(false, offPos);
        this.positions.put(true, onPos);
    }

}
