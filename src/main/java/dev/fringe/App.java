package dev.fringe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

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
	private UserService service2;
	@Autowired 
	private EmailService service;

	@SuppressWarnings("resource")
	public static void main(String[] args){
		log.debug("Entering application.");
		new AnnotationConfigApplicationContext(App.class).getBean(App.class).run();
		log.debug("End of application.");
	}

	public void run() {
		service2.save();
		Map<String, Object> map = new HashMap<>();
		map.put("users", service2.get(50,50));
		String html =  service.getContentFromTemplate("template/template.mustache", map);
		service.sendSimpleMessage(new Mail("dev.fringe@gmail.com", "dev.fringe@gmail.com", "title", html, Arrays.asList(new Attachment("app-local.yml","app-local.yml") )));
	}


}
