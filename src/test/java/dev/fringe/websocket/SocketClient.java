package dev.fringe.websocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

public class SocketClient
{
    static public class MyStompSessionHandler
	extends StompSessionHandlerAdapter
    {
	private String userId;

	public MyStompSessionHandler(String userId)
	{
	    this.userId = userId;
	}

	private void showHeaders(StompHeaders headers)
	{
	    for (Map.Entry<String,List<String>> e:headers.entrySet()) {
		System.err.print("  " + e.getKey() + ": ");
		boolean first = true;
		for (String v : e.getValue()) {
		    if ( ! first ) System.err.print(", ");
		    System.err.print(v);
		    first = false;
		}
		System.err.println();
	    }
	}

	private void sendJsonMessage(StompSession session)
	{
	    ClientMessage msg = new ClientMessage(userId,
						  "hello  spring");
	    session.send("/app/chat/java", msg);
	}

	private void subscribeTopic(String topic,StompSession session)
	{
	    session.subscribe(topic, new StompFrameHandler() {

		    public Type getPayloadType(StompHeaders headers) {
			return ServerMessage.class;
		    }

		    public void handleFrame(StompHeaders headers,
					    Object payload)
		    {
			System.err.println(payload.toString());
		    }
		});
	}

	public void afterConnected(StompSession session,
				   StompHeaders connectedHeaders)
	{
	    System.err.println("Connected! Headers:");
	    showHeaders(connectedHeaders);

	    subscribeTopic("/topic/messages", session);
	    sendJsonMessage(session);
	}
    }

    public static void main(String args[]) throws Exception
    {
	WebSocketClient simpleWebSocketClient =
	    new StandardWebSocketClient();
	List<Transport> transports = new ArrayList<>(1);
	transports.add(new WebSocketTransport(simpleWebSocketClient));
 
	SockJsClient sockJsClient = new SockJsClient(transports);
	WebSocketStompClient stompClient = 
	    new WebSocketStompClient(sockJsClient);
	stompClient.setMessageConverter(new MappingJackson2MessageConverter());

	String url = "ws://35.230.49.213/chat";
	String userId = "spring-" +
	    ThreadLocalRandom.current().nextInt(1, 99);
	StompSessionHandler sessionHandler = new MyStompSessionHandler(userId);
	StompSession session = stompClient.connect(url, sessionHandler)
	    .get();
	BufferedReader in =
	    new BufferedReader(new InputStreamReader(System.in));
	for (;;) {
	    System.out.print(userId + " >> ");
	    System.out.flush();
	    String line = in.readLine();
	    if ( line == null ) break;
	    if ( line.length() == 0 ) continue;
	    ClientMessage msg = new ClientMessage(userId, line);
	    session.send("/app/chat/java", msg);
	}
    }
}
