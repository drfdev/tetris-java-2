package dev.drf.tetris.console;

import dev.drf.tetris.core.MapCell;
import dev.drf.tetris.core.MapContainer;
import dev.drf.tetris.core.MapRow;

public class TetrisConsole {
    private static final int SKIPPED_SPACE_COUNT = 10;

    private static final String SPACE = " ";
    private static final String WALL = "#";
    private static final String BOTTOM = " #";

    private static final String CELL_FILLED = " *";
    private static final String CELL_EMPTY = "  ";

    public void draw(MapContainer container) {
        container.getRows().forEach(this::drawRow);
        drawLastRow(container.getWidth());
    }

    private void drawRow(MapRow row) {
        StringBuilder builder = new StringBuilder();
        builder.append(SPACE.repeat(SKIPPED_SPACE_COUNT));
        builder.append(WALL);
        row.getCells().forEach(
                cell -> drawCell(builder, cell)
        );
        builder.append(WALL);

        System.out.println(builder.toString());
    }

    private void drawCell(StringBuilder builder, MapCell cell) {
        builder.append(
                cell.isEmpty() ? CELL_EMPTY : CELL_FILLED
        );
    }

    private void drawLastRow(int width) {
        StringBuilder builder = new StringBuilder();
        builder.append(SPACE.repeat(SKIPPED_SPACE_COUNT));
        builder.append(WALL);
        builder.append(BOTTOM.repeat(width));
        builder.append(WALL);

        System.out.println(builder.toString());
    }
}
