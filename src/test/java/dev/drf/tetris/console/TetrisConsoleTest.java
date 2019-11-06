package dev.drf.tetris.console;

import dev.drf.tetris.core.MapCell;
import dev.drf.tetris.core.MapContainer;
import org.junit.Test;

public class TetrisConsoleTest {
    private TetrisConsole console = new TetrisConsole();

    @Test
    public void drawConsole() {
        MapContainer container = new MapContainer(12, 20);

        container.getRows().get(19).getCells().forEach(MapCell::fill);

        container.fillCell(0, 6);
        container.fillCell(0, 7);
        container.fillCell(1, 7);
        container.fillCell(2, 7);

        console.draw(container);
    }
}
