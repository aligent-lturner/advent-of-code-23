package Util;

import java.util.*;

public class RangeMapping {

    private RangeMapping next = null;

    private final List<Range> ranges = new ArrayList<>();

    public RangeMapping(List<String> mapLines) {
        mapLines.forEach((line) -> {
            String[] values = line.split("\\s+");
            long destinationStart = Long.parseLong(values[0]);
            long sourceStart = Long.parseLong(values[1]);
            long length = Long.parseLong(values[2]);

            Range range = new Range();
            range.setSourceRangeStart(sourceStart);
            range.setDestinationRangeStart(destinationStart);
            range.setLength(length);
            ranges.add(range);
        });
    }

    public long getFinalDestination(long source) {
        if (this.next == null) {
            return getDestination(source);
        }
        return next.getFinalDestination(getDestination(source));
    }

    private long getDestination(long source) {
        for (Range range : ranges) {
            OptionalLong destination = range.getDestination(source);
            if (destination.isPresent()) {
                return destination.getAsLong();
            }
        }
        return Long.MAX_VALUE;
    }

    public void setNext(RangeMapping next) {
        this.next = next;
    }
}
