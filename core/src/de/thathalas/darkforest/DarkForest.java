package de.thathalas.darkforest;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import de.thathalas.darkforest.entities.background.BackgroundLevel1Entity;
import de.thathalas.darkforest.entities.background.BackgroundLevel2Entity;
import de.thathalas.darkforest.entities.background.ForegroundLevel1Entity;
import de.thathalas.darkforest.entities.player.Cappy;
import de.thathalas.darkforest.systems.CameraMoveSystem;
import de.thathalas.darkforest.systems.CharakterRenderSystem;
import de.thathalas.darkforest.systems.PlayerControllSystem;
import de.thathalas.darkforest.systems.RenderSystem;

public class DarkForest extends ApplicationAdapter {
	private Engine engine;
	private PerspectiveCamera camera;
	private DecalBatch batch;

	public static float meterInPixel(float meter) {
		return meter*1000;
	}

	public static float pixelInMeter(float pixel) {
		return 1000/pixel;
	}

	@Override
	public void create () {
		AssetHandler.loadAssets();
		camera = new PerspectiveCamera(45, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
		camera.near = 0.01f;
		camera.far = 20;
		camera.position.set(-0.3f, 0, 3.5f);

		batch = new DecalBatch(new CameraGroupStrategy(camera));

		engine = new Engine();
		engine.addSystem(new PlayerControllSystem());
		engine.addSystem(new RenderSystem(camera, batch));
		engine.addSystem(new CameraMoveSystem(camera));
		engine.addSystem(new CharakterRenderSystem(camera, batch));

		engine.addEntity(new BackgroundLevel1Entity());
		engine.addEntity(new BackgroundLevel2Entity());
		engine.addEntity(new ForegroundLevel1Entity());

		engine.addEntity(new Cappy());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.271f, 0.329f, 0.42f, 1);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
		engine.update(Gdx.graphics.getDeltaTime());
		batch.flush();
	}
	
	@Override
	public void dispose () {
		AssetHandler.disposeAssets();
	}
}
