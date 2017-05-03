package gdev.projectbattle;

import gdev.projectbattle.config.Colors;
import gdev.projectbattle.engine.BattleScene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import gdev.projectbattle.config.UiConfig;
import gdev.projectbattle.graphics.javafx.fps.FpsOverlay;
import gdev.projectbattle.graphics.javafx.battle.BattleSceneGraphics;
import gdev.projectbattle.graphics.javafx.fps.FpsTracker;
import gdev.projectbattle.graphics.javafx.root.GraphicsRoot;

public class JavaFXMain extends Application {

    @Override
    public void start(final Stage initialStage) throws Exception {
        final BattleScene battleScene = new BattleScene();

        final GraphicsRoot graphicsRoot = new GraphicsRoot();
        final BattleSceneGraphics battleSceneGraphics = new BattleSceneGraphics(graphicsRoot, battleScene);
        FpsOverlay fpsOverlay = new FpsOverlay();
        graphicsRoot.addToRoot(fpsOverlay);

        final Scene scene = new Scene(graphicsRoot, UiConfig.SCREEN_SIZE_X, UiConfig.SCREEN_SIZE_Y, true, SceneAntialiasing.BALANCED);
        scene.setFill(Colors.BLACK);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.R) {
                graphicsRoot.resetMoveZoomLayer();
            }
            if (event.getCode() == KeyCode.U) {
                battleSceneGraphics.toggleQuadTreeTerrainGraphics();
            }
            if (event.getCode() == KeyCode.I) {
                battleSceneGraphics.toggleGridTerrainGraphics();
            }
            if (event.getCode() == KeyCode.O) {
                battleSceneGraphics.toggleCollisionGraphics();
            }
            if (event.getCode() == KeyCode.P) {
                battleSceneGraphics.toggleSoliderPath();
            }
        });

        final Stage stage = new Stage();
        stage.setTitle("Battle v1.0");
        stage.setScene(scene);
        stage.show();

        FpsTracker fpsTracker = new FpsTracker();
        fpsTracker.addObserver(fpsOverlay);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(UiConfig.TIME_FRAME_S), event -> {
            battleScene.update();
            battleSceneGraphics.update();
            fpsTracker.update();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}