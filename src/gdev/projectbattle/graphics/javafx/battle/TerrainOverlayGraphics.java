package gdev.projectbattle.graphics.javafx.battle;

import gdev.projectbattle.config.Colors;
import gdev.projectbattle.config.UiConfig;
import gdev.projectbattle.engine.terrain.Terrain;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class TerrainOverlayGraphics extends Group {
    private final Group grid = new Group();

    public TerrainOverlayGraphics(Terrain terrain) {
        double mapSize = terrain.mapSize * UiConfig.UNIT_SIZE;
        double tileSize = terrain.mapSize * UiConfig.UNIT_SIZE / (double) terrain.tiles.length;

        for (int x = 0; x < terrain.tiles.length; x++) {
            for (int y = 0; y < terrain.tiles.length; y++) {
                if (terrain.tiles[x][y].isObstacle) {
                    double X = ((double) x / (double) terrain.tiles.length) * mapSize;
                    double Y = ((double) (y + 1) / (double) terrain.tiles.length) * mapSize;
                    Rectangle o = new Rectangle(X, -Y, tileSize, tileSize);
                    o.setFill(Colors.DEBUG_RED);
                    getChildren().add(o);
                }
            }
        }

        double margin = 0.5;
        for (int i = 1; i < terrain.tiles.length; i++) {
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

            grid.getChildren().addAll(lx, ly);
        }
        getChildren().add(grid);
    }
}
