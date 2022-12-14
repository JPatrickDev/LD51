package me.jack.ld51.Entity.Projectiles.Weapons;

import com.badlogic.gdx.graphics.Texture;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.ui.TexCache;

public class Knife extends MeleeWeapon{
    public Knife(Mob mob) {
        super(mob);
        icon  = TexCache.get("knife.png");
    }

    @Override
    public long fireRate() {
        return 500;
    }

    @Override
    public float damage() {
        return 10;
    }

    @Override
    public float range() {
        return 30f;
    }
}
