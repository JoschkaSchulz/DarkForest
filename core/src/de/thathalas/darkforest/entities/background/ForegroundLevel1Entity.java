package de.thathalas.darkforest.entities.background;

import com.badlogic.ashley.core.Entity;
import de.thathalas.darkforest.AssetHandler;
import de.thathalas.darkforest.components.PositionComponent;
import de.thathalas.darkforest.components.TextureComponent;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class ForegroundLevel1Entity extends Entity {
    public ForegroundLevel1Entity() {
        add(new PositionComponent(0,0,-10));
        add(new TextureComponent(AssetHandler.FOREGROUND_LEVEL_1));
    }
}
