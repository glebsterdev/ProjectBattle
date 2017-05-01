package gdev.projectbattle.graphics.javafx.battle;

import gdev.projectbattle.config.Colors;
import gdev.projectbattle.config.GameConfig;
import gdev.projectbattle.config.UiConfig;
import gdev.projectbattle.engine.terrain.GridTerrain;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class GridTerrainGraphics extends Group {

    public GridTerrainGraphics(GridTerrain gridTerrain) {
        double mapSize = GameConfig.GRID_SIZE * UiConfig.UNIT_SIZE;
        double tileSize = GameConfig.GRID_SIZE * UiConfig.UNIT_SIZE / (double) gridTerrain.tiles.length;

        for (int x = 0; x < gridTerrain.tiles.length; x++) {
            for (int y = 0; y < gridTerrain.tiles.length; y++) {
                if (gridTerrain.tiles[x][y].isFull) {
                    double X = ((double) x / (double) gridTerrain.tiles.length) * mapSize;
                    double Y = ((double) (y + 1) / (double) gridTerrain.tiles.length) * mapSize;
                    Rectangle o = new Rectangle(X, -Y, tileSize, tileSize);
                    o.setFill(Colors.DEBUG_RED);
                    getChildren().add(o);
                }
            }
        }

        double margin = 0.5;
        Group gridLines = new Group();
        for (int i = 1; i < gridTerrain.tiles.length; i++) {
            Line lx = new Line(
                    margin,
                    -tileSize * i,
                    mapSize - margin,
                    -tileSize * i);
            lx.setStroke(Colors.GRID);

            Line ly = new Line(
                    tileSize * i,
                    -margin,
                    tileSize * i,
                    -mapSize + margin);
            ly.setStroke(Colors.GRID);

            gridLines.getChildren().addAll(lx, ly);
        }
        getChildren().add(gridLines);
    }
}
