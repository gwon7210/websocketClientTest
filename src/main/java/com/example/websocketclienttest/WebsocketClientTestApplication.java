package com.example.websocketclienttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class WebsocketClientTestApplication {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(WebsocketClientTestApplication.class, args);

	}

}
