package me.jack.ld51.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

import me.jack.ld51.LD51Game;
import me.jack.ld51.level.Level;
import me.jack.ld51.ui.HUD;

public class MainMenu extends Screen {

    SpriteBatch batch;
    ShapeRenderer renderer;

    static Texture background = new Texture("mainmenu.png");



    public MainMenu(SpriteBatch batch, ShapeRenderer renderer) {
        this.batch = batch;
        this.renderer = renderer;
        batch.setTransformMatrix(new Matrix4());
    }

    public void render() {

        batch.begin();
        HUD.font.setColor(Color.RED);
        //   HUD.font.draw(batch,"Game Over :(", 300,300);
        batch.draw(background, 0, 0);
        batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
            LD51Game.changeScreen(new InGameScreen(batch,renderer));
        }
    }

    public void dispose() {
        //TODO level and hud dispose
    }
}
