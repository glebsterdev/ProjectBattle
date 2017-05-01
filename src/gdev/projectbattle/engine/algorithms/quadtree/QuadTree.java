package gdev.projectbattle.engine.algorithms.quadtree;

import gdev.projectbattle.engine.soldier.Soldier;
import gdev.projectbattle.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
    private final double level;
    public final Quadrant quadrant;

    private QuadTree northWest;
    private QuadTree northEast;
    private QuadTree southWest;
    private QuadTree southEast;

    public final List<Soldier> soldierList = new ArrayList<>();

    public QuadTree(Vec2 center, double dimension, double level) {
        quadrant = new Quadrant(center, dimension);
        this.level = level;

        if (level > 0) {
            double halfDim = dimension/2;
            northWest = new QuadTree(new Vec2(center.x - halfDim, center.y + halfDim), halfDim, level - 1);
            northEast = new QuadTree(new Vec2(center.x + halfDim, center.y + halfDim), halfDim, level - 1);
            southWest = new QuadTree(new Vec2(center.x - halfDim, center.y - halfDim), halfDim, level - 1);
            southEast = new QuadTree(new Vec2(center.x + halfDim, center.y - halfDim), halfDim, level - 1);
        }
    }

    private boolean contains(Soldier soldier) {
        return quadrant.contains(soldier.position);
    }

    public void insert(Soldier soldier, boolean resetSoldier) {
        if(soldier.isDead() || !contains(soldier))
            return;

//        if(resetSoldier)
//            soldier.resetQuads();
//
//        if(level == 0) {
//            soldierList.add(soldier);
//            soldier.addQuad(this);
//        } else {
//            northWest.insert(soldier, false);
//            northEast.insert(soldier, false);
//            southWest.insert(soldier, false);
//            southEast.insert(soldier, false);
//        }
    }

    public void clear() {
        soldierList.clear();
        if(level == 0)
            soldierList.clear();
        else {
            northWest.clear();
            northEast.clear();
            southWest.clear();
            southEast.clear();
        }
    }
}
