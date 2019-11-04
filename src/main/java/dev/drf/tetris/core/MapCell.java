package dev.drf.tetris.core;

import java.util.Objects;

public final class MapCell {
    private final int number;
    private CellStatus status;

    private MapCell(int number) {
        this.number = number;
        this.status = CellStatus.EMPTY;
    }

    public int getNumber() {
        return number;
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

    public static MapCell of(int number) {
        return new MapCell(number);
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
        return number == mapCell.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
