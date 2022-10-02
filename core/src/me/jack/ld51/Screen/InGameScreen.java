package me.jack.ld51.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import me.jack.ld51.level.Level;
import me.jack.ld51.ui.HUD;

public class InGameScreen extends Screen{

    SpriteBatch batch;
    ShapeRenderer renderer;
    Level level;
    HUD hud;

    public InGameScreen(SpriteBatch batch, ShapeRenderer renderer){
        this.batch = batch;
        this.renderer = renderer;
        level = new Level();
        hud = new HUD(level);
    }
    int i= 0;
    public void render(){
       // System.out.println(Gdx.graphics.getFramesPerSecond());
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
        hud.drawShapes(renderer);
        renderer.end();
        batch.begin();
        level.getPlayer().renderTextures(batch);
        hud.drawTextures(batch);
        batch.end();
    }

    public void dispose(){
        //TODO level and hud dispose
    }
}
