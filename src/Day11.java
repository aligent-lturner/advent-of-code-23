import Util.Coordinates;
import Util.ReadFileAsArray;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day11 {

    private static final char GALAXY_CHAR = '#';

    public static void main(String[] args) {
        List<String> galaxyMap = ReadFileAsArray.execute("./input/day-11.txt");
        List<Integer> emptyRows = getEmptyRows(galaxyMap);
        List<Integer> emptyColumns = getEmptyRows(transposeLines(galaxyMap));

        List<Coordinates> galaxies = getGalaxyCoordinates(galaxyMap, emptyRows, emptyColumns, 1);
        long distanceSum = findDistancesSum(galaxies);
        System.out.println("Part 1: Sum of distances = " + distanceSum);
        galaxies = getGalaxyCoordinates(galaxyMap, emptyRows, emptyColumns, 999999);
        distanceSum = findDistancesSum(galaxies);
        System.out.println("Part 2: Sum of distances = " + distanceSum);
    }

    private static List<Integer> getEmptyRows(List<String> lines) {
        List<Integer> emptyRows = new ArrayList<>();
        for (int i = 1; i <= lines.size(); i++) {
            if (lines.get(i-1).indexOf(GALAXY_CHAR) == -1) {
                emptyRows.add(i);
            }
        }
        return emptyRows;
    }

    private static List<String> transposeLines(List<String> lines) {
        List<List<Character>> transposed = IntStream.range(0, lines.getFirst().length()).
                mapToObj(i -> lines.stream().map(line -> line.charAt(i)).collect(Collectors.toList())).
                toList();
        return transposed.stream().map(charList ->  {
            StringBuilder sb = new StringBuilder();
            for (Character character : charList) {
                sb.append(character);
            }
            return sb.toString();
        }).toList();
    }

    private static List<Coordinates> getGalaxyCoordinates(List<String> galaxyMap,
                                                          List<Integer> emptyRows,
                                                          List<Integer> emptyColumns,
                                                          int expansionAmount) {

        List<Coordinates> galaxies = new ArrayList<>();

        int addToY = 0;
        for (int y = 1; y <= galaxyMap.size(); y++) {
            int addToX = 0;
            if (emptyRows.contains(y)) {
                addToY += expansionAmount;
            }
            String line = galaxyMap.get(y-1);
            char[] charArray = line.toCharArray();
            for (int x = 1; x <= charArray.length; x++) {
                if (emptyColumns.contains(x)) {
                    addToX += expansionAmount;
                }
                char value = charArray[x-1];
                if (value == GALAXY_CHAR) {
                    galaxies.add(new Coordinates(x + addToX, y + addToY));
                }
            }
        }
        return galaxies;
    }

    private static long findDistancesSum(List<Coordinates> galaxies) {
        long sum = 0;
        for (int i = 0; i < galaxies.size() - 1; i++) {
            for (int j = i+1; j < galaxies.size(); j++) {
                Coordinates galaxyA = galaxies.get(i);
                Coordinates galaxyB = galaxies.get(j);
                long distance = Math.abs(galaxyA.getX() - galaxyB.getX()) + Math.abs(galaxyA.getY() - galaxyB.getY());
                sum += distance;
            }
        }
        return sum;
    }
}
