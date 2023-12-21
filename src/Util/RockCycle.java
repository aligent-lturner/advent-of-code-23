package Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RockCycle {

    private static final char SQUARE_ROCK = '#';
    public static final char ROUND_ROCK = 'O';
    private static final char EMPTY = '.';

    public static List<String> runCycles(List<String> lines, long cycleNumber) {
        Map<String, Long> observedStates = new HashMap<>();

        for (long i = 0; i < cycleNumber; i++) {
            String stateKey = generateKey(lines);
            if (observedStates.containsKey(stateKey)) {
                long index = observedStates.get(stateKey);
                long cycleLength = i - index;
                while (i < cycleNumber) {
                    i+= cycleLength;
                }
                i -= cycleLength;
            } else {
                observedStates.put(stateKey, i);
            }
            lines = runSingleCycle(lines);
        }
        return lines;
    }

    private static String generateKey(List<String> lines) {
        StringBuilder key = new StringBuilder();
        lines.forEach(key::append);
        return key.toString();
    }

    private static List<String> runSingleCycle(List<String> lines) {
        return rollEast(rollSouth(rollWest(rollNorth(lines))));
    }

    public static List<String> rollNorth(List<String> lines) {
        // transpose and roll west then transpose again
        return TransposeLines.execute(rollWest(TransposeLines.execute(lines)));
    }

    public static List<String> rollEast(List<String> lines) {
        // reverse the lines and roll west, then reverse again
        return lines.stream().map(line -> new StringBuilder(line).reverse().toString()).
                map(line -> rollLeft(line, findSquareRocks(line))).
                map(line -> new StringBuilder(line).reverse().toString()).toList();
    }

    public static List<String> rollSouth(List<String> lines) {
        // reverse and roll north, then reverse again
        return rollNorth(lines.reversed()).reversed();
    }

    public static List<String> rollWest(List<String> lines) {
        return lines.stream().map(line -> rollLeft(line, findSquareRocks(line))).toList();
    }

    private static List<Integer> findSquareRocks(String line) {
        List<Integer> rocks = new ArrayList<>();
        int index = line.lastIndexOf(SQUARE_ROCK);
        while (index != -1) {
            rocks.add(index);
            index = line.substring(0, index).lastIndexOf(SQUARE_ROCK);
        }
        return rocks;
    }

    private static String rollLeft(String line, List<Integer> squareRocks) {
        StringBuilder newLine = new StringBuilder();
        int currentIndex = line.length();
        for (int squareRock : squareRocks) {
            // get count of round rocks between
            long roundRockCount = line.substring(squareRock, currentIndex).chars().filter(ch -> ch == ROUND_ROCK).count();
            long emptyCount = currentIndex - squareRock - roundRockCount - 1;
            newLine.append(String.valueOf(EMPTY).repeat((int)emptyCount)).
                    append(String.valueOf(ROUND_ROCK).repeat((int)roundRockCount)).
                    append(SQUARE_ROCK);
            currentIndex = squareRock;
        }
        if (currentIndex > 0) {
            long roundRockCount = line.substring(0, currentIndex).chars().filter(ch -> ch == ROUND_ROCK).count();
            long emptyCount = currentIndex - roundRockCount;
            newLine.append(String.valueOf(EMPTY).repeat((int)emptyCount)).
                    append(String.valueOf(ROUND_ROCK).repeat((int)roundRockCount));
        }
        return newLine.reverse().toString();
    }
}
