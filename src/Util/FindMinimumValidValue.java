package Util;

import java.util.OptionalLong;

public class FindMinimumValidValue {

    public static OptionalLong execute(RangeMapping rangeMapping) {
        Range boundingRange = new Range(0, Long.MAX_VALUE);
        return binarySearch(rangeMapping, boundingRange);
    }

    private static OptionalLong binarySearch(RangeMapping rangeMapping, Range boundingRange) {
        if (rangeMapping.isValidRange(boundingRange)) {
            if (boundingRange.getStartValue() == boundingRange.getEndValue()) {
                return OptionalLong.of(boundingRange.getStartValue());
            }
            OptionalLong leftResult = binarySearch(rangeMapping, getLeftRange(boundingRange));
            if (leftResult.isPresent()) {
                return leftResult;
            }
            return binarySearch(rangeMapping, getRightRange(boundingRange));
        } else {
            return OptionalLong.empty();
        }
    }

    private static Range getLeftRange(Range boundingRange) {
        return new Range(boundingRange.getStartValue(), getMidpoint(boundingRange));
    }

    private static Range getRightRange(Range boundingRange) {
        return new Range(getMidpoint(boundingRange) + 1, boundingRange.getEndValue());
    }

    private static long getMidpoint(Range boundingRange) {
        return ((boundingRange.getEndValue() - boundingRange.getStartValue()) / 2) + boundingRange.getStartValue();
    }
}
