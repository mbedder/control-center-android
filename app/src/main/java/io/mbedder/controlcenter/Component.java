package io.mbedder.controlcenter;

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

    public Component(String name, boolean state, Map<Boolean, Integer> positions) {
        this.name = name;
        this.state = state;
        this.positions = positions;
    }

}
