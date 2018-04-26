package com.zjitc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 * Create By IntelliJ IDEA
 *
 * @author: jsonor
 * @create-Time: 2017/11/6 16:37
 * @description: ${description}
 */
public class Mail {

  private static final String KEY_USERNAME = "username";
  private static final String KEY_PASSWORD = "password";
  private static final String KEY_PROTOCOL = "mail.transport.protocol";
  private static final String KEY_HOST = "mail.smtp.host";
  private static final String KEY_PORT = "mail.smtp.port";


  private static final Logger log = Logger.getLogger(Mail.class);
  private static final Properties DEFAULT_PROPERTIES;
  private Properties properties;

  static {
    try {
      InputStream stream = Mail.class.getClassLoader().getResourceAsStream("mail.properties");
      DEFAULT_PROPERTIES = new Properties();

      DEFAULT_PROPERTIES.load(stream);
    } catch (IOException e) {
      log.warn("mail properties load exception", e);
      throw new ExceptionInInitializerError(e);
    }
  }

  public Mail(String username, String password, String protocol,
      String host, String port) {
    properties = new Properties();

    properties.put(KEY_USERNAME, username);
    properties.put(KEY_PASSWORD, password);
    properties.put(KEY_PROTOCOL, protocol);
    properties.put(KEY_HOST, host);
    properties.put(KEY_PORT, port);
  }

  public Mail() {
    properties = DEFAULT_PROPERTIES;
  }

  /**
   * 发送邮件到对应的地址
   *
   * @param toMail
   * @param subject
   * @param content
   */
  public void send(String toMail, String subject, String content) throws MessagingException {
    Session mailSession = Session.getDefaultInstance(properties);
    Message msg = new MimeMessage(mailSession);

    msg.setFrom(new InternetAddress(properties.getProperty(KEY_USERNAME)));
    msg.addRecipient(RecipientType.TO, new InternetAddress(toMail));
    msg.setSubject(subject);
    msg.setContent(content, "text/html;charset=utf-8");
    msg.saveChanges();

    Transport transport = mailSession.getTransport(properties.getProperty(KEY_PROTOCOL));
    transport.connect(properties.getProperty(KEY_HOST),
        properties.getProperty(KEY_USERNAME),
        properties.getProperty(KEY_PASSWORD));
    transport.sendMessage(msg, msg.getAllRecipients());
    transport.close();
  }
}
