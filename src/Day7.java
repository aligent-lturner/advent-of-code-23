import Util.CamelCardsHand;
import Util.CamelCardsHandComparator;
import Util.ReadFileAsArray;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

public class Day7 {

    public static void main(String[] args) {
        ArrayList<String> lines = ReadFileAsArray.execute("./input/day-7.txt");
        Map<CamelCardsHand, Long> handsToBids = new TreeMap<>(new CamelCardsHandComparator(false));
        Map<CamelCardsHand, Long> jokerHandsToBids = new TreeMap<>(new CamelCardsHandComparator(true));
        for (String line: lines) {
            String[] values = line.split("\\s+");
            handsToBids.put(new CamelCardsHand(values[0]), Long.valueOf(values[1]));
        }
        jokerHandsToBids.putAll(handsToBids);

        System.out.println("Part 1 Total Winnings = " + getWinnings(handsToBids));
        System.out.println("Part 2 Total Winnings = " + getWinnings(jokerHandsToBids));
    }

    private static long getWinnings(Map<CamelCardsHand, Long> handsToBids) {
        AtomicLong winnings = new AtomicLong();
        AtomicLong rank = new AtomicLong(1);
        handsToBids.keySet().forEach(key -> {
            long bid = handsToBids.get(key);
            winnings.addAndGet((bid * rank.get()));
            rank.addAndGet(1);
        });
        return winnings.get();
    }
}
