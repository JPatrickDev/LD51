package me.jack.ld51;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.print.DocFlavor;

import me.jack.ld51.Screen.GameOverScreen;
import me.jack.ld51.Screen.InGameScreen;
import me.jack.ld51.Screen.Screen;
import me.jack.ld51.level.Level;
import me.jack.ld51.ui.HUD;

public class LD51Game extends ApplicationAdapter {

    Screen currentScreen;
    private static LD51Game instance;
    SpriteBatch batch;
    ShapeRenderer renderer;

    public static void gameover() {
       changeScreen(new GameOverScreen(getInstance().batch, getInstance().renderer));
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        currentScreen = new InGameScreen(batch,renderer);
        instance = this;
    }

    int i = 0;

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);
        currentScreen.render();
        if(newScreen != null){
           currentScreen.dispose();
           currentScreen = newScreen;
        }
    }

    @Override
    public void dispose() {
        currentScreen.dispose();
    }

    public static LD51Game getInstance(){
        return instance;
    }
    Screen newScreen = null;
    public static void changeScreen(Screen newScreen){
        getInstance().newScreen = newScreen;
    }



}
