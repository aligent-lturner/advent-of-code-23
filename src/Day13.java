import Util.LevenshteinDistance;
import Util.ReadFileAsArray;
import Util.TransposeLines;

import java.util.ArrayList;
import java.util.List;

public class Day13 {

    public static void main(String[] args) {
        List<String> lines = ReadFileAsArray.execute("./input/day-13.txt");
        List<List<String>> patterns = getPatterns(lines);
        long sum = 0;
        for (List<String> pattern : patterns) {
            long horizontalSymmetryLines = countLinesAboveSymmetryLine(pattern, false);
            if (horizontalSymmetryLines > 0) {
                sum += (horizontalSymmetryLines * 100);
            } else {
                sum += countLinesAboveSymmetryLine(TransposeLines.execute(pattern), false);
            }
        }
        System.out.println("Part 1: sum = " + sum);
        sum = 0;
        for (List<String> pattern : patterns) {
            long horizontalSymmetryLines = countLinesAboveSymmetryLine(pattern, true);
            if (horizontalSymmetryLines > 0) {
                sum += (horizontalSymmetryLines * 100);
            } else {
                sum += countLinesAboveSymmetryLine(TransposeLines.execute(pattern), true);
            }
        }
        System.out.println("Part 1: sum = " + sum);
    }

    private static List<List<String>> getPatterns(List<String> lines) {
        List<List<String>> patterns = new ArrayList<>();
        List<String> pattern = new ArrayList<>();
        for (String line: lines) {
            if (line.isBlank()) {
                if (!pattern.isEmpty()) {
                    patterns.add(pattern);
                }
                pattern = new ArrayList<>();
                continue;
            }
            pattern.add(line);
        }
        if (!pattern.isEmpty()) {
            patterns.add(pattern);
        }
        return patterns;
    }

    private static long countLinesAboveSymmetryLine(List<String> pattern, boolean offByOne) {
        long linesSum = 0;
        // assume there has to be at least 1 row on each side
        for (int i = 1; i < pattern.size(); i++) {
            List<String> linesAbove = pattern.subList(0, i).reversed();
            List<String> linesBelow = pattern.subList(i, pattern.size());
            if (presentLinesAreEqual(linesAbove, linesBelow, offByOne)) {
                linesSum += linesAbove.size();
            }
        }

        return linesSum;
    }

    private static boolean presentLinesAreEqual(List<String> linesAbove, List<String> linesBelow, boolean offByOne) {
        int totalDistance = 0;
        for (int i = 0; i < Math.min(linesAbove.size(), linesBelow.size()); i++) {
            if (offByOne) {
                totalDistance += LevenshteinDistance.computeLevenshteinDistance(linesAbove.get(i), linesBelow.get(i));
            } else {
                if (!linesAbove.get(i).equals(linesBelow.get(i))) {
                    return false;
                }
            }
        }
        if (offByOne) {
            return totalDistance == 1;
        }
        return true;
    }
}
