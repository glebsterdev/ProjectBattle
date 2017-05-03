package gdev.projectbattle.engine.obstacle;

import gdev.projectbattle.config.GameConfig;
import gdev.projectbattle.config.Converter;
import gdev.projectbattle.math.Point;
import gdev.projectbattle.math.Vec2;

import java.awt.*;
import java.util.*;
import java.util.List;

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
            int top = Converter.toInt(vTop.y);
            int bottom = Converter.toInt(vBottom.y);
            if (vTop.x == vBottom.x) {
                for (int y = bottom; y <= top; y++) {
                    double Y = Converter.toDouble(y);
                    if (Y > vBottom.y && Y < vTop.y) {
                        addToMap(pixelMap, Converter.toPoint(vTop.x, Y));
                    }
                }
            } else {
                double slope = (vBottom.y - vTop.y) / (vBottom.x - vTop.x);
                double offset = vTop.y - slope * vTop.x;
                for (int y = bottom; y <= top; y++) {
                    double Y = Converter.toDouble(y);
                    if (Y >= vBottom.y && Y < vTop.y) {
                        double X = (Y - offset) / slope;
                        addToMap(pixelMap, Converter.toPoint(X, Y));
                    }
                }
            }
        }
        return pixelMap;
    }

    private static void addToMap(HashMap<Integer, List<Integer>> pixelMap, Point p) {
        if (pixelMap.containsKey(p.y)) {
            List<Integer> l = pixelMap.get(p.y);
            l.add(p.x);
        } else {
            List<Integer> l = new ArrayList<>();
            l.add(p.x);
            pixelMap.put(p.y, l);
        }
    }
}
