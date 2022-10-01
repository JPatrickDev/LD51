package me.jack.ld51.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import me.jack.ld51.level.Level;

public abstract class Entity {

    private Texture texture;

    protected int x, y;
    protected int w;
    protected int h;


    //How many times the Entity has bounced off a wall
    public int bounces;

    protected float dX, dY;

    public Entity(Texture t, int x, int y) {
        this.texture = t;
        this.x = x;
        this.y = y;
        w = texture.getWidth();
        h = texture.getHeight();
    }


    public void renderTextures(SpriteBatch batch) {
        batch.draw(texture, getX(), getY());
    }

    public void renderShapes(ShapeRenderer renderer) {

    }

    public void update(Level parent) {
        parent.doMove(this);
    }
    //Slightly tweak the current velocity, so bouncing between two walls isn't deterministic
    public void randVel(){
        float xOff = new Random().nextFloat();
        float yOff = new Random().nextFloat();
        if(dX > 0){
            dX += xOff;
        }else{
            dX -=xOff;
        }

        if(dY > 0){
            dY += yOff;
        }else{
            dY -=yOff;
        }
    }

    public void onDeath(Level level) {
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public int getW() {
        return w;
    }


    public int getH() {
        return h;
    }

    //Should only be called from Level, doesn't perform any checks
    public void move(){
        x += dX;
        y += dY;
        dX /= 1.1;
        dY /= 1.1;
    }


    public float getdX() {
        return dX;
    }


    public float getdY() {
        return dY;
    }

    public void setdX(float dX) {
        this.dX = dX;
    }

    public void setdY(float dY) {
        this.dY = dY;
    }
}
