import Util.GetPossibleSpringArrangements;
import Util.ReadFileAsArray;

import java.util.List;
import java.util.stream.Stream;

public class Day12 {

    private static final char UNKNOWN = '?';

    public static void main(String[] args) {
        List<String> lines = ReadFileAsArray.execute("./input/day-12.txt");
        long sum = 0;
        for (String line : lines) {
            String[] values = line.split("\\s+");
            String springs = values[0];
            int[] springGroups = Stream.of(values[1].split(",")).mapToInt(Integer::parseInt).toArray();
            List<String> allArrangements = GetPossibleSpringArrangements.execute("", springs.length(), springGroups);
            long possibleArrangements = allArrangements.stream().filter(arrangement -> matchesPattern(springs, arrangement)).count();
            sum += possibleArrangements;
        }
        System.out.println("Part 1: Sum = " + sum);
    }

    private static boolean matchesPattern(String value, String requiredPattern) {
        if (value.length() != requiredPattern.length()) {
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            char nextChar = value.charAt(i);
            char requiredChar = requiredPattern.charAt(i);
            if (nextChar != requiredChar) {
                if (nextChar != UNKNOWN) {
                    return false;
                }
            }
        }
        return true;
    }
}
