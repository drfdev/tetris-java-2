package dev.drf.tetris.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static dev.drf.tetris.core.FigureRotate.*;
import static dev.drf.tetris.core.FigureType.*;

public final class Figure {
    private final FigureType type;

    private FigureRotate rotate;
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

    public MapCell getBaseCell() {
        return baseCell;
    }

    public void setBaseCell(MapCell baseCell) {
        this.baseCell = baseCell;
    }

    public void rotate() {
        // clockwise
        rotate = switch (rotate) {
            case TO_TOP -> TO_RIGHT;
            case TO_RIGHT -> TO_BOTTOM;
            case TO_BOTTOM -> TO_LEFT;
            case TO_LEFT -> TO_TOP;
        };
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

    public List<MapCell> detectCells(MapContainer map) {
        return switch (type) {
            case I_BLOCK -> I_Block(map);
            case J_BLOCK -> J_Block(map);
            case L_BLOCK -> L_Block(map);
            case O_BLOCK -> O_Block(map);
            case S_BLOCK -> S_Block(map);
            case T_BLOCK -> T_Block(map);
            case Z_BLOCK -> Z_Block(map);
        };
    }

    private List<MapCell> I_Block(MapContainer map) {
        final int row = baseCell.getRow();
        final int cell = baseCell.getNumber();

        if (rotate == TO_LEFT || rotate == TO_RIGHT) {
            final int rrow = Math.min(row + 3, map.lastRow());
            return Arrays.asList(
                    map.getCell(rrow - 3, cell),
                    map.getCell(rrow - 2, cell),
                    map.getCell(rrow - 1, cell),
                    map.getCell(rrow, cell)
            );
        }
        if (rotate == TO_TOP || rotate == TO_BOTTOM) {
            final int rrow = Math.min(row + 2, map.lastRow());
            final int rcell = (cell - 2) < 0
                    ? 0
                    : Math.min(cell + 1, map.lastCell()) - 3;
            return Arrays.asList(
                    map.getCell(rrow, rcell),
                    map.getCell(rrow, rcell + 1),
                    map.getCell(rrow, rcell + 2),
                    map.getCell(rrow, rcell + 3)
            );
        }

        return Collections.emptyList();
    }

    private List<MapCell> J_Block(MapContainer map) {
        final int row = baseCell.getRow();
        final int cell = baseCell.getNumber();

        if (rotate == TO_TOP) {
            final int rrow = Math.min(row, map.lastRow() - 2);
            final int rcell = (cell - 1) < 0
                    ? 1
                    : Math.min(cell, map.lastCell());
            return Arrays.asList(
                    map.getCell(rrow, rcell),
                    map.getCell(rrow + 1, rcell),
                    map.getCell(rrow + 2, rcell),
                    map.getCell(rrow + 2, rcell - 1)
            );
        }
        if (rotate == TO_RIGHT) {
            final int rrow = Math.min(row, map.lastRow() - 2);
            final int rcell = (cell - 1) < 0
                    ? 1
                    : Math.min(cell, map.lastCell() - 1);
            return Arrays.asList(
                    map.getCell(rrow + 1, rcell - 1),
                    map.getCell(rrow + 2, rcell - 1),
                    map.getCell(rrow + 2, rcell),
                    map.getCell(rrow + 2, rcell + 1)
            );
        }
        if (rotate == TO_BOTTOM) {
            final int rrow = Math.min(row, map.lastRow() - 2);
            final int rcell = Math.min(cell, map.lastCell() - 1);
            return Arrays.asList(
                    map.getCell(rrow, rcell),
                    map.getCell(rrow, rcell + 1),
                    map.getCell(rrow + 1, rcell),
                    map.getCell(rrow + 2, rcell)
            );
        }
        if (rotate == TO_LEFT) {
            final int rrow = Math.min(row, map.lastRow() - 2);
            final int rcell = (cell - 1) < 0
                    ? 1
                    : Math.min(cell, map.lastCell() - 1);
            return Arrays.asList(
                    map.getCell(rrow + 1, rcell - 1),
                    map.getCell(rrow + 1, rcell),
                    map.getCell(rrow + 1, rcell + 1),
                    map.getCell(rrow + 2, rcell + 1)
            );
        }
        return Collections.emptyList();
    }

    private List<MapCell> L_Block(MapContainer map) {
        final int row = baseCell.getRow();
        final int cell = baseCell.getNumber();

        if (rotate == TO_TOP) {
            final int rrow = Math.min(row, map.lastRow() - 2);
            final int rcell = Math.min(cell, map.lastCell() - 1);
            return Arrays.asList(
                    map.getCell(rrow, rcell),
                    map.getCell(rrow + 1, rcell),
                    map.getCell(rrow + 2, rcell),
                    map.getCell(rrow + 2, rcell + 1)
            );
        }
        if (rotate == TO_RIGHT) {
            final int rrow = Math.min(row, map.lastRow() - 2);
            final int rcell = (cell - 1) < 0
                    ? 1
                    : Math.min(cell, map.lastCell() - 1);
            return Arrays.asList(
                    map.getCell(rrow + 1, rcell - 1),
                    map.getCell(rrow + 1, rcell),
                    map.getCell(rrow + 1, rcell + 1),
                    map.getCell(rrow + 2, rcell - 1)
            );
        }
        if (rotate == TO_BOTTOM) {
            final int rrow = Math.min(row, map.lastRow() - 2);
            final int rcell = Math.min(cell, map.lastCell() - 1);
            return Arrays.asList(
                    map.getCell(rrow, rcell),
                    map.getCell(rrow, rcell + 1),
                    map.getCell(rrow + 1, rcell + 1),
                    map.getCell(rrow + 2, rcell + 1)
            );
        }
        if (rotate == TO_LEFT) {
            final int rrow = Math.min(row, map.lastRow() - 2);
            final int rcell = (cell - 1) < 0
                    ? 1
                    : Math.min(cell, map.lastCell() - 1);
            return Arrays.asList(
                    map.getCell(rrow + 1, rcell + 1),
                    map.getCell(rrow + 2, rcell + 1),
                    map.getCell(rrow + 2, rcell),
                    map.getCell(rrow + 2, rcell - 1)
            );
        }
        return Collections.emptyList();
    }

    private List<MapCell> O_Block(MapContainer map) {
        final int row = baseCell.getRow();
        final int cell = baseCell.getNumber();

        // rotate ignore
        return Arrays.asList(
                map.getCell(row, cell),
                map.getCell(row, cell + 1),
                map.getCell(row + 1, cell),
                map.getCell(row + 1, cell + 1)
        );
    }

    private List<MapCell> S_Block(MapContainer map) {
        final int row = baseCell.getRow();
        final int cell = baseCell.getNumber();

        if (rotate == TO_TOP || rotate == TO_BOTTOM) {
            final int rrow = Math.min(row, map.lastRow() - 1);
            final int rcell = (cell - 1) < 0
                    ? 1
                    : Math.min(cell, map.lastCell() - 1);
            return Arrays.asList(
                    map.getCell(rrow, rcell),
                    map.getCell(rrow, rcell + 1),
                    map.getCell(rrow + 1, rcell),
                    map.getCell(rrow + 1, rcell - 1)
            );
        }
        if (rotate == TO_RIGHT || rotate == TO_LEFT) {
            final int rrow = (row - 1) < 0
                    ? 1
                    : Math.min(row, map.lastRow() - 1);
            final int rcell = (cell - 1) < 0
                    ? 1
                    : cell;
            return Arrays.asList(
                    map.getCell(rrow - 1, rcell - 1),
                    map.getCell(rrow, rcell - 1),
                    map.getCell(rrow, rcell),
                    map.getCell(rrow + 1, rcell)
            );
        }
        return Collections.emptyList();
    }

    private List<MapCell> T_Block(MapContainer map) {
        final int row = baseCell.getRow();
        final int cell = baseCell.getNumber();

        if (rotate == TO_TOP) {
            final int rrow = Math.min(row, map.lastRow() - 1);
            final int rcell = (cell - 1) < 0
                    ? 1
                    : Math.min(cell, map.lastCell() - 1);
            return Arrays.asList(
                    map.getCell(rrow + 1, rcell - 1),
                    map.getCell(rrow + 1, rcell),
                    map.getCell(rrow + 1, rcell + 1),
                    map.getCell(rrow, rcell)
            );
        }
        if (rotate == TO_RIGHT) {
            final int rrow = Math.min(row, map.lastRow() - 2);
            final int rcell = Math.min(cell, map.lastCell() - 1);
            return Arrays.asList(
                    map.getCell(rrow, rcell),
                    map.getCell(rrow + 1, rcell),
                    map.getCell(rrow + 2, rcell),
                    map.getCell(rrow + 1, rcell + 1)
            );
        }
        if (rotate == TO_BOTTOM) {
            final int rrow = Math.min(row, map.lastRow() - 2);
            final int rcell = (cell - 1) < 0
                    ? 1
                    : Math.min(cell, map.lastCell() - 1);
            return Arrays.asList(
                    map.getCell(rrow + 1, rcell - 1),
                    map.getCell(rrow + 1, rcell),
                    map.getCell(rrow + 1, rcell + 1),
                    map.getCell(rrow + 2, rcell)
            );
        }
        if (rotate == TO_LEFT) {
            final int rrow = Math.min(row, map.lastRow() - 1);
            final int rcell = (cell - 1) < 0
                    ? 1
                    : cell;
            return Arrays.asList(
                    map.getCell(rrow, rcell),
                    map.getCell(rrow + 1, rcell),
                    map.getCell(rrow + 2, rcell),
                    map.getCell(rrow + 1, rcell - 1)
            );
        }
        return Collections.emptyList();
    }

    private List<MapCell> Z_Block(MapContainer map) {
        final int row = baseCell.getRow();
        final int cell = baseCell.getNumber();

        if (rotate == TO_TOP || rotate == TO_BOTTOM) {
            final int rrow = Math.min(row, map.lastRow() - 1);
            final int rcell = (cell - 1) < 0
                    ? 1
                    : Math.min(cell, map.lastCell() - 1);
            return Arrays.asList(
                    map.getCell(rrow, rcell - 1),
                    map.getCell(rrow, rcell),
                    map.getCell(rrow + 1, rcell),
                    map.getCell(rrow + 1, rcell + 1)
            );
        }
        if (rotate == TO_RIGHT || rotate == TO_LEFT) {
            final int rrow = (row - 1) < 0
                    ? 1
                    : Math.min(row, map.lastRow() - 1);
            final int rcell = (cell - 1) < 0
                    ? 1
                    : cell;
            return Arrays.asList(
                    map.getCell(rrow - 1, rcell),
                    map.getCell(rrow, rcell),
                    map.getCell(rrow, rcell - 1),
                    map.getCell(rrow + 1, rcell - 1)
            );
        }
        return Collections.emptyList();
    }

    public void changeBaseCell(MapContainer map, MapCell baseCell) {
        MapCell current = getBaseCell();
        FigureType type = getType();

        final int cell = baseCell.getNumber();
        final int maxCell = map.lastCell();

        if (type == I_BLOCK) {
            if (rotate == TO_TOP || rotate == TO_BOTTOM) {
                if (cell - 2 < 0 || cell + 1 > maxCell) {
                    return;
                }
            }
        }
        if (type == J_BLOCK) {
            if (rotate == TO_TOP) {
                if (cell - 1 < 0) {
                    return;
                }
            }
            if (rotate == TO_RIGHT) {
                if (cell - 1 < 0 || cell + 1 > maxCell) {
                    return;
                }
            }
            if (rotate == TO_BOTTOM) {
                if (cell + 1 > maxCell) {
                    return;
                }
            }
            if (rotate == TO_LEFT) {
                if (cell - 1 < 0 || cell + 1 > maxCell) {
                    return;
                }
            }
        }
        if (type == L_BLOCK) {
            if (rotate == TO_TOP) {
                if (cell + 2 > maxCell) {
                    return;
                }
            }
            if (rotate == TO_RIGHT) {
                if (cell - 1 < 0 || cell + 1 > maxCell) {
                    return;
                }
            }
            if (rotate == TO_BOTTOM) {
                if (cell + 1 > maxCell) {
                    return;
                }
            }
            if (rotate == TO_LEFT) {
                if (cell - 1 < 0 || cell + 1 > maxCell) {
                    return;
                }
            }
        }
        if (type == O_BLOCK) {
            if (cell + 1 > maxCell) {
                return;
            }
        }
        if (type == S_BLOCK) {
            if (rotate == TO_TOP || rotate == TO_BOTTOM) {
                if (cell - 1 < 0 || cell + 1 > maxCell) {
                    return;
                }
            }
            if (rotate == TO_RIGHT || rotate == TO_LEFT) {
                if (cell - 1 < 0) {
                    return;
                }
            }
        }
        if (type == T_BLOCK) {
            if (rotate == TO_TOP) {
                if (cell - 1 < 0 || cell + 1 > maxCell) {
                    return;
                }
            }
            if (rotate == TO_RIGHT) {
                if (cell + 1 > maxCell) {
                    return;
                }
            }
            if (rotate == TO_BOTTOM) {
                if (cell - 1 < 0 || cell + 1 > maxCell) {
                    return;
                }
            }
            if (rotate == TO_LEFT) {
                if (cell - 1 < 0) {
                    return;
                }
            }
        }
        if (type == Z_BLOCK) {
            if (rotate == TO_TOP || rotate == TO_BOTTOM) {
                if (cell - 1 < 0 || cell + 1 > maxCell) {
                    return;
                }
            }
            if (rotate == TO_RIGHT || rotate == TO_LEFT) {
                if (cell - 1 < 0) {
                    return;
                }
            }
        }

        setBaseCell(baseCell);
    }
}
