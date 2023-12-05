
import Util.Coordinates;
import Util.CoordinatesRange;
import Util.ReadFileAsArray;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Day3 {

    public static final Set<Character> NON_SYMBOLS = Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.');
    public static final Set<Character> NUMBERS = Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9', '0');

    public static void main(String[] args) {
        ArrayList<String> lines = ReadFileAsArray.execute("./input/day-3.txt");
        ArrayList<Coordinates> symbolCoordinates = new ArrayList<>();
        ArrayList<CoordinatesRange> numberCoordinates = new ArrayList<>();
        for (int i = 1; i <= lines.size(); i++) {
            String line = lines.get(i-1);
            symbolCoordinates.addAll(getSymbolCoordinates(i, line));
        }
        for (int i = 1; i <= lines.size(); i++) {
            String line = lines.get(i-1);
            numberCoordinates.addAll(getNumberCoordinates(i, line));
        }
        System.out.println(getAdjacentNumbersSum(symbolCoordinates, numberCoordinates));
    }

    private static ArrayList<Coordinates> getSymbolCoordinates(int y, String line) {
        ArrayList<Coordinates> symbolCoordinates = new ArrayList<>();
        char[] charArray = line.toCharArray();
        for (int i = 1; i <= charArray.length; i++) {
            if (!NON_SYMBOLS.contains(charArray[i-1])) {
                Coordinates coords = new Coordinates();
                coords.setX(i);
                coords.setY(y);
                symbolCoordinates.add(coords);
            }
        }
        return symbolCoordinates;
    }

    private static int getAdjacentNumbersSum(
            ArrayList<Coordinates> symbolCoordinates,
            ArrayList<CoordinatesRange> numberCoordinates
    ) {
        AtomicInteger sum = new AtomicInteger();
        numberCoordinates.forEach((n) -> symbolCoordinates.forEach((s) -> {
            if (n.isAdjacent(s)) {
                sum.addAndGet(n.getNumber());
            }
        }));
        return sum.get();
    }

    private static ArrayList<CoordinatesRange> getNumberCoordinates(int y, String line) {
        ArrayList<CoordinatesRange> numberCoordinates = new ArrayList<>();
        char[] charArray = line.toCharArray();
        for (int i = 1; i <= charArray.length; i++) {
            if (NUMBERS.contains(charArray[i-1])) {
                CoordinatesRange coordinatesRange = new CoordinatesRange();
                ArrayList<Coordinates> range = new ArrayList<>();
                StringBuilder numberString = new StringBuilder();
                do {
                    numberString.append(charArray[i - 1]);
                    Coordinates coordinates = new Coordinates();
                    coordinates.setX(i);
                    coordinates.setY(y);
                    range.add(coordinates);
                    i++;
                } while (i <= charArray.length && NUMBERS.contains(charArray[i-1]));
                coordinatesRange.setRange(range);
                coordinatesRange.setNumber(Integer.parseInt(numberString.toString()));
                numberCoordinates.add(coordinatesRange);
            }
        }
        return numberCoordinates;
    }
}
