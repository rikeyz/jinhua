package org.rikey.jinhua.pojo;

public interface SignalReactor {

    void reactBegin();

    void reactDeal(Poker poker);

    void reactDealFinish();

    void reactEnd();
}
