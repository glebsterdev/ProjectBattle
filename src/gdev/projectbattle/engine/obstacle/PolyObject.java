package gdev.projectbattle.engine.obstacle;

import gdev.projectbattle.math.Vec2;

public class PolyObject {
    public final Vec2[] vertices;

    public PolyObject(Vec2... vertices) {
        this.vertices = vertices;
    }
}
