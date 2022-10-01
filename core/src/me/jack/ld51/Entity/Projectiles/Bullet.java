package me.jack.ld51.Entity.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import me.jack.ld51.Entity.Entity;

public class Bullet extends Weapon{
    //TODO Map for common textures for all entities to use
    public Bullet() {
        super(new Texture("projectiles/bullet.png"));

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
}
