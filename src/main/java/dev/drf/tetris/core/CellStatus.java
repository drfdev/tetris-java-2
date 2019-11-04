package dev.drf.tetris.core;

public enum CellStatus {
    EMPTY,
    FILLED;

    public boolean isEmpty() {
        return this == EMPTY;
    }
}
