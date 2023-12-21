import Util.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day16 {

    public static void main(String[] args) {
        List<String> lines = ReadFileAsArray.execute("./input/day-16.txt");
        Map<Coordinates, Character> symbolMap = getSymbolMap(lines);
        int energizedCount = getEnergizedCount(List.of(new Beam(new Coordinates(1, 1), Direction.RIGHT)), symbolMap);
        System.out.println("Part 1: Energized count = " + energizedCount);

        // part 2 - multiple starting states
        List<Beam> initialBeams = getInitialBeams(lines);
        AtomicInteger maxEnergized = new AtomicInteger();
        initialBeams.forEach(beam -> {
            int count = getEnergizedCount(List.of(beam), symbolMap);
            if (count > maxEnergized.get()) {
                maxEnergized.set(count);
            }
        });
        System.out.println("Part 2: Max energized count = " + maxEnergized.get());
    }

    private static int getEnergizedCount(List<Beam> beams, Map<Coordinates, Character> symbolMap) {
        Set<Coordinates> energizedSet = new HashSet<>();
        Set<String> visitedBeams = new HashSet<>();
        while (!beams.isEmpty()) {
            beams = MoveBeams.execute(beams, symbolMap, energizedSet);
            beams = beams.stream().filter(beam-> !visitedBeams.contains(beam.getKey())).toList();
            beams.forEach(beam -> visitedBeams.add(beam.getKey()));
        }
        return energizedSet.size();
    }

    private static Map<Coordinates, Character> getSymbolMap(List<String> lines) {
        Map<Coordinates, Character> symbolMap = new HashMap<>();
        for (int y = 1; y <= lines.size(); y++) {
            String line = lines.get(y - 1);
            for (int x = 1; x <= line.length(); x++) {
                symbolMap.put(new Coordinates(x, y), line.charAt(x-1));
            }
        }
        return symbolMap;
    }

    private static List<Beam> getInitialBeams(List<String> lines) {
        List<Beam> beams = new ArrayList<>();
        int minX = 1;
        int minY = 1;
        int maxX = lines.getFirst().length();
        int maxY = lines.size();
        // top row and bottom row
        for (int x = minX; x <= maxX; x++) {
            beams.add(new Beam(new Coordinates(x, minY), Direction.DOWN));
            beams.add(new Beam(new Coordinates(x, maxY), Direction.UP));
        }

        // left side and right side
        for (int y = minY; y <= maxY; y++) {
            beams.add(new Beam(new Coordinates(minX, y), Direction.RIGHT));
            beams.add(new Beam(new Coordinates(maxX, y), Direction.LEFT));
        }
        return beams;
    }
}
