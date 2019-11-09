package dev.drf.tetris.game;

import dev.drf.tetris.console.ConsoleInput;
import dev.drf.tetris.console.TetrisConsole;
import dev.drf.tetris.core.MapContainer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TetrisGame {
    private static final String EXIT_COMMAND = "exit";

    private MapContainer container;
    private StepByStep stepByStep;
    private TetrisConsole console;

    public TetrisGame() {
    }

    public void preload() {
        int width = 0;
        int height = 0;

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("game.properties")) {
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
        stepByStep = new StepByStep();

        console = new TetrisConsole();
    }

    public void startGame(ConsoleInput input) {
        while (true) {
            stepByStep.nextStep(container);
            console.draw(container);
            String command = input.witForInput();

            if (command == null) {
                throw new RuntimeException("Null command");
            }
            if (EXIT_COMMAND.equals(command)) {
                break;
            }
        }
    }
}
