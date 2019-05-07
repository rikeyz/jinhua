package org.rikey.jinhua.pojo;

import org.springframework.web.socket.WebSocketSession;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class JinhuaRoom {

    private List<Player> players;

    private int banker = 0;

    private AtomicInteger playersNum = new AtomicInteger(0);

    private Poker poker;

    public JinhuaRoom() {
        this.players = new CopyOnWriteArrayList<Player>();
        this.poker = new Poker();
    }

    /**
     * 洗牌
     */
    public void shuffle() {
        this.poker.shuffle();
    }

    /**
     * 切牌
     * @param pos
     */
    public void cutCards(int pos) {
        this.poker.cutCards(pos);
    }

    /**
     * 发牌
     */
    public void deal() {
        for (int i = 0; i < 3; i++) {
            for (int j = banker; j < banker + playersNum.get(); j++) {
                players.get(j % playersNum.get()).acceptCard(this.poker.deal());
            }
        }
    }

    public int enter(Player player) {
        players.add(player);
        return playersNum.addAndGet(1);
    }

    public void setBanker(int banker) {
        this.banker = banker % playersNum.get();
    }

    public Player getCutter() {
        return players.get((banker-1+playersNum.get()) % playersNum.get());
    }

    public void print() {
        for (int i = banker; i < banker + playersNum.get(); i++) {
            Player player = players.get(i % playersNum.get());
            System.out.println(player.getUserName() + ":\t" + player.cardsString());
        }
    }

    public static void main(String[] args) {
        JinhuaRoom room = new JinhuaRoom();
        room.shuffle();
        Player zhongrui = new Player("钟锐", 100);
        Player lixiao = new Player("李骁", 100);
        Player huangqiyu = new Player("黄启宇", 100);
        Player tangming = new Player("唐明", 100);
        Player mazong = new Player("马总", 100);
        Player wanghu = new Player("王虎", 100);
        Player ningshaopeng = new Player("宁绍鹏", 100);
        Player chenyunfeng = new Player("陈云风", 100);
        Player wuyuwei = new Player("吴煜玮", 100);
        Player yangyixiao = new Player("杨一笑", 100);
        Player pengqiling = new Player("彭麒菱", 100);
        room.enter(zhongrui);
        room.enter(lixiao);
        room.enter(huangqiyu);
        room.enter(tangming);
        room.enter(mazong);
        room.enter(wanghu);
        room.enter(ningshaopeng);
        room.enter(chenyunfeng);
        room.enter(wuyuwei);
        room.enter(yangyixiao);
        room.enter(pengqiling);
        room.setBanker(3);
        room.deal();

        room.print();

    }

}
