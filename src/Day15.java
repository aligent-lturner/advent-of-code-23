import Util.ReadFileAsArray;

import java.util.Arrays;
import java.util.List;

public class Day15 {

    public static void main(String[] args) {
        String sequence = ReadFileAsArray.execute("./input/day-15.txt").getFirst();
        List<String> steps = Arrays.asList(sequence.split(","));
        long sum = steps.stream().mapToLong(Day15::getHash).sum();
        System.out.println("Part 1: " + sum);
    }

    private static long getHash(String instruction) {
        long value = 0;
        for (char c: instruction.toCharArray()) {
            value += (int) c;
            value *= 17;
            value = value % 256;
        }
        return value;
    }
}
