package me.jack.ld51.Entity.Particles.Weapons;

import com.badlogic.gdx.graphics.Color;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.Entity.Particles.Particle;

public class WeaponParticle extends Particle {
    public Mob owner;
    public WeaponParticle(int x, int y, int w, int h, Color c1, Color c2, Mob owner) {
        super(x, y, w, h, c1, c2);
        this.owner = owner;
    }


    public float getDamage(){
        return 20f;
    }

}
