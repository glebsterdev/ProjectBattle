package gdev.projectbattle.engine.obstacle;

import gdev.projectbattle.math.Vec2;

public class Obstacle {
    public final Vec2[] vertices;

    public final Vec2[][] segments;

    public Obstacle(Vec2... vertices) {
        this.vertices = vertices;
        segments = new Vec2[vertices.length][2];
        for (int i = 0; i < vertices.length; i++) {
            Vec2 p0 = vertices[i];
            Vec2 p1;
            if (i == vertices.length - 1)
                p1 = vertices[0];
            else
                p1 = vertices[i + 1];
            if (p0.y >= p1.y) {
                segments[i][0] = p1;
                segments[i][1] = p0;
            } else {
                segments[i][0] = p0;
                segments[i][1] = p1;
            }
        }
    }
}
