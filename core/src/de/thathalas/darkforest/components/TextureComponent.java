package de.thathalas.darkforest.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class TextureComponent implements Component {
    public TextureRegion textureRegion;

    public TextureComponent(Texture textureRegion) {
        this.textureRegion = new TextureRegion(textureRegion);
    }
}
