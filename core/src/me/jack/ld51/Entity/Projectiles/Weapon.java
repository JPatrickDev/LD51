package me.jack.ld51.Entity.Projectiles;

import com.badlogic.gdx.graphics.Texture;

public abstract class Weapon {

    public Texture projectileTexture;
    public Weapon(Texture projectileTexture){
        this.projectileTexture = projectileTexture;
    }
    public abstract long fireRate();
    public abstract long lifespan();
    public abstract float damage();
}
