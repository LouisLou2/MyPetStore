package org.csu.mypetstore.utils;

import com.sun.mail.util.MailSSLSocketFactory;
import jakarta.mail.Authenticator;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.csu.mypetstore.constant.AppProperties;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class EmailUtil {
    private static Properties emailProperties;
    private static String propertiesPath = "emailsupport.properties";
    
    private static jakarta.mail.Session session;
    private static InternetAddress sender;
    static {
        //load email properties
        emailProperties = new Properties();
        try (InputStream inputStream = EmailUtil.class.getClassLoader().getResourceAsStream(propertiesPath)) {
            emailProperties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        sf.setTrustAllHosts(true);
        emailProperties.put("mail.smtp.ssl.socketFactory", sf);
        String userName = emailProperties.getProperty("auth.username");
        String password = emailProperties.getProperty("auth.password");
        session = Session.getDefaultInstance(emailProperties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(userName, password); //发件人邮件用户名、密码
            }
        });
        try {
            sender = new InternetAddress(emailProperties.getProperty("auth.username"), AppProperties.APP_NAME);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean sendEmail(String to, String subject, String content) {
        boolean result = false;
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(sender);
            message.setRecipients(MimeMessage.RecipientType.TO, to);
            message.setSubject(subject);
            message.setContent(content, "text/html;charset=UTF-8");
            jakarta.mail.Transport.send(message);
            result = true;
        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } 
        return result;
    }
    //test
    public static void main(String[] args) {
        String to = "865113609@qq.com";
        String subject = "test";
        String content = "test";
        boolean result = EmailUtil.sendEmail(to, subject, content);
        System.out.println(result);
    }
}
