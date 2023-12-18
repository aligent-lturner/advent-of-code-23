package Util;

import java.util.HashSet;
import java.util.Set;

public class PipeNode {

    private static final char VERTICAL = '|';
    private static final char HORIZONTAL = '-';
    private static final char NORTH_EAST = 'L';
    private static final char NORTH_WEST = 'J';
    private static final char SOUTH_WEST = '7';
    private static final char SOUTH_EAST = 'F';
    private static final char START = 'S';

    private final Coordinates position;

    private final char pipe;

    public PipeNode(int x, int y, char pipe) {
        position = new Coordinates(x, y);
        this.pipe = pipe;
    }

    public Coordinates getPosition() {
        return position;
    }

    public boolean isStart() {
        return pipe == START;
    }

    public Set<Coordinates> getConnectedCoordinates() {
        Set<Coordinates> connectedCoords = new HashSet<>();
        switch (pipe) {
            case VERTICAL:
                connectedCoords.add(new Coordinates(position.getX(), position.getY() - 1));
                connectedCoords.add(new Coordinates(position.getX(), position.getY() + 1));
                break;
            case HORIZONTAL:
                connectedCoords.add(new Coordinates(position.getX() - 1, position.getY()));
                connectedCoords.add(new Coordinates(position.getX() + 1, position.getY()));
                break;
            case NORTH_EAST:
                connectedCoords.add(new Coordinates(position.getX(), position.getY() - 1));
                connectedCoords.add(new Coordinates(position.getX() + 1, position.getY()));
                break;
            case NORTH_WEST:
                connectedCoords.add(new Coordinates(position.getX(), position.getY() - 1));
                connectedCoords.add(new Coordinates(position.getX() - 1, position.getY()));
                break;
            case SOUTH_WEST:
                connectedCoords.add(new Coordinates(position.getX() - 1, position.getY()));
                connectedCoords.add(new Coordinates(position.getX(), position.getY() + 1));
                break;
            case SOUTH_EAST:
                connectedCoords.add(new Coordinates(position.getX() + 1, position.getY()));
                connectedCoords.add(new Coordinates(position.getX(), position.getY() + 1));
        }
        return connectedCoords;
    }

    public boolean equals(Object other) {
        if (!(other instanceof PipeNode)) {
            return false;
        }
        return position.equals(((PipeNode) other).getPosition());
    }

    public int hashCode() {
        return position.hashCode();
    }

    public String toString() {
        return pipe + ": " + position;
    }
}
