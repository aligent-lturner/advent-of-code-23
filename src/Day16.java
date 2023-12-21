import Util.*;

import java.util.*;

public class Day16 {

    public static void main(String[] args) {
        List<String> lines = ReadFileAsArray.execute("./input/day-16.txt");
        Map<Coordinates, Character> symbolMap = getSymbolMap(lines);
        Set<Coordinates> energizedSet = new HashSet<>();
        Set<String> visitedBeams = new HashSet<>();
        List<Beam> beams = new ArrayList<>();
        beams.add(new Beam(new Coordinates(1,1), Direction.RIGHT));
        while (!beams.isEmpty()) {
            beams = MoveBeams.execute(beams, symbolMap, energizedSet);
            beams = beams.stream().filter(beam-> !visitedBeams.contains(beam.getKey())).toList();
            beams.forEach(beam -> visitedBeams.add(beam.getKey()));
        }
        System.out.println("Part 1: Energized count = " + energizedSet.size());
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
}
