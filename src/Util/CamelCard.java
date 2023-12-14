package Util;

public class CamelCard {

    private final String card;

    public CamelCard(char cardChar) {
        card = String.valueOf(cardChar);
    }

    public boolean isJoker() {
        return card.equals("J");
    }

    public String getCard() {
        return card;
    }
}
