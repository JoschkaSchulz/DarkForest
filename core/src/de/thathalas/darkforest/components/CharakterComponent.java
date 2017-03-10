package de.thathalas.darkforest.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by joschkaschulz on 03.03.17.
 */
public class CharakterComponent implements Component{
    public static final int TEXTURE_ROWS = 2;
    public static final int TEXTURE_COLS = 7;

    public static final int STATE_IDLE = 0;
    public static final int STATE_WALK = 1;

    public int state;
    public float speed;

    public TextureRegion texture;
    public TextureRegion[][] textureRegion;

    public Animation<TextureRegion> walkAnimation;
    public Animation<TextureRegion> idleAnimation;
    public Vector2 direction;

    public CharakterComponent(Texture texture) {
        this.state = STATE_IDLE;
        this.speed = 1f;
        this.direction = new Vector2(1,0);
        this.texture = new TextureRegion(texture);
        this.textureRegion = TextureRegion.split(texture, texture.getWidth()/TEXTURE_COLS, texture.getHeight()/TEXTURE_ROWS);

        walkAnimation = new Animation<TextureRegion>(0.25f, collectFrames(1,0,4));
        idleAnimation = new Animation<TextureRegion>(0.25f, collectFrames(0,0,7));
    }

    public TextureRegion getFrame(float delta) {
        TextureRegion tex;
        switch (state) {
            default:
            case STATE_IDLE:
                tex = idleAnimation.getKeyFrame(delta, true);
                break;
            case STATE_WALK:
                tex = walkAnimation.getKeyFrame(delta, true);
                break;
        }

        if (direction.x < 0 && !tex.isFlipX()) {
            tex.flip(true, false);
        } else if (direction.x >= 0 && tex.isFlipX()) {
            tex.flip(true, false);
        }

        return tex;
    }

    private TextureRegion[] collectFrames(int row, int col, int length) {
        TextureRegion[] animation = new TextureRegion[length];
        int index = 0;
        for (int i = row; i < textureRegion.length; i++) {
            for (int j = col; j < textureRegion[0].length; j++) {
                if(index < length) {
                    animation[index++] = textureRegion[i][j];
                } else {
                    return animation;
                }
            }
        }

        return animation;
    }
}
