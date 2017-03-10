package de.thathalas.darkforest.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import de.thathalas.darkforest.components.CharakterComponent;
import de.thathalas.darkforest.components.PlayerComponent;
import de.thathalas.darkforest.components.PositionComponent;

/**
 * Created by joschkaschulz on 10.03.17.
 */
public class PlayerControllSystem extends IteratingSystem {

    private ComponentMapper<PositionComponent> positionMapper;
    private PositionComponent position;

    private ComponentMapper<CharakterComponent> charakterMapper;
    private CharakterComponent charakter;

    private float move;

    public PlayerControllSystem() {
        super(Family.all(PlayerComponent.class, PositionComponent.class).get());

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        charakterMapper = ComponentMapper.getFor(CharakterComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        position = positionMapper.get(entity);
        charakter = charakterMapper.get(entity);

        if(Gdx.input.isKeyPressed(Keys.D)) {
            move = 1;
            charakter.direction.x = 1;
            charakter.state = CharakterComponent.STATE_WALK;
        } else if(Gdx.input.isKeyPressed(Keys.A)) {
            move = 1;
            charakter.direction.x = -1;
            charakter.state = CharakterComponent.STATE_WALK;
        } else {
            move = 0;
            charakter.state = CharakterComponent.STATE_IDLE;
        }

        position.x += move * deltaTime * charakter.speed * charakter.direction.x;
    }
}
