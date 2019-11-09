package dev.drf.tetris.game;

import dev.drf.tetris.core.MapContainer;

import static dev.drf.tetris.game.MoveDirection.DOWN;

public class StepByStep {
    private MapInteractor interactor = new MapInteractor();
    private final int ticCount;
    private int currentTic;

    public StepByStep(int ticCount) {
        this.ticCount = ticCount;
        currentTic = 0;
    }

    public void nextStep(MapContainer map) {
        currentTic++;
        if (currentTic >= ticCount) {
            currentTic = 0;
            moveFigure(map, DOWN);
        }
    }

    public void moveFigure(MapContainer map, MoveDirection direction) {
        // TODO
    }

    public void rotateFigure(MapContainer map) {
        // TODO
    }
}
