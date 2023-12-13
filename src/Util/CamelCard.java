package Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CamelCard implements Comparable<CamelCard> {

    public static List<String> ranking;

    private final String card;

    public CamelCard(char cardChar) {
        card = String.valueOf(cardChar);
    }

    static {
        ranking = new ArrayList<>();
        ranking.addAll(Arrays.asList("A", "K", "Q", "J", "T", "9", "8", "7", "6", "5", "4", "3", "2", "1"));
    }


    @Override
    public int compareTo(CamelCard o) {
        return Comparator.comparingInt(CamelCard::getCardValue).reversed().compare(this, o);
    }

    public String getCard() {
        return card;
    }

    public int getCardValue() {
        return ranking.indexOf(card);
    }
}
