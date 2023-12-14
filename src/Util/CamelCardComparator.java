package Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CamelCardComparator implements Comparator<CamelCard> {

    public static List<String> ranking;

    public static List<String> jokerRanking;

    static {
        ranking = new ArrayList<>();
        ranking.addAll(Arrays.asList("A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2", "1"));
        jokerRanking = new ArrayList<>();
        jokerRanking.addAll(Arrays.asList("A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2", "1", "J"));
    }

    private final boolean useJokers;

    public CamelCardComparator(boolean useJokers) {
        this.useJokers = useJokers;
    }

    @Override
    public int compare(CamelCard o1, CamelCard o2) {
        return getCardValue(o2.getCard()) - getCardValue(o1.getCard());
    }

    private int getCardValue(String card) {
        return useJokers ? jokerRanking.indexOf(card) : ranking.indexOf(card);
    }
}
