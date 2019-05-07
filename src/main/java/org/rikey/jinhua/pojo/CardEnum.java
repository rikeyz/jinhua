package org.rikey.jinhua.pojo;

/**
 * 扑克牌花色枚举
 */
public enum CardEnum {
    /** 黑桃 */
    SPADE(4,"♠️"),
    /** 红桃 */
    HEART(3,"♥️"),
    CLUB(2,"\u2666"),
    DIAMOND(1,"♣️");

    private int cardId;

    private String cardStr;

    private CardEnum(int cardId, String cardStr) {
        this.cardId = cardId;
        this.cardStr = cardStr;
    }

    public int getCardId() {
        return cardId;
    }

    public String getCardStr() {
        return cardStr;
    }}
