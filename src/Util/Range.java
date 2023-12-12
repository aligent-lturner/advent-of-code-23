package Util;

import java.util.Comparator;

public class Range implements Comparable<Range> {

    private final long startValue;
    private final long endValue;

    public Range(long startValue, long endValue) {
        this.startValue = startValue;
        this.endValue = endValue;
    }

    public long getStartValue() {
        return startValue;
    }

    public long getEndValue() {
        return this.endValue;
    }

    public boolean intersects(Range other) {
        if (other.startValue <= this.startValue) {
            return (other.endValue >= this.startValue);
        } else {
            return other.startValue <= this.endValue;
        }
    }

    public Range getIntersection(Range other) {
        return new Range(Math.max(startValue, other.getStartValue()), Math.min(endValue, other.getEndValue()));
    }

    public boolean isInvalid() {
        return endValue < startValue;
    }

    @Override
    public int compareTo(Range o) {
        return Comparator.comparingLong(Range::getStartValue).compare(this, o);
    }

    public String toString() {
        return String.format("%010d", startValue) + " - " + String.format("%010d", endValue);
    }
}
