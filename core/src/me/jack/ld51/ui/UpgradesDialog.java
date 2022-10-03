package me.jack.ld51.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.Rectangle;
import java.util.HashMap;

import me.jack.ld51.Entity.Mobs.Player;
import me.jack.ld51.Entity.Projectiles.Weapons.Weapon;
import me.jack.ld51.Screen.InGameScreen;
import me.jack.ld51.level.Level;

public class UpgradesDialog extends Dialog{
    Level parent;

    public static Texture coin = new Texture("coin.png");
    public static Texture padlock = new Texture("padlock.png");
    public static Texture purchaseButton = new Texture("buttons/purchase.png");
    public static Texture close = new Texture("closedialog.png");
    public HashMap<Integer, Rectangle> weaponSelections = new HashMap<>();

    public HashMap<Rectangle, Object[]> currentPurchaseButtons = null;
    int currentSelection = 3;

    InGameScreen igs;

    public UpgradesDialog(Level parent, InGameScreen igs) {
        this.parent = parent;
        this.igs = igs;
        for (int i = 0; i != 4; i++) {
            weaponSelections.put(i, new Rectangle(60 + (i * 150), 350, 64, 64));
        }


    }

    public void renderTextures(SpriteBatch batch) {
        drawWeaponWheelTextures(batch, 60, 350);

        if (Gdx.input.isButtonJustPressed(0)) {
            for (Integer i : weaponSelections.keySet()) {
                if (weaponSelections.get(i).contains(InGameScreen.getMX(), InGameScreen.getMY())) {
                    currentSelection = i;
                    currentPurchaseButtons = null;
                }
            }
            if (new Rectangle(550, 415, 32, 32).contains(InGameScreen.getMX(), InGameScreen.getMY())) {
                igs.dialog = null;
                parent.roundTimer += (System.currentTimeMillis() - parent.pausedAt);
                parent.pausedAt = -1;

            }
            if (currentPurchaseButtons == null) {
                return;
            }
            HashMap<Rectangle, Object[]> copy = new HashMap<>(currentPurchaseButtons);
            for (Rectangle r : copy.keySet()) {
                int c = Integer.parseInt((String) currentPurchaseButtons.get(r)[2]);
                if (r.contains(InGameScreen.getMX(), InGameScreen.getMY()) && parent.getPlayer().canBuy(c)) {
                    ((Weapon) currentPurchaseButtons.get(r)[0]).appliedUpgrades.add((String) currentPurchaseButtons.get(r)[1]);
                    currentPurchaseButtons.remove(r);
                    parent.getPlayer().buy(c);
                }
            }
        }
        batch.draw(close, 550, 415);
    }

    public void renderShapes(ShapeRenderer renderer) {
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.GRAY);
        renderer.rect(50, 50, 540, 400);
        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        renderer.rect(50, 50, 540, 400);


        drawWeaponWheelShapes(renderer, 60, 350);


    }

    public void drawWeaponWheelShapes(ShapeRenderer renderer, int x, int y) {
        Player p = parent.getPlayer();
        Weapon[] w = p.weaponWheel;
        renderer.setColor(Color.WHITE);
        for (int i = 0; i != w.length; i++) {
            renderer.rect(x + (i * 150), y, 64, 64);
        }
        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        Weapon selected = null;
        for (int i = 0; i != w.length; i++) {
            if (i == currentSelection) {
                selected = w[i];
                renderer.setColor(Color.RED);
            }
            renderer.rect(x + (i * 150), y, 64, 64);
            renderer.setColor(Color.BLACK);
        }
        drawUpgradesListShapes(selected, x, (50 + (y - 50) / 2) - (70 * 3) / 2, renderer);
    }

    public void drawWeaponWheelTextures(SpriteBatch batch, int x, int y) {
        Player p = parent.getPlayer();
        Weapon[] w = p.weaponWheel;
        Weapon selected = null;
        for (int i = 0; i != w.length; i++) {
            if (i == currentSelection) {
                selected = w[i];
            }
            batch.draw(w[i].icon, x + i * 150, y, 64, 64);
            if (w[i] != null && w[i].unlockedAt > parent.currentRound) {
                batch.draw(padlock, x + i * 150, y, 64, 64);
                HUD.font.draw(batch, w[i].unlockedAt + "", x + i * 150 + 25, y + 16);
            }
        }

        HUD.font.draw(batch, selected.name, x + currentSelection * 150, y);
        drawUpgradesListTextures(selected, x, (50 + (y - 50) / 2) - (70 * 3) / 2, batch);
    }

    public void drawUpgradesListShapes(Weapon weapon, int x, int y, ShapeRenderer renderer) {
        int i = 0;
        renderer.set(ShapeRenderer.ShapeType.Line);
        for (String s : weapon.upgrades) {
            String[] data = s.split(":");
            String texture = data[4];
            renderer.rect(x, y + (i * 70), 520, 70);
            i++;
        }
    }

    GlyphLayout layout = new GlyphLayout();

    public void drawUpgradesListTextures(Weapon weapon, int x, int y, SpriteBatch batch) {
        int i = 0;
        if (currentPurchaseButtons == null) {
            currentPurchaseButtons = new HashMap<>();
            for (String s : weapon.upgrades) {
                String[] data = s.split(":");
                if (parent.currentRound < Integer.parseInt(data[1])) {
                } else if (parent.getPlayer().coins < Integer.parseInt(data[2])) {
                } else if (weapon.appliedUpgrades.contains(data[0])) {

                } else {
                    currentPurchaseButtons.put(new Rectangle(440, y + (i * 70) + 20, purchaseButton.getWidth(), purchaseButton.getHeight()), new Object[]{weapon, data[0],data[2]});
                    batch.draw(purchaseButton, 480, y + (i * 70) + 20);
                }
                i++;

            }
        }
        i = 0;
        for (String s : weapon.upgrades) {
            String[] data = s.split(":");
            String texture = data[4];
            Texture t = new Texture(texture);
            batch.draw(t, x, y + (i * 70), 64, 64);
            HUD.font.draw(batch, data[3], x + 70, y + (i * 70) + 32 + HUD.font.getLineHeight() / 2);


            if (parent.currentRound < Integer.parseInt(data[1])) {
                batch.draw(padlock, 480, y + (i * 70) + 26, 16, 16);
                HUD.font.draw(batch, "Round:" + data[1], 500, y + (i * 70) + 32 + HUD.font.getLineHeight() / 2);
            } else if (parent.getPlayer().coins < Integer.parseInt(data[2])) {
                batch.draw(coin, 480, y + (i * 70) + 26, 16, 16);
                HUD.font.draw(batch, data[2], 500, y + (i * 70) + 32 + HUD.font.getLineHeight() / 2);
            } else if (weapon.appliedUpgrades.contains(data[0])) {

            } else {
                batch.draw(purchaseButton, 440, y + (i * 70) + 20);
            }

            i++;

        }
    }
}
