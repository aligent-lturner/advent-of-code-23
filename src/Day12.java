import Util.ReadFileAsArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Day12 {

    private static final char GOOD = '.';
    private static final char DAMAGED = '#';
    private static final char UNKNOWN = '?';

    public static void main(String[] args) {
        List<String> lines = ReadFileAsArray.execute("./input/day-12.txt");
        long sum = getSumOfArrangements(lines);
        System.out.println("Part 1: Sum = " + sum);
        lines = lines.stream().map(Day12::unfoldLine).toList();
        sum = getSumOfArrangements(lines);
        System.out.println("Part 2: Sum = " + sum);
    }

    private static String unfoldLine(String line) {
        String[] values = line.split("\\s+");
        StringBuilder springs = new StringBuilder(values[0]);
        StringBuilder springGroups = new StringBuilder(values[1]);
        for (int i = 0; i < 4; i++) {
            springs.append('?').append(values[0]);
            springGroups.append(",").append(values[1]);
        }

        return springs + " " + springGroups;
    }

    private static long getSumOfArrangements(List<String> lines) {
        long sum = 0;
        for (String line : lines) {
            String[] values = line.split("\\s+");
            String springs = values[0];
            int[] springGroups = Stream.of(values[1].split(",")).mapToInt(Integer::parseInt).toArray();

            long possibleArrangements = getNumberOfMatches(springs, springGroups);

            sum += possibleArrangements;
        }
        return sum;
    }

    private static long getNumberOfMatches(String springs, int[] springGroups) {
        StringBuilder statesBuilder = new StringBuilder(".");
        for (int group : springGroups) {
            statesBuilder.append("#".repeat(Math.max(0, group)));
            statesBuilder.append(".");
        }
        String states = statesBuilder.toString();

        Map<Integer, Long> statesMap = new HashMap<>();
        statesMap.put(0, 1L);
        Map<Integer, Long> newMap = new HashMap<>();

        for (char c : springs.toCharArray()) {
            for (Integer state: statesMap.keySet()) {
                if (c == UNKNOWN) {
                    if (state + 1 < states.length()) {
                        newMap.put(state + 1, newMap.getOrDefault(state + 1, 0L) + statesMap.get(state));
                    }
                    if (states.charAt(state) == GOOD) {
                        newMap.put(state, newMap.getOrDefault(state, 0L) + statesMap.get(state));
                    }
                } else if (c == GOOD) {
                    if (state + 1 < states.length() && states.charAt(state + 1) == GOOD) {
                        newMap.put(state + 1, newMap.getOrDefault(state + 1, 0L) + statesMap.get(state));
                    }
                    if (states.charAt(state) == '.') {
                        newMap.put(state, newMap.getOrDefault(state, 0L) + statesMap.get(state));
                    }
                } else if (c == DAMAGED) {
                    if (state + 1 < states.length() && states.charAt(state + 1) == DAMAGED) {
                        newMap.put(state + 1, newMap.getOrDefault(state + 1, 0L) + statesMap.get(state));
                    }
                }
            }
            statesMap = newMap;
            newMap = new HashMap<>();
        }
        return statesMap.getOrDefault(states.length() - 1, 0L) +
                statesMap.getOrDefault(states.length() - 2, 0L);
    }
}
