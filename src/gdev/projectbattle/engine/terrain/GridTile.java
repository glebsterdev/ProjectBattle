package gdev.projectbattle.engine.terrain;

import gdev.projectbattle.math.Vec2;

public class GridTile {
    public final int x;
    public final int y;
    public final Vec2 center;
    public boolean isFull = false;

    public GridTile(int x, int y, Vec2 center) {
        this.x = x;
        this.y = y;
        this.center = center;
    }

    public void setFull() {
        isFull = true;
    }
}
