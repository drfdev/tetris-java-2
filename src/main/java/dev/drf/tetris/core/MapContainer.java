package dev.drf.tetris.core;

import java.util.ArrayList;
import java.util.List;

public class MapContainer {
    private final int width;
    //    private final int height;
    private final List<MapRow> rows;

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

    public void fillCell(int row, int cell) {
        rows.get(row).getCells().get(cell).fill();
    }
    // TODO банка
}
