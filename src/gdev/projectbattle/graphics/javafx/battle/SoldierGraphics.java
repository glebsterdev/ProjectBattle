package gdev.projectbattle.graphics.javafx.battle;

import gdev.projectbattle.config.Colors;
import gdev.projectbattle.engine.soldier.Soldier;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import gdev.projectbattle.config.UiConfig;

public class SoldierGraphics extends Group {
    private Soldier soldier;
    private final Polyline soliderPath = new Polyline();
    private final Group dot = new Group();

    private int pathLenght = 0;

    public SoldierGraphics(Soldier soldier) {
        this.soldier = soldier;

        soliderPath.setStroke(Colors.RED);
        soliderPath.setStrokeWidth(4);
        Circle c = new Circle(0, 0, UiConfig.UNIT_SIZE / 2);
        c.setFill(Colors.BACKGROUND);
        c.setStroke(Colors.FOREGROUND);
        Rectangle e = new Rectangle(-UiConfig.UNIT_SIZE / 4, -UiConfig.UNIT_SIZE / 4, UiConfig.UNIT_SIZE / 2, UiConfig.UNIT_SIZE / 2);
        switch (soldier.faction) {
            case BLUE:
                e.setFill(Colors.SOLIDER_BLUE);
                break;
            case RED:
                e.setFill(Colors.RED);
                break;
        }
        dot.getChildren().addAll(c, e);

        getChildren().addAll(soliderPath, dot);
    }

    public void update() {
        if(soldier == null)
            return;

        dot.setLayoutX(soldier.position.x * UiConfig.UNIT_SIZE);
        dot.setLayoutY(-soldier.position.y * UiConfig.UNIT_SIZE);


        if(pathLenght != soldier.path.size()) {
            soliderPath.getPoints().clear();
            Double[] d = new Double[soldier.path.size() * 2];
            for (int i = 0; i < soldier.path.size(); i++) {
                d[2*i] = soldier.path.get(i).x * UiConfig.UNIT_SIZE;
                d[2*i+1] = -soldier.path.get(i).y * UiConfig.UNIT_SIZE;
            }
            soliderPath.getPoints().setAll(d);
            pathLenght = soldier.path.size();
        }
    }

    public void togglePath(){
        soliderPath.setVisible(!soliderPath.isVisible());
    }
}
