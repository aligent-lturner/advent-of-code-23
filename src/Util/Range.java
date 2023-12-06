package Util;

import java.util.OptionalLong;

public class Range {

    private long destinationRangeStart;
    private long sourceRangeStart;
    private long length;

    public OptionalLong getDestination(long source) {
        if (source >= sourceRangeStart && source < sourceRangeStart + length) {
            return OptionalLong.of(source - (sourceRangeStart - destinationRangeStart));
        }
        return OptionalLong.empty();
    }

    public void setDestinationRangeStart(long destinationRangeStart) {
        this.destinationRangeStart = destinationRangeStart;
    }

    public void setSourceRangeStart(long sourceRangeStart) {
        this.sourceRangeStart = sourceRangeStart;
    }

    public void setLength(long length) {
        this.length = length;
    }
}
