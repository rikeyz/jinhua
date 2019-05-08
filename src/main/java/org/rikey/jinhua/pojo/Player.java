package org.rikey.jinhua.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Player extends SignalSponsorAndReactorAdapter {

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private int chip;

    @Getter
    @Setter
    private int tablePosition;

    @Getter
    @Setter
    private Hand hand;

    public Player(String userName, int chip, int tablePosition) {
        this.userName = userName;
        this.chip = chip;
        this.tablePosition = tablePosition;
    }

    @Override
    public void reactBegin(Signal signal) {
//        this.hand = null;
        this.hand = new Hand();
    }

    @Override
    public void reactDeal(Signal signal) throws Exception{
        Round round =signal.getRound();
        Poker poker = round.getPoker();
        if (!poker.isDeal() && round.getBanker().getTablePosition() >= tablePosition){
            log.debug("skip deal");
        }else {
            if(!poker.isDeal()){
                poker.setDeal(true);
            }
            this.hand.acceptCard(poker.deal());
        }
    }

    @Override
    public void reactDealFinish(Signal signal) {
        this.hand.init();
    }

    @Override
    public void reactEnd(Signal signal) {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : this.hand.getCards()) {
            sb.append(card.getCardNum().getCardStr())
                    .append(":")
                    .append(card.getNumEnum().getNumStr())
                    .append(" ");
        }
//        return sb.toString();
        return sb.toString()+"["+this.hand.getHandType().getHandTypeName()+"]";
    }
}
