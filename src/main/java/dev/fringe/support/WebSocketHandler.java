package dev.fringe.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class WebSocketHandler extends TextWebSocketHandler {

	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		log.info("{} ø¨∞·µ ", session.getId());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionList.remove(session);
        log.info("{} «ÿ¡¶µ ", session.getId()); 
		super.afterConnectionClosed(session, status);
	}

	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

		for (WebSocketSession sess : sessionList) {
			sess.sendMessage(new TextMessage("echo:" + message.getPayload()));
		}
		String clientMessage = message.getPayload();

		if (clientMessage.startsWith("Hello") || clientMessage.startsWith("Hi")) {
			session.sendMessage(new TextMessage("Hello! What can i do for you?"));
		} else {
			session.sendMessage(new TextMessage("This is a simple hello world example of using Spring WebSocket."));
		}
	}
}