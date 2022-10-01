package me.jack.ld51.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import me.jack.ld51.level.Level;
import me.jack.ld51.ui.HUD;

public class GameOverScreen extends Screen{

    SpriteBatch batch;
    ShapeRenderer renderer;


    public GameOverScreen(SpriteBatch batch,ShapeRenderer renderer){
       this.batch = batch;
       this.renderer = renderer;
        renderer = new ShapeRenderer();
    }
    public void render(){

        batch.begin();
        HUD.font.setColor(Color.RED);
        HUD.font.draw(batch,"Game Over :(", 300,300);
        batch.end();
    }

    public void dispose(){
        //TODO level and hud dispose
    }
}
