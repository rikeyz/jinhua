package org.rikey.jinhua.pojo;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String userName;

    private List<Card> cards;

    private int chip;

    public Player(String userName, int chip) {
        this.userName = userName;
        this.chip = chip;
        this.cards = new ArrayList<Card>();
    }

    public void acceptCard(Card card) {
        cards.add(card);
    }

    public void clearCards() {
        cards.clear();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getChip() {
        return chip;
    }

    public void setChip(int chip) {
        this.chip = chip;
    }

    public String cardsString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.getCardNum().getCardStr())
                    .append(card.getNumEnum().getNumStr())
                    .append(" ");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return this.userName;
    }
}
