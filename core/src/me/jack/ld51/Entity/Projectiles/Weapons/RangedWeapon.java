package me.jack.ld51.Entity.Projectiles.Weapons;

import com.badlogic.gdx.graphics.Texture;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.Entity.Projectiles.Projectile;
import me.jack.ld51.level.Level;

public abstract class RangedWeapon extends Weapon {
    public Texture projectileTexture;

    public RangedWeapon(Texture projectileTexture, Mob owner) {
        super(owner);
        this.projectileTexture = projectileTexture;
    }

    @Override
    public void use(Level parent, Mob target) {
        use(parent, target.getX(), target.getY());
    }

    public abstract long lifespan();

    public void use(Level parent, int tx, int ty) {
        System.out.println(lastUse);
        if (System.currentTimeMillis() - lastUse > fireRate() & usage > 0.3) {
            parent.spawnEntity(new Projectile(this.owner, this, tx, ty));
            lastUse = System.currentTimeMillis();
            usage -= usageRate;
        }

    }

    public void onRemove(Projectile source,Level parent){

    }
}
