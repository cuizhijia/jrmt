package com.swift.jrmt.common.utils;

import java.util.Calendar;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EMailUtil {
	private static Logger logger = LoggerFactory.getLogger(EMailUtil.class);
	public static Session mailSession = null;
	private static String userName = "sys_crm@56kuaiche.com";
	private static String userPwd = "j7s6b9b6j6r2o5c3J!@#$%^";
	@SuppressWarnings("static-access")
	private static void sendMessage(String smtpHost, String from,
			String fromUserPassword, String to, String subject,
			String messageText, String messageType) throws MessagingException {
		logger.info("send begin");
		// 第一步：配置javax.mail.Session对象
		mailSession = getMailSession(smtpHost, from, fromUserPassword);
		// 第二步：编写消息
		InternetAddress fromAddress = new InternetAddress(from);
		InternetAddress toAddress = new InternetAddress(to);
		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(fromAddress);
		message.addRecipient(RecipientType.TO, toAddress);
		message.setSentDate(Calendar.getInstance().getTime());
		message.setSubject(subject);
		message.setContent(messageText, messageType);
		// 第三步：发送消息
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(smtpHost,"今日煤炭后台系统", fromUserPassword);
		transport.send(message, message.getRecipients(RecipientType.TO));
		logger.info("send end, win");
	}
	private static Session getMailSession(String smtpHost,String from,String  fromUserPassword){
		if(mailSession == null){
			Properties props = new Properties();
			props.put("mail.smtp.host", smtpHost);
			props.put("mail.smtp.starttls.enable","true");//使用 STARTTLS安全连接
			props.put("mail.smtp.auth", "true");		// 使用验证
			mailSession = Session.getInstance(props,new MyAuthenticator(from,fromUserPassword));
		}
		return mailSession;
	}
	public static void main(String[] args) {
		sentMail("标题", "内容","@56kuaiche.com");
	}
	/**
	 * @param subject 标题
	 * @param messageText 内容
	 * @param toMail 目标邮箱
	 * @return
	 */
	public static boolean sentMail(String subject,String messageText,String toMail ){
		try {
			sendMessage("smtp.exmail.qq.com",userName ,
					userPwd, toMail, subject,
					messageText,
					"text/html;charset=gb2312");
		} catch (MessagingException e) {
			logger.error("邮件发送失败",e);
			return false;
		}
		return true;
	}
}
	class MyAuthenticator extends Authenticator{
		String userName="";
		String password="";
		public MyAuthenticator(){
			
		}
		public MyAuthenticator(String userName,String password){
			this.userName=userName;
			this.password=password;
		}
		 protected PasswordAuthentication getPasswordAuthentication(){   
			return new PasswordAuthentication(userName, password);   
		 } 
	}
