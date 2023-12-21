import Util.ReadFileAsArray;
import Util.RockCycle;

import java.util.List;

public class Day14 {


    public static void main(String[] args) {
        List<String> lines = ReadFileAsArray.execute("./input/day-14.txt");
        List<String> newLines = RockCycle.rollNorth(lines);

        long load = getLoad(newLines);
        System.out.println("Part 1: Load = " + load);

        newLines = RockCycle.runCycles(lines, 1000000000);
        load = getLoad(newLines);
        System.out.println("Part 2: Load = " + load);
    }

    private static long getLoad(List<String> newLines) {
        long load = 0;
        for (int i = 0; i < newLines.size(); i++) {
            String line = newLines.get(i);
            long lineLoad = newLines.size() - i;
            long roundRockCount = line.chars().filter(ch -> ch == RockCycle.ROUND_ROCK).count();
            load += roundRockCount * lineLoad;
        }
        return load;
    }
}
