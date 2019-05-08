package org.rikey.jinhua.pojo;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Game {


    public static void main(String[] args) throws Exception{

        List<Player> players = new ArrayList<Player>();

        Player zhongrui = new Player("钟锐", 100, 0);
        Player lixiao = new Player("李骁", 100, 1);
        Player huangqiyu = new Player("黄启宇", 100, 2);
        Player tangming = new Player("唐明", 100, 3);
        Player mazong = new Player("马总", 100, 4);
        Player wanghu = new Player("王虎", 100, 5);
        Player ningshaopeng = new Player("宁绍鹏", 100, 6);
        Player chenyunfeng = new Player("陈云风", 100, 7);
        Player wuyuwei = new Player("吴煜玮", 100, 8);
        Player yangyixiao = new Player("杨一笑", 100, 9);
        Player pengqiling = new Player("彭麒菱", 100, 10);
        players.add(zhongrui);
        players.add(lixiao);
        players.add(huangqiyu);
        players.add(tangming);
        players.add(mazong);
        players.add(wanghu);
        players.add(ningshaopeng);
        players.add(chenyunfeng);
        players.add(wuyuwei);
        players.add(yangyixiao);
        players.add(pengqiling);


        while (true) {
            Poker poker = new Poker();
            Round round = new Round();
            round.setBanker(players.get(new Random().nextInt(players.size())));
            System.out.println("庄家:"+round.getBanker().getUserName()+"|"+round.getBanker().getTablePosition());
            round.join(players);
            round.addPoker(poker);
            Signal s = new Signal();
            s.setRound(round);

            s.setSignal(Round.BEGIN);
            round.goTo(s);

            System.out.println("倒牌前：");
            for (Card card : poker.getPorkerCards()) {
                System.out.printf(card.getCardNum().getCardStr() + card.getNumEnum().getNumStr() + " - ");
            }
            System.out.println("");

            Scanner scanner = new Scanner(System.in);
            System.out.println("\n倒牌：");
            int cutPos = scanner.nextInt();

            s.setSignal(Round.CUT);
            round.setCutNum(cutPos);
            round.goTo(s);

            System.out.println("倒牌后：");
            for (Card card : poker.getPorkerCards()) {
                System.out.printf(card.getCardNum().getCardStr() + card.getNumEnum().getNumStr() + " - ");
            }
            System.out.println("");

            //耍魔术
            Card a1 = new Card(CardEnum.SPADE, NumEnum.ACE);
            Card a2 = new Card(CardEnum.HEART, NumEnum.ACE);
            Card a3 = new Card(CardEnum.CLUB, NumEnum.ACE);
            zhongrui.getHand().cheat(poker,a1, a2, a3);

            Card p2 = new Card(CardEnum.SPADE, NumEnum.FIVE);
            Card p3 = new Card(CardEnum.HEART, NumEnum.THREE);
            Card p5 = new Card(CardEnum.CLUB, NumEnum.TWO);
            lixiao.getHand().cheat(poker,p2, p3, p5);

            Card k1 = new Card(CardEnum.SPADE, NumEnum.KING);
            Card k2 = new Card(CardEnum.HEART, NumEnum.KING);
            Card k3 = new Card(CardEnum.CLUB, NumEnum.KING);
            mazong.getHand().cheat(poker,k1, k2, k3);


            s.setSignal(Round.DEAL);
            while (!round.isAllHandValid()) {
                round.goTo(s);
            }
            s.setSignal(Round.DEAL_FINISH);
            round.goTo(s);
            for (Player player : players) {
                System.out.println(player.getUserName() + ": " + player.toString());
            }

            System.out.println("*********************************************");

        }


    }
}
