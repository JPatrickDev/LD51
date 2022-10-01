package me.jack.ld51.Entity.Projectiles;

import com.badlogic.gdx.graphics.Texture;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.level.Level;

public abstract class Projectile extends Entity {


    public Projectile(Texture t, int x, int y) {
        super(t, x, y);
    }

    @Override
    public void update(Level parent) {
        super.update(parent);
        if(bounces > 1){
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
}
