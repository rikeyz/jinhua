package org.rikey.jinhua.pojo;



import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Round extends Observable {

    public static final int BEGIN = 1;
    public static final int DEAL = 2;
    public static final int DEAL_FINISH = 3;
    public static final int END = 4;


    public void join(Player player){
        addObserver(player);
    }

    public void join(Collection<Player> players){
        for (Iterator<Player> playerIterator = players.iterator(); playerIterator.hasNext();){
            addObserver(playerIterator.next());
        }
    }

    public void exit(Player player){
        deleteObserver(player);
    }

    public void goTo(RoundSignal signal){
        setChanged();
        notifyObservers(signal);
    }

}
