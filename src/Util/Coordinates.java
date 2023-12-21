package Util;

public class Coordinates {

    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isAdjacent(Coordinates other) {
        if (Math.abs(x - other.getX()) > 1) {
            return false;
        }
        return (Math.abs(y - other.getY()) <= 1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinates move(Direction direction, int distance) {
        return switch (direction) {
            case Direction.UP -> move(0, -distance);
            case Direction.DOWN -> move(0, distance);
            case Direction.LEFT -> move(-distance, 0);
            case Direction.RIGHT -> move(distance, 0);
        };
    }

    private Coordinates move(int x, int y) {
        return new Coordinates(this.x + x, this.y + y);
    }

    public boolean equals(Object other) {
        if (!(other instanceof  Coordinates)) {
            return false;
        }
        return x == ((Coordinates) other).getX() && y == ((Coordinates) other).getY();
    }

    public int hashCode() {
        return x * 1000 + y;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
