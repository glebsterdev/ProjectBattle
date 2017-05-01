package gdev.projectbattle.graphics.javafx.battle;

import gdev.projectbattle.config.Colors;
import gdev.projectbattle.config.UiConfig;
import gdev.projectbattle.engine.obstacle.Obstacle;
import javafx.scene.shape.Polygon;

public class ObstacleGraphics extends Polygon {
    public ObstacleGraphics(Obstacle obstacle) {
        Double[] points = new Double[2 * obstacle.vertices.length];
        for (int i = 0; i < obstacle.vertices.length; i++) {
            points[2 * i] = UiConfig.UNIT_SIZE * obstacle.vertices[i].x;
            points[2 * i + 1] = -UiConfig.UNIT_SIZE * obstacle.vertices[i].y;
        }
        setFill(Colors.OBSTACLE_BROWN);
        getPoints().addAll(points);
    }
}
