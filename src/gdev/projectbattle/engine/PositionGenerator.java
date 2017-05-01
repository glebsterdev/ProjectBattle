package gdev.projectbattle.engine;

import gdev.projectbattle.config.GameConfig;
import gdev.projectbattle.engine.terrain.GridTerrain;
import gdev.projectbattle.math.RandUtils;
import gdev.projectbattle.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class PositionGenerator {
    private static double MARGIN = 50;

    public static List<Vec2> generate(int number, GridTerrain gridTerrain) {
        List<Vec2> positions = new ArrayList<>();
        for(int i = 0; i < number; i++)
            positions.add(newPosition(positions, gridTerrain));
        return positions;
    }

    private static Vec2 newPosition(List<Vec2> positions, GridTerrain gridTerrain) {
        while(true) {
            Vec2 p = new Vec2(
                    RandUtils.doubleRange(0 + MARGIN, GameConfig.GRID_SIZE - MARGIN),
                    RandUtils.doubleRange(0 + MARGIN, GameConfig.GRID_SIZE - MARGIN));
            if(!gridTerrain.getTileFromPos(p).isFull && !collides(p, positions))
                return p;
        }
    }

    private static boolean collides(Vec2 p, List<Vec2> positions) {
        for(Vec2 v: positions){
            if(Vec2.diff(p, v).magnitude() < 1)
                return true;
        }
        return false;
    }
}
