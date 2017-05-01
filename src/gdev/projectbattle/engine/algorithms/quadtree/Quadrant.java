package gdev.projectbattle.engine.algorithms.quadtree;

import gdev.projectbattle.math.Vec2;

public class Quadrant {
    public final double MARGIN = 1;
    public final Vec2 center;
    public final double dimension;

    public Quadrant(Vec2 center, double dimension) {
        this.center = center;
        this.dimension = dimension;
    }

    public boolean contains(Vec2 p) {
        return Math.abs(center.x - p.x) <= dimension + MARGIN && Math.abs(center.y - p.y) <= dimension + MARGIN;
    }

    public double leftSide() {
        return center.x - dimension;
    }

    public double rightSide() {
        return center.x + dimension;
    }

    public double topSide() {
        return center.y + dimension;
    }

    public double bottonSide() {
        return center.y - dimension;
    }
}
