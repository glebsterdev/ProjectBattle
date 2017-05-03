package gdev.projectbattle.engine.terrain.pathfinder;

import gdev.projectbattle.config.Converter;
import gdev.projectbattle.engine.terrain.GridTerrain;
import gdev.projectbattle.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class PathMap {
    private final Tile[][] tiles;

    public PathMap(GridTerrain gridTerrain, Vec2 destination) {
        tiles = new Tile[gridTerrain.tiles.length][gridTerrain.tiles.length];
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles.length; y++) {
                tiles[x][y] = new Tile(x, y, gridTerrain.tiles);
            }
        }

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles.length; y++) {
                tiles[x][y].createLinks(tiles);
            }
        }

        List<Tile> closed = new ArrayList<>();
        List<Tile> opened = new ArrayList<>();

        Tile d = getTileFromPos(destination);
        d.close();
        closed.add(d);

        while (!closed.isEmpty()) {
            for (Tile closedTile : closed) {
                closedTile.openAdjacent(opened);
            }
            closed.clear();
            for (Tile openedTile : opened) {
                openedTile.close();
                closed.add(openedTile);
            }
            opened.clear();
        }
    }

    public List<Vec2> getPathFrom(Vec2 position) {
        List<Vec2> path = new ArrayList<>();
        return getTileFromPos(position).getPath(path);
    }

    private Tile getTileFromPos(Vec2 position) {
        return tiles[Converter.toInt(position.x)][Converter.toInt(position.y)];
    }
}
