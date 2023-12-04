package Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
        Pattern pattern = Pattern.compile(getPattern());
        ArrayList<String> matches = new ArrayList<>();
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            matches.add(matcher.group(1));
        }
        StringBuilder result = new StringBuilder();
        for (String match: matches) {
            result.append(digitMap.getOrDefault(match, match));
        }
        return result.toString();
    }

    private static String getPattern() {
        StringJoiner joiner = new StringJoiner("|");
        for(String digit: digitStrings) {
            joiner.add(digit);
        }
        return "(?=(" + joiner + "|[1-9])).";
    }
}
