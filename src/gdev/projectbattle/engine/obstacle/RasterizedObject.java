package gdev.projectbattle.engine.obstacle;

import gdev.projectbattle.config.GameConfig;
import gdev.projectbattle.math.Point;
import gdev.projectbattle.math.Vec2;

import java.util.*;

public class RasterizedObject {
    public final List<Point> raster = new ArrayList<>();

    public RasterizedObject(PolyObject polyObject){
        final HashMap<Integer, List<Integer>> pixelMap = createPixelMap(polyObject.vertices);

        for (int y : pixelMap.keySet()) {
            List<Integer> l = pixelMap.get(y);
            Collections.sort(l);
            if (l.size() % 2 == 0) {
                for (int i = 0; i < l.size(); i += 2) {
                    for (int x = l.get(i); x <= l.get(i + 1); x++) {
                        Point p = new Point(x, y);
                        if(!raster.contains(p))
                            raster.add(p);
                    }
                }
            }
        }
    }

    private HashMap<Integer, List<Integer>> createPixelMap(Vec2[] vertices) {
        HashMap<Integer, List<Integer>> pixelMap = new HashMap<>();
        for (int i = 0; i < vertices.length; i++) {
            Vec2 v0 = vertices[i];
            Vec2 v1 = vertices[(i == vertices.length - 1) ? 0 : i + 1];
            Vec2 vTop = (v0.y > v1.y) ? v0 : v1;
            Vec2 vBottom = (v0.y > v1.y) ? v1 : v0;
            int top = getY(vTop.y);
            int bottom = getY(vBottom.y);
            if (vTop.x == vBottom.x) {
                for (int y = bottom; y <= top; y++) {
                    double Y = getAsymptote(y);
                    if (Y > vBottom.y && Y < vTop.y) {
                        addToMap(pixelMap, getX(vTop.x), getY(Y));
                    }
                }
            } else {
                double slope = (vBottom.y - vTop.y) / (vBottom.x - vTop.x);
                double offset = vTop.y - slope * vTop.x;
                for (int y = bottom; y <= top; y++) {
                    double Y = getAsymptote(y);
                    if (Y >= vBottom.y && Y < vTop.y) {
                        double X = (Y - offset) / slope;
                        addToMap(pixelMap, getX(X), getY(Y));
                    }
                }
            }
        }
        return pixelMap;
    }

    private double getAsymptote(int y) {
        return (((double) y + 0.5) / (double) GameConfig.GRID_RESOLUTION) * GameConfig.GRID_SIZE;
    }

    private int getX(double x) {
        return (int) ((x / GameConfig.GRID_SIZE) * GameConfig.GRID_RESOLUTION);
    }

    private int getY(double y) {
        return (int) ((y / GameConfig.GRID_SIZE) * GameConfig.GRID_RESOLUTION);
    }

    private static void addToMap(HashMap<Integer, List<Integer>> pixelMap, int x, int y) {
        if (pixelMap.containsKey(y)) {
            List<Integer> l = pixelMap.get(y);
            l.add(x);
        } else {
            List<Integer> l = new ArrayList<>();
            l.add(x);
            pixelMap.put(y, l);
        }
    }
}
