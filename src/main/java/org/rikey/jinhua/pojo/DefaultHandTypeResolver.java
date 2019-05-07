package org.rikey.jinhua.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class DefaultHandTypeResolver implements HandTypeResolver {
    @Override
    public HandTypeEnum resolveHandType(Hand hand) {
        if(isZhaDan(hand)){
            return HandTypeEnum.ZHADAN;
        }else if(isShunJin(hand)){
            return HandTypeEnum.SHUNJIN;
        }else if(isJinHua(hand)){
            return HandTypeEnum.JINHUA;
        }else if(isShunZi(hand)){
            return HandTypeEnum.SHUNZI;
        }else if(isDuiZi(hand)){
            return HandTypeEnum.DUIZI;
        }else if(isESWWithoutJinHua(hand)){
            return HandTypeEnum.ESWWITHOUTJINHUA;
        }else {
            return HandTypeEnum.DANPAI;
        }
    }

    private HashSet diffCard(Hand hand){
        HashSet<CardEnum> cardSet = new HashSet<CardEnum>();
        cardSet.add(hand.getCard1().getCardNum());
        cardSet.add(hand.getCard2().getCardNum());
        cardSet.add(hand.getCard3().getCardNum());
        return cardSet;
    }

    private HashSet diffNum(Hand hand){
        HashSet<NumEnum> numSet = new HashSet<NumEnum>();
        numSet.add(hand.getCard1().getNumEnum());
        numSet.add(hand.getCard2().getNumEnum());
        numSet.add(hand.getCard3().getNumEnum());
        return numSet;
    }

    private boolean isZhaDan(Hand hand){
        if(diffNum(hand).size() == 1){
            return true;
        }else {
            return false;
        }
    }

    private boolean isShunZi(Hand hand){
        int[] sortArray = new int[]{hand.getCard1().getNumEnum().getNumId(),
                hand.getCard2().getNumEnum().getNumId(),
                hand.getCard3().getNumEnum().getNumId()};
        Arrays.sort(sortArray);
        if ((sortArray[2]-sortArray[1] == 1) && (sortArray[1]-sortArray[0] == 1)){
            return true;
        }
        // A23
        else if(sortArray[0] == 2 && sortArray[1] == 3 && sortArray[2] == 14){
            return true;
        }else {
            return false;
        }
    }

    private boolean isTongHua(Hand hand){
        if(diffCard(hand).size() == 1){
            return true;
        }else {
            return false;
        }
    }

    private boolean isJinHua(Hand hand){
        if(isTongHua(hand) && !isShunZi(hand)){
            return true;
        }else {
            return false;
        }
    }

    private boolean isShunJin(Hand hand){
        if(isTongHua(hand) && isShunZi(hand)) {
            return true;
        }else {
            return false;
        }
    }

    private boolean isDuiZi(Hand hand){
        if(diffNum(hand).size() == 2){
            return true;
        }else {
            return false;
        }
    }

    private boolean isESWWithoutJinHua(Hand hand){
        HashSet diffNum = diffNum(hand);
        ArrayList<NumEnum> eswList = new ArrayList<NumEnum>();
        eswList.add(NumEnum.TWO);
        eswList.add(NumEnum.THREE);
        eswList.add(NumEnum.FIVE);
        if(diffNum.containsAll(eswList) && !isTongHua(hand)){
            return true;
        }else {
            return false;
        }
    }
}
