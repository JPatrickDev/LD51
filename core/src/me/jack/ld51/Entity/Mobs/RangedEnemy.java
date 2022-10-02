package me.jack.ld51.Entity.Mobs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.compression.lzma.Base;

import me.jack.ld51.Entity.Projectiles.Weapons.Bullet;
import me.jack.ld51.Entity.Projectiles.Weapons.Knife;
import me.jack.ld51.level.Level;

public class RangedEnemy extends BaseEnemy {


    static Texture texture = new Texture("rangedenemy.png");
    public RangedEnemy(int x, int y) {
        super(texture, x, y);
        currentWeapon = new Bullet(this,1500);
    }

}
