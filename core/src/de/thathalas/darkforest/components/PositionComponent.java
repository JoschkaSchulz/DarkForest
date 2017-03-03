package de.thathalas.darkforest.components;

import com.badlogic.ashley.core.Component;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class PositionComponent implements Component {
    public float x;
    public float y;
    public float z;

    public float rotation = 0;

    public PositionComponent(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
