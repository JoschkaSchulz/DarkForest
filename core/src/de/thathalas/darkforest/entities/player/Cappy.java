package de.thathalas.darkforest.entities.player;

import com.badlogic.ashley.core.Entity;
import de.thathalas.darkforest.AssetHandler;
import de.thathalas.darkforest.components.PlayerAnimationComponent;
import de.thathalas.darkforest.components.PositionComponent;
import de.thathalas.darkforest.components.TextureComponent;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class Cappy extends Entity{
    public Cappy() {
        add(new PositionComponent(0,0,-0.2f));
        add(new PlayerAnimationComponent(AssetHandler.PLAYER_CAPPY,1,4));
    }
}
