package Util;

import java.util.*;

public class RangeMapping {

    private final Set<RangePair> rangePairs = new TreeSet<>();

    private RangeMapping next;
    private final String name;

    public RangeMapping(List<String> mapLines, RangeMapping next, String name) {
        this.name = name;
        this.next = next;
        mapLines.forEach((line) -> {
            String[] values = line.split("\\s+");
            long destinationStart = Long.parseLong(values[0]);
            long sourceStart = Long.parseLong(values[1]);
            long length = Long.parseLong(values[2]);

            Range sourceRange = new Range(sourceStart, sourceStart + length - 1);
            Range destinationRange = new Range(destinationStart, destinationStart + length - 1);
            rangePairs.add(new RangePair(sourceRange, destinationRange));
        });
    }

    public void setNext(RangeMapping next) {
        this.next = next;
    }

    public boolean isValidRange(Range boundingRange) {
        if (next == null) {
            for (RangePair rangePair : rangePairs) {
                if (rangePair.hasValueInRange(boundingRange)) {
                    return true;
                }
            }
            return false;
        }

        List<RangePair> filteredList = rangePairs.stream().
                filter(rangePair -> rangePair.hasValueInRange(boundingRange)).
                toList();
        return filteredList.stream().
                anyMatch(rangePair -> next.isValidRange(generateNewBoundingRange(rangePair, boundingRange)));
    }

    private Range generateNewBoundingRange(RangePair rangePair, Range boundingRange) {
        Range intersection = rangePair.getDestinationRange().getIntersection(boundingRange);
        long sourceOffset = rangePair.getDestinationRange().getStartValue() - rangePair.getSourceRange().getStartValue();
        return new Range(intersection.getStartValue() - sourceOffset, intersection.getEndValue() - sourceOffset);
    }

}
