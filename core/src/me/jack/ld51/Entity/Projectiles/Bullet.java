package me.jack.ld51.Entity.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.level.Level;

public class Bullet extends RangedWeapon{
    //TODO Map for common textures for all entities to use
    public Bullet(Mob owner) {
        super(new Texture("projectiles/bullet.png"),owner);

    }


    @Override
    public long fireRate(){
        return 100;
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
        return 30;
    }

}
