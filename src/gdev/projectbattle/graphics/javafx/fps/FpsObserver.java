package gdev.projectbattle.graphics.javafx.fps;

public interface FpsObserver
{
    void updateFrameDelta(long frameDeltaMs);

    void updateFps(long fpsMs);
}
