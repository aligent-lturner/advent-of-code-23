package Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetPossibleSpringArrangements {

    public static List<String> execute(String prefix, int length, int[] groups) {
        List<String> arrangements = new ArrayList<>();
        if (groups.length == 1) {
            int minLength = groups[0];
            int numBlanks = length - minLength;
            for (int i = 0; i <= numBlanks; i++) {
                String sb = prefix + ".".repeat(i) +
                        "#".repeat(minLength) +
                        ".".repeat(numBlanks - i);
                arrangements.add(sb);
            }
        } else {
            int[] remainingGroups = Arrays.copyOfRange(groups, 1, groups.length);
            int sumOfPatternValues = Arrays.stream(remainingGroups).sum();
            int numGaps = Math.max(remainingGroups.length - 1, 0) + 1;
            int maxLengthRemaining = length - (sumOfPatternValues + numGaps);
            int minLengthRequired = groups[0];

            for (int i = minLengthRequired; i <= maxLengthRemaining; i++) {
                List<String> groupArrangements = execute(prefix, i, Arrays.copyOfRange(groups, 0, 1));
                for (String groupValue: groupArrangements) {
                    if (groupValue.endsWith(".")) {
                        arrangements.addAll(execute(groupValue, length - i, remainingGroups));
                    } else {
                        arrangements.addAll(execute(groupValue + ".", length - 1 - i, remainingGroups));
                    }
                }
            }
        }

        return arrangements.stream().distinct().toList();
    }
}
