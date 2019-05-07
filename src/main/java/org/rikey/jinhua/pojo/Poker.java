package org.rikey.jinhua.pojo;

import java.util.*;

public class Poker {
    private List<Card> porkerCards = null;

    private int cardsNum = 0;

    private int current=0;

    public Poker() {
        porkerCards = new ArrayList<Card>();
        for (NumEnum numEnum : NumEnum.values()) {
            for (CardEnum cardsEnum : CardEnum.values()) {
                Card card = new Card(cardsEnum, numEnum);
                porkerCards.add(card);
                this.cardsNum ++;
            }
        }
    }

    public void shuffle() {
        Random rnd = new Random(System.currentTimeMillis());
        Collections.shuffle(porkerCards, rnd);
        current = 0;
    }

    public void cutCards(int cutPos) {
        current = cutPos;
    }

    public Card deal() {
        return porkerCards.get(current++ % cardsNum);
    }

    public List<Card> getPorkerCards() {
        return porkerCards;
    }

    public int getCurrent() {
        return current;
    }

    public static void main(String[] args) {
        Poker poker = new Poker();
        poker.shuffle();

        List<Player> players = new ArrayList<Player>();

        Player zhongrui = new Player("钟锐", 100, 1);
        Player lixiao = new Player("李骁", 100, 2);
        Player huangqiyu = new Player("黄启宇", 100, 3);
        Player tangming = new Player("唐明", 100, 4);
        Player mazong = new Player("马总", 100, 5);
        Player wanghu = new Player("王虎", 100, 6);
        Player ningshaopeng = new Player("宁绍鹏", 100, 7);
        Player chenyunfeng = new Player("陈云风", 100, 8);
        Player wuyuwei = new Player("吴煜玮", 100, 9);
        Player yangyixiao = new Player("杨一笑", 100, 10);
        Player pengqiling = new Player("彭麒菱", 100, 11);
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




        while(true) {
            Scanner scanner = new Scanner(System.in);
            for (Card card : poker.getPorkerCards()) {
                System.out.printf(card.getCardNum().getCardStr() + card.getNumEnum().getNumStr() + " - ");
            }
            System.out.println("\n倒牌：");
            int cutPos = scanner.nextInt();
            poker.shuffle();
            poker.cutCards(cutPos);

            System.out.println("curpos: " + poker.getCurrent());
            for (int i = 0; i < 3; i++) {
                for (Player player : players) {
                    player.acceptCard(poker.deal());
                }
            }

            for (Player player : players) {
                System.out.println(player.getUserName() + ": " + player.toString());
            }

            for (Player player : players) {
                player.clearCards();
            }

        }

    }



}
