package me.jack.ld51.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.HashMap;

import me.jack.ld51.level.Level;

public abstract class Tile {

    private boolean solid;
    public static HashMap<String,Texture> textures = new HashMap<>();

    public static final int TILE_SIZE = 32;
    public int tX,tY;

    public Tile(int tX,int tY){
        this.tX = tX;
        this.tY = tY;
    }
    public abstract String getTextureName();

    public void render(SpriteBatch batch){
        if(!textures.containsKey(getTextureName())){
            textures.put(getTextureName(), new Texture(getTextureName()));
        }else{
            Texture t = textures.get(getTextureName());
            batch.draw(t,tX* TILE_SIZE,tY* TILE_SIZE);
        }

    }


    public void update(Level parent){}
}
