package de.thathalas.darkforest.entities.stage;

import com.badlogic.ashley.core.Entity;
import de.thathalas.darkforest.components.StageComponent;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class StageEntity extends Entity {
    public StageEntity(StageComponent stageComponent) {
        add(stageComponent);
    }
}
