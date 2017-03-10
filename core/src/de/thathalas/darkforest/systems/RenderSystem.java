package de.thathalas.darkforest.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.utils.Array;
import de.thathalas.darkforest.DarkForest;
import de.thathalas.darkforest.components.PositionComponent;
import de.thathalas.darkforest.components.TextureComponent;

import java.util.Comparator;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class RenderSystem extends IteratingSystem {
    private Engine engine;
    private Array<Entity> renderQueue;
    private DecalBatch batch;
    private Comparator<Entity> comparator;
    private PerspectiveCamera camera;

    private Decal decal;

    private TextureRegion texture;
    private PositionComponent position;

    private ComponentMapper<PositionComponent> pm;
    private ComponentMapper<TextureComponent> tm;

    public RenderSystem(PerspectiveCamera camera, DecalBatch batch) {
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
        this.batch = batch;
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

        for(Entity entity : renderQueue) {
            texture = tm.get(entity).textureRegion;
            position = pm.get(entity);

            Decal decal = Decal.newDecal(
                    DarkForest.pixelInMeter(texture.getRegionHeight()),
                    DarkForest.pixelInMeter(texture.getRegionWidth()),
                    texture, true);
            if (position.z < 0) {
                decal.setScale(Math.abs(position.z * 7.5f));
            } else {
                decal.setScale(1 + position.z * 3);
            }
            decal.setPosition(position.x, position.y, position.z);
            batch.add(decal);
        }
        renderQueue.clear();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
