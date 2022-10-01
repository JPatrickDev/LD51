package me.jack.ld51.tile;

public class FloorTile extends Tile{
    public FloorTile(int tX, int tY) {
        super(tX, tY);
    }

    @Override
    public String getTextureName() {
        return "floor.png";
    }
}
