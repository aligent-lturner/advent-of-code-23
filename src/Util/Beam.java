package Util;

public class Beam {

    private static final char LEFT_MIRROR = '\\';
    private static final char RIGHT_MIRROR = '/';
    private static final char VERTICAL_SPLITTER = '|';
    private static final char HORIZONTAL_SPLITTER = '-';

    private Coordinates currentPosition;

    private Direction currentDirection;

    public Beam(Coordinates currentPosition, Direction currentDirection) {
        this.currentPosition = currentPosition;
        this.currentDirection = currentDirection;
    }

    public void move() {
        int xChange = switch (currentDirection) {
            case Direction.RIGHT -> 1;
            case Direction.LEFT -> -1;
            default -> 0;
        };
        int yChange = switch(currentDirection) {
            case Direction.UP -> -1;
            case Direction.DOWN -> 1;
            default -> 0;
        };
        currentPosition = new Coordinates(currentPosition.getX() + xChange, currentPosition.getY() + yChange);
    }

    public Coordinates getCurrentPosition() {
        return currentPosition;
    }

    public Beam applySymbol(char symbol) {
        switch (symbol) {
            case LEFT_MIRROR: {
                currentDirection = switch (currentDirection) {
                    case Direction.RIGHT -> Direction.DOWN;
                    case Direction.LEFT -> Direction.UP;
                    case Direction.UP -> Direction.LEFT;
                    case Direction.DOWN -> Direction.RIGHT;
                };
                return null;
            }
            case RIGHT_MIRROR: {
                currentDirection = switch (currentDirection) {
                    case Direction.RIGHT -> Direction.UP;
                    case Direction.LEFT -> Direction.DOWN;
                    case Direction.UP -> Direction.RIGHT;
                    case Direction.DOWN -> Direction.LEFT;
                };
                return null;
            }
            case VERTICAL_SPLITTER: {
                return switch (currentDirection) {
                    case Direction.RIGHT, Direction.LEFT -> {
                        currentDirection = Direction.UP;
                        yield new Beam(new Coordinates(currentPosition.getX(), currentPosition.getY()), Direction.DOWN);
                    }
                    default -> null;
                };
            }
            case HORIZONTAL_SPLITTER: {
                return switch (currentDirection) {
                    case Direction.UP, Direction.DOWN -> {
                        currentDirection = Direction.RIGHT;
                        yield new Beam(new Coordinates(currentPosition.getX(), currentPosition.getY()), Direction.LEFT);
                    }
                    default -> null;
                };
            }
        }
        return null;
    }

    public String getKey() {
        return currentPosition + "~" + currentDirection.getCode();
    }
}

