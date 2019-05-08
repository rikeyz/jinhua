package org.rikey.jinhua.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Poker extends SignalSponsorAndReactorAdapter {

    @Getter
    private LinkedList<Card> porkerCards = null;

    @Getter
    private int cardsNum = 0;

    @Getter
    private int usedNum = 0;

    @Getter
    private int current = 0;

    /**
     * 是否已开牌
     */
    @Getter
    @Setter
    private boolean isDeal = false;


    public Poker() {
        porkerCards = new LinkedList<>();
        for (NumEnum numEnum : NumEnum.values()) {
            for (CardEnum cardsEnum : CardEnum.values()) {
                Card card = new Card(cardsEnum, numEnum);
                porkerCards.add(card);
                this.cardsNum++;
            }
        }
    }

    public void shuffle() {
        Random rnd = new Random(System.currentTimeMillis());
        Collections.shuffle(porkerCards, rnd);
    }

    public void cutCards(int cutPos) throws Exception{
        if(cutPos >= cardsNum){
            throw new Exception("cut too much");
        }
        for (int i = cutPos; i > 0; i--) {
            Card card = porkerCards.pollFirst();
            porkerCards.offerLast(card);
        }
    }

    public Card deal() throws Exception{
        if (porkerCards != null) {
            cardsNum--;
            usedNum++;
            return porkerCards.pollFirst();
        }else {
            throw new Exception("poker has not init");
        }
    }

    public Card getSpecified(CardEnum cardEnum, NumEnum numEnum) throws Exception {
        if (porkerCards != null) {
            for (Iterator<Card> iterator = porkerCards.iterator(); iterator.hasNext(); ) {
                Card card = iterator.next();
                if (card.getNumEnum() == numEnum && card.getCardNum() == cardEnum) {
                    iterator.remove();
                    cardsNum--;
                    usedNum++;
                    return card;
                }
            }
        } else {
            throw new Exception("poker has not init");
        }
        throw new Exception("no such card");
    }

    @Override
    public void reactBegin(Signal signal) {
        shuffle();
    }

    @Override
    public void reactCut(Signal signal) throws Exception{
        cutCards(signal.getRound().getCutNum());
    }
}
