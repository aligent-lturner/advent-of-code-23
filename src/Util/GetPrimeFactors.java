package Util;

import java.util.ArrayList;
import java.util.List;

public class GetPrimeFactors {

    public static List<Long> execute(long number) {
        List<Long> primeFactors = new ArrayList<>();

        long workingNumber = number;
        while (workingNumber % 2 == 0) {
            primeFactors.add(2L);
            workingNumber = workingNumber / 2;
        }

        for (long i = 3; i < number; i += 2) {
            if (!IsPrime.execute(i)) {
                continue;
            }
            if (workingNumber % i == 0) {
                primeFactors.add(i);
                workingNumber = workingNumber / i;
            }
        }
        return primeFactors;
    }

}
