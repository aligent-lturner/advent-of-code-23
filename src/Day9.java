import Util.OasisSequence;
import Util.ReadFileAsArray;

import java.util.List;

public class Day9 {

    public static void main(String[] args) {
        List<String> lines = ReadFileAsArray.execute("./input/day-9.txt");
        List<Long> nextValues = getNextValues(lines);
        long sum = getSum(nextValues);
        System.out.println("Part 1 - sum = " + sum);
    }

    private static List<Long> getNextValues(List<String> lines) {
        return lines.stream().map(OasisSequence::new).map(OasisSequence::getNextValue).toList();
    }

    private static long getSum(List<Long> nextValues) {
        return nextValues.stream().mapToLong(Long::valueOf).sum();
    }
}
