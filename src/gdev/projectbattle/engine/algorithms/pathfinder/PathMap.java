package gdev.projectbattle.engine.algorithms.pathfinder;

import gdev.projectbattle.engine.terrain.Terrain;
import gdev.projectbattle.engine.terrain.TerrainTile;
import gdev.projectbattle.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class PathMap {
    private final Tile[][] tiles;
    private Terrain terrain;

    public PathMap(Terrain terrain, Vec2 destination){
        this.terrain = terrain;
        tiles = new Tile[terrain.tiles.length][terrain.tiles.length];
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles.length; y++) {
                tiles[x][y] = new Tile(x, y, terrain.tiles);
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

        while(!closed.isEmpty()) {
            for(Tile closedTile: closed){
                closedTile.openAdjacent(opened);
            }
            closed.clear();
            for(Tile openedTile: opened){
                openedTile.close();
                closed.add(openedTile);
            }
            opened.clear();
        }
    }

    public List<Vec2> getPathFrom(Vec2 position){
        List<Vec2> path = new ArrayList<>();
        return getTileFromPos(position).getPath(path);
    }

    private Tile getTileFromPos(Vec2 position) {
        TerrainTile t = terrain.getTileFromPos(position);
        return tiles[t.x][t.y];
    }
}
