package Sender;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {

    public MailSender() {
    }

    /**
     * Methode um eine Gmail-Verbindung aufzubauen
     *
     * @param user ist die E-Mail-Adresse von GMail
     * @param pass ist das dazugehörige Passwort
     * @return Session die zum Senden von Daten benutzt wird
     */
    public static Session getGMailSession(String user, String pass) {
        final String username = user;
        final String password = pass;
        
        final Properties props = new Properties();

        // Zum Senden
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", SenderKonstanten.PORTLOCAL);
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
     * Methode um eine 1und1-Verbindung aufzubauen
     *
     * @param user E-Mail-Adrese des 1und1 Accounts
     * @param pass das dazugehörige Passwort
     * @return Session die zum Senden von Daten benutzt wird
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
     * Sende eine Nachricht per Email
     *
     * @param recipient gültige Email-Adresse des Empfängers
     * @param subject Betreff der Email
     * @param message Inhalt der Email
     * @throws MessagingException wirft eine Exception
     */
    public static void postMail(String recipient, String subject, String message) throws MessagingException {
        Session s = getGMailSession(SenderKonstanten.EMAILSENDER, SenderKonstanten.EMAILPASSWORT);
        Message msg = new MimeMessage(s);
        String filename = "Dateipfad";
        
        msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

        msg.setSubject(ModMessage.convertFromUTF8(subject));
        msg.setContent(ModMessage.convertFromUTF8(message), "text/html; charset=utf-8");
        
        
        // sends the e-mail
        Transport.send(msg);
    }
    
}