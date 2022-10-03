package me.jack.ld51.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.Rectangle;

import me.jack.ld51.LD51Game;
import me.jack.ld51.level.Level;
import me.jack.ld51.ui.HUD;

public class GameOverScreen extends Screen {

    SpriteBatch batch;
    ShapeRenderer renderer;

    static Texture background = new Texture("gameover.png");
    static Texture play = new Texture("playagainbutton.png");
    static Texture menu = new Texture("mainmenubutton.png");
    static Texture quitButton = new Texture("quitbutton.png");

    public static Rectangle playAgain = new Rectangle(185,225 + 90 - 100,277,69);
    public static Rectangle mainMenu = new Rectangle(183,225 - 100,282,70);
    public static Rectangle quit = new Rectangle(188,225-90 - 100,277,69);
    Level level;
    public GameOverScreen(SpriteBatch batch, ShapeRenderer renderer, Level parent) {
        this.batch = batch;
        this.renderer = renderer;
        this.level = parent;
    }

    public void render() {

        batch.begin();
        HUD.font.setColor(Color.WHITE);
        //   HUD.font.draw(batch,"Game Over :(", 300,300);
        batch.draw(background, 0, -90);
        batch.draw(play,playAgain.x,playAgain.y);
        batch.draw(menu,mainMenu.x,mainMenu.y);
        batch.draw(quitButton,quit.x,quit.y);
        HUD.font.draw(batch,"You survived " + level.currentRound + " rounds.",270,390);
        batch.end();

        if(Gdx.input.isButtonJustPressed(0)){
            if(playAgain.contains(InGameScreen.getMX(),InGameScreen.getMY())){
                LD51Game.changeScreen(new InGameScreen(batch,renderer));
            }
            if(mainMenu.contains(InGameScreen.getMX(),InGameScreen.getMY())){
                LD51Game.changeScreen(new MainMenu(batch,renderer));
            }
            if(quit.contains(InGameScreen.getMX(),InGameScreen.getMY())){
               Gdx.app.exit();
            }
        }
    }

    public void dispose() {
        //TODO level and hud dispose
    }
}
