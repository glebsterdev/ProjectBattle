package gdev.projectbattle.graphics.javafx.fps;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;

public class GraphOverlay extends Group {
    private double dataLimit;
    private double step;
    private List<Long> dataPoints = new ArrayList<>();

    Polyline polyline = new Polyline();

    public GraphOverlay(double span, double nominal, int points) {
        step = span / points;
        this.dataLimit = points;

        Line line0 = new Line(0, 0, span, 0);
        Line lineN = new Line(0, -nominal, span, -nominal);
        getChildren().addAll(line0, lineN, polyline);
    }

    public void addDataPoint(long val) {
        dataPoints.add(val);
        if (dataPoints.size() > 1) {
            if (dataPoints.size() > dataLimit)
                dataPoints.remove(0);
            polyline.getPoints().clear();

            Double[] d = new Double[dataPoints.size() * 2];
            for (int i = 0; i < dataPoints.size(); i++) {
                d[2*i] = i * step;
                d[2*i+1] = (double) -dataPoints.get(i);
            }
            polyline.getPoints().addAll(d);
        }
    }
}
