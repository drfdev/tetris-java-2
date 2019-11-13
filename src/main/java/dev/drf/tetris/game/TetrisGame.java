package dev.drf.tetris.game;

import dev.drf.tetris.console.ConsoleInput;
import dev.drf.tetris.console.TetrisConsole;
import dev.drf.tetris.core.MapContainer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static dev.drf.tetris.game.MoveDirection.*;

public class TetrisGame {
    private static final String EXIT_COMMAND = "exit";
    private static final String SHORT_EXIT_COMMAND = "e";
    private static final String LEFT_MOVE_COMMAND = "a";
    private static final String RIGHT_MOVE_COMMAND = "d";
    private static final String ROTATE_FIGURE_COMMAND = "w";
    private static final String DOWN_MOVE_COMMAND = "s";
    private static final String SKIP_COMMAND = "q";
    private static final String EMPTY_COMMAND = "";

    private MapContainer container;
    private StepByStep stepByStep;
    private TetrisConsole console;

    public TetrisGame() {
    }

    public void preload() {
        int width = 0;
        int height = 0;
        int ticCount = 0;

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("game.properties")) {
            if (input == null) {
                return;
            }
            Properties prop = new Properties();
            prop.load(input);

            width = Integer.parseInt(prop.getProperty("map.size.width"));
            height = Integer.parseInt(prop.getProperty("map.size.height"));
            ticCount = Integer.parseInt(prop.getProperty("game.step.tic_count"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        container = new MapContainer(width, height);
        stepByStep = new StepByStep(ticCount);

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
            if (EXIT_COMMAND.equals(command)
                    || SHORT_EXIT_COMMAND.equals(command)) {
                break;
            }

            switch (command) {
                case LEFT_MOVE_COMMAND -> stepByStep.moveFigure(container, LEFT);
                case RIGHT_MOVE_COMMAND -> stepByStep.moveFigure(container, RIGHT);
                case ROTATE_FIGURE_COMMAND -> stepByStep.rotateFigure(container);
                case DOWN_MOVE_COMMAND -> stepByStep.moveFigure(container, DOWN);
                case EMPTY_COMMAND, SKIP_COMMAND -> {
                }
            }
        }
    }
}
