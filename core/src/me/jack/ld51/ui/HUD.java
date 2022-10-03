package me.jack.ld51.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.Rectangle;

import me.jack.ld51.Entity.Mobs.Player;
import me.jack.ld51.Entity.Projectiles.Weapons.Weapon;
import me.jack.ld51.Screen.InGameScreen;
import me.jack.ld51.level.Level;

public class HUD {

    private Level level;
    public static BitmapFont font = new BitmapFont();
    public static final float wheelRadius = 45f;
    public Texture upgradesButton = new Texture("upgrade.png");
    public Texture tutorialButton = new Texture("tutorialbutton.png");
    public Texture roundBar = new Texture("roundbar.png");
    InGameScreen igs;


    public HUD(Level parent, InGameScreen igs) {
        this.level = parent;
        this.igs = igs;
    }


    public void drawTextures(SpriteBatch batch) {
        drawHealthWheelTextures(batch, 0, (int) -wheelRadius);
        drawRoundBarTextures(batch, 100, (int) -wheelRadius);
        drawWeaponWheelTextures(batch, 220, (int) -wheelRadius*2 + 12);
        drawButtons(batch, Gdx.graphics.getWidth() - upgradesButton.getWidth() - 10);
        if (Gdx.input.isButtonJustPressed(0)) {
            if (new Rectangle(Gdx.graphics.getWidth() - upgradesButton.getWidth() - 10, (int) ((int) 10 - wheelRadius * 2), 128, 32).contains(InGameScreen.getMX(), InGameScreen.getMY())) {
                if (igs.dialog == null) {
                    igs.dialog = new UpgradesDialog(level, igs);
                    level.pausedAt = System.currentTimeMillis();
                }
            }
            if (new Rectangle(Gdx.graphics.getWidth() - upgradesButton.getWidth() - 10, (int) (10 + upgradesButton.getHeight() - wheelRadius * 2 + 10), 128, 32).contains(InGameScreen.getMX(), InGameScreen.getMY())) {
                if (igs.dialog == null) {
                    igs.dialog = new TutorialDialog(level, igs);
                    level.pausedAt = System.currentTimeMillis();
                }
            }
        }
    }

    public void drawButtons(SpriteBatch batch, int x) {
        batch.draw(upgradesButton, x, 10 - wheelRadius * 2);
        batch.draw(tutorialButton, x, 10 + upgradesButton.getHeight() - wheelRadius * 2 + 10);
    }

    public void drawShapes(ShapeRenderer renderer) {
        drawHealthWheelShapes(renderer, 0, (int) -wheelRadius);
        drawRoundBarShapes(renderer, 100, (int) -wheelRadius);
        drawWeaponWheelShapes(renderer, 220, (int) -wheelRadius * 2 + 12);
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
        renderer.rect(x, y, 110, 30);
        renderer.setColor(Color.CYAN);
        long v = System.currentTimeMillis();
        if (level.pausedAt != -1)
            v = level.pausedAt;
        renderer.rect(x, y, 100 * ((v - level.roundTimer) / 10000.0f), 30);
    }

    public void drawRoundBarTextures(SpriteBatch batch, int x, int y) {
     //   renderer.rect(x, y, 100 * ((v - level.roundTimer) / 10000.0f), 30);
        batch.draw(roundBar,x,y);
        font.setColor(Color.RED);
        font.draw(batch, "Round: " + level.currentRound, x, y - 2);
        batch.draw(UpgradesDialog.coin, x, y - 34, 16, 16);
        font.draw(batch, level.getPlayer().coins + "", x + 20, y - 20);
    }


    public void drawWeaponWheelShapes(ShapeRenderer renderer, int x, int y) {
        Player p = level.getPlayer();
        Weapon[] w = p.weaponWheel;
        renderer.setColor(Color.WHITE);
        for (int i = 0; i != w.length; i++) {
            renderer.rect(x + (i * 70), y, 64, 64);
        }
        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        for (int i = 0; i != w.length; i++) {
            renderer.set(ShapeRenderer.ShapeType.Filled);
            float num = w[i].usage;
            if (w[i] == p.currentWeapon) {
                        renderer.setColor(Color.RED);
            }else{
                renderer.setColor(Color.BLACK);
            }
            if (num >= 0) {
                float f = num / 0.25f;
                if (f > 1)
                    f = 1;
                renderer.rectLine(x + (i * 70), y + 64, x + (i * 70) + 64 * f, y + 64, 3);
            }
            if (num >= 0.25) {
                float factor = num - 0.25f;
                float f = factor / 0.25f;
                if (f > 1)
                    f = 1;
                renderer.rectLine(x + (i * 70) + 64, y + (64 - 64 * f), x + (i * 70) + 64, y + 64, 3);
            }
            if (num >= 0.5) {
                float factor = num - 0.5f;
                float f = factor / 0.25f;
                if (f > 1)
                    f = 1;
                renderer.rectLine(x + (i * 70) + (64 - (64 * f)), y, x + (i * 70) + 64, y, 3);
            }
            if (num >= 0.75) {
                float factor = num - 0.75f;
                float f = factor / 0.25f;
                if (f > 1)
                    f = 1;
                renderer.rectLine(x + (i * 70), y, x + (i * 70), y + (64 * f), 3);
            }


            renderer.set(ShapeRenderer.ShapeType.Line);


        //    renderer.rect(x + (i * 40), y, 32, 32);
            renderer.setColor(Color.BLACK);
        }
        renderer.set(ShapeRenderer.ShapeType.Filled);
    }

    public void drawWeaponWheelTextures(SpriteBatch batch, int x, int y) {
        Player p = level.getPlayer();
        Weapon[] w = p.weaponWheel;

        for (int i = 0; i != w.length; i++) {
            if (w[i] != null) {
                batch.draw(w[i].icon, x + i * 70, y,64,64);
                if (w[i].unlockedAt > level.currentRound) {
                    batch.draw(UpgradesDialog.padlock, x + i * 70 + 16, y + 16);
                    font.setColor(Color.WHITE);
                    font.draw(batch, w[i].unlockedAt + "", x + i * 40 + 10, y + 16);
                }
            }
        }
        //  batch.draw(upgradesButton, x + 2, y - 34);
    }
}
