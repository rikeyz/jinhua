package org.rikey.jinhua.pojo;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String userName;

    private List<Card> cards;

    private int chip;

    private int tablePosition;

    public Player(String userName, int chip, int tablePosition) {
        this.userName = userName;
        this.chip = chip;
        this.tablePosition = tablePosition;
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

    public int getTablePosition() {
        return tablePosition;
    }

    public void setTablePosition(int tablePosition) {
        this.tablePosition = tablePosition;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.getCardNum().getCardStr())
                    .append(":")
                    .append(card.getNumEnum().getNumStr())
                    .append(" ");
        }

        return sb.toString();
    }
}
