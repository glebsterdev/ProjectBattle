package gdev.projectbattle.engine.terrain;

import gdev.projectbattle.config.Converter;
import gdev.projectbattle.config.GameConfig;
import gdev.projectbattle.math.Point;
import gdev.projectbattle.math.Vec2;

public class QuadTreeTerrain {
    private final double level;
    public final Vec2 center;
    public final double dimension;
    public boolean isFull = false;

    public QuadTreeTerrain northWest;
    public QuadTreeTerrain northEast;
    public QuadTreeTerrain southWest;
    public QuadTreeTerrain southEast;

    public QuadTreeTerrain(Vec2 center, double dimension, double level) {
        this.center = center;
        this.dimension = dimension;
        this.level = level;
    }

    public boolean contains(Point point) {
        final Vec2 c = Converter.toVec2(point);
        return Math.abs(center.x - c.x) <= dimension && Math.abs(center.y - c.y) <= dimension;
    }

    public void insert(Point point) {
        if(!contains(point) || isFull)
            return;

        if(level == 0)
            isFull = true;
        else {
            if(northEast == null){
                double halfDim = dimension/2;
                northWest = new QuadTreeTerrain(new Vec2(center.x - halfDim, center.y + halfDim), halfDim, level - 1);
                northEast = new QuadTreeTerrain(new Vec2(center.x + halfDim, center.y + halfDim), halfDim, level - 1);
                southWest = new QuadTreeTerrain(new Vec2(center.x - halfDim, center.y - halfDim), halfDim, level - 1);
                southEast = new QuadTreeTerrain(new Vec2(center.x + halfDim, center.y - halfDim), halfDim, level - 1);
            }
            northWest.insert(point);
            northEast.insert(point);
            southWest.insert(point);
            southEast.insert(point);

            if(northWest.isFull && northEast.isFull && southWest.isFull && southEast.isFull) {
                isFull = true;
                northWest = null;
                northEast = null;
                southWest = null;
                southEast = null;
            }
        }
    }

    public void load(GridTerrain gridTerrain) {
        for (int x = 0; x < GameConfig.GRID_RESOLUTION; x++) {
            for (int y = 0; y < GameConfig.GRID_RESOLUTION; y++) {
                if(gridTerrain.tiles[x][y].isFull)
                    insert(new Point(x, y));
            }
        }
    }
}
