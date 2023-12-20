package Util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TransposeLines {

    public static List<String> execute(List<String> lines) {
        List<List<Character>> transposed = IntStream.range(0, lines.getFirst().length()).
                mapToObj(i -> lines.stream().map(line -> line.charAt(i)).collect(Collectors.toList())).
                toList();
        return transposed.stream().map(charList ->  {
            StringBuilder sb = new StringBuilder();
            for (Character character : charList) {
                sb.append(character);
            }
            return sb.toString();
        }).toList();
    }
}
