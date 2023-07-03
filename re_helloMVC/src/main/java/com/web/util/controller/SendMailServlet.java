package com.web.util.controller;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 * Servlet implementation class SendMailServlet
 */
@WebServlet("/SendMailServlet")
public class SendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mail="";
		final String user = "";
        final String password = "";
		
		
		Properties prop = new Properties();
	    prop.put("mail.smtp.host", "smtp.gmail.com");
	    prop.put("mail.smtp.port", 465);
	    prop.put("mail.smtp.auth", "true");
	    prop.put("mail.smtp.ssl.enable", "true");
	    prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

	    Session session=Session.getDefaultInstance(prop,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(user, password);
			}
	    });
	    
	    MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(user));
 
            // 수신자 메일 주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
 
            // Subject
            message.setSubject("PLAYDDIT verification code");
 
            // Text
            message.setText("Welcome to playddit. your code is [1234]");
 
            Transport.send(message);    // send message
 
 
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
