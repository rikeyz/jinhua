package org.rikey.jinhua.pojo;

import lombok.Getter;
import lombok.Setter;

public class RoundSignal {

    @Getter
    @Setter
    private int signal;

    @Getter
    @Setter
    private Poker poker;

    @Setter
    @Getter
    private Player speaker;
}
