package gdev.projectbattle.graphics.javafx.battle;

import gdev.projectbattle.config.Colors;
import gdev.projectbattle.config.UiConfig;
import gdev.projectbattle.engine.obstacle.PolyObject;
import javafx.scene.shape.Polygon;

public class PolyObjectGraphics extends Polygon {
    public PolyObjectGraphics(PolyObject polyObject) {
        Double[] points = new Double[2 * polyObject.vertices.length];
        for (int i = 0; i < polyObject.vertices.length; i++) {
            points[2 * i] = UiConfig.UNIT_SIZE * polyObject.vertices[i].x;
            points[2 * i + 1] = -UiConfig.UNIT_SIZE * polyObject.vertices[i].y;
        }
        setFill(Colors.OBSTACLE_BROWN);
        getPoints().addAll(points);
    }
}
