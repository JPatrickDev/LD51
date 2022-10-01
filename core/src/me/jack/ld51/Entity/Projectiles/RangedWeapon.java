package me.jack.ld51.Entity.Projectiles;

import com.badlogic.gdx.graphics.Texture;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.level.Level;

public abstract class RangedWeapon extends Weapon{
    public Texture projectileTexture;
    public RangedWeapon(Texture projectileTexture, Mob owner) {
        super(owner);
        this.projectileTexture = projectileTexture;
    }

    @Override
    public void use(Level parent, Mob target) {

    }

    public abstract long lifespan();

    public void use(Level parent, int tx,int ty){
        System.out.println( lastUse);
        if(System.currentTimeMillis() - lastUse > fireRate()) {
            parent.spawnEntity(new Projectile(this.owner, this, tx, ty));
            lastUse = System.currentTimeMillis();
        }
    }
}
