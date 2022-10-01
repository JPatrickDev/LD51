package me.jack.ld51.Entity.Projectiles;

import com.badlogic.gdx.graphics.Texture;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.level.Level;

public abstract class Weapon {

    protected Mob owner;
    protected long lastUse;
    public Weapon(Mob owner){
        this.owner = owner;
    }
    public abstract long fireRate();

    public abstract float damage();
    public abstract float range();

    public abstract void use(Level parent, Mob target);
}
