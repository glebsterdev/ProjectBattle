package gdev.projectbattle.engine;

import gdev.projectbattle.config.GameConfig;
import gdev.projectbattle.engine.obstacle.PolyObject;
import gdev.projectbattle.engine.obstacle.RasterizedObject;
import gdev.projectbattle.engine.soldier.CollisionQuadTree;
import gdev.projectbattle.engine.soldier.Soldier;
import gdev.projectbattle.engine.soldier.SoldierFaction;
import gdev.projectbattle.engine.soldier.SoldierType;
import gdev.projectbattle.engine.terrain.GridTerrain;
import gdev.projectbattle.engine.terrain.QuadTreeTerrain;
import gdev.projectbattle.engine.terrain.pathfinder.PathFinder;
import gdev.projectbattle.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class BattleScene {
    public final List<Soldier> soldierList = new ArrayList<>();
    public final List<PolyObject> polyObjects = new ArrayList<>();

    public final CollisionQuadTree collisionTree = new CollisionQuadTree(
            new Vec2(GameConfig.GRID_SIZE / 2, GameConfig.GRID_SIZE / 2),
            GameConfig.GRID_SIZE / 2,
            GameConfig.SOLDIER_QT_LEVEL);

    public final QuadTreeTerrain quadTreeTerrain = new QuadTreeTerrain(
            new Vec2(GameConfig.GRID_SIZE / 2, GameConfig.GRID_SIZE / 2),
            GameConfig.GRID_SIZE / 2,
            GameConfig.TERRAIN_QT_LEVEL);

    public final GridTerrain gridTerrain = new GridTerrain();

    public final PathFinder pathFinder;

    public BattleScene() {
        polyObjects.add(new PolyObject(
                new Vec2(750, 250),
                new Vec2(610, 210),
                new Vec2(650, 300)));

        polyObjects.add(new PolyObject(
                new Vec2(410, 210),
                new Vec2(550, 250),
                new Vec2(450, 300)));

        polyObjects.add(new PolyObject(
                new Vec2(400, 100),
                new Vec2(600, 100),
                new Vec2(540, 150)));

        polyObjects.add(new PolyObject(
                new Vec2(102, 102),
                new Vec2(202, 152),
                new Vec2(302, 102),
                new Vec2(302, 302),
                new Vec2(202, 252),
                new Vec2(102, 302)));

        for (PolyObject po : polyObjects) {
            RasterizedObject ro = new RasterizedObject(po);
            gridTerrain.addObject(ro);
        }

        pathFinder = new PathFinder(gridTerrain);

        quadTreeTerrain.load(gridTerrain);

        for (Vec2 p : PositionGenerator.generate(100, gridTerrain)) {
            Soldier s = new Soldier(SoldierType.CAVALRY, SoldierFaction.BLUE);
            s.setPosition(p);
            soldierList.add(s);
            collisionTree.insert(s);
        }
    }

    public void update() {
        for (Soldier s : soldierList)
            s.update();

//        collisionTree.clear();
//        for (Soldier s : soldierList)
//            collisionTree.insert(s, true);
    }
}
