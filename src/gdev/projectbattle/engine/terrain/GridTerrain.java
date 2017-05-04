package gdev.projectbattle.engine.terrain;

import gdev.projectbattle.config.Converter;
import gdev.projectbattle.config.GameConfig;
import gdev.projectbattle.engine.obstacle.RasterizedObject;
import gdev.projectbattle.math.Point;
import gdev.projectbattle.math.Vec2;

public class GridTerrain {
    public GridTile[][] tiles;

    public GridTerrain() {
        this.tiles = new GridTile[GameConfig.GRID_RESOLUTION][GameConfig.GRID_RESOLUTION];

        for (int x = 0; x < GameConfig.GRID_RESOLUTION; x++) {
            for (int y = 0; y < GameConfig.GRID_RESOLUTION; y++) {
                tiles[x][y] = new GridTile(x, y);
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
        return tiles[Converter.toInt(position.x)][Converter.toInt(position.y)];
    }
}
