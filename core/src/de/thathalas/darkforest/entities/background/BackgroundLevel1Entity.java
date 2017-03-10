package de.thathalas.darkforest.entities.background;

import com.badlogic.ashley.core.Entity;
import de.thathalas.darkforest.AssetHandler;
import de.thathalas.darkforest.components.PositionComponent;
import de.thathalas.darkforest.components.TextureComponent;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class BackgroundLevel1Entity extends Entity {
    public BackgroundLevel1Entity() {
        add(new PositionComponent(0,1f,-3f));
        add(new TextureComponent(AssetHandler.BACKGROUND_LEVEL_1));
    }
}
