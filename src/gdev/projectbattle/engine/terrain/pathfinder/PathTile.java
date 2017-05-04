package gdev.projectbattle.engine.terrain.pathfinder;

import gdev.projectbattle.engine.terrain.GridTile;
import gdev.projectbattle.math.Point;
import gdev.projectbattle.math.Vec2;

import java.util.HashMap;
import java.util.List;

class PathTile {
    private enum State {NONE, OPEN, CLOSED}

    private final PathTile[][] pathTiles;
    public final GridTile refTile;

    private int gScore;
    private State state;
    private PathTile destinationPathTile;

    private HashMap<Point, Integer> adjacentTiles = new HashMap<>();

    public PathTile(int x, int y, GridTile[][] gridTiles, PathTile[][] pathTiles) {
        refTile = gridTiles[x][y];
        this.pathTiles = pathTiles;
        reset();

        if (!refTile.isFull) {
            if (x + 1 < gridTiles.length && !gridTiles[x + 1][y].isFull)
                adjacentTiles.put(gridTiles[x + 1][y].cell, 10);
            if (x > 0 && !gridTiles[x - 1][y].isFull)
                adjacentTiles.put(gridTiles[x - 1][y].cell, 10);
            if (y + 1 < gridTiles.length && !gridTiles[x][y + 1].isFull)
                adjacentTiles.put(gridTiles[x][y + 1].cell, 10);
            if (y > 0 && !gridTiles[x][y - 1].isFull)
                adjacentTiles.put(gridTiles[x][y - 1].cell, 10);

            if (x + 1 < gridTiles.length && y + 1 < gridTiles.length && !gridTiles[x + 1][y + 1].isFull)
                adjacentTiles.put(gridTiles[x + 1][y + 1].cell, 14);
            if (x > 0 && y > 0 && !gridTiles[x - 1][y - 1].isFull)
                adjacentTiles.put(gridTiles[x - 1][y - 1].cell, 14);
            if (x > 0 && y + 1 < gridTiles.length && !gridTiles[x - 1][y + 1].isFull)
                adjacentTiles.put(gridTiles[x - 1][y + 1].cell, 14);
            if (x + 1 < gridTiles.length && y > 0 && !gridTiles[x + 1][y - 1].isFull)
                adjacentTiles.put(gridTiles[x + 1][y - 1].cell, 14);
        }

    }

    public void reset() {
        gScore = 0;
        state = State.NONE;
        destinationPathTile = null;
    }

    public boolean isFull() {
        return refTile.isFull;
    }

    public List<Vec2> getPath(List<Vec2> path) {
        path.add(refTile.center);
        if (destinationPathTile == null)
            return path;
        else
            return destinationPathTile.getPath(path);
    }

    void openAdjacent(List<PathTile> opened) {
        for (Point p : adjacentTiles.keySet()) {
            PathTile tt = pathTiles[p.x][p.y];
            if (tt.open())
                opened.add(tt);
        }
    }

    boolean open() {
        if (isFull() || state != State.NONE)
            return false;
        state = State.OPEN;
        return true;
    }

    void close() {
        if (isFull())
            return;
        for (Point p : adjacentTiles.keySet()) {
            PathTile tt = pathTiles[p.x][p.y];
            if (tt.isClosed()) {
                int g = tt.gScore + adjacentTiles.get(p);
                if (gScore == 0 || g < gScore) {
                    gScore = g;
                    destinationPathTile = tt;
                }
            }
        }
        state = State.CLOSED;
    }

    boolean isClosed() {
        return state == State.CLOSED;
    }
}