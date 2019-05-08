package org.rikey.jinhua.pojo;

import java.util.*;

public class Game {


    public static void main(String[] args) {

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

            Scanner scanner = new Scanner(System.in);
            System.out.println("\n倒牌：");
            int cutPos = scanner.nextInt();
            s.setSignal(Round.BEGIN);
            round.goTo(s);
            for (Card card : poker.getPorkerCards()) {
                System.out.printf(card.getCardNum().getCardStr() + card.getNumEnum().getNumStr() + " - ");
            }
            System.out.println("curpos: " + poker.getCurrent());
            s.setSignal(Round.CUT);
            round.setCutNum(cutPos);
            round.goTo(s);

            s.setSignal(Round.DEAL);
            while (!round.isAllHandValid()) {
                round.goTo(s);
            }
            s.setSignal(Round.DEAL_FINISH);
            round.goTo(s);
            for (Player player : players) {
                System.out.println(player.getUserName() + ": " + player.toString());
            }



        }


    }
}
