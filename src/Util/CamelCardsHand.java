package Util;

import java.util.ArrayList;
import java.util.List;

public class CamelCardsHand {

    private final List<CamelCard> cards;

    public CamelCardsHand(String handString) {
        cards = new ArrayList<>();
        for (char cardChar: handString.toCharArray()) {
            cards.add(new CamelCard(cardChar));
        }
    }

    public List<CamelCard> getCards() {
        return cards;
    }

    public CamelCard getCardAt(int index) {
        return cards.size() > index ? cards.get(index) : null;
    }
}
