package Util.CityBlock;

import Util.Coordinates;

import java.util.Map;

public class Grid {

    private final Map<Coordinates, Integer> positionCostMap;

    public Grid(Map<Coordinates, Integer> positionCostMap) {
        this.positionCostMap = positionCostMap;
    }

    public int getCost(Coordinates coordinates) {
        return positionCostMap.getOrDefault(coordinates, 0);
    }

    public boolean inBounds(Coordinates coordinates) {
        return positionCostMap.containsKey(coordinates);
    }
}
