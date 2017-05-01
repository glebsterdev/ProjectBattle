package gdev.projectbattle.engine.algorithms.rasterizer;

import gdev.projectbattle.engine.terrain.Terrain;
import gdev.projectbattle.engine.terrain.TerrainTile;
import gdev.projectbattle.math.Vec2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Rasterizer {
    public static void rasterizeOnTerrain(Vec2[] vertices, Terrain terrain) {
        HashMap<Integer, List<Integer>> pixelMap = createPixelMap(terrain, vertices);
        for (int y : pixelMap.keySet()) {
            List<Integer> l = pixelMap.get(y);
            Collections.sort(l);
            if (l.size() % 2 == 0) {
                for (int i = 0; i < l.size(); i += 2) {
                    for (int x = l.get(i); x <= l.get(i + 1); x++) {
                        terrain.addObstacle(x, y);
                    }
                }
            }
        }
    }

    private static HashMap<Integer, List<Integer>> createPixelMap(Terrain terrain, Vec2[] vertices) {
        HashMap<Integer, List<Integer>> pixelMap = new HashMap<>();
        for (int i = 0; i < vertices.length; i++) {
            Vec2 v0 = vertices[i];
            Vec2 v1 = vertices[(i == vertices.length - 1) ? 0 : i + 1];
            Vec2 vTop = (v0.y > v1.y) ? v0 : v1;
            Vec2 vBottom = (v0.y > v1.y) ? v1 : v0;
            TerrainTile top = terrain.getTileFromPos(vTop);
            TerrainTile bottom = terrain.getTileFromPos(vBottom);
            if (vTop.x == vBottom.x) {
                for (int y = bottom.y; y <= top.y; y++) {
                    double Y = terrain.tiles[0][y].center.y;
                    if (Y > vBottom.y && Y < vTop.y) {
                        addToMap(pixelMap, terrain.getTileFromPos(new Vec2(vTop.x, Y)));
                    }
                }
            } else {
                double slope = (vBottom.y - vTop.y) / (vBottom.x - vTop.x);
                double offset = vTop.y - slope * vTop.x;
                for (int y = bottom.y; y <= top.y; y++) {
                    double Y = terrain.tiles[0][y].center.y;
                    if (Y >= vBottom.y && Y < vTop.y) {
                        double X = (Y - offset) / slope;
                        addToMap(pixelMap, terrain.getTileFromPos(new Vec2(X, Y)));
                    }
                }
            }
        }
        return pixelMap;
    }

    private static void addToMap(HashMap<Integer, List<Integer>> pixelMap, TerrainTile t) {
        if (pixelMap.containsKey(t.y)) {
            List<Integer> l = pixelMap.get(t.y);
            l.add(t.x);
        } else {
            List<Integer> l = new ArrayList<>();
            l.add(t.x);
            pixelMap.put(t.y, l);
        }
    }
}
