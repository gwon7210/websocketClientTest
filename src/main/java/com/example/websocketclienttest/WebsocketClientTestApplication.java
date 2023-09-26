package com.example.websocketclienttest;

import com.example.websocketclienttest.handler.MyStompSessionHandler;
import com.example.websocketclienttest.model.ChatMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class WebsocketClientTestApplication {
	public static StompSession stompSession;
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		StompSessionHandler sessionHandler = new MyStompSessionHandler("3333333");

		WebSocketClient simpleWebSocketClient = new StandardWebSocketClient();
		List<Transport> transports = new ArrayList<>(1);
		transports.add(new WebSocketTransport(simpleWebSocketClient));

		SockJsClient sockJsClient = new SockJsClient(transports);
		WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());

		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setContent("hello word");
		chatMessage.setType(ChatMessage.MessageType.CHAT);
		chatMessage.setSender("parkjiwon");

		//Connect
		stompSession = stompClient.connect("ws://localhost:8080/ws", sessionHandler).get();

		SpringApplication.run(WebsocketClientTestApplication.class, args);

	}

}
