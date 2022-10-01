package me.jack.ld51.Entity.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.level.Level;

public class Projectile extends Entity {

    Entity owner;
    public Weapon toFire;
    long start;
    public Projectile(Entity owner, Weapon toFire, int x, int y) {
        super(toFire.projectileTexture, x, y);
        this.owner = owner;
        this.toFire = toFire;
        start = System.currentTimeMillis();
        float xSpeed = (fuzz(Gdx.input.getX()) - x);
        float ySpeed = (fuzz(Gdx.graphics.getHeight()-Gdx.input.getY() ) - y);

        float factor = (float) (15/ Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        dX = xSpeed;
        dY = ySpeed;
    }

    public int fuzz(int input){
        int i = (new Random().nextInt(15));
        if(new Random().nextBoolean())
            i *= -1;
        return input + i;
    }
    @Override
    public void update(Level parent) {
        super.update(parent);
        if(bounces > 1){
            parent.removeEntity(this);
        }
        if(System.currentTimeMillis() - start > 2000){
            parent.removeEntity(this);
        }
    }

    //Should only be called from Level, doesn't perform any checks
    //Projectiles don't slow down over time
    @Override
    public void move(){
        x += dX;
        y += dY;
    }

    public Entity getOwner() {
        return this.owner;
    }

}
