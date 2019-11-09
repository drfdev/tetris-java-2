package dev.drf.tetris;

import dev.drf.tetris.console.ConsoleInput;
import dev.drf.tetris.game.TetrisGame;

public class Main {
    public static void main(String[] args) {
        // preload:
        System.out.println("Tetris runnnig...");

        TetrisGame game = new TetrisGame();
        game.preload();

        // start the game:
        System.out.println("Game starting...");
        game.startGame(new ConsoleInput());
    }
}
