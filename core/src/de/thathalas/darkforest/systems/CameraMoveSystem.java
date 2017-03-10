package de.thathalas.darkforest.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import de.thathalas.darkforest.components.PlayerComponent;
import de.thathalas.darkforest.components.PositionComponent;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class CameraMoveSystem extends IteratingSystem {
    private PerspectiveCamera camera;

    private ComponentMapper<PositionComponent> positionMapper;
    private PositionComponent position;

    public CameraMoveSystem(PerspectiveCamera camera) {
        super(Family.all(PlayerComponent.class, PositionComponent.class).get());
        this.camera = camera;

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
//        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && camera.position.x < 0.3) {
//            camera.position.add(0.01f,0,0);
//        }
//        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && camera.position.x > -0.3) {
//            camera.position.add(-0.01f,0,0);
//        }
////        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
////            camera.position.add(0,0.01f,0);
////        }
////        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
////            camera.position.add(0,-0.01f,0);
////        }
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
            camera.position.add(0,0,0.01f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ALT_RIGHT)) {
            camera.position.add(0,0,-0.01f);
        }

    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        position = positionMapper.get(entity);

        camera.position.set(position.x, camera.position.y, camera.position.z);
    }
}
