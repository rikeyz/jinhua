package org.rikey.jinhua.pojo;

import lombok.Getter;

public enum HandTypeEnum {

    // 单牌235
    ESWWITHOUTJINHUA(-1, "单牌235"),
    // 单牌
    DANPAI(1, "单牌"),
    // 对子
    DUIZI(2, "对子"),
    // 顺子
    SHUNZI(3, "顺子"),
    // 金花
    JINHUA(4, "金花"),
    // 顺金
    SHUNJIN(5, "顺金"),
    // 炸蛋
    ZHADAN(6,"炸蛋");


    private HandTypeEnum(int handTypeId, String handTypeName){
        this.handTypeId = handTypeId;
        this.handTypeName = handTypeName;
    }

    @Getter
    private int handTypeId;

    @Getter
    private String handTypeName;

}
