package Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CamelCardsHand implements Comparable<CamelCardsHand> {

    public static final int FIVE_OF_A_KIND = 7;
    public static final int FOUR_OF_A_KIND = 6;
    public static final int FULL_HOUSE = 5;
    public static final int THREE_OF_A_KIND = 4;
    public static final int TWO_PAIR = 3;
    public static final int ONE_PAIR = 2;
    public static final int HIGH_CARD = 1;

    private final List<CamelCard> cards;

    public CamelCardsHand(String handString) {
        cards = new ArrayList<>();
        for (char cardChar: handString.toCharArray()) {
            cards.add(new CamelCard(cardChar));
        }
    }

    public int getHandRank() {
        AtomicInteger maxCount = new AtomicInteger();
        AtomicInteger numPairs = new AtomicInteger();

        CamelCard.ranking.forEach(cardRank -> {
            AtomicInteger count = new AtomicInteger();
            cards.forEach(card -> {
                if (card.getCard().equals(cardRank)) {
                    count.addAndGet(1);
                }
            });
            if (count.get() > maxCount.get()) {
                maxCount.set(count.get());
            }
            if (count.get() == 2) {
                numPairs.addAndGet(1);
            }
        });

        return switch (maxCount.get()) {
            case 5 -> FIVE_OF_A_KIND;
            case 4 -> FOUR_OF_A_KIND;
            case 3 -> (numPairs.get() > 0) ? FULL_HOUSE : THREE_OF_A_KIND;
            case 2 -> (numPairs.get() > 1) ? TWO_PAIR : ONE_PAIR;
            default -> HIGH_CARD;
        };
    }

    public CamelCard getCardAt(int index) {
        return cards.size() > index ? cards.get(index) : null;
    }

    @Override
    public int compareTo(CamelCardsHand o) {
        int comparison = Comparator.comparingInt(CamelCardsHand::getHandRank).compare(this, o);
        if (comparison != 0) {
            return comparison;
        }
        for (int i = 0; i < cards.size(); i++) {
            comparison = getCardAt(i).compareTo(o.getCardAt(i));
            if (comparison != 0) {
                return comparison;
            }
        }
        return 0;
    }
}
