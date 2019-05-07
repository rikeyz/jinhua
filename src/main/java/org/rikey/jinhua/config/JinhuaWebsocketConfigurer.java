package org.rikey.jinhua.config;

import org.rikey.jinhua.controller.JinhuaWebsocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;

@Configuration
@EnableWebSocket
public class JinhuaWebsocketConfigurer implements WebSocketConfigurer {

    @Resource
    private JinhuaWebsocketHandler jinhuaWebsocketHandler;

    @Resource JinhuaWebsocketHandlerInterceptor handlerInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(jinhuaWebsocketHandler, "/websocket/jinhuaroom")
                .addInterceptors(handlerInterceptor).setAllowedOrigins("*");
    }
}
