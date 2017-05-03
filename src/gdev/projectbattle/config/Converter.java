package gdev.projectbattle.config;

import gdev.projectbattle.math.Point;
import gdev.projectbattle.math.Vec2;

public class Converter {
    public static Vec2 toVec2(Point point) {
        return new Vec2(toDouble(point.x), toDouble(point.y));
    }

    public static Vec2 toVec2(int x, int y) {
        return new Vec2(toDouble(x), toDouble(y));
    }

    public static Point toPoint(Vec2 position) {
        return new Point(toInt(position.x), toInt(position.y));
    }

    public static Point toPoint(double x, double y) {
        return new Point(toInt(x), toInt(y));
    }

    public static int toInt(double d) {
        return (int) ((d / GameConfig.GRID_SIZE) * GameConfig.GRID_RESOLUTION);
    }

    public static double toDouble(int i) {
        return (((double) i + 0.5) / (double) GameConfig.GRID_RESOLUTION) * GameConfig.GRID_SIZE;
    }
}
