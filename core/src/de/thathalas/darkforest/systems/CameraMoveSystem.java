package de.thathalas.darkforest.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class CameraMoveSystem extends EntitySystem {
    private OrthographicCamera camera;

    public CameraMoveSystem(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void update(float deltaTime) {
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.position.add(1,0,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.position.add(-1,0,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.position.add(0,1,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.position.add(0,-1,0);
        }
    }
}
