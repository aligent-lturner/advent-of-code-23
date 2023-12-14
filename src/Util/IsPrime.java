package Util;

public class IsPrime {

    public static boolean execute(long number) {
        if (number <= 1) {
            return false;
        }

        for (long i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
