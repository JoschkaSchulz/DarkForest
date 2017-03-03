package de.thathalas.darkforest.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import de.thathalas.darkforest.components.PositionComponent;
import de.thathalas.darkforest.components.StageComponent;
import de.thathalas.darkforest.components.TextureComponent;
import de.thathalas.darkforest.entities.stage.StageEntity;

import java.util.Comparator;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class RenderSystem extends IteratingSystem {
    private Engine engine;
    private Array<Entity> renderQueue;
    private SpriteBatch batch;
    private Comparator<Entity> comparator;
    private OrthographicCamera camera;

    private TextureRegion texture;
    private PositionComponent position;

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<TextureComponent> tm = ComponentMapper.getFor(TextureComponent.class);

    public RenderSystem(OrthographicCamera camera) {
        super(Family.all(PositionComponent.class, TextureComponent.class).get());

        pm = ComponentMapper.getFor(PositionComponent.class);
        tm = ComponentMapper.getFor(TextureComponent.class);

        comparator = new Comparator<Entity>() {
            @Override
            public int compare(Entity entityA, Entity entityB) {
                return (int)Math.signum(pm.get(entityB).z -
                        pm.get(entityA).z);
            }
        };

        this.camera = camera;
        this.batch = new SpriteBatch();
        renderQueue = new Array<Entity>();
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        this.engine = engine;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        camera.update();

        renderQueue.sort(comparator);

        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        for(Entity entity : renderQueue) {
            texture = tm.get(entity).textureRegion;
            position = pm.get(entity);
            batch.draw(texture, position.x, position.y,
                    texture.getRegionWidth()/2, texture.getRegionHeight()/2,
                    texture.getRegionWidth(), texture.getRegionHeight(),
                    1, 1, position.rotation);
        }
        batch.end();
        renderQueue.clear();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
