package gdev.projectbattle.graphics.javafx.fps;

import java.util.ArrayList;

public class FpsTracker {
    private long frameCounter = 0;
    private long delta = 0;
    private long lastTime = 0;

    private ArrayList<FpsObserver> observers = new ArrayList<>();

    public void addObserver(FpsObserver o) {
        observers.add(o);
    }

    public void publishDelta(long frameDeltaMs) {
        for (FpsObserver o : observers)
            o.updateFrameDelta(frameDeltaMs);
    }

    private void publishFps(long fpsMs) {
        for (FpsObserver o : observers)
            o.updateFps(fpsMs);
    }

    public void update() {
        long time = System.currentTimeMillis();
        delta = delta + (time - lastTime);
        if (lastTime != 0)
            publishDelta(time - lastTime);
        lastTime = time;
        frameCounter = frameCounter + 1;

        if (delta > 1000) {
            long d = delta / frameCounter;
            publishFps(1000 / d);
            delta = 0;
            frameCounter = 0;
        }
    }
}
