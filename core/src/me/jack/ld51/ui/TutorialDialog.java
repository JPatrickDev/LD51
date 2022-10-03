package me.jack.ld51.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.Rectangle;
import java.util.HashMap;

import me.jack.ld51.Entity.Mobs.Player;
import me.jack.ld51.Entity.Projectiles.Weapons.Weapon;
import me.jack.ld51.LD51Game;
import me.jack.ld51.Screen.InGameScreen;
import me.jack.ld51.level.Level;

public class TutorialDialog extends Dialog{

    Texture t = new Texture("tutorial.png");
    InGameScreen igs;
    public TutorialDialog(Level parent, InGameScreen igs) {
        this.igs = igs;
    }

    public void renderTextures(SpriteBatch batch) {
        batch.draw(t,0,-90);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            igs.dialog = null;

        }
    }

    public void renderShapes(ShapeRenderer renderer) {


    }

}
