package gdev.projectbattle.graphics.javafx.battle;

import gdev.projectbattle.config.Colors;
import gdev.projectbattle.config.UiConfig;
import gdev.projectbattle.engine.soldier.CollisionQuadTree;
import javafx.scene.Group;
import javafx.scene.shape.Line;

public class CollisionGraphics extends Group {
    public CollisionGraphics(CollisionQuadTree quadTree) {
        if(quadTree.northWest != null) {
            Line lx = new Line(
                    UiConfig.UNIT_SIZE * (quadTree.center.x - quadTree.dimension),
                    UiConfig.UNIT_SIZE * (-quadTree.center.y),
                    UiConfig.UNIT_SIZE * (quadTree.center.x + quadTree.dimension),
                    UiConfig.UNIT_SIZE * (-quadTree.center.y));
            lx.setStroke(Colors.GRID);

            Line ly = new Line(
                    UiConfig.UNIT_SIZE * (quadTree.center.x),
                    UiConfig.UNIT_SIZE * (-quadTree.center.y - quadTree.dimension),
                    UiConfig.UNIT_SIZE * (quadTree.center.x),
                    UiConfig.UNIT_SIZE * (-quadTree.center.y + quadTree.dimension));
            ly.setStroke(Colors.GRID);

            getChildren().addAll(lx, ly);

            getChildren().addAll(
                    new CollisionGraphics(quadTree.northEast),
                    new CollisionGraphics(quadTree.southEast),
                    new CollisionGraphics(quadTree.northWest),
                    new CollisionGraphics(quadTree.southWest));
        }
    }
}
