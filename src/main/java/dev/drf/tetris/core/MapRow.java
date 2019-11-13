package dev.drf.tetris.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class MapRow {
    private final int number;
    private final List<MapCell> cells;

    private MapRow(int number, int length) {
        this.number = number;
        this.cells = new ArrayList<>(length);
        for (int i = 0; i < length; ++i) {
            cells.add(MapCell.of(i, number));
        }
    }

    public static MapRow of(int number, int length) {
        return new MapRow(number, length);
    }

    public int getNumber() {
        return number;
    }

    public List<MapCell> getCells() {
        return cells;
    }

    public void copyRow(MapRow row) {
        for (int i = 0; i < row.cells.size(); ++i) {
            MapCell item = row.cells.get(i);
            cells.get(i).copyStatus(item);
        }
    }

    public void clearRow() {
        cells.forEach(MapCell::clear);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MapRow mapRow = (MapRow) o;
        return number == mapRow.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
