import Util.Coordinates;
import Util.ReadFileAsArray;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day11 {

    private static final char GALAXY_CHAR = '#';

    public static void main(String[] args) {
        List<String> lines = ReadFileAsArray.execute("./input/day-11.txt");
        List<String>  galaxyMap = expandUniverse(lines);

        List<Coordinates> galaxies = getGalaxyCoordinates(galaxyMap);
        long distanceSum = findDistancesSum(galaxies);
        System.out.println("Sum of distances = " + distanceSum);

    }

    private static List<String> expandUniverse(List<String> lines) {
        List<String> expanded = expandRows(lines);
        expanded = transposeLines(expanded);
        expanded = expandRows(expanded);
        expanded = transposeLines(expanded);
        return expanded;
    }

    private static List<String> expandRows(List<String> lines) {
        List<String> expanded = new ArrayList<>();
        // find all rows that have no galaxies
        for (String line : lines) {
            expanded.add(line);
            if (line.indexOf(GALAXY_CHAR) == -1) {
                expanded.add(line);
            }
        }
        return expanded;
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

    private static List<Coordinates> getGalaxyCoordinates(List<String> galaxyMap) {
        List<Coordinates> galaxies = new ArrayList<>();
        for (int y = 1; y <= galaxyMap.size(); y++) {
            String line = galaxyMap.get(y-1);
            char[] charArray = line.toCharArray();
            for (int x = 1; x <= charArray.length; x++) {
                char value = charArray[x-1];
                if (value == GALAXY_CHAR) {
                    galaxies.add(new Coordinates(x, y));
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
