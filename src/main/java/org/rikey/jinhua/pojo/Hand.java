package org.rikey.jinhua.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Hand extends DefaultHandTypeResolver {

    @Getter
    @Setter
    private List<Card> cards = new ArrayList<Card>();

    @Getter
    @Setter
    private List<Card> sortedCards;

    @Getter
    @Setter
    private Card card1;

    @Getter
    @Setter
    private Card card2;

    @Getter
    @Setter
    private Card card3;

    @Getter
    private HandTypeEnum handType;

    @Getter
    @Setter
    private boolean isOpener = false;

    @Getter
    @Setter
    private boolean isNoSee = true;

    @Getter
    @Setter
    private boolean isCheat = false;

    public boolean isValid(){
        if (cards.size()==3){
            return true;
        }else {
            return false;
        }
    }

    public void sort(){
        sortedCards = new ArrayList<Card>();
        Map<Integer, Card> cardNumMap = new TreeMap<Integer, Card>();
        cardNumMap.put(card1.getNumEnum().getNumId(), card1);
        cardNumMap.put(card2.getNumEnum().getNumId(), card2);
        cardNumMap.put(card3.getNumEnum().getNumId(), card3);
        for(Iterator<Map.Entry<Integer, Card>> iterator = cardNumMap.entrySet().iterator();iterator.hasNext();){
            sortedCards.add(iterator.next().getValue());
        }
    }

    public void init(){
        this.card1 = cards.get(0);
        this.card2 = cards.get(1);
        this.card3 = cards.get(2);
        this.handType = resolveHandType(this);
        sort();
    }

//    public Hand(Card card1, Card card2, Card card3){
//        this.card1 = card1;
//        this.card2 = card2;
//        this.card3 = card3;
//        this.cards = new ArrayList<Card>();
//        this.cards.add(card1);
//        this.cards.add(card2);
//        this.cards.add(card3);
//    }
//
//    public Hand(List<Card> cards){
//        this.cards = cards;
//        this.card1 = cards.get(0);
//        this.card2 = cards.get(1);
//        this.card3 = cards.get(2);
//    }
//
//    public Hand(){}

    public void acceptCard(Card card) {
        this.cards.add(card);
    }
}
