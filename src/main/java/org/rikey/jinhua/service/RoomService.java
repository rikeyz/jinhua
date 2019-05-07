package org.rikey.jinhua.service;


import org.rikey.jinhua.pojo.JinhuaRoom;
import org.rikey.jinhua.pojo.Player;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RoomService {

    private Map<Integer, JinhuaRoom> rooms;

    private AtomicInteger roomNums;

    @PostConstruct
    public void initRoomsHolder() {
        rooms = new ConcurrentHashMap<Integer, JinhuaRoom>();
        roomNums = new AtomicInteger(0);
    }

    public int createRoom() {
        int roomId = roomNums.getAndAdd(1);
        JinhuaRoom room = new JinhuaRoom();
        rooms.put(roomId, room);
        return roomId;
    }

    public JinhuaRoom getRoom(int idx) {
        return rooms.get(idx);
    }

    public void enterRoom(int roomId, Player player) {
        rooms.get(roomId).enter(player);
    }

    public Map<Integer, JinhuaRoom> getRooms() {
        return rooms;
    }
}
