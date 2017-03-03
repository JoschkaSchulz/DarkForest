package de.thathalas.darkforest.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class StageComponent implements Component {
    public Stage stage;

    public StageComponent(Stage stage) {
        this.stage = stage;
    }
}
