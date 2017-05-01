package gdev.projectbattle.config;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UiConfig {
    public static final double FPS = 30;
    public static final double TIME_FRAME_S = 1.0 / FPS;

    public static final double SCREEN_SIZE_X = 1600;
    public static final double SCREEN_SIZE_Y = 1000;
    public static final double UNIT_SIZE = 20;

    public final static Font button_font = Font.font("Calibri", FontWeight.BOLD, 18);
    public final static Font big_font = Font.font("Calibri", FontWeight.BOLD, 200);
}
