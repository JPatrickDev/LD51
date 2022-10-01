package me.jack.ld51.Entity.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class Bullet extends Projectile{
    //TODO Map for common textures for all entities to use
    public Bullet(int x, int y) {
        super(new Texture("projectiles/bullet.png"), x, y);
        float xSpeed = (fuzz(Gdx.input.getX()) - x);
        float ySpeed = (fuzz(Gdx.graphics.getHeight()-Gdx.input.getY() ) - y);

        float factor = (float) (5/ Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        dX = xSpeed;
        dY = ySpeed;
    }


    public int fuzz(int input){
        int i = (new Random().nextInt(5) + 2);
        if(new Random().nextBoolean())
            i *= -1;
        return input + i;
    }
}
