package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OasisSequence {

    private final List<Long> sequence;

    private final List<List<Long>> history = new ArrayList<>();

    public OasisSequence(String sequence) {
        this.sequence = Stream.of(sequence.split("\\s+")).map(Long::parseLong).collect(Collectors.toList());
        generateHistory();
    }

    private void generateHistory() {
        List<Long> previous = sequence;
        while (notAllZero(previous)) {
            List<Long> next = new ArrayList<>();
            for (int i = 1; i < previous.size(); i++) {
                next.add(previous.get(i) - previous.get(i - 1));
            }
            history.add(next);
            previous = next;
        }
    }

    public long getNextValue() {
        // get 2nd to last history line (last one is all zeros)
        if (history.size() < 2) {
            return sequence.getLast();
        }
        long nextValue = 0;
        for (int i = history.size() - 2; i >= 0; i--) {
            List<Long> previous = history.get(i);
            nextValue = nextValue + previous.getLast();
        }
        return nextValue + sequence.getLast();
    }

    private boolean notAllZero(List<Long> previous) {
        return !previous.stream().allMatch(value -> value == 0L);
    }

}
