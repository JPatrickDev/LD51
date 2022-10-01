package me.jack.ld51.Entity.Mobs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.Entity.Projectiles.Bullet;
import me.jack.ld51.Entity.Projectiles.Weapon;
import me.jack.ld51.level.Level;

public class Mob extends Entity {


    long lastWeaponUse = 0;
    public float health = 50f,maxHealth = health;
    protected Weapon currentWeapon = new Bullet();
    public Mob(Texture t, int x, int y) {
        super(t, x, y);
    }

    public void setStartHealth(float start){
        health = start;
        maxHealth = start;
    }

    @Override
    public void update(Level parent) {
        super.update(parent);
        if(health <= 0){
            parent.removeEntity(this);
        }
    }


    public void takeDamage(float d){
        health -= d;
        if(health <= 0){
            health = 0;
        }
    }
}
