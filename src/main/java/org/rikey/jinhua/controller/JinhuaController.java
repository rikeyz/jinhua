package org.rikey.jinhua.controller;

import com.alibaba.fastjson.JSONObject;
import org.rikey.jinhua.pojo.Player;
import org.rikey.jinhua.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class JinhuaController {

    private Map<String, Integer> rooms;

    @Resource
    private RoomService roomService;

    @PostConstruct
    public void init() {
        rooms = new ConcurrentHashMap<String, Integer>();
    }


    @PostMapping("/login")
    @ResponseBody
    public JSONObject login(@RequestParam("username") String username, HttpSession session) {
        session.setAttribute("username", username);
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("msg", "欢迎进入搓喜金花争霸");
        return result;
    }

    @GetMapping("/roomlist")
    @ResponseBody
    public JSONObject getRoomList() {
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("data", rooms);
        return result;
    }

    @PostMapping("/createroom")
    @ResponseBody
    public JSONObject createRoom(@RequestParam("roomname") String roomName, HttpSession session) {
        String username = (String)session.getAttribute("username");
        int roomId = roomService.createRoom();
        rooms.put(roomName, roomId);
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("roomId", roomId);
        return result;
    }

    @PostMapping("/enterroom")
    @ResponseBody
    public JSONObject enterRoom(@RequestParam("roomId")int roomId, HttpSession session) {
        String username = (String)session.getAttribute("username");
        Player player = new Player(username, 100);
        session.setAttribute("player", player);
        roomService.enterRoom(roomId, player);
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("username", username);
        return result;
    }
}
