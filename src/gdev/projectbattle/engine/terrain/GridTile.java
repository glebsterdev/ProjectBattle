package gdev.projectbattle.engine.terrain;

import gdev.projectbattle.config.Converter;
import gdev.projectbattle.math.Point;
import gdev.projectbattle.math.Vec2;

public class GridTile {
    public final Point cell;
    public final Vec2 center;
    public boolean isFull = false;

    public GridTile(int x, int y) {
        cell = new Point(x, y);
        center = Converter.toVec2(x, y);
    }

    public void setFull() {
        isFull = true;
    }
}
