package me.jack.ld51.Entity.Projectiles;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.Entity.Projectiles.Weapons.RangedWeapon;
import me.jack.ld51.level.Level;

public class SeekingProjectile extends Projectile {

    Entity owner;
    public RangedWeapon toFire;
    long start;
    Mob target;

    public SeekingProjectile(Entity owner, RangedWeapon toFire, Mob target) {
        super(owner, toFire, target.getX(), target.getY());
        this.target = target;
    }

    @Override
    public void update(Level parent) {
        super.update(parent);
        if (target != null) {
            float xSpeed = (fuzz(target.getX()) - x);
            float ySpeed = (fuzz(target.getY()) - y);

            float factor = (float) (10 / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
            xSpeed *= factor;
            ySpeed *= factor;
            dX = xSpeed;
            dY = ySpeed;
        }
        if (!parent.entities.contains(target)) {
            target = null;
        }
        if (bounces > 0) {
            parent.removeEntity(this);
        }
    }

}
