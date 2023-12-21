package Util.CityBlock;

import Util.Coordinates;
import Util.Direction;

public class State {

    private final Coordinates coordinates;
    private final Direction direction;
    private final int distance;

    public State(Coordinates coordinates, Direction direction, int distance) {
        this.coordinates = coordinates;
        this.direction = direction;
        this.distance = distance;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getDistance() {
        return distance;
    }

    public String getKey() {
        return coordinates + "~" + direction.getCode() + "~" + distance;
    }
}
