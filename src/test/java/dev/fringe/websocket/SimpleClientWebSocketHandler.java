package dev.fringe.websocket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SimpleClientWebSocketHandler extends TextWebSocketHandler {

	protected Log logger = LogFactory.getLog(SimpleClientWebSocketHandler.class);

	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.debug("Received: " + message);
		session.close();
	}

}