package gdev.projectbattle.graphics.javafx.battle;

import gdev.projectbattle.config.Colors;
import gdev.projectbattle.config.GameConfig;
import gdev.projectbattle.config.UiConfig;
import gdev.projectbattle.engine.BattleScene;
import gdev.projectbattle.engine.terrain.pathfinder.PathMap;
import gdev.projectbattle.engine.obstacle.PolyObject;
import gdev.projectbattle.engine.soldier.Soldier;
import gdev.projectbattle.graphics.javafx.root.GraphicsRoot;
import javafx.scene.shape.Rectangle;
import gdev.projectbattle.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class BattleSceneGraphics {
    private GridTerrainGraphics gridTerrainGraphics;
    private List<SoldierGraphics> soldierList = new ArrayList<>();
    private CollisionGraphics collisionGraphics;
    private QuadTreeTerrainGraphics quadTreeTerrainGraphics;

    public BattleSceneGraphics(GraphicsRoot graphicsRoot, BattleScene battleScene) {
        double size = GameConfig.GRID_SIZE * UiConfig.UNIT_SIZE;
        Rectangle background = new Rectangle(0, -size, size, size);
        background.setFill(Colors.TERRAIN_GREEN);
        graphicsRoot.addToMoveZoomLayer(background);
        graphicsRoot.resetMoveZoomLayer();

        for (PolyObject o : battleScene.polyObjects) {
            PolyObjectGraphics og = new PolyObjectGraphics(o);
            graphicsRoot.addToMoveZoomLayer(og);
        }

        gridTerrainGraphics = new GridTerrainGraphics(battleScene.gridTerrain);
        graphicsRoot.addToMoveZoomLayer(gridTerrainGraphics);

        for (Soldier s : battleScene.soldierList) {
            SoldierGraphics sg = new SoldierGraphics(s);
            graphicsRoot.addToMoveZoomLayer(sg);
            soldierList.add(sg);
        }

        collisionGraphics = new CollisionGraphics(battleScene.collisionTree);
        graphicsRoot.addToMoveZoomLayer(collisionGraphics);

        quadTreeTerrainGraphics = new QuadTreeTerrainGraphics(battleScene.quadTreeTerrain);
        graphicsRoot.addToMoveZoomLayer(quadTreeTerrainGraphics);

        graphicsRoot.setObserver((x, y) -> {
            double xx = GameConfig.GRID_SIZE * x;
            double yy = GameConfig.GRID_SIZE * y;
            PathMap pathMap = battleScene.gridTerrain.generatePathMap(new Vec2(xx, yy));
            for (Soldier s : battleScene.soldierList) {
                pathMap.getPathFrom(s.position);
                s.setPath(pathMap.getPathFrom(s.position));
            }
        });

        toggleGridTerrainGraphics();
        toggleQuadTreeTerrainGraphics();
        toggleCollisionGraphics();
    }

    public void update() {
        for (SoldierGraphics sg : soldierList)
            sg.update();
    }

    public void toggleGridTerrainGraphics() {
        gridTerrainGraphics.setVisible(!gridTerrainGraphics.isVisible());
    }

    public void toggleCollisionGraphics() {
        collisionGraphics.setVisible(!collisionGraphics.isVisible());
    }

    public void toggleQuadTreeTerrainGraphics() {
        quadTreeTerrainGraphics.setVisible(!quadTreeTerrainGraphics.isVisible());
    }

    public void toggleSoliderPath() {
        for (SoldierGraphics s : soldierList) {
            s.togglePath();
        }
    }
}
