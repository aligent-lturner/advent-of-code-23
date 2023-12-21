import Util.Lens;
import Util.LensBox;
import Util.ReadFileAsArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day15 {

    private static final char INSERT = '=';
    private static final char REMOVE = '-';

    public static void main(String[] args) {
        String sequence = ReadFileAsArray.execute("./input/day-15.txt").getFirst();
        List<String> steps = Arrays.asList(sequence.split(","));
        long sum = steps.stream().mapToLong(Day15::getHash).sum();
        System.out.println("Part 1: " + sum);
        // part 2

        // initialise boxes
        List<LensBox> boxes = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            boxes.add(new LensBox());
        }
        for (String instruction: steps) {
            handleInstruction(instruction, boxes);
        }
        long focusingPower = getTotalFocusingPower(boxes);
        System.out.println("Part 2: focusing power = " + focusingPower);
    }

    private static int getHash(String instruction) {
        int value = 0;
        for (char c: instruction.toCharArray()) {
            value += c;
            value *= 17;
            value = value % 256;
        }
        return value;
    }

    private static void handleInstruction(String instruction, List<LensBox> boxes) {
        String label;
        if (isInsert(instruction)) {
            label = instruction.substring(0, instruction.indexOf(INSERT));
        } else {
            label = instruction.substring(0, instruction.indexOf(REMOVE));
        }

        int boxIndex = getHash(label);
        LensBox box = boxes.get(boxIndex);

        if (isInsert(instruction)) {
            int focalLength = Integer.parseInt(instruction.substring(instruction.indexOf(INSERT) + 1));
            Lens lens = new Lens(label, focalLength);
            box.insertLens(lens);
        } else {
            box.removeLens(label);
        }
    }

    private static boolean isInsert(String instruction) {
        return (instruction.indexOf(INSERT) != -1);
    }

    private static long getTotalFocusingPower(List<LensBox> boxes) {
        long sum = 0;
        for (int i = 1; i <= boxes.size(); i++) {
            sum += i * boxes.get(i-1).getFocusingPower();
        }
        return sum;
    }
}
