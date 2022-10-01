package me.jack.ld51;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import me.jack.ld51.level.Level;

public class LD51Game extends ApplicationAdapter {
    SpriteBatch batch;
    ShapeRenderer renderer;
    Level level;

    @Override
    public void create() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        level = new Level();
    }

    int i = 0;

    @Override
    public void render() {
        System.out.println(Gdx.graphics.getFramesPerSecond());
        if (i % 2 == 0) {
            level.update();
            i = 0;
        }
        i++;
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        level.renderTextures(batch);
        batch.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        level.renderShapes(renderer);
        renderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
