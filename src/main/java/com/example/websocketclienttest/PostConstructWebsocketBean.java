package com.example.websocketclienttest;

import com.example.websocketclienttest.handler.MyStompSessionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PostConstructWebsocketBean {

    public static StompSession stompSession;

    @PostConstruct
     public void init(){

         try {
             StompSessionHandler sessionHandler = new MyStompSessionHandler("3333333");

             WebSocketClient simpleWebSocketClient = new StandardWebSocketClient();
             List<Transport> transports = new ArrayList<>(1);
             transports.add(new WebSocketTransport(simpleWebSocketClient));

             SockJsClient sockJsClient = new SockJsClient(transports);
             WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
             stompClient.setMessageConverter(new MappingJackson2MessageConverter());

             //Connect
             stompSession = stompClient.connect("ws://localhost:8080/ws", sessionHandler).get();
             System.out.println(stompClient);
         }catch (Exception e){
             log.error("웹 소켓 서버와의 연결에 실패하여습니다. " + e.getMessage() );
         }
    }
}
