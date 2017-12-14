package Sender;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

    public MailSender() {
    }

    /**
     * Methode Gmail Verbindung aufzubauen
     *
     * @param user ist die E-Mail-Adresse von GMail
     * @param pass ist das dazugehörige Passwort
     * @return session to be used to send data
     */
    public Session getGMailSession(String user, String pass) {
        final String username = user;
        final String password = pass;
        
        final String portlocal = "587";
        final String portlive = "465";
        
        final Properties props = new Properties();

        // Zum Senden
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", portlocal);
        props.setProperty("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");

        props.setProperty("mail.smtp.from", username);
        props.setProperty("mail.smtp.user", username);
        props.setProperty("mail.smtp.password", password);

        return Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
    
    /**
     * Methode 1und1 Verbindung aufzubauen
     *
     * @param user E-Mail-Adrese des 1und1 Accounts
     * @param pass das dazugehörige Passwort
     * @return session to be used to send data
     */
    public Session get1and1Session(String user, String pass) {
        final String username = user;
        final String password = pass;
        
        final Properties props = new Properties();

        // Zum Senden
        props.setProperty("mail.smtp.host", "smtp.1und1.de");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.socketFactory.port", "587");
        props.setProperty("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");

        props.setProperty("mail.smtp.from", username);
        props.setProperty("mail.smtp.user", username);
        props.setProperty("mail.smtp.password", password);

        return Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    /**
     * Send a message with email
     *
     * @param session valid session form gerGmail Session
     * @param recipient valid email address
     * @param subject subject of our mail
     * @param message our message as string
     * @throws MessagingException will throw exception
     */
    public void postMail(Session session, String recipient,
            String subject, String message)
            throws MessagingException {
        Message msg = new MimeMessage(session);

        InternetAddress addressTo = new InternetAddress(recipient);
        msg.setRecipient(Message.RecipientType.TO, addressTo);

        msg.setSubject(subject);
        msg.setContent(message, "text/html; charset=utf-8");
        Transport.send(msg);
    }

}
