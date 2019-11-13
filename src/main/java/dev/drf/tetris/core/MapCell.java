package dev.drf.tetris.core;

import java.util.Objects;

public final class MapCell {
    private final int row;
    private final int number;
    private CellStatus status;

    private MapCell(int number, int row) {
        this.number = number;
        this.row = row;
        this.status = CellStatus.EMPTY;
    }

    public int getNumber() {
        return number;
    }

    public int getRow() {
        return row;
    }

    public CellStatus getStatus() {
        return status;
    }

    public void copyStatus(MapCell cell) {
        this.status = cell.status;
    }

    public boolean isEmpty() {
        return status.isEmpty();
    }

    public void clear() {
        status = CellStatus.EMPTY;
    }

    public void fill() {
        status = CellStatus.FILLED;
    }

    public static MapCell of(int number, int row) {
        return new MapCell(number, row);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MapCell mapCell = (MapCell) o;
        return row == mapCell.row &&
                number == mapCell.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, number);
    }
}
