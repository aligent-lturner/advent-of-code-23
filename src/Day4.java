import Util.ReadFileAsArray;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day4 {

    public static void main(String[] args) {
        ArrayList<String> lines = ReadFileAsArray.execute("./input/day-4.txt");
        AtomicInteger sum = new AtomicInteger();
        lines.forEach((line) -> sum.addAndGet(getScore(line)));
        System.out.println(sum.get());

        Map<Integer, Integer> cardCounts = new HashMap<>();
        // initialise count of cards
        for (int i = 1; i <= lines.size(); i++) {
            cardCounts.put(i, 1);
        }
        for (int i = 1; i <= lines.size(); i++) {
            String line = lines.get(i-1);
            int cardCount = cardCounts.get(i);
            int winningNumberCount = getWinningNumberCount(line);
            for (int j = i + 1; j <= lines.size() && j <= i + winningNumberCount; j++) {
                cardCounts.put(j, cardCounts.get(j) + cardCount);
            }
        }
        AtomicInteger totalCards = new AtomicInteger();
        cardCounts.forEach((k, v) -> totalCards.addAndGet(v));
        System.out.println(totalCards.get());
    }

    private static int getWinningNumberCount(String line) {
        String numbers = line.substring(line.indexOf(':') + 1);
        String[] numberSets = numbers.split("\\|");
        String winningNumbers = numberSets[0].trim();
        String playedNumbers = numberSets[1].trim();
        Set<Integer> winningSet = getNumberSet(winningNumbers);
        Set<Integer> playedSet = getNumberSet(playedNumbers);
        winningSet.retainAll(playedSet);
        return winningSet.size();
    }

    private static int getScore(String line) {
        int power = getWinningNumberCount(line) - 1;
        return (int)Math.pow(2, power);
    }

    private static Set<Integer> getNumberSet(String numbers) {
        String[] numberList = numbers.split("\\s+");
        Set<Integer> numberSet = new HashSet<>();
        for (String number : numberList) {
            numberSet.add(Integer.valueOf(number));
        }
        return numberSet;
    }
}
