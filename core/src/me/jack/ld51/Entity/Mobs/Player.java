package me.jack.ld51.Entity.Mobs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import me.jack.ld51.Entity.Projectiles.Bullet;
import me.jack.ld51.level.Level;

public class Player extends Mob {
    public Player(int x, int y) {
        super(new Texture("player.png"), x, y);

    }

    @Override
    public void update(Level parent) {
        super.update(parent);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            dX += 2;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            dX -= 2;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            dY += 2;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            dY -= 2;
        }

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            parent.spawnEntity(new Bullet(getX(),getY()));
        }
    }

}
