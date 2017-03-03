package de.thathalas.darkforest.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.utils.Array;
import de.thathalas.darkforest.DarkForest;
import de.thathalas.darkforest.components.PlayerAnimationComponent;
import de.thathalas.darkforest.components.PositionComponent;
import de.thathalas.darkforest.components.TextureComponent;

import java.util.Comparator;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class PlayerRenderSystem extends IteratingSystem {
    private Engine engine;
    private Array<Entity> renderQueue;
    private DecalBatch batch;
    private Comparator<Entity> comparator;
    private PerspectiveCamera camera;

    private Decal decal;
    private float stateTime;

    private PlayerAnimationComponent playerAnimation;
    private TextureRegion texture;
    private PositionComponent position;

    private ComponentMapper<PositionComponent> pm;
    private ComponentMapper<PlayerAnimationComponent> pam;

    public PlayerRenderSystem(PerspectiveCamera camera, DecalBatch batch) {
        super(Family.all(PositionComponent.class, PlayerAnimationComponent.class).get());
        stateTime = 0;

        pm = ComponentMapper.getFor(PositionComponent.class);
        pam = ComponentMapper.getFor(PlayerAnimationComponent.class);

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
        stateTime += deltaTime;
        camera.update();
        renderQueue.sort(comparator);

        for(Entity entity : renderQueue) {
            playerAnimation = pam.get(entity);
            texture = playerAnimation.walkAnimation.getKeyFrame(stateTime, true);
            position = pm.get(entity);

            Decal decal = Decal.newDecal(
                    DarkForest.pixelInMeter(texture.getRegionHeight()*8),
                    DarkForest.pixelInMeter(texture.getRegionWidth()*8),
                    texture, true);
            decal.setPosition(position.x, position.y-0.1f, position.z);
            batch.add(decal);
        }
        renderQueue.clear();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
