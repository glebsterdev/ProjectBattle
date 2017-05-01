package gdev.projectbattle.graphics.javafx.root;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.input.ScrollEvent;

public class MoveZoomLayer extends Group {
    public void zoom(ScrollEvent event) {
        double oldScale = getScaleX();
        double scale = oldScale * Math.pow(1.01, event.getDeltaY());
        if (scale < 0.025) scale = 0.025;
        if (scale > 5) scale = 5;
        setScaleX(scale);
        setScaleY(scale);

        double f = (scale / oldScale) - 1;
        Bounds bounds = localToScene(getBoundsInLocal());
        double dx = (event.getSceneX() - (bounds.getWidth() / 2 + bounds.getMinX()));
        double dy = (event.getSceneY() - (bounds.getHeight() / 2 + bounds.getMinY()));

        setTranslateX(getTranslateX() - f * dx);
        setTranslateY(getTranslateY() - f * dy);
    }

    public void reset() {
        setScaleX(1);
        setScaleY(1);
        setTranslateX(0);
        setTranslateY(0);
        setLayoutX(0);
        setLayoutY(1000);
//        Bounds bounds = localToScene(getBoundsInLocal());
//        setLayoutX(-bounds.getWidth()/2);
//        setLayoutY(bounds.getHeight()/2);
    }
}
