package org.rikey.jinhua.pojo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.NotImplementedException;

import java.util.Observable;

@Slf4j
public abstract class SignalSponsorAndReactorAdapter extends Observable implements RoundSignalReactor {


    public void goTo(Signal signal){
        setChanged();
        notifyObservers(signal);
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            if(o instanceof Round) {
                Round round = (Round)o;
                Signal signal = (Signal) arg;
                switch (signal.getSignal()) {
                    case Round.BEGIN:
                        reactBegin(signal);
                        break;
                    case Round.CUT:
                        reactCut(signal);
                        break;
                    case Round.DEAL:
                        reactDeal(signal);
                        break;
                    case Round.DEAL_FINISH:
                        reactDealFinish(signal);
                        break;
                }
            }
            if(o instanceof Poker){

            }
        }catch (Exception e){
            log.error("信号响应异常",e);
        }

    }
}
