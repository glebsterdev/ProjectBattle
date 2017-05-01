package gdev.projectbattle.engine.terrain;

import gdev.projectbattle.config.GameConfig;
import gdev.projectbattle.engine.algorithms.pathfinder.PathMap;
import gdev.projectbattle.engine.obstacle.RasterizedObject;
import gdev.projectbattle.math.Point;
import gdev.projectbattle.math.Vec2;

public class GridTerrain {
    public GridTile[][] tiles;

    public GridTerrain() {
        this.tiles = new GridTile[GameConfig.GRID_RESOLUTION][GameConfig.GRID_RESOLUTION];

        for (int x = 0; x < GameConfig.GRID_RESOLUTION; x++) {
            for (int y = 0; y < GameConfig.GRID_RESOLUTION; y++) {
                Vec2 center = new Vec2(
                        (((double) x + 0.5) / (double) GameConfig.GRID_RESOLUTION) * GameConfig.GRID_SIZE,
                        (((double) y + 0.5) / (double) GameConfig.GRID_RESOLUTION) * GameConfig.GRID_SIZE);
                tiles[x][y] = new GridTile(x, y, center);
            }
        }
    }

    public void addObject(RasterizedObject ro) {
        for (Point p: ro.raster)
            addObstacle(p.x, p.y);
    }

    public void addObstacle(int x, int y) {
        tiles[x][y].setFull();
    }

    public GridTile getTileFromPos(Vec2 position) {
        int sX = (int) ((position.x / GameConfig.GRID_SIZE) * tiles.length);
        int sY = (int) ((position.y / GameConfig.GRID_SIZE) * tiles.length);
        return tiles[sX][sY];
    }

    public PathMap generatePathMap(Vec2 destination) {
        return new PathMap(this, destination);
    }
}
