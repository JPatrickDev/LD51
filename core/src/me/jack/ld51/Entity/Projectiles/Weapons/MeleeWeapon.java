package me.jack.ld51.Entity.Projectiles.Weapons;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.level.Level;

public abstract class MeleeWeapon extends Weapon{
    public MeleeWeapon(Mob owner) {
        super(owner);
    }

    @Override
    public void use(Level parent, Mob target) {
        if(System.currentTimeMillis() - lastUse > fireRate()) {
         //   target.takeDamage(damage());
            lastUse = System.currentTimeMillis();
        }
    }
}
