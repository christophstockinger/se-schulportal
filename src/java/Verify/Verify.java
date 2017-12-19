package Verify;

import DB.DBConnector;
import Sender.MailSender;
import anwender.Anwender;
import anwender.Rollenname;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Christoph
 */
public class Verify {

    public static int code;
    public static String emailfooter = "<p>Bei Problemen melden Sie sich bitte bei folgender E-Mail-Adresse: support@schulportal.de</p><br></p> Ihr Schulportal-Team</p>";
    public static enum statuscodes {
        delete, freigabe;
    }

    // Send Verify-E-Mail with Link
    public static String sendVerfifyMail(String anr, String vn, String nn, String em) {
        try {
            MailSender send = new MailSender();
           

            // E-Mail Components
            String subject = "Bestaetigung deines Schulportal-Accounts";
            String link = "localhost:8080/schulportal/register/mailverify.jsp?email=" + em + "";
            String message = "<html><head></head><body><p>Sehr geehrter " + anr + " " + vn + " " + nn + ", </p><p> vielen Dank für die Registrierung in unserem Schulportal. Um sicher zu stellen, dass das ihre E-Mail-Adresse ist, klicken Sie bitte auf nach folgenden Link: <a href='" + link + "'>" + link + "</a></p><p> Sollte es Probleme mit dem Link geben können Sie alternativ, diesen einfach in ihr Browserfenster einfügen.</p>" + emailfooter + "</body></html>";

            // E-Mail versenden
            send.postMail(em, subject, message);

            return "Bitte überprüfen Sie ihr E-Mail-Postfach und bestätigen Sie ihren Account.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Ist es sinnvoll hier die Daten wieder aus der AnwenderDB zu löschen?
            return "Es konnte leider keien E-Mail an dich versendet werden! Bitte wende dich an folgende E-Mail-Adresse: support@schulportal.de";
        }
    }

    // Generate SMS Code
    public static void setVerifySMSCode() {
        int pin = (int) ((Math.random() * 10000) + (Math.random() * 1000) + (Math.random() * 100) + Math.random());
        Verify.code = pin;
    }

    // Return SMS Code
    public static int getVerifySMSCode() {
        return Verify.code;
    }

    // Rollenausgabe / auswahl
    public static String rollenSelectionOutput() {
        String output = "";

        Map<String, String> rollen = new HashMap<String, String>();
        try {
            rollen = DBConnector.getRollennamen();
            System.out.println("Rollenanzahl: " + rollen.size());
            for (int i = 1; i <= rollen.size(); i++) {
                System.out.print("Verify: " + i + ":" + rollen.get(i) + " ");
                output += "<div class='checkbox'><input type='checkbox' class='checkbox' id='rolle-" + i + "'  name='" + rollen.get(i) + "' value='" + rollen.get(i) + "' /><label>" + rollen.get(i) + "</label></div>";
            }
        } catch (NullPointerException err) {
            output += "Leider ist ein Fehler unterlaufen";
            System.out.println(err.getMessage());
        }
        return output;
    }

    // Send Mail to User after SMSCode Verify
    public static String sendVerifySMSMailUser(String anr, String vn, String nn, String em) {
        try {
            MailSender send = new MailSender();
            
            
            String link = "localhost:8080/schulportal/";

            // E-Mail Components
            String subject = "Freischaltung deines Schulportal-Accounts abgeschlossen";
            String message = "<html><head></head><body><p>Sehr geehrter " + anr + " " + vn + " " + nn + ", </p><p> die Schulverwaltung hat ihre Rollen bestätigt und ihr Account ist nun freigeschaltet.</p><p>Sie können Sie jetzt unter " + link + " anmelden.</p>" + emailfooter + "</body></html>";

            // E-Mail versenden
            send.postMail(em, subject, message);

            return "Der Benutzer ist nun freigeschaltet. Er wurde per E-Mail über seine Freischaltung informiert.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Ist es sinnvoll hier die Daten wieder aus der AnwenderDB zu löschen?
            return "Es konnte leider keien E-Mail an dich versendet werden! Bitte wende dich an folgende E-Mail-Adresse: support@schulportal.de";
        }
    }

    // Send Mail to Administration after SMSCode Verify
    // TODO: Mail content fertig schreiben Rollen einbauen!
    public static String sendVerifySMSMailAdmin(String email, Map anwenderrollen) {
        try {
            MailSender send = new MailSender();
            

            final String adminemail = "cs@christophstockinger.de";
            // GET Userdata
            Map<String, String> anwenderdata = new HashMap<String, String>();
            anwenderdata = DBConnector.getAnwenderdaten(Anwender.databasetablename, email);

            // E-Mail Components
            String subject = "Neue Registrierung eines Users";
            String link = "localhost:8080/schulportal/register/adminverify.jsp?email=" + email + "&status=freizugeben";

            // String für Rollen bauen
            String rollen = "";
            for (int i = 0; i < anwenderrollen.size(); i++) {
                rollen += "<li>" + anwenderrollen.get(i) + "</li>";
            }

            String message = "<html><head></head><body><p>Liebe Verwaltung,</p><p> Ein neuer Benutzer muss freigeschaltet werden: <br>Benutzername: " + (String) anwenderdata.get("ANREDE") + " " + (String) anwenderdata.get("VORNAME") + " " + (String) anwenderdata.get("NACHNAME") + "<br>Benutzer-E-Mail: " + email + "</p><p>Der Benutzer hat bereits erfolgreich seine E-Mail-Adresse  und SMS bestätigt.</p><p>Der neue Benutzer beantragt folgende Berechtigungsrollen:<br><ul>" + rollen + "</ul></p><p>Mit folgenden Link können Sie die Berechtigungen und den neuen Benutzer löschen:<br><a href='" + link + "'>" + link + "</a></p><p> Sollte es Probleme mit dem Link geben können Sie alternativ, diesen einfach in ihr Browserfenster einfügen.</p>" + emailfooter + "</body></html>";

            // E-Mail versenden
            send.postMail(adminemail, subject, message);

            return "Bitte überprüfen Sie ihr E-Mail-Postfach und bestätigen Sie ihren Account.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Ist es sinnvoll hier die Daten wieder aus der AnwenderDB zu löschen?
            return "Es konnte leider keien E-Mail an dich versendet werden! Bitte wende dich an folgende E-Mail-Adresse: support@schulportal.de";
        }
    }

}
