import Util.CityBlock.CreateGrid;
import Util.CityBlock.FindCheapestCost;
import Util.CityBlock.Grid;
import Util.Coordinates;
import Util.ReadFileAsArray;

import java.util.List;

public class Day17 {

    public static void main(String[] args) {
        List<String> lines = ReadFileAsArray.execute("./input/day-17.txt");
        Grid grid = CreateGrid.execute(lines);
        int cost = FindCheapestCost.execute(
                grid,
                new Coordinates(1, 1),
                new Coordinates(lines.size(), lines.getFirst().length())
        );
        System.out.println("Part 1: Cost = " + cost);
    }
}
