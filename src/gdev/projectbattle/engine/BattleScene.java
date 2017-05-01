package gdev.projectbattle.engine;

import gdev.projectbattle.config.GameConfig;
import gdev.projectbattle.engine.obstacle.PolyObject;
import gdev.projectbattle.engine.obstacle.RasterizedObject;
import gdev.projectbattle.engine.soldier.Soldier;
import gdev.projectbattle.engine.soldier.SoldierFaction;
import gdev.projectbattle.engine.soldier.SoldierQuadTree;
import gdev.projectbattle.engine.soldier.SoldierType;
import gdev.projectbattle.engine.terrain.GridTerrain;
import gdev.projectbattle.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class BattleScene {
    public List<Soldier> soldierList = new ArrayList<>();
    public List<PolyObject> polyObjects = new ArrayList<>();

    public SoldierQuadTree quadTree = new SoldierQuadTree(new Vec2(GameConfig.GRID_SIZE / 2, GameConfig.GRID_SIZE / 2), GameConfig.GRID_SIZE / 2, GameConfig.QT_LEVEL);

    public GridTerrain gridTerrain = new GridTerrain();

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

        for (Vec2 p : PositionGenerator.generate(100, gridTerrain)) {
            Soldier s = new Soldier(SoldierType.CAVALRY, SoldierFaction.BLUE);
            s.setPosition(p);
            soldierList.add(s);
            quadTree.insert(s);
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
