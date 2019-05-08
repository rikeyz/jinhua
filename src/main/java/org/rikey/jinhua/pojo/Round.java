package org.rikey.jinhua.pojo;


import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Round extends SignalSponsorAndReactorAdapter {

    public static final int BEGIN = 1;
    public static final int CUT = 2;
    public static final int DEAL = 3;
    public static final int DEAL_FINISH = 4;
    public static final int END = 5;

    @Setter
    @Getter
    private TreeMap<Integer, Player> allPlayers = new TreeMap<>();

    /**
     * 庄家
     */
    @Getter
    @Setter
    private Player banker;

    /**
     * 当前玩家
     */
    @Setter
    @Getter
    private Player speaker;

    @Getter
    @Setter
    private int cutNum;

    @Getter
    private Poker poker;

    public void join(List<Player> players) {
        for (int i = players.size() - 1 ; i >= 0; i--) {
            allPlayers.put(players.get(i).getTablePosition(), players.get(i));
            addObserver(players.get(i));
        }
    }

    public void exit(Player player) {
        deleteObserver(player);
        allPlayers.remove(player.getTablePosition());
    }

    public void addPoker(Poker poker) {
        this.poker = poker;
        addObserver(poker);
    }

    public void clearPoker(Poker poker) {
        this.poker = null;
        deleteObserver(poker);
    }

    public boolean isAllHandValid(){
        boolean allValid = true;
        for (Iterator<Map.Entry<Integer, Player>> iterator=allPlayers.entrySet().iterator();iterator.hasNext();){
            allValid = allValid && iterator.next().getValue().getHand().isValid();
            if (!allValid){
                return false;
            }
        }
        return true;
    }

}
