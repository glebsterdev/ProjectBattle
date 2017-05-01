package gdev.projectbattle.graphics.javafx.fps;

import gdev.projectbattle.config.Colors;
import javafx.geometry.VPos;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import gdev.projectbattle.config.UiConfig;

public class FpsOverlay extends Group implements FpsObserver {

    double margine = 20;
    double height = 160;
    double width = 400;

    private final Text textField = new Text("FPS: 30");
    private final GraphOverlay graph = new GraphOverlay(width - 2 * margine, 1000/UiConfig.FPS, 200);

    public FpsOverlay() {
        setLayoutX(margine);
        setLayoutY(margine);

        Rectangle background = new Rectangle(0, 0, width, height);
        background.setFill(Colors.UI_BACKGROUND);

        textField.setTextAlignment(TextAlignment.CENTER);
        textField.setLayoutX(20);
        textField.setLayoutY(20);
        textField.setFontSmoothingType(FontSmoothingType.LCD);
        textField.setFont(UiConfig.button_font);
        textField.setFill(Colors.FONT_BLACK);
        textField.setTextOrigin(VPos.CENTER);

        graph.setLayoutX(margine);
        graph.setLayoutY(height - margine);

        getChildren().addAll(background, textField, graph);
    }

    @Override
    public void updateFrameDelta(long frameDeltaMs) {
        graph.addDataPoint(frameDeltaMs);
    }

    @Override
    public void updateFps(long fpsMs) {
        textField.setText("FPS: " + fpsMs);
    }
}
