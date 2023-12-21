package Util;

import java.util.Set;

public enum Direction {
    RIGHT (1),
    LEFT (2),
    UP (3),
    DOWN (4);

    private final int code;

    Direction(final int code) {
        this.code = code;
    }

    public String getCode() {
        return String.valueOf(code);
    }

    public Set<Direction> getPerpendicularDirections() {
        return switch (this) {
            case UP, DOWN -> Set.of(LEFT, RIGHT);
            case LEFT,RIGHT -> Set.of(UP,DOWN);
        };
    }
}
