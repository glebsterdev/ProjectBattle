package gdev.projectbattle.engine.soldier;

import gdev.projectbattle.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class Soldier {
    public final SoldierType type;
    public final SoldierFaction faction;
    public SoldierState state = SoldierState.ALIVE;

    public double health;
    public Vec2 position = Vec2.zero();
    public List<Vec2> path = new ArrayList<>();

    public Soldier(SoldierType type, SoldierFaction faction) {
        this.type = type;
        this.faction = faction;

        health = type.maxHealth;
    }

    public double getAttackDamage() {
        switch (type) {
            case INFANTRY:
                return health;
            case CAVALRY:
                return health;
            case ARCHER:
                return health;
            default:
                return health;
        }
    }

    public void setPosition(Vec2 position) {
        this.position = position;
        path.clear();
    }

    public void update() {

    }

    public boolean isDead() {
        return state == SoldierState.DEAD;
    }

    public void kill() {
        state = SoldierState.DEAD;
    }

    public void setPath(List<Vec2> path) {
        this.path = path;
        path.set(0, position);
    }
}
