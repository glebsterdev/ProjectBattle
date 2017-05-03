package gdev.projectbattle.engine.terrain.pathfinder;

import gdev.projectbattle.engine.terrain.GridTile;
import gdev.projectbattle.math.Vec2;

import java.util.HashMap;
import java.util.List;

class Tile {
    private enum State {NONE, OPEN, CLOSED}

    private final int x;
    private final int y;
    private final boolean isObstacle;
    private final Vec2 center;

    private int gScore = 0;
    private State state = State.NONE;
    private Tile destinationTile = null;

    private HashMap<Tile, Integer> adjacentTiles = new HashMap<>();

    Tile(int x, int y, GridTile[][] gridTiles) {
        this.x = x;
        this.y = y;
        this.isObstacle = gridTiles[x][y].isFull;
        this.center = gridTiles[x][y].center;
    }

    void createLinks(Tile[][] tiles) {
        if (isObstacle)
            return;

        if (x + 1 < tiles.length && !tiles[x + 1][y].isObstacle)
            adjacentTiles.put(tiles[x + 1][y], 10);
        if (x > 0 && !tiles[x - 1][y].isObstacle)
            adjacentTiles.put(tiles[x - 1][y], 10);
        if (y + 1 < tiles.length && !tiles[x][y + 1].isObstacle)
            adjacentTiles.put(tiles[x][y + 1], 10);
        if (y > 0 && !tiles[x][y - 1].isObstacle)
            adjacentTiles.put(tiles[x][y - 1], 10);

        if (x + 1 < tiles.length && y + 1 < tiles.length && !tiles[x + 1][y + 1].isObstacle)
            adjacentTiles.put(tiles[x + 1][y + 1], 14);
        if (x > 0 && y > 0 && !tiles[x - 1][y - 1].isObstacle)
            adjacentTiles.put(tiles[x - 1][y - 1], 14);
        if (x > 0 && y + 1 < tiles.length && !tiles[x - 1][y + 1].isObstacle)
            adjacentTiles.put(tiles[x - 1][y + 1], 14);
        if (x + 1 < tiles.length && y > 0 && !tiles[x + 1][y - 1].isObstacle)
            adjacentTiles.put(tiles[x + 1][y - 1], 14);
    }

    List<Vec2> getPath(List<Vec2> path) {
        path.add(center);
        if(destinationTile == null)
            return path;
        else
            return destinationTile.getPath(path);
    }

    void openAdjacent(List<Tile> opened) {
        for(Tile tt: adjacentTiles.keySet()) {
            if(tt.open())
                opened.add(tt);
        }
    }

    boolean open() {
        if (isObstacle || state != State.NONE)
            return false;
        state = State.OPEN;
        return true;
    }

    void close() {
        if (isObstacle)
            return;
        for(Tile tt: adjacentTiles.keySet()) {
            if(tt.isClosed()) {
                int g = tt.gScore + adjacentTiles.get(tt);
                if(gScore == 0 || g < gScore) {
                    gScore = g;
                    destinationTile = tt;
                }
            }
        }
        state = State.CLOSED;
    }

    boolean isClosed() {
        return state == State.CLOSED;
    }
}