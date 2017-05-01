package gdev.projectbattle.engine;

import gdev.projectbattle.engine.algorithms.rasterizer.Rasterizer;
import gdev.projectbattle.engine.obstacle.Obstacle;
import gdev.projectbattle.engine.soldier.Soldier;
import gdev.projectbattle.engine.soldier.SoldierFaction;
import gdev.projectbattle.engine.soldier.SoldierType;
import gdev.projectbattle.engine.terrain.Terrain;
import gdev.projectbattle.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class BattleScene {
    public List<Soldier> soldierList = new ArrayList<>();
    public List<Obstacle> obstacles = new ArrayList<>();

//    public QuadTree quadTree = new QuadTree(new Vec2(0, 0), 500, 7);

    public Terrain terrain = new Terrain(400, 1000);

    public BattleScene() {
        obstacles.add(new Obstacle(
                new Vec2(750, 250),
                new Vec2(610, 210),
                new Vec2(650, 300)));

        obstacles.add(new Obstacle(
                new Vec2(410, 210),
                new Vec2(550, 250),
                new Vec2(450, 300)));

        obstacles.add(new Obstacle(
                new Vec2(400, 100),
                new Vec2(600, 100),
                new Vec2(540, 150)));

        obstacles.add(new Obstacle(
                new Vec2(102, 102),
                new Vec2(202, 152),
                new Vec2(302, 102),
                new Vec2(302, 302),
                new Vec2(202, 252),
                new Vec2(102, 302)));

        for(Obstacle o: obstacles)
            Rasterizer.rasterizeOnTerrain(o.vertices, terrain);

        for (Vec2 p : PositionGenerator.generate(100, terrain)) {
            SoldierFaction sf = (p.x < 0) ? SoldierFaction.BLUE : SoldierFaction.RED;
            Soldier s = new Soldier(SoldierType.CAVALRY, sf);
            soldierList.add(s);
            s.setPosition(p);
        }
    }

    public void update() {
        for (Soldier s : soldierList)
            s.update();

//        quadTree.clear();
//        for (Soldier s : soldierList)
//            quadTree.insert(s, true);
    }
}
