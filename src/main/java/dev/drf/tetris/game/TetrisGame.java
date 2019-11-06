package dev.drf.tetris.game;

import dev.drf.tetris.console.TetrisConsole;
import dev.drf.tetris.core.MapContainer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TetrisGame {
    private MapContainer container;
    private MapInteractor interactor;
    private StepByStep stepByStep;
    private TetrisConsole console;

    public TetrisGame() {
    }

    public void preload() {
        int width = 0;
        int height = 0;

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                return;
            }
            Properties prop = new Properties();
            prop.load(input);

            width = Integer.parseInt(prop.getProperty("map.size.width"));
            height = Integer.parseInt(prop.getProperty("map.size.height"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        container = new MapContainer(width, height);
        interactor = new MapInteractor();
        stepByStep = new StepByStep();

        console = new TetrisConsole();
    }

    public void startGame() {
        // TODO
    }
}
