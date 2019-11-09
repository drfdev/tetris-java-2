package dev.drf.tetris.console;

import java.util.Scanner;

public class ConsoleInput {
    private Scanner scanner;

    public ConsoleInput() {
        scanner = new Scanner(System.in);
    }

    public String witForInput() {
        System.out.print("Command: ");
        return scanner.nextLine();
    }
}
