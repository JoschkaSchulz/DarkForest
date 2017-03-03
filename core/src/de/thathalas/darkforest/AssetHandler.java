package de.thathalas.darkforest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class AssetHandler {
    public static Texture BACKGROUND_LEVEL_1;
    public static Texture BACKGROUND_LEVEL_2;

    public static Texture FOREGROUND_LEVEL_1;

    public static Texture PLAYER_CAPPY;

    static public void loadAssets() {
        BACKGROUND_LEVEL_1 = new Texture(Gdx.files.internal("gfx/background/level1.png"));
        BACKGROUND_LEVEL_2 = new Texture(Gdx.files.internal("gfx/background/level2.png"));

        FOREGROUND_LEVEL_1 = new Texture(Gdx.files.internal("gfx/foreground/level1.png"));
        PLAYER_CAPPY = new Texture(Gdx.files.internal("gfx/player/cappy.png"));
    }

    static public void disposeAssets() {
        BACKGROUND_LEVEL_1.dispose();
        BACKGROUND_LEVEL_2.dispose();

        FOREGROUND_LEVEL_1.dispose();

        PLAYER_CAPPY.dispose();
    }
}
