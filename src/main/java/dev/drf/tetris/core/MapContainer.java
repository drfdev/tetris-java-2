package dev.drf.tetris.core;

import dev.drf.tetris.game.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class MapContainer {
    private final int width;
    //    private final int height;
    private final List<MapRow> rows;
    private Figure figure;

    private MapCell baseCell;

    public MapContainer(int width, int height) {
        this.width = width;
//        this.height = height;
        this.rows = new ArrayList<>(height);
        for (int i = 0; i < height; ++i) {
            MapRow row = MapRow.of(i, width);
            rows.add(row);
            if (i == 0) {
                baseCell = row.getCells().get(width / 2);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return rows.size();
    }

    public int lastRow() {
        return getHeight() - 1;
    }

    public int lastCell() {
        return getWidth() - 1;
    }

    public List<MapRow> getRows() {
        return rows;
    }

    public Figure getFigure() {
        return figure;
    }

    public MapCell getCell(int row, int cell) {
        return rows.get(row).getCells().get(cell);
    }

    public void fillCell(int row, int cell) {
        getCell(row, cell).fill();
    }

    public boolean anyFigure() {
        return figure != null;
    }

    public void generateRandomFigure() {
        figure = Figure.randomFigure();
        figure.setBaseCell(baseCell);
        remapFigureCells();
    }

    public void moveFigure(MoveDirection direction) {
        if (anyFigure()) {
            clearFigureCells();
            MapCell currentBaseCell = figure.getBaseCell();
            MapCell newBaseCell = calculateBaseCells(currentBaseCell, direction);
            figure.changeBaseCell(this, newBaseCell);

            // TODO detect position

            remapFigureCells();
        }
    }

    public void rotateFigure() {
        if (anyFigure()) {
            clearFigureCells();

            figure.rotate();
            // TODO detect position

            remapFigureCells();
        }
    }

    private MapCell calculateBaseCells(MapCell baseCell, MoveDirection direction) {
        final int row = baseCell.getRow();
        final int cell = baseCell.getNumber();

        int newRow = direction == MoveDirection.DOWN
                ? Math.min(row + 1, getHeight() - 1)
                : row;
        int newCell = switch (direction) {
            case LEFT -> Math.max(0, cell - 1);
            case RIGHT -> Math.min(width - 1, cell + 1);
            default -> cell;
        };

        return getCell(newRow, newCell);
    }

    private void clearFigureCells() {
        List<MapCell> cells = figure.detectCells(this);
        cells.forEach(MapCell::clear);
    }

    private void remapFigureCells() {
        List<MapCell> cells = figure.detectCells(this);
        cells.forEach(MapCell::fill);
    }
}
