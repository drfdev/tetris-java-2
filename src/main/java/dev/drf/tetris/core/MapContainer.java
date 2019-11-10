package dev.drf.tetris.core;

import dev.drf.tetris.game.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class MapContainer {
    private final int width;
    //    private final int height;
    private final List<MapRow> rows;
    private Figure figure;

    public MapContainer(int width, int height) {
        this.width = width;
//        this.height = height;
        this.rows = new ArrayList<>(height);
        for (int i = 0; i < height; ++i) {
            rows.add(MapRow.of(i, width));
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return rows.size();
    }

    public List<MapRow> getRows() {
        return rows;
    }

    public Figure getFigure() {
        return figure;
    }

    public void fillCell(int row, int cell) {
        rows.get(row).getCells().get(cell).fill();
    }

    public boolean anyFigure() {
        return figure != null;
    }

    public void generateRandomFigure() {
        figure = Figure.randomFigure();
    }

    public void moveFigure(MoveDirection direction) {
        if (anyFigure()) {
            // TODO
        }
    }

    public void rotateFigure() {
        if (anyFigure()) {
            // TODO
        }
    }
}
