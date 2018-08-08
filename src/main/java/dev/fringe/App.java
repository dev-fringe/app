package dev.fringe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import dev.fringe.entity.User;
import dev.fringe.entity.support.Attachment;
import dev.fringe.entity.support.Mail;
import dev.fringe.service.EmailService;
import dev.fringe.service.UserService;
import dev.fringe.support.AbstractAppLogging;
import lombok.extern.log4j.Log4j2;

@ComponentScan(basePackages = {"dev.fringe.repository","dev.fringe.service"})
@ImportResource(value = { "classpath:/infrastructure/context-application.xml" })
@Log4j2
public class App extends AbstractAppLogging {
	@Autowired
	private UserService userService;
	@Autowired 
	private EmailService emailService;

	@SuppressWarnings("resource")
	public static void main(String[] args){
		log.debug("Entering application.");
		new AnnotationConfigApplicationContext(App.class).getBean(App.class).run();
		log.debug("End of application.");
	}

	public void run() {
		userService.save();
		Map<String, Object> map = new HashMap<>();
		User user = new User();
		map.put("users", userService.list(user));
		String html =  emailService.getContentFromTemplate("template/template.mustache", map);
		emailService.sendSimpleMessage(new Mail("dev.fringe@gmail.com", "dev.fringe@gmail.com", "title", html, Arrays.asList(new Attachment("app-local.yml","app-local.yml") )));
	}


}
