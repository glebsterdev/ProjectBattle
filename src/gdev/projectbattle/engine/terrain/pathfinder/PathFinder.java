package gdev.projectbattle.engine.terrain.pathfinder;

import gdev.projectbattle.config.Converter;
import gdev.projectbattle.engine.terrain.GridTerrain;
import gdev.projectbattle.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    private final PathTile[][] pathTiles;

    public PathFinder(GridTerrain gridTerrain) {
        pathTiles = new PathTile[gridTerrain.tiles.length][gridTerrain.tiles.length];
        for (int x = 0; x < pathTiles.length; x++) {
            for (int y = 0; y < pathTiles.length; y++) {
                pathTiles[x][y] = new PathTile(x, y, gridTerrain.tiles, pathTiles);
            }
        }
    }

    public void reCalculate(Vec2 destination){
        for (int x = 0; x < pathTiles.length; x++) {
            for (int y = 0; y < pathTiles.length; y++) {
                pathTiles[x][y].reset();
            }
        }

        List<PathTile> closed = new ArrayList<>();
        List<PathTile> opened = new ArrayList<>();

        PathTile d = getTileFromPos(destination);
        d.close();
        closed.add(d);

        while (!closed.isEmpty()) {
            for (PathTile closedPathTile : closed) {
                closedPathTile.openAdjacent(opened);
            }
            closed.clear();
            for (PathTile openedPathTile : opened) {
                openedPathTile.close();
                closed.add(openedPathTile);
            }
            opened.clear();
        }
    }

    public List<Vec2> getPathFrom(Vec2 position) {
        List<Vec2> path = new ArrayList<>();
        return getTileFromPos(position).getPath(path);
    }

    private PathTile getTileFromPos(Vec2 position) {
        return pathTiles[Converter.toInt(position.x)][Converter.toInt(position.y)];
    }
}
