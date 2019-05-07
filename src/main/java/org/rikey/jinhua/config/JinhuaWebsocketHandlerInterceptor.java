package org.rikey.jinhua.config;

import org.apache.commons.lang.StringUtils;
import org.rikey.jinhua.pojo.Player;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Configuration
public class JinhuaWebsocketHandlerInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        ServletServerHttpRequest request = (ServletServerHttpRequest)serverHttpRequest;
        HttpSession httpSession = request.getServletRequest().getSession(false);
        String username = (String)httpSession.getAttribute("username");
        Player player = (Player)httpSession.getAttribute("player");

        if (StringUtils.isEmpty(username)) {
            return false;
        }
        map.put("username", username);
        map.put("player", player);

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
