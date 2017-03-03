package de.thathalas.darkforest.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class PlayerAnimationComponent implements Component{
    public TextureRegion texture;
    public TextureRegion[][] textureRegion;

    public Animation<TextureRegion> walkAnimation;

    public PlayerAnimationComponent(Texture texture, int rows, int cols) {
        this.texture = new TextureRegion(texture);
//        this.texture.setRegionWidth(this.texture.getRegionWidth()/4);
//        this.texture.setRegionHeight(this.texture.getRegionHeight()/4);
        this.textureRegion = TextureRegion.split(texture, texture.getWidth()/4, texture.getHeight()/1);

        TextureRegion walkFrames[] = new TextureRegion[4];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 4; j++) {
                walkFrames[index++] = textureRegion[i][j];
            }
        }

        walkAnimation = new Animation<TextureRegion>(0.25f, textureRegion[0]);
    }
}
