package gdev.projectbattle.engine.terrain;

import gdev.projectbattle.engine.soldier.Soldier;
import gdev.projectbattle.engine.algorithms.pathfinder.PathMap;
import gdev.projectbattle.math.Vec2;

public class Terrain {
    public TerrainTile[][] tiles;
    public final double mapSize;

    public Terrain(int resolution, double mapSize) {
        this.tiles = new TerrainTile[resolution][resolution];
        this.mapSize = mapSize;

        for (int x = 0; x < resolution; x++) {
            for (int y = 0; y < resolution; y++) {
                Vec2 center = new Vec2(
                        (((double) x + 0.5) / (double) resolution) * mapSize,
                        (((double) y + 0.5) / (double) resolution) * mapSize);
                tiles[x][y] = new TerrainTile(x, y, center);
            }
        }
    }

    public void addObstacle(int x, int y) {
        tiles[x][y].setObstacle();
    }

    public boolean addSoldier(Soldier s) {
        int sX = (int) ((s.position.x / mapSize) * tiles.length);
        int sY = (int) ((s.position.y / mapSize) * tiles.length);

        if (sX < tiles.length && sX >= 0 && sY < tiles.length && sY >= 0)
            return false;

        tiles[sX][sY].addSoldier(s);
        return true;
    }

    public TerrainTile getTileFromPos(Vec2 position) {
        int sX = (int) ((position.x / mapSize) * tiles.length);
        int sY = (int) ((position.y / mapSize) * tiles.length);
        return tiles[sX][sY];
    }

    public PathMap generatePathMap(Vec2 destination) {
        return new PathMap(this, destination);
    }
}
