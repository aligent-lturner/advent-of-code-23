import Util.Coordinates;
import Util.Direction;
import Util.GetPolygonArea;
import Util.ReadFileAsArray;

import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;

public class Day18 {

    public static void main(String[] args) {
        List<String> lines = ReadFileAsArray.execute("./input/day-18.txt");
        List<Coordinates> digPlanPart1 = getPart1DigPlan(lines);
        List<Coordinates> digPlanPart2 = getPart2DigPlan(lines);

        long area = GetPolygonArea.execute(digPlanPart1);
        area += getPerimeter(digPlanPart1)/2 + 1;
        System.out.println("Part 1: Area = " + area);

        area = GetPolygonArea.execute(digPlanPart2);
        area += getPerimeter(digPlanPart2)/2 + 1;
        System.out.println("Part 2: Area = " + area);
    }

    private static List<Coordinates> getPart1DigPlan(List<String> lines) {
        List<Coordinates> digPlan = new ArrayList<>();
        Coordinates coordinates = new Coordinates(1, 1);
        for (String line: lines) {
            digPlan.add(coordinates);
            String[] values = line.split("\\s+");
            Direction direction = getDirection(values[0]);
            int distance = Integer.parseInt(values[1]);
            coordinates = coordinates.move(direction, distance);
        }
        return digPlan;
    }

    private static List<Coordinates> getPart2DigPlan(List<String> lines) {
        List<Coordinates> digPlan = new ArrayList<>();
        Coordinates coordinates = new Coordinates(1, 1);
        for (String line: lines) {
            digPlan.add(coordinates);
            String[] values = line.split("\\s+");
            String hexValue = values[2].substring(2, 8);
            int distance = getDistance(hexValue.substring(0, 5));
            Direction direction = getDirection(Integer.parseInt(hexValue.substring(hexValue.length() - 1)));
            coordinates = coordinates.move(direction, distance);
        }
        return digPlan;
    }

    private static Direction getDirection(String direction) {
        return switch (direction.charAt(0)) {
            case 'R' -> Direction.RIGHT;
            case 'L' -> Direction.LEFT;
            case 'U' -> Direction.UP;
            default -> Direction.DOWN;
        };
    }

    private static Direction getDirection(int directionCode) {
        return switch (directionCode) {
            case 0 -> Direction.RIGHT;
            case 1 -> Direction.DOWN;
            case 2 -> Direction.LEFT;
            default -> Direction.UP;
        };
    }

    private static int getDistance(String hexCode) {
        return HexFormat.fromHexDigits(hexCode);
    }

    private static int getPerimeter(List<Coordinates> digPlan) {
        int perimeter = 0;
        for (int i = 1; i < digPlan.size(); i++) {
            int xDiff = Math.abs(digPlan.get(i).getX() - digPlan.get(i-1).getX());
            int yDiff = Math.abs(digPlan.get(i).getY() - digPlan.get(i-1).getY());
            perimeter += xDiff + yDiff;
        }
        // add difference between first and last points
        perimeter += Math.abs(digPlan.getFirst().getX() - digPlan.getLast().getX());
        perimeter += Math.abs(digPlan.getFirst().getY() - digPlan.getLast().getY());
        return perimeter;
    }
}
