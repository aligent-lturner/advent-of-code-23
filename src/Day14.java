import Util.ReadFileAsArray;
import Util.TransposeLines;

import java.util.ArrayList;
import java.util.List;

public class Day14 {

    private static final char SQUARE_ROCK = '#';
    private static final char ROUND_ROCK = 'O';
    private static final char EMPTY = '.';

    public static void main(String[] args) {
        List<String> lines = ReadFileAsArray.execute("./input/day-14.txt");
        // transpose lines, as easier to deal with.
        lines = TransposeLines.execute(lines);
        List<String> newLines = new ArrayList<>();
        for (String line : lines) {
            // find position of square rocks
            List<Integer> squareRockIndices = findSquareRocks(line);
            String shiftedLine = rollLeft(line, squareRockIndices);
            newLines.add(shiftedLine);
        }
        newLines = TransposeLines.execute(newLines);

        long load = getLoad(newLines);
        System.out.println("Part 1: Load = " + load);
    }

    private static long getLoad(List<String> newLines) {
        long load = 0;
        for (int i = 0; i < newLines.size(); i++) {
            String line = newLines.get(i);
            long lineLoad = newLines.size() - i;
            long roundRockCount = line.chars().filter(ch -> ch == ROUND_ROCK).count();
            load += roundRockCount * lineLoad;
        }
        return load;
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
