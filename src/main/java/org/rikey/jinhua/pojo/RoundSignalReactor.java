package org.rikey.jinhua.pojo;

import org.apache.commons.lang.NotImplementedException;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public interface RoundSignalReactor extends Observer {

     default void reactBegin(Signal signal) throws Exception{
     }

    default void reactCut(Signal signal) throws Exception{
    }

     default void reactDeal(Signal signal) throws Exception{
     }

    default void reactDealFinish(Signal signal) throws Exception{
    }

    default void reactEnd(Signal signal) throws Exception{
    }
}
