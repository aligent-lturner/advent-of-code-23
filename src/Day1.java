import Util.ReadFileAsArray;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Day1 {

    public static void main(String[] args) {
        ArrayList<String> fileData = ReadFileAsArray.execute("./input/day-1.txt");
        System.out.println("Sum: " + getSum(fileData));
    }

    private static int getSum(ArrayList<String> lines) {
        AtomicInteger sum = new AtomicInteger();
        lines.forEach((line) -> {
            String digits = line.replaceAll("[^0-9]", "");
            char[] digitChars = digits.toCharArray();
            char first = digitChars[0];
            char last = digitChars[digitChars.length - 1];
            int value = Integer.parseInt(String.valueOf(new char[] {first, last}));
            sum.addAndGet(value);
        });
        return sum.get();
    }
}
