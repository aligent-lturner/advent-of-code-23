package Util;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CamelCardsHandComparator implements Comparator<CamelCardsHand> {

    public static final int FIVE_OF_A_KIND = 7;
    public static final int FOUR_OF_A_KIND = 6;
    public static final int FULL_HOUSE = 5;
    public static final int THREE_OF_A_KIND = 4;
    public static final int TWO_PAIR = 3;
    public static final int ONE_PAIR = 2;
    public static final int HIGH_CARD = 1;

    private final boolean useJokers;
    private final CamelCardComparator camelCardComparator;

    public CamelCardsHandComparator(boolean useJokers) {
        this.useJokers = useJokers;
        camelCardComparator = new CamelCardComparator(useJokers);
    }

    @Override
    public int compare(CamelCardsHand o1, CamelCardsHand o2) {
        int comparison = getHandRank(o1) - getHandRank(o2);
        if (comparison != 0) {
            return comparison;
        }
        for (int i = 0; i < o1.getCards().size(); i++) {
            comparison = camelCardComparator.compare(o1.getCardAt(i), o2.getCardAt(i));
            if (comparison != 0) {
                return comparison;
            }
        }
        return 0;
    }

    private int getHandRank(CamelCardsHand hand) {
        AtomicInteger maxCount = new AtomicInteger();
        AtomicInteger numPairs = new AtomicInteger();
        AtomicInteger numJokers = new AtomicInteger();

        if (useJokers) {
            hand.getCards().forEach(card -> {
                if (card.isJoker()) {
                    numJokers.addAndGet(1);
                }
            });
        }

        List<String> ranking = useJokers ? CamelCardComparator.jokerRanking : CamelCardComparator.ranking;
        ranking.stream().filter(cardRank -> !useJokers || !cardRank.equals("J")).forEach(cardRank -> {
            AtomicInteger count = new AtomicInteger();
            hand.getCards().forEach(card -> {
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

        if (useJokers) {
            maxCount.addAndGet(numJokers.get());
            numPairs.addAndGet(-1 * numJokers.get());
        }

        return switch (maxCount.get()) {
            case 5 -> FIVE_OF_A_KIND;
            case 4 -> FOUR_OF_A_KIND;
            case 3 -> (numPairs.get() > 0) ? FULL_HOUSE : THREE_OF_A_KIND;
            case 2 -> (numPairs.get() > 1) ? TWO_PAIR : ONE_PAIR;
            default -> HIGH_CARD;
        };
    }
}
