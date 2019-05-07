package org.rikey.jinhua.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {


    public static void main(String[] args) {
        Poker poker = new Poker();
        poker.shuffle();

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
        Round round = new Round();
        round.join(players);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            for (Card card : poker.getPorkerCards()) {
                System.out.printf(card.getCardNum().getCardStr() + card.getNumEnum().getNumStr() + " - ");
            }
            System.out.println("\n倒牌：");
            int cutPos = scanner.nextInt();
            poker.shuffle();
            poker.cutCards(cutPos);

            System.out.println("curpos: " + poker.getCurrent());


            RoundSignal s = new RoundSignal();
            s.setPoker(poker);
            s.setSignal(Round.BEGIN);
            round.goTo(s);

            for (int i = 0; i < 3; i++) {
                s = new RoundSignal();
                s.setPoker(poker);
                s.setSignal(Round.DEAL);
                round.goTo(s);
            }
            s = new RoundSignal();
            s.setPoker(poker);
            s.setSignal(Round.DEAL_FINISH);
            round.goTo(s);
            for (Player player : players) {
                System.out.println(player.getUserName() + ": " + player.toString());
            }



        }


    }
}
