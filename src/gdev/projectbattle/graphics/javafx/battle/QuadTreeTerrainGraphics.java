package gdev.projectbattle.graphics.javafx.battle;

import gdev.projectbattle.config.Colors;
import gdev.projectbattle.config.UiConfig;
import gdev.projectbattle.engine.terrain.QuadTreeTerrain;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class QuadTreeTerrainGraphics extends Group {
    public QuadTreeTerrainGraphics(QuadTreeTerrain quadTreeTerrain) {
        if(quadTreeTerrain.northWest != null) {
            Line lx = new Line(
                    UiConfig.UNIT_SIZE * (quadTreeTerrain.center.x - quadTreeTerrain.dimension),
                    UiConfig.UNIT_SIZE * (-quadTreeTerrain.center.y),
                    UiConfig.UNIT_SIZE * (quadTreeTerrain.center.x + quadTreeTerrain.dimension),
                    UiConfig.UNIT_SIZE * (-quadTreeTerrain.center.y));
            lx.setStroke(Colors.GRID);

            Line ly = new Line(
                    UiConfig.UNIT_SIZE * (quadTreeTerrain.center.x),
                    UiConfig.UNIT_SIZE * (-quadTreeTerrain.center.y - quadTreeTerrain.dimension),
                    UiConfig.UNIT_SIZE * (quadTreeTerrain.center.x),
                    UiConfig.UNIT_SIZE * (-quadTreeTerrain.center.y + quadTreeTerrain.dimension));
            ly.setStroke(Colors.GRID);

            getChildren().addAll(lx, ly);

            getChildren().addAll(
                    new QuadTreeTerrainGraphics(quadTreeTerrain.northEast),
                    new QuadTreeTerrainGraphics(quadTreeTerrain.southEast),
                    new QuadTreeTerrainGraphics(quadTreeTerrain.northWest),
                    new QuadTreeTerrainGraphics(quadTreeTerrain.southWest));
        }

        if(quadTreeTerrain.isFull) {
            Rectangle o = new Rectangle(
                    UiConfig.UNIT_SIZE * (quadTreeTerrain.center.x - quadTreeTerrain.dimension),
                    UiConfig.UNIT_SIZE * (-quadTreeTerrain.center.y - quadTreeTerrain.dimension),
                    UiConfig.UNIT_SIZE * quadTreeTerrain.dimension * 2,
                    UiConfig.UNIT_SIZE * quadTreeTerrain.dimension * 2);
            o.setFill(Colors.DEBUG_RED);
            getChildren().add(o);
        }
    }
}
