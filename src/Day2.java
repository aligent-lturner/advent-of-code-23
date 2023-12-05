
import Util.RGBValues;
import Util.ReadFileAsArray;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2 {

    private final static int MAX_RED = 12;
    private final static int MAX_GREEN = 13;
    private final static int MAX_BLUE = 14;

    public static void main(String[] args) {
        // read file into array
        ArrayList<String> lines = ReadFileAsArray.execute("./input/day-2.txt");
        System.out.println(getSum(lines));

    }

    private static int getSum(ArrayList<String> lines) {
        // split lines into game id + array of values
        AtomicInteger sum = new AtomicInteger();
        lines.forEach((line) -> {
            String[] idAndData = line.split(":");
            int id = Integer.parseInt(idAndData[0].replace("Game ", ""));
            String[] games = idAndData[1].split(";");
            boolean allPossible = true;
            for (String game : games) {
                RGBValues values = getRGBValues(game);
                if (!isPossible(values)) {
                    allPossible = false;
                }
            }
            if (allPossible) {
                System.out.println("Game " + id + " is possible");
                sum.addAndGet(id);
            }
        });
        return sum.get();
    }

    private static RGBValues getRGBValues(String input) {
        String[] values = input.split(",");
        RGBValues rgbValues = new RGBValues();
        for (String value : values) {
            String[] numberAndColour = value.trim().split(" ");
            int number = Integer.parseInt(numberAndColour[0]);
            String colour = numberAndColour[1];
            if (Objects.equals(colour, "blue")) {
                rgbValues.setBlue(number);
            } else if (Objects.equals(colour, "green")) {
                rgbValues.setGreen(number);
            } else if (Objects.equals(colour, "red")) {
                rgbValues.setRed(number);
            }
        }
        return rgbValues;
    }

    private static boolean isPossible(RGBValues input) {
        if (input.getRed() > MAX_RED) {
            return false;
        }
        if (input.getGreen() > MAX_GREEN) {
            return false;
        }
        return input.getBlue() <= MAX_BLUE;
    }

}
