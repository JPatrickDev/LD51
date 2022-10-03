package me.jack.ld51.Entity.Projectiles.Weapons;

import com.badlogic.gdx.graphics.Texture;

import me.jack.ld51.Entity.Mobs.BaseEnemy;
import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.Entity.Projectiles.Projectile;
import me.jack.ld51.level.Level;
import me.jack.ld51.ui.TexCache;

public class Bullet extends RangedWeapon {
    //TODO Map for common textures for all entities to use
    long firerate = 100;

    public Bullet(Mob owner, long firerate) {
        super(TexCache.get("projectiles/bullet.png"), owner);
        icon = TexCache.get("gun.png");
        this.firerate = firerate;
        this.name = "Handgun";
        this.description = "A basic handgun.";
        this.upgrades = new String[]{"FIRERATE:5:100:Increase the rate of fire:rapidfire.png",
                "DAMAGE:10:200:Deal more damage with bigger bullets:largerbullet.png",
                "MULTISHOT:15:500:Shoot three bullets at once:tripleshot.png"};
        unlockedAt = 0;
        usageRate=0.1f;
        regenRate=0.01f;
    }


    @Override
    public long fireRate() {
        if(owner instanceof BaseEnemy){
            return 2000;
        }
        if (appliedUpgrades.contains("FIRERATE")) {
            return 100;
        } else {
            return 150;
        }
    }

    @Override
    public void use(Level parent, int tx, int ty) {
        if (appliedUpgrades.contains("MULTISHOT")) {
            System.out.println(lastUse);
            if (System.currentTimeMillis() - lastUse > fireRate() & usage > 0.2) {
                parent.spawnEntity(new Projectile(this.owner, this, tx + 15, ty));
                parent.spawnEntity(new Projectile(this.owner, this, tx, ty));
                parent.spawnEntity(new Projectile(this.owner, this, tx - 15, ty));
                lastUse = System.currentTimeMillis();
                usage -= usageRate;
            }
        } else {
            super.use(parent, tx, ty);
        }


    }

    @Override
    public long lifespan() {
        return 2000;
    }

    @Override
    public float damage() {
        if (appliedUpgrades.contains("DAMAGE")) {
            return 55;
        } else {
            return 20;
        }
    }

    @Override
    public float range() {
        return 200;
    }

}
