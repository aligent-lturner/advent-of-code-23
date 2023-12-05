package Util;

import java.util.ArrayList;
import java.util.Optional;

public class CoordinatesRange {

    private int number;
    private ArrayList<Coordinates> range;

    public void setRange(ArrayList<Coordinates> range) {
        this.range = range;
    }

    public boolean isAdjacent(Coordinates other) {
        Optional<Coordinates> first = range.stream().filter((n) -> n.isAdjacent(other)).findFirst();
        return first.isPresent();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
