package me.jack.ld51.Entity.Mobs;

import com.badlogic.gdx.graphics.Texture;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.Entity.Projectiles.Weapons.Weapon;
import me.jack.ld51.level.Level;

public class Mob extends Entity {


    long lastWeaponUse = 0;
    public float health = 50f,maxHealth = health;
    public Weapon currentWeapon = null;
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

    public void addHealth(float v) {
        health += v;
        if(health > maxHealth)
            health = maxHealth;
    }

    public void addCoins(int i) {
        if(this instanceof Player){
            ((Player) this).coins += i;
        }
    }
}
