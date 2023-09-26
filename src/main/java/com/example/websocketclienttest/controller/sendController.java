package com.example.websocketclienttest.controller;

import com.example.websocketclienttest.model.ChatMessage;
import com.example.websocketclienttest.handler.MyStompSessionHandler;
import lombok.RequiredArgsConstructor;
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
import java.util.concurrent.ExecutionException;

import static com.example.websocketclienttest.WebsocketClientTestApplication.stompSession;


@RestController
@RequiredArgsConstructor
@RequestMapping
public class sendController {


    @GetMapping("/send")
    public void send() throws ExecutionException, InterruptedException {
//        StompSessionHandler sessionHandler = new MyStompSessionHandler("3333333");
//
//        WebSocketClient simpleWebSocketClient = new StandardWebSocketClient();
//        List<Transport> transports = new ArrayList<>(1);
//        transports.add(new WebSocketTransport(simpleWebSocketClient));
//
//        SockJsClient sockJsClient = new SockJsClient(transports);
//        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
//        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
//
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent("hello word");
        chatMessage.setType(ChatMessage.MessageType.CHAT);
        chatMessage.setSender("parkjiwon");

//
//        //Connect
//        StompSession stompSession = stompClient.connect("ws://localhost:8080/ws", sessionHandler).get();

        //Subscribe
//        stompSession.subscribe("/topic/public", sessionHandler);

        //Send Message
//        stompSession.send("/app/chat.addUser", chatMessage);


        stompSession.send("/app/chat.sendMessage", chatMessage);

//        stompSession.send("/app/chat.sendMessage", chatMessage);
//        stompSession.disconnect();
    }

}
