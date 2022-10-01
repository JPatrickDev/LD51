package me.jack.ld51.tile;

public class WallTile extends Tile{
    public WallTile(int tX, int tY) {
        super(tX, tY);
    }

    @Override
    public String getTextureName() {
        return "wall.png";
    }
}
