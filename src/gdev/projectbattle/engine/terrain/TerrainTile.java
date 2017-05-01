package gdev.projectbattle.engine.terrain;

import gdev.projectbattle.engine.soldier.Soldier;
import gdev.projectbattle.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class TerrainTile {
    public final int x;
    public final int y;
    public final Vec2 center;
    public boolean isObstacle = false;
    public final List<Soldier> soldierList = new ArrayList<>();

    public TerrainTile(int x, int y, Vec2 center) {
        this.x = x;
        this.y = y;
        this.center = center;
    }

    public void addSoldier(Soldier s) {
        soldierList.add(s);
    }

    public void setObstacle() {
        isObstacle = true;
    }
}
