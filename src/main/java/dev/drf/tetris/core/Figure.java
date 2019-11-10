package dev.drf.tetris.core;

import java.util.concurrent.ThreadLocalRandom;

public final class Figure {
    private final FigureType type;
    private final FigureRotate rotate;

    private MapCell baseCell;

    private Figure(FigureType type, FigureRotate rotate) {
        this.type = type;
        this.rotate = rotate;
    }

    public FigureType getType() {
        return type;
    }

    public FigureRotate getRotate() {
        return rotate;
    }

    public static Figure of(FigureType type, FigureRotate rotate) {
        return new Figure(type, rotate);
    }

    public static Figure randomFigure() {
        // type
        int randomType = random(FigureType.values().length);
        FigureType type = FigureType.values()[randomType];

        // rotate
        int randomRotate = random(FigureRotate.values().length);
        FigureRotate rotate = FigureRotate.values()[randomRotate];

        return of(type, rotate);
    }


    private static int random(int max) {
        return ThreadLocalRandom.current().nextInt(max);
    }
}
