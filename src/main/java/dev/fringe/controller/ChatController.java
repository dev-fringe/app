package dev.fringe.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import dev.fringe.entity.support.Message;
import dev.fringe.entity.support.OutputMessage;

/**
 * @author dev-frine
 */
@Controller
public class ChatController {

	@MessageMapping("/chat/{topic}")
	@SendTo("/topic/messages")
	public OutputMessage send(@DestinationVariable("topic") String topic, Message message) throws Exception {
		return new OutputMessage(message.getFrom(), message.getText(), topic);
	}
	
	@GetMapping("/c")
	public String c() {
		return "c";
	}
}
