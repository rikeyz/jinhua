package org.rikey.jinhua.pojo;

public class Card {

    private CardEnum cardNum;

    private NumEnum numEnum;


    public Card(CardEnum cards, NumEnum num) {
        this.cardNum = cards;
        this.numEnum = num;
    }

    public CardEnum getCardNum() {
        return cardNum;
    }

    public void setCardNum(CardEnum cardNum) {
        this.cardNum = cardNum;
    }

    public NumEnum getNumEnum() {
        return numEnum;
    }

    public void setNumEnum(NumEnum numEnum) {
        this.numEnum = numEnum;
    }

    public static void main(String[] args) {
        String s = "♠️♥️♣️";
        System.out.println(Integer.toHexString((int)s.charAt(0)));
        System.out.println(Integer.toHexString((int)s.charAt(1)));
        System.out.println(Integer.toHexString((int)s.charAt(2)));

    }
}
