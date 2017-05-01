package gdev.projectbattle.graphics.javafx.root;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;

public class GraphicsRoot extends Parent {
    private double refLayoutX = 0;
    private double refLayoutY = 0;
    private double refMouseX = 0;
    private double refMouseY = 0;

    private final MoveZoomLayer mzLayer = new MoveZoomLayer();

    private MoveZoomObserver observer = (x, y) -> {};

    public GraphicsRoot() {
        this.setOnMousePressed(me -> {
            if (me.isPrimaryButtonDown()) {
                refMouseX = me.getX();
                refMouseY = me.getY();

                refLayoutX = mzLayer.getLayoutX();
                refLayoutY = mzLayer.getLayoutY();
            } else if (me.isSecondaryButtonDown()) {
                Bounds bounds = mzLayer.localToScene(mzLayer.getBoundsInLocal());
                double x = ((me.getX() - bounds.getMinX()) / bounds.getWidth());
                double y = (1 - (me.getY() - bounds.getMinY()) / bounds.getHeight());
                observer.clicked(x, y);
            }
        });

        this.setOnMouseDragged(me -> {
            if (me.isPrimaryButtonDown()) {
                double delta_x = refMouseX - me.getX();
                double delta_y = refMouseY - me.getY();

                mzLayer.setLayoutX(refLayoutX - delta_x);
                mzLayer.setLayoutY(refLayoutY - delta_y);
            }
        });

        this.setOnScroll(mzLayer::zoom);

        addToRoot(mzLayer);
    }

    public void setObserver(MoveZoomObserver o) {
        observer = o;
    }

    public void addToMoveZoomLayer(Node... nodes) {
        mzLayer.getChildren().addAll(nodes);
    }

    public void addToRoot(Node... nodes) {
        getChildren().addAll(nodes);
    }

    public void resetMoveZoomLayer() {
        mzLayer.reset();
    }


}
