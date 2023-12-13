import Util.ReadFileAsArray;

import java.util.ArrayList;
import java.util.Arrays;

public class Day6 {

    public static void main(String[] args) {
        ArrayList<String> lines = ReadFileAsArray.execute("./input/day-6.txt");
        String times = lines.get(0);
        String distances = lines.get(1);
        String[] timeValues = times.replace("Time:", "").trim().split("\\s+");
        String[] distanceValues = distances.replace("Distance:", "").trim().split("\\s+");
        long[] timesAsLongs = Arrays.stream(timeValues).mapToLong(Long::parseLong).toArray();
        long[] distancesAsLongs = Arrays.stream(distanceValues).mapToLong(Long::parseLong).toArray();

        System.out.println(getProductOfWays(timesAsLongs, distancesAsLongs));
    }

    private static long getProductOfWays(long[] times, long[] distances) {
        long total = 1;
        for (int i = 0; i < times.length; i++) {
            long ways = calculateDistinctWays(times[i], distances[i]);
            total *= ways;
        }
        return total;
    }

    private static long calculateDistinctWays(long time, long distanceRecord) {
        /*
        Let x be the number of milliseconds the button is held for
        Let y be the distance travelled
        Let z be the current record = distanceRecord
        Let T be the total time for the race

        y = (T - x)x = Tx - x^2
        We want to find values where y > z, so we want to find solutions of y - z = 0
        This will give us two roots, and the values we are after are the integer values between them.

        y - z = 0
        => Tx - x^2 - z = 0
        This is a quadratic equation of the form ax^2 + bx + c = 0, with a = -1, b = T, c = -z
        Roots of the equation are therefore:
        -T/-2 + sqrt(T^2 - 4z)/-2 and -T/-2 - sqrt(T^2 -4z)/-2
         */
        double component = Math.sqrt((double)((time * time) - (4 * distanceRecord))) / (-2d);
        double root1 = ((double)(-time) / -2) + component;
        double root2 = ((double)(-time) / -2) - component;
        long maxTime = (long) Math.floor(Math.max(root1, root2));
        maxTime = adjustTimeForEdgeCase(maxTime, time, distanceRecord, true);
        long minTime = (long) Math.ceil(Math.min(root1, root2));
        minTime = adjustTimeForEdgeCase(minTime, time, distanceRecord, false);
        return maxTime - minTime + 1;
    }

    private static long adjustTimeForEdgeCase(long time, long raceTime, long record, boolean isMax) {
        long distance = (raceTime - time) * time;
        if (distance == record) {
            time = time + (isMax ? -1 : 1);
        }
        return time;
    }
}
