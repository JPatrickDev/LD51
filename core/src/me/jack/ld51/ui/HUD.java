package me.jack.ld51.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import me.jack.ld51.Entity.Mobs.Player;
import me.jack.ld51.Entity.Projectiles.Weapon;
import me.jack.ld51.level.Level;
import sun.jvm.hotspot.asm.sparc.SPARCRegister;

public class HUD {

    private Level level;
    public static BitmapFont font = new BitmapFont();
    float wheelRadius = 45f;

    public HUD(Level parent) {
        this.level = parent;
    }


    public void drawTextures(SpriteBatch batch) {
        drawHealthWheelTextures(batch, 0, 50);
        drawRoundBarTextures(batch, 100, 50);
        drawWeaponWheelTextures(batch, 230, 50);
    }

    public void drawShapes(ShapeRenderer renderer) {
        drawHealthWheelShapes(renderer, 0, 50);
        drawRoundBarShapes(renderer, 100, 50);
        drawWeaponWheelShapes(renderer, 230, 50);
    }

    public void drawHealthWheelShapes(ShapeRenderer renderer, int x, int y) {
        renderer.setColor(Color.BLACK);
        renderer.arc(x + wheelRadius, y, wheelRadius, 0, 360);
        renderer.setColor(Color.RED);
        renderer.arc(x + wheelRadius, y, wheelRadius, 90, 360 * (level.getPlayer().health / level.getPlayer().maxHealth));
        renderer.setColor(Color.WHITE);
        renderer.setColor(Color.BLACK);
        renderer.arc(x + wheelRadius, y, wheelRadius - 5, 0, 360);
        renderer.setColor(Color.WHITE);

    }

    public void drawHealthWheelTextures(SpriteBatch batch, int x, int y) {
        font.setColor(Color.WHITE);
        font.draw(batch, Math.round(level.getPlayer().health) + "", x + wheelRadius - 10, y + 12);
        font.draw(batch, "___", x + wheelRadius - 10, y + 7);
        font.draw(batch, Math.round(level.getPlayer().maxHealth) + "", x + wheelRadius - 10, y - 13);
    }

    public void drawRoundBarShapes(ShapeRenderer renderer, int x, int y) {
        renderer.setColor(Color.BLACK);
        renderer.rect(x, y, 100, 30);
        renderer.setColor(Color.CYAN);
        renderer.rect(x, y, 100 * ((System.currentTimeMillis() - level.roundTimer) / 10000.0f), 30);
    }

    public void drawRoundBarTextures(SpriteBatch batch, int x, int y) {
        font.setColor(Color.RED);
        font.draw(batch, "Round: " + level.currentRound, x, y - 2);

    }


    public void drawWeaponWheelShapes(ShapeRenderer renderer, int x, int y) {
        Player p = level.getPlayer();
        Weapon[] w = p.weaponWheel;
        renderer.setColor(Color.WHITE);
        for(int i = 0; i != w.length; i++){
            renderer.rect(x + (i*34),y,32,32);
        }
        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        for(int i = 0; i != w.length; i++){
            if(w[i] == p.currentWeapon){
                renderer.setColor(Color.RED);
            }
            renderer.rect(x + (i*34),y,32,32);
            renderer.setColor(Color.BLACK);
        }
        renderer.set(ShapeRenderer.ShapeType.Filled);
    }

    public void drawWeaponWheelTextures(SpriteBatch batch, int x, int y) {
        Player p = level.getPlayer();
        Weapon[] w = p.weaponWheel;

        for(int i = 0; i != w.length; i++){
            if(w[i] != null){
                batch.draw(w[i].icon,x + i * 32,y);
            }
        }

    }
}
