package org.rikey.jinhua.controller;

import com.alibaba.fastjson.JSONObject;
import org.rikey.jinhua.pojo.Player;
import org.rikey.jinhua.service.RoomService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class JinhuaWebsocketHandler implements WebSocketHandler {


    private ConcurrentHashMap<Player, WebSocketSession> sessions;

    @Resource
    RoomService roomService;

    @PostConstruct
    public void initRoom() {
        sessions = new ConcurrentHashMap<Player, WebSocketSession>();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        String username = (String)webSocketSession.getAttributes().get("username");
        Player player = (Player)webSocketSession.getAttributes().get("player");

        JSONObject welcomeMsg = new JSONObject();
        welcomeMsg.put("type", "WELCOME");
        welcomeMsg.put("msg", username + " 加入\n");

        sendGroupMessage(welcomeMsg);
        sessions.put(player, webSocketSession);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        String message = (String)webSocketMessage.getPayload();
        JSONObject payloadJson = JSONObject.parseObject(message);
        if ("DEAL_CARD".equals(payloadJson.getString("event"))) {
            roomService.getRooms();
        }



    }



    private void sendP2PMessage(JSONObject message, WebSocketSession session) throws IOException {
        WebSocketMessage textMessage = new TextMessage(message.toJSONString());
        session.sendMessage(textMessage);
    }

    private void sendGroupMessage(JSONObject message) throws IOException {
        Iterator<Map.Entry<Player, WebSocketSession>> it = sessions.entrySet().iterator();

        WebSocketMessage textMessage = new TextMessage(message.toJSONString());
        while (it.hasNext()) {
            Map.Entry<Player, WebSocketSession> entry = it.next();
            entry.getValue().sendMessage(textMessage);
        }
    }



    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
