package Verify;

import DB.DBConnector;
import Sender.MailSender;
import Sender.SMSSender;
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
        delete, freigabe, freizugeben;
    }
    public static String link = "localhost:8080/se-schulportal/";

    /**
     * Methode zum Versenden der Registrierungsbestätigungsmail
     * @param anr Anrede des neuen Anwenders
     * @param vn Vorname des neuen Anwenders
     * @param nn Nachname des neuen Anwenders
     * @param em E-Mail des neuen Anwenders
     * @return Erfolgsmeldung über den Versand der E-Mail
     */
    public static String sendVerfifyMail(String anr, String vn, String nn, String em) {
        try {
            MailSender send = new MailSender();
            Session absender = send.getGMailSession("mail@dit.education", "Mail2017+1");

            String gruss = "";
            switch (anr) {
                case "Frau":
                    gruss = "Sehr geehrte " + anr;
                    break;

                case "Herr":
                    gruss = "Sehr geehrter " + anr;
                    break;
            }

            // E-Mail Components
            String subject = "Bestaetigung deines Schulportal-Accounts";
            String link = Verify.link + "register/mailverify.jsp?email=" + em + "";
            String message = "<html><head></head><body><p>" + gruss + " " + vn + " " + nn + ", </p><p> vielen Dank für die Registrierung in unserem Schulportal. Um sicher zu stellen, dass das ihre E-Mail-Adresse ist, klicken Sie bitte auf nach folgenden Link: <a href='" + link + "'>Registrierung bestätigen</a></p><p> Sollte es Probleme mit dem Link geben können Sie alternativ, diesen einfach in ihr Browserfenster einfügen.</p>" + emailfooter + "</body></html>";

            // E-Mail versenden
            send.postMail(absender, em, subject, message);

            return "Bitte überprüfen Sie ihr E-Mail-Postfach und bestätigen Sie ihren Account.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Ist es sinnvoll hier die Daten wieder aus der AnwenderDB zu löschen?
            return "Es konnte leider keien E-Mail an dich versendet werden! Bitte wende dich an folgende E-Mail-Adresse: support@schulportal.de";
        }
    }

    /**
     * Methode um zur Ermittlung eines SMS-Verifizierungs-Codes
     */
    public static void setVerifySMSCode() {
        int pin = (int) ((Math.random() * 10000) + (Math.random() * 1000) + (Math.random() * 100) + Math.random());
        Verify.code = pin;
    }

    /**
     * Methode, um den SMS-Verifizierungs-Code wieder verwenden zu können
     * @return Integer mit dem SMS-Verifizierungs-Code
     */
    public static int getVerifySMSCode() {
        return Verify.code;
    }

    /**
     * Methode, die die Auswahlmöglichkeit der Rollen mit Checkboxen zusammenbaut
     * @return StringHTML-Konstrukt
     */
    public static String rollenSelectionOutput() {
        String returnstr = "<div class='row'>";

        Map<String, String> rollen = new HashMap<String, String>();
        try {
            rollen = DBConnector.getRollennamen();
            System.out.println("Rollenanzahl: " + rollen.size());
            for (int i = 1; i <= rollen.size(); i++) {
                System.out.print("Verify: " + i + ":" + rollen.get(i));
                returnstr += "<div class='col-12 col-sm-12 col-md-4 checkbox'><input type='checkbox' class='checkbox' id='rolle-" + i + "'  name='" + rollen.get(i) + "' value='" + rollen.get(i) + "' /><label>" + rollen.get(i) + "</label></div>";
            }
        } catch (NullPointerException err) {
            returnstr += "Leider ist ein Fehler unterlaufen";
            System.out.println(err.getMessage());
        }
        returnstr += "</div>";
        return returnstr;
    }

    /**
     * Methode, die eine E-Mail an den neuen Anwender über die erfolgreiche Verifizierung sendet
     * @param anr Anrede des neuen Anwenders
     * @param vn Vorname des neuen Anwenders
     * @param nn Nachname des neuen Anwenders
     * @param em E-Mail des neuen Anwenders
     * @return Erfolgsmeldung über den Versand der E-Mail
     */
    public static String sendVerifySMSMailUser(String anr, String vn, String nn, String em) {
        try {
            MailSender send = new MailSender();
            Session absender = send.getGMailSession("mail@dit.education", "Mail2017+1");
            String gruss = "";
            switch (anr) {
                case "Frau":
                    gruss = "Sehr geehrte " + anr;
                    break;

                case "Herr":
                    gruss = "Sehr geehrter " + anr;
                    break;
            }

            // E-Mail Components
            String subject = "Freischaltung deines Schulportal-Accounts abgeschlossen";
            String message = "<html><head></head><body><p>" + gruss + " " + vn + " " + nn + ", </p><p> die Schulverwaltung hat ihre Rollen bestätigt und ihr Account ist nun freigeschaltet.</p><p>Sie können Sie jetzt anmleden.<br><a href='" + Verify.link + "'>zur Anmeldung</a></p>" + emailfooter + "</body></html>";

            // E-Mail versenden
            send.postMail(absender, em, subject, message);

            return "Der Benutzer ist nun freigeschaltet. Er wurde per E-Mail über seine Freischaltung informiert.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Ist es sinnvoll hier die Daten wieder aus der AnwenderDB zu löschen?
            return "Es konnte leider keien E-Mail an dich versendet werden! Bitte wende dich an folgende E-Mail-Adresse: support@schulportal.de";
        }
    }

    /**
     * Methode, die eine E-Mail an den/die Admins schickt, dass eine neuer User verifiziert werden muss
     * @param email E-Mail des neuen Anwenders
     * @param anwenderrollen Rollen des neuen Anwenders
     * @return Erfolgsmeldung über den Versand der E-Mail
     */
    public static String sendVerifySMSMailAdmin(String email, Map anwenderrollen) {
        try {
            MailSender send = new MailSender();
            Session absender = send.getGMailSession("mail@dit.education", "Mail2017+1");

            final String adminemail = "cs@christophstockinger.de";
            // GET Userdata
            Map<String, String> anwenderdata = new HashMap<String, String>();
            anwenderdata = DBConnector.getAnwenderdaten(Anwender.databasetablename, email);

            // E-Mail Components
            String subject = "Neue Registrierung eines Users";
            String link = "localhost:8080/se-schulportal/register/adminverify.jsp?email=" + email + "&status=freizugeben";

            // String für Rollen bauen
            String rollen = "";
            for (int i = 0; i < anwenderrollen.size(); i++) {
                rollen += "<li>" + anwenderrollen.get(i) + "</li>";
            }

            String message = "<html><head></head><body><p>Liebe Verwaltung,</p><p> Ein neuer Benutzer muss freigeschaltet werden: <br>Benutzername: " + (String) anwenderdata.get("ANREDE") + " " + (String) anwenderdata.get("VORNAME") + " " + (String) anwenderdata.get("NACHNAME") + "<br>Benutzer-E-Mail: " + email + "</p><p>Der Benutzer hat bereits erfolgreich seine E-Mail-Adresse  und SMS bestätigt.</p><p>Der neue Benutzer beantragt folgende Berechtigungsrollen:<br><ul>" + rollen + "</ul></p><p>Mit folgenden Link können Sie die Berechtigungen und den neuen Benutzer löschen:<br><a href='" + link + "'>" + link + "</a></p><p> Sollte es Probleme mit dem Link geben können Sie alternativ, diesen einfach in ihr Browserfenster einfügen.</p>" + emailfooter + "</body></html>";

            // E-Mail versenden
            send.postMail(absender, adminemail, subject, message);

            return "Bitte überprüfen Sie ihr E-Mail-Postfach und bestätigen Sie ihren Account.";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // Ist es sinnvoll hier die Daten wieder aus der AnwenderDB zu löschen?
            return "Es konnte leider keien E-Mail an dich versendet werden! Bitte wende dich an folgende E-Mail-Adresse: support@schulportal.de";
        }
    }

    /**
     * Methodem, die einen neuen Anwender regisitiert und in die Datenbank schreibt
     * @param anrede Anrede des neuen Anwenders
     * @param vorname Vorname des neuen Anwenders
     * @param nachname Nachname des neuen Anwenders
     * @param telefonnummer Telefonnummer des neuen Anwenders
     * @param email E-Mail-Addresse des neuen Anwenders
     * @param password Password des neuen Anwenders
     * @return Meldungen über die Einträge in den Datenbanken als String
     */
    public static String anwenderRegister(String anrede, String vorname, String nachname, String telefonnummer, String email, String password) {
        String returnstr = "";
        // Parsen der Telefonnummer
        if (telefonnummer != null) {
            String anfang = telefonnummer.substring(0, 1);
            if (anfang.equals("0")) {
                telefonnummer = telefonnummer.substring(1, telefonnummer.length());
                telefonnummer = "+49" + telefonnummer;
            }
        }

        // Database Insert
        Boolean dbinsert = DBConnector.writeRegistryAnwenderData(Anwender.databasetablename, anrede, vorname, nachname, telefonnummer, email, password);
        Boolean dbverifyinsert = DBConnector.writeAnwenderVerify(email, false, false, false);

        if (dbinsert) {
            returnstr += "<p>Deine Daten konnten erfolgreich verarbeitet werden.</p>";

            // Mailversand.
            if (dbverifyinsert) {
                // Mail versenden
                String feedbackverifymail = Verify.sendVerfifyMail(anrede, vorname, nachname, email);

                // Feedback
                returnstr += "<p>" + feedbackverifymail + "</p>";
            } else {
                // Falls Verify-Einträge nicht in DB geschrieben wurden!
                returnstr += "<p>Leider ist bei der Verifizierungseintragung eine Fehler unterlaufen. Bitte wende dich an den Administrator.</p>";
            }
        } else {
            // Falls User-Einträge nicht in DB geschrieben wurden!
            returnstr += "<p>Leider ist bei der Datenverarbeitung ein Fehler unterlaufen! Bitte versuche dich erneut zu registrieren.</p>";
        }

        return returnstr;
    }

    /**
     * Methode, die die E-Mail-Verifizierung des neuen Anwenders verarbeitet und in der Datenbank aktualisisert
     * @param email E-Mail-Adresse des neuen Anwenders
     * @return HTML-Konstrukt als String mit SMS-Verifierzierungs-Formular
     */
    public static String mailVerify(String email) {
        String returnstr = "";
        Boolean verifymail = DBConnector.writeAnwenderVerifystatusMail(email, "true");

        if (verifymail) {
            // E-Mail Verify successful
            returnstr += "<p>Deine E-Mail-Adresse ist nun authentifiziert.</p>";

            // Telefonnummer Verify
            // Powered by Nexmo
            returnstr += "<form method='GET' action='telverify.jsp'><h4>Telefon-Authentifizierung</h4>";

            // GET Telefonnummer
            Map anwenderdata = DBConnector.getAnwenderdaten(Anwender.databasetablename, email);
            String tel = (String) anwenderdata.get("TELEFONNUMMER");

            // Create PIN
            Verify.setVerifySMSCode();
            // Pin bekommen
            int pin = Verify.getVerifySMSCode();

            // Create Message
            String message = "Dein Authentifizierungscode für deine Handynummer " + tel + " lautet: " + pin;
            System.out.println("Message: " + message);
            // Send SMS                            
            SMSSender.sendSMS(tel, message);

            returnstr += "<p>Wir haben Ihnen soeben an ihre Handynummer eine SMS mit einem PIN gesendet.</p>";

            returnstr += "<input type='number' name='pineingabe' id='userpin'>";
            returnstr += "<input type='hidden' value='" + email + "' name='email' id='email'>";
            returnstr += "<button type='submit' class='button' name='telverify' value='true' >Absenden</button>";
            returnstr += "</form>";

        } else {
            returnstr += "<p>Leider ist ein Fehler unterlaufen, bitte wende dich an den Administrator!</p>";
        }
        return returnstr;
    }

    /**
     * Methode, die die Telefon-Verifzierung per SMS verarbeitet und in der Datenbank aktualisiert
     * @param email E-Mail-Addresse des neuen Anwenders
     * @param pineingabe Pineingabe des neuen Anwenders
     * @param pin der richtige Pin
     * @param versuch die Anzahl der Pin-Eingabe-Versuche
     * @return HTML-Konstrukt über Erfolgsmeldung mit Rollenauswahl oder Negativmeldung mit erneuertem Versand sowie Formular
     */
    public static String telVerify(String email, String pineingabe, int pin, int versuch) {
        String returnstr = "";

        // PIN Check
        /* 3 Versuche für die richtige Eingabe:
                         * --> Bei Success: Rollenausgabe 
                         * --> Bei Fail: Löschen von Userdaten aus der Anweder-Database
         */
        // Set Startversuchcounter
        final int startversuch = 0;
        ;

        // Check Set Parameter
        if (pineingabe == null) {

            // Wenn kein Parameter gesetzt ist
            // --> Ausgabe: Formular
            returnstr += "<form method='GET' action='telverify.jsp'>";
            returnstr += "<input type='number' name='pineingabe' id='userpin'>";
            returnstr += "<input type='hidden' value='" + email + "' name='email' id='email'>";
            returnstr += "<button type='submit' class='button' name='telverify' value='true' >Absenden</button>";
            returnstr += "</form> ";
        } else {
            System.out.println("Pineingabe ist da!");
            // Wenn Parameter gesetzt    
            // Set Versuchcounter
            if (versuch == 0) {
                versuch = startversuch + 1;
            } else {
                versuch++;
            }

            // --> Check der Eingabe
            String truepin = Integer.toString(pin);
            if (pineingabe.equals(truepin)) {
                System.out.println("Eingabe ist korrekt!");
                // Korrekt
                returnstr += "<p>Deine Eingabe ist richtig!</p>";
                // Write Database 
                Boolean verifytel = DBConnector.writeAnwenderVerifystatusTel(email, "true");

                if (verifytel) {
                    // Rollenauswahl
                    returnstr += "<form method='GET'><h4>Schritt 3: Rollenauswahl</h4>";
                    returnstr += "<p>Bitte wähle deine Rollen aus.</p>";
                    returnstr += Verify.rollenSelectionOutput();
                    returnstr += "<input type='hidden' value='" + email + "' name='email' id='email'>";
                    returnstr += "<button type='button' class='button' onclick=\"rollen()\">Speichern</button>";
                    returnstr += "</form>";
                } else {
                    returnstr += "<p> Es ist ein Fehler unterlaufen!. Bitte wende dich an den Administrator!</p>";
                }

            } else {
                returnstr += "<p>Die Eingabe war leider nicht erfolgreich</p>";
                // Dritter Versuch
                if (versuch == 3) {
                    // Userdaten aus DB löschen
                    String deletefeedback = DBConnector.deleteAnwenderdaten(email);
                    returnstr += "<p>" + deletefeedback + "</p>";
                } else {

                    // Versuch um eins erhöhen
                    versuch++;

                    // Sende neuen Pin
                    // GET Telefonnummer
                    Map anwenderdata = DBConnector.getAnwenderdaten(Anwender.databasetablename, email);
                    String tel = (String) anwenderdata.get("TELEFONNUMMER");

                    // Create PIN
                    Verify.setVerifySMSCode();
                    // Pin bekommen
                    pin = Verify.getVerifySMSCode();

                    // Create Message
                    String message = "Dein Authentifizierungscode für deine Handynummer " + tel + " lautet: " + pin;
                    System.out.println("Message: " + message);
                    // Send SMS                            
                    SMSSender.sendSMS(tel, message);

                    returnstr += "<p>Wir haben Ihnen soeben an ihre Handynummer eine SMS mit einem neuen PIN gesendet.</p>";
                    returnstr += "<form method='GET' action='telverify.jsp'>";
                    returnstr += "<input type='number' name='pineingabe' id='userpin'>";
                    returnstr += "<input type='hidden' value='" + email + "' name='email' id='email'>";
                    returnstr += "<button type='submit' class='button' name='telverify' value='true' >Absenden</button>";
                    returnstr += "</form> ";
                }
            }
        }

        return returnstr;
    }

    /**
     * Methode, die die Admin-Verifizierung verarbeitet und in der Datenbank aktualisiert
     * @param email E-Mail-Adresse des neuen Anwenders
     * @param status Status des Verifizierungsverfahren
     * @param rolle eine gelöschte Rolle
     * @return HTML-Konstrukt mit Erfolgsmeldung bzw. bei Rollenlöschung neuer Formularaufbau ohne gelöschte Rolle
     */
    public static String adminVerify(String email, String status, String rolle) {
        String returnstr = "";
        Map anwenderdata;
        Map anwenderrollen;
        
        switch(status) {
            case "freizugeben":
                 anwenderdata = DBConnector.getAnwenderdaten(Anwender.databasetablename, email);
                 anwenderrollen = DBConnector.getAnwenderRollen(email);
                 
                returnstr +="<form method='GET' action='adminverify.jsp'><p class='textleft'>Folgender neuer Benutzer muss freigegeben werden:</p>";
                returnstr +="<input type='hidden' name='email' value='" + anwenderdata.get("EMAIL") + "'>";
                returnstr +="<p class='textleft'> Vorname: " + anwenderdata.get("VORNAME") + "</p>";
                returnstr +="<p class='textleft'> Nachname: " + anwenderdata.get("NACHNAME") + "</p>";
                returnstr +="<p class='textleft'> E-Mail: <span id='email'>" + anwenderdata.get("EMAIL") + "</span></p>";
                returnstr +="<p class='textleft'> Telefonnummer: " + anwenderdata.get("TELEFONNUMMER") + "</p>";
                if (anwenderrollen == null) {
                    returnstr += "<p class='textleft'>Leider wurden keine Rollen gefunden.</p>";
                } else {
                    returnstr += "<div class='rollen'><p>Der User beantragt folgende <span id='anzahl'>" + anwenderrollen.size() + "</span> Rollen:</p>";
                    returnstr += "<ul>";
                    for (int i = 1; i <= anwenderrollen.size(); i++) {
                        returnstr += "<li>" + anwenderrollen.get(i) + "<button name='status' value='delete' class='deletebtn'><input type='hidden' name='rolle' value='" + anwenderrollen.get(i) + "'><img src='/se-schulportal/images/icons/trash.svg' alt='Löschen' /></button></li>";
                    }
                }
                returnstr += "</ul></div>";
                returnstr += "<button class='button' name='status' value='freigabe'>Benutzer freigeben</button>";
                returnstr += "</form>";
                
                break;
                
            case "delete":
                String deletefeedback = DBConnector.deleteAnwenderRolle(email, rolle);

                // Get Anwenderdaten
                anwenderdata = DBConnector.getAnwenderdaten(Anwender.databasetablename, email );

                // Get Rollen
                anwenderrollen = DBConnector.getAnwenderRollen( email );

                returnstr += "<p>" + deletefeedback + "</p>";
                returnstr += "<form method='GET' action='adminverify.jsp'><p class='textleft'>Folgender neuer Benutzer muss freigegeben werden:</p>";
                returnstr += "<input type='hidden' name='email' value='" + anwenderdata.get("EMAIL") + "'>";
                returnstr += "<p class='textleft'> Vorname: " + anwenderdata.get("VORNAME") + "</p>";
                returnstr += "<p class='textleft'> Nachname: " + anwenderdata.get("NACHNAME") + "</p>";
                returnstr += "<p class='textleft'> E-Mail: <span id='email'>" + anwenderdata.get("EMAIL") + "</span></p>";
                returnstr += "<p class='textleft'> Telefonnummer: " + anwenderdata.get("TELEFONNUMMER") + "</p>";
                if (anwenderrollen == null) {
                    returnstr += "<p class='textleft'>Leider wurden keine Rollen gefunden.</p>";
                } else {
                    returnstr += "<div class='rollen'><p>Der User beantragt folgende <span id='anzahl'>" + anwenderrollen.size() + "</span> Rollen:</p>";
                    returnstr += "<ul>";
                    for (int i = 1; i <= anwenderrollen.size(); i++) {
                        returnstr += "<li>" + anwenderrollen.get(i) + "<button name='status' value='delete' class='deletebtn'><input type='hidden' name='rolle' value='" + anwenderrollen.get(i) + "'><img src='/se-schulportal/images/icons/trash.svg' alt='Löschen' /></button></li>";
                    }
                }
                returnstr += "</ul></div>";
                returnstr += "<button class='button' name='status' value='freigabe'>Benutzer freigeben</button>";
                
                break;
                
            case "freigabe":
                anwenderdata = DBConnector.getAnwenderdaten(Anwender.databasetablename, email );

                // SET VerifyAdmin in Database
                Boolean adminverify = DBConnector.writeAnwenderVerifystatusAdmin( email, "true");
                String freigabefeedback;
                if (adminverify) {
                    // Send Verifymail User
                    freigabefeedback = Verify.sendVerifySMSMailUser((String) anwenderdata.get("ANREDE"), (String) anwenderdata.get("VORNAME"), (String) anwenderdata.get("NACHNAME"), email);
                } else {
                    freigabefeedback = "Leider ist etwas schief gelaufen. Bitte melde sich bei support@schulportal.de";
                }

                returnstr += "<p>" + freigabefeedback + "</p>";
                
                break;
        }
        
        return returnstr;
    }
    
    /**
     * Methode, die die Rollenauswahl des neuen Anwenders verarbeitet und in der Datenbank erfasst
     * @param email E-Mail-Addresse des neuen Anwenders
     * @param anwenderrollen Map mit allen ausgewählten Rollen des neuen Anwenders
     * @return Erfolgs-/Negativmeldung über den Datenbankeintrag
     */
    public static String rollenVerify(String email, Map anwenderrollen) {
        String returnstr = "";
        
         Boolean rollenwrite = DBConnector.writeAnwenderRollen(email, anwenderrollen);

        if (rollenwrite) {
            returnstr += "<p>Deine Rollen wurden vermerkt und gespeichert.</p>";
            returnstr += "<p>Sobald ihr Account freigeschaltet ist, bekommen Sie eine E-Mail von uns.</p>";
            // Send E-Mail to Administration
            Verify.sendVerifySMSMailAdmin(email, anwenderrollen);
        } else {
            returnstr += "<p>Leider ist ein Fehler unterlaufen, bitte melde dich bei support@schulsportal.de.</p>";
        }
        
        return returnstr;
    }

}
