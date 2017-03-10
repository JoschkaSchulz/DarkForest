package de.thathalas.darkforest.entities.player;

import com.badlogic.ashley.core.Entity;
import de.thathalas.darkforest.AssetHandler;
import de.thathalas.darkforest.components.CharakterComponent;
import de.thathalas.darkforest.components.PlayerComponent;
import de.thathalas.darkforest.components.PositionComponent;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class Cappy extends Entity{
    public Cappy() {
        add(new PlayerComponent());
        add(new PositionComponent(0,-0.5f,0f));
        add(new CharakterComponent(AssetHandler.PLAYER_CAPPY));
    }
}
