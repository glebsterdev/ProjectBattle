package gdev.projectbattle.engine.terrain;

import gdev.projectbattle.config.Converter;
import gdev.projectbattle.math.Vec2;

public class GridTile {
    public final int x;
    public final int y;
    public final Vec2 center;
    public boolean isFull = false;

    public GridTile(int x, int y) {
        this.x = x;
        this.y = y;
        this.center = Converter.toVec2(x, y);
    }

    public void setFull() {
        isFull = true;
    }
}
