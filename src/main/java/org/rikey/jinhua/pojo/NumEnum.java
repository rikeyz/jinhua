package org.rikey.jinhua.pojo;

/**
 * 数字枚举
 */
public enum NumEnum {

    /** 2 */
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JOKER(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K"),
    ACE(14, "A");



    private int numId;

    private String numStr;

    private NumEnum(int numId, String numStr) {
        this.numId = numId;
        this.numStr = numStr;
    }

    public int getNumId() {
        return numId;
    }

    public String getNumStr() {
        return numStr;
    }}
