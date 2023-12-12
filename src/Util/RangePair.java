package Util;

import java.util.OptionalLong;

public class RangePair implements Comparable<RangePair> {

    private final Range sourceRange;
    private final Range destinationRange;

    public RangePair(Range sourceRange, Range destinationRange) {
        this.sourceRange = sourceRange;
        this.destinationRange = destinationRange;
    }

    public Range getSourceRange() {
        return sourceRange;
    }

    public Range getDestinationRange() {
        return destinationRange;
    }

    public boolean hasValueInRange(Range boundingRange) {
        return destinationRange.intersects(boundingRange);
    }

    public OptionalLong getSourceValueFromDestination(long destination) {
        if (hasValueInRange(new Range(destination, destination))) {
            return OptionalLong.of((destination - destinationRange.getStartValue()) + sourceRange.getStartValue());
        }
        return OptionalLong.empty();
    }

    @Override
    public int compareTo(RangePair o) {
        return this.destinationRange.compareTo(o.destinationRange);
    }

    public String toString() {
        return destinationRange + " , " + sourceRange;
    }
}
