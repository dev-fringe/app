package dev.fringe.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.github.mustachejava.DefaultMustacheFactory;

import dev.fringe.entity.support.Attachment;
import dev.fringe.entity.support.Mail;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	public void sendSimpleMessage(final Mail mail) {
		MimeMessage message = emailSender.createMimeMessage();
		try {
			message.setSubject(mail.getSubject());
			MimeMessageHelper helper;
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(mail.getFrom());
			helper.setTo(mail.getTo());
			helper.setText(mail.getContent(), true);
			for (Attachment attachment : mail.getAttachments()) {
				helper.addAttachment(attachment.getFilename(), new ClassPathResource(attachment.getPathfilename()));
			}
		} catch (MessagingException e) {
			log.error(e);
			e.printStackTrace();
		}
		emailSender.send(message);
	}
	public String getContentFromTemplate(String template, Map<String, Object> data) {
		StringWriter writer = new StringWriter();
		try {
			new DefaultMustacheFactory().compile(template).execute(writer, data).flush();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}
		return writer.toString();
	}

}