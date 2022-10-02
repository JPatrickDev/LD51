package me.jack.ld51.Entity.Mobs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import me.jack.ld51.Entity.Projectiles.Weapons.Knife;
import me.jack.ld51.level.Level;

public class GruntEnemy extends BaseEnemy {
    static Texture texture = new Texture("grunt.png");
    public GruntEnemy(int x, int y) {
        super(texture,x, y);
        currentWeapon = new Knife(this);
    }
}
