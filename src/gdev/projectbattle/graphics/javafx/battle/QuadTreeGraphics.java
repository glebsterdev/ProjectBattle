package gdev.projectbattle.graphics.javafx.battle;

import gdev.projectbattle.config.Colors;
import gdev.projectbattle.config.UiConfig;
import gdev.projectbattle.engine.soldier.SoldierQuadTree;
import javafx.scene.Group;
import javafx.scene.shape.Line;

public class QuadTreeGraphics extends Group {
    public QuadTreeGraphics(SoldierQuadTree quadTree) {
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

        if(quadTree.northWest != null) {
            getChildren().addAll(
                    new QuadTreeGraphics(quadTree.northEast),
                    new QuadTreeGraphics(quadTree.southEast),
                    new QuadTreeGraphics(quadTree.northWest),
                    new QuadTreeGraphics(quadTree.southWest));
        }
    }
}
