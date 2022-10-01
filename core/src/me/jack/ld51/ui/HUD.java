package me.jack.ld51.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import me.jack.ld51.level.Level;
import sun.jvm.hotspot.asm.sparc.SPARCRegister;

public class HUD {

    private Level level;
    public static BitmapFont font = new BitmapFont();
    float wheelRadius = 45f;
    public HUD(Level parent){
        this.level = parent;
    }


    public void drawTextures(SpriteBatch batch){
        drawHealthWheelTextures(batch,0,50);
    }

    public void drawShapes(ShapeRenderer renderer){
        drawHealthWheelShapes(renderer,0,50);
    }

    public void drawHealthWheelShapes(ShapeRenderer renderer,int x,int y){
        renderer.setColor(Color.BLACK);
        renderer.arc(x + wheelRadius,y,wheelRadius,0,360);
        renderer.setColor(Color.RED);
        renderer.arc(x + wheelRadius,y,wheelRadius,90,360 * (level.getPlayer().health / level.getPlayer().maxHealth));
        renderer.setColor(Color.WHITE);
        renderer.setColor(Color.BLACK);
        renderer.arc(x + wheelRadius,y,wheelRadius - 5,0,360);
        renderer.setColor(Color.WHITE);

    }

    public void drawHealthWheelTextures(SpriteBatch batch,int x,int y){
        font.setColor(Color.WHITE);
        font.draw(batch,Math.round(level.getPlayer().health) + "" , x+ wheelRadius - 10,y + 12);
        font.draw(batch, "___", x + wheelRadius - 10,y + 7);
        font.draw(batch, Math.round(level.getPlayer().maxHealth) + "", x+ wheelRadius - 10 ,y - 13);
    }
}
