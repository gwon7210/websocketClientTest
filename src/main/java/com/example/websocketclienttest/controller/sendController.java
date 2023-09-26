package com.example.websocketclienttest.controller;

import com.example.websocketclienttest.model.ChatMessage;
import com.example.websocketclienttest.handler.MyStompSessionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

import static com.example.websocketclienttest.PostConstructWebsocketBean.stompSession;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class sendController {

    @GetMapping("/send")
    public void send() {

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("hello word");
        chatMessage.setType(ChatMessage.MessageType.CHAT);
        chatMessage.setSender("박지원");

        if (stompSession!= null && stompSession.isConnected()) {
            stompSession.send("/app/chat.sendMessage", chatMessage);
        } else {

            // 연결이 없는 경우 처리할 로직을 추가합니다.
            System.out.println("STOMP 연결이 끊어져서 다시 연결을 시도합니다.");

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
                stompSession.send("/app/chat.sendMessage", chatMessage);
            }catch (Exception e){
                log.error("웹 소켓 서버와의 연결에 실패하여습니다. " + e.getMessage() );
            }

        }




    }

}
