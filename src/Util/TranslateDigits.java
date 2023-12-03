package Util;

import java.util.HashMap;
import java.util.Map;

public class TranslateDigits {

    private static final Map<String, String> digitMap = new HashMap<>();
    private static final String[] digitStrings = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    static
    {
        digitMap.put("one", "1");
        digitMap.put("two", "2");
        digitMap.put("three", "3");
        digitMap.put("four", "4");
        digitMap.put("five", "5");
        digitMap.put("six", "6");
        digitMap.put("seven", "7");
        digitMap.put("eight", "8");
        digitMap.put("nine", "9");
    }

    public static String execute(String input) {
        String result =  input;
        for (String digit: digitStrings) {
            result = result.replaceAll(digit, digitMap.get(digit));
        }
        return result;
    }
}
