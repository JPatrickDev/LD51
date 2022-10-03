package me.jack.ld51;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

import javax.print.DocFlavor;

import me.jack.ld51.Entity.Mobs.RangedEnemy;
import me.jack.ld51.Screen.GameOverScreen;
import me.jack.ld51.Screen.InGameScreen;
import me.jack.ld51.Screen.MainMenu;
import me.jack.ld51.Screen.Screen;
import me.jack.ld51.level.Level;
import me.jack.ld51.ui.HUD;

public class LD51Game extends ApplicationAdapter {

    Screen currentScreen;
    private static LD51Game instance;
    SpriteBatch batch;
    ShapeRenderer renderer;

    public static void gameover(Level parent) {
        changeScreen(new GameOverScreen(getInstance().batch, getInstance().renderer,parent));
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        currentScreen = new MainMenu(batch, renderer);
        instance = this;
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(float amountX, float amountY) {
                if(currentScreen instanceof InGameScreen){
                    ((InGameScreen)currentScreen).mouseWheel(amountY);
                }
                return true;
            }
        });
    }

    int i = 0;

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);
        currentScreen.render();
        if (newScreen != null) {
            currentScreen.dispose();
            currentScreen = newScreen;
        }
    }

    @Override
    public void dispose() {
        currentScreen.dispose();
    }

    public static LD51Game getInstance() {
        return instance;
    }

    Screen newScreen = null;

    public static void changeScreen(Screen newScreen) {
        getInstance().newScreen = newScreen;
    }


    public static final Random r = new Random();

    public static int rand(int max) {
        return r.nextInt(max);
    }

    public static float rand() {
        return r.nextFloat();
    }

    public static boolean randBool() {
        return r.nextBoolean();
    }
}
