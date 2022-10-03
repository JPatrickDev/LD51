package me.jack.ld51.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.ScreenUtils;

import me.jack.ld51.LD51Game;
import me.jack.ld51.level.Level;
import me.jack.ld51.ui.HUD;

public class MainMenu extends Screen {

    SpriteBatch batch;
    ShapeRenderer renderer;

    static Texture background = new Texture("mainmenu.png");


    Level displayLevel = null;

    public MainMenu(SpriteBatch batch, ShapeRenderer renderer) {
        this.batch = batch;
        this.renderer = renderer;
        batch.setTransformMatrix(new Matrix4());
        displayLevel = new Level(20, 12);
    }

    int i = 0;

    public void render() {
        // System.out.println(Gdx.graphics.getFramesPerSecond());
        if (i % 2 == 0) {
            displayLevel.update();
            i = 0;
        }
        i++;
        ScreenUtils.clear(0.5f, 0.5f, 0.5f, 1);
        Matrix4 matrix4 = new Matrix4();
        matrix4.setToTranslation(0, HUD.wheelRadius * 2, 0);
        batch.setTransformMatrix(matrix4);
        renderer.setTransformMatrix(matrix4);
        batch.begin();
        displayLevel.renderTextures(batch);
         batch.draw(background, 0, -90);
        batch.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        displayLevel.renderShapes(renderer);
        renderer.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            LD51Game.changeScreen(new InGameScreen(batch, renderer));
        }
    }

    public void dispose() {
        //TODO level and hud dispose
    }
}
