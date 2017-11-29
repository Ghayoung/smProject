package net.skhu.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import net.skhu.dto.User;

@Component
public class MyEmailService {

	@Autowired
    public JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendSimpleMessage(User from, String to, String subject, String text) {
    	MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
        try {
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom(new InternetAddress(from.getEmail(), from.getName()+" (SKHU SM)"));
            emailSender.send(message);
        } catch (MessagingException messageException) {
            throw new RuntimeException(messageException);
        }catch (Throwable e) {
            e.printStackTrace();
            return;
          }
    }

    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);
        try{
        	helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);

            emailSender.send(message);
        }catch (MessagingException messageException) {
            throw new RuntimeException(messageException);
        }

    }

    public void sendMessageWithAttachment(User from, String to, String subject, String text, String pathToAttachment) {
        MimeMessage message = emailSender.createMimeMessage();


        try{
        	MimeMessageHelper helper = new MimeMessageHelper(message,true);

        	helper.setFrom(new InternetAddress(from.getEmail(), from.getName()+" (SKHU SM)"));
        	helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);

            emailSender.send(message);
        }catch (MessagingException messageException) {
            throw new RuntimeException(messageException);
        }catch (Throwable e) {
            e.printStackTrace();
            return;
          }

    }
}
