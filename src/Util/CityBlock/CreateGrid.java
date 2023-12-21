package Util.CityBlock;

import Util.Coordinates;

import java.util.*;

public class CreateGrid {

    public static Grid execute(List<String> lines) {
        Map<Coordinates, Integer> map = new HashMap<>();
        // initialise all nodes
        for (int y = 1; y <= lines.size(); y++) {
            String line = lines.get(y - 1);
            for (int x = 1; x <= line.length(); x++) {
                map.put(new Coordinates(x, y), Integer.parseInt(String.valueOf(line.charAt(x-1))));
            }
        }
        return new Grid(map);
    }
}
