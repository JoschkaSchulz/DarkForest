package de.thathalas.darkforest;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.thathalas.darkforest.entities.background.BackgroundLevel1Entity;
import de.thathalas.darkforest.entities.background.BackgroundLevel2Entity;
import de.thathalas.darkforest.entities.background.ForegroundLevel1Entity;
import de.thathalas.darkforest.systems.CameraMoveSystem;
import de.thathalas.darkforest.systems.RenderSystem;

public class DarkForest extends ApplicationAdapter {
	private Engine engine;
	private SpriteBatch batch;
	private OrthographicCamera camera;

	@Override
	public void create () {
		AssetHandler.loadAssets();
		camera = new OrthographicCamera(1600,900);
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);

		engine = new Engine();
		engine.addSystem(new RenderSystem(camera));
		engine.addSystem(new CameraMoveSystem(camera));

		engine.addEntity(new BackgroundLevel1Entity());
		engine.addEntity(new BackgroundLevel2Entity());
		engine.addEntity(new ForegroundLevel1Entity());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.271f, 0.329f, 0.42f, 1);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		engine.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		AssetHandler.disposeAssets();
	}
}
