package dev.fringe.websocket;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

public class StandardClient {

	private static final String WS_URI = "ws://localhost/socketHandler";

	public static void main(String[] args) throws IOException {
		try {
			AnnotationConfigApplicationContext cxt = new AnnotationConfigApplicationContext(ClientConfig.class);
			System.out.println("\n\n\nWhen ready, press any key to exit\n\n\n");
			System.in.read();
			cxt.close();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	@Configuration
	static class ClientConfig {

		@Bean
		public WebSocketConnectionManager connectionManager() {
			WebSocketConnectionManager manager = new WebSocketConnectionManager(client(), handler(), WS_URI);
			manager.setAutoStartup(true);
			return manager;
		}

		@Bean
		public StandardWebSocketClient client() {
			return new StandardWebSocketClient();
		}

		@Bean
		public SimpleClientWebSocketHandler handler() {
			return new SimpleClientWebSocketHandler();
		}
	}
}