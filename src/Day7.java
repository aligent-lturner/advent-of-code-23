import Util.CamelCardsHand;
import Util.ReadFileAsArray;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

public class Day7 {

    public static void main(String[] args) {
        ArrayList<String> lines = ReadFileAsArray.execute("./input/day-7.txt");
        Map<CamelCardsHand, Long> handsToBids = new TreeMap<>();
        for (String line: lines) {
            String[] values = line.split("\\s+");
            handsToBids.put(new CamelCardsHand(values[0]), Long.valueOf(values[1]));
        }
        AtomicLong winnings = new AtomicLong();
        AtomicLong rank = new AtomicLong(1);
        handsToBids.keySet().forEach(key -> {
            long bid = handsToBids.get(key);
            winnings.addAndGet((bid * rank.get()));
            rank.addAndGet(1);
        });
        System.out.println("Total Winnings = " + winnings.get());
    }
}
