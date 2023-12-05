import Util.ReadFileAsArray;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Day4 {

    public static void main(String[] args) {
        ArrayList<String> lines = ReadFileAsArray.execute("./input/day-4.txt");
        AtomicInteger sum = new AtomicInteger();
        lines.forEach((line) -> sum.addAndGet(getScore(line)));
        System.out.println(sum.get());
    }

    private static int getScore(String line) {
        String numbers = line.substring(line.indexOf(':') + 1);
        String[] numberSets = numbers.split("\\|");
        String winningNumbers = numberSets[0].trim();
        String playedNumbers = numberSets[1].trim();
        Set<Integer> winningSet = getNumberSet(winningNumbers);
        Set<Integer> playedSet = getNumberSet(playedNumbers);
        winningSet.retainAll(playedSet);
        int power = winningSet.size() - 1;
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
