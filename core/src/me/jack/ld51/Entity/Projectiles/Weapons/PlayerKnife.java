package me.jack.ld51.Entity.Projectiles.Weapons;

import com.badlogic.gdx.graphics.Texture;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.ui.TexCache;

public class PlayerKnife extends MeleeWeapon{
    public PlayerKnife(Mob mob) {
        super(mob);
        icon  = TexCache.get("knife.png");
    }

    @Override
    public long fireRate() {
        return 250;
    }

    @Override
    public float damage() {
        return 30;
    }

    @Override
    public float range() {
        return 60f;
    }
}
