package gdev.projectbattle.engine.soldier;

import gdev.projectbattle.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class CollisionQuadTree {
    private final double level;
    private final double MARGIN = 1;
    public final Vec2 center;
    public final double dimension;

    public CollisionQuadTree northWest;
    public CollisionQuadTree northEast;
    public CollisionQuadTree southWest;
    public CollisionQuadTree southEast;

    public List<Soldier> soldierList;

    public CollisionQuadTree(Vec2 center, double dimension, double level) {
        this.center = center;
        this.dimension = dimension;
        this.level = level;

        if(level == 0)
            soldierList = new ArrayList<>();
    }

    public boolean contains(Vec2 p) {
        return Math.abs(center.x - p.x) <= dimension + MARGIN && Math.abs(center.y - p.y) <= dimension + MARGIN;
    }

    public void insert(Soldier soldier) {
        if(!contains(soldier.position))
            return;

        if(level == 0)
            soldierList.add(soldier);
        else {
            if(northEast == null){
                double halfDim = dimension/2;
                northWest = new CollisionQuadTree(new Vec2(center.x - halfDim, center.y + halfDim), halfDim, level - 1);
                northEast = new CollisionQuadTree(new Vec2(center.x + halfDim, center.y + halfDim), halfDim, level - 1);
                southWest = new CollisionQuadTree(new Vec2(center.x - halfDim, center.y - halfDim), halfDim, level - 1);
                southEast = new CollisionQuadTree(new Vec2(center.x + halfDim, center.y - halfDim), halfDim, level - 1);
            }
            northWest.insert(soldier);
            northEast.insert(soldier);
            southWest.insert(soldier);
            southEast.insert(soldier);
        }
    }
}
