package me.jack.ld51.Entity.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.level.Level;

public class Bullet extends RangedWeapon{
    //TODO Map for common textures for all entities to use
    long firerate = 100;
    public Bullet(Mob owner, long firerate) {
        super(new Texture("projectiles/bullet.png"),owner);
        icon  = new Texture("gun.png");
        this.firerate = firerate;
    }


    @Override
    public long fireRate(){
        return firerate;
    }

    @Override
    public long lifespan() {
        return 2000;
    }

    @Override
    public float damage() {
        return 20;
    }

    @Override
    public float range() {
        return 200;
    }

}
