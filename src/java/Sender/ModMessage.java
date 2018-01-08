/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sender;

import java.util.Map;

/**
 * 
 * @author mwitzlsperger und lgraml
 * 
 * Java-Klasse zum Nachrichten senden (SMS und EMail)
 */
public class ModMessage {
    
    public static String modulname = "Nachrichten senden";
    public static String moduldesc = "Wählen Sie ob Sie eine SMS oder Email versenden wollen."; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.
    
    public ModMessage() {}
    
    /**
     * Methode zur Darstellung des Untermenüs<br>
     * Untermenüpunkte: SMS und EMail
     * @return gibt HTML-Code für Unternavigation zurück
     */
    public static String getSubNavigation() {
        String output = "<ul>";
        output += "<li>  <a href='/se-schulportal/messages/sms.jsp'>SMS</a> </li>";
        output += "<li> <a href='/se-schulportal/messages/email.jsp'>Email</a> </li>";
        output += "</ul>";
        return output;
    }
    
    /**
     * Methode zur Darstellung des Formulars für die Email-Versendung
     * <br> - Logik für die Datenbankanbindung
     * <br> - Auslesen der Rollen und Verbindung mit den zugehörigen EMail-Adressen
     * @return returnstr: gibt den HTML/Javascript-Code zurück der in der Variable returnstr gespeichert ist
     */
    
    public static String getEmailForm(){
        String returnstr = "";
        returnstr += "<select id='list'>";
        returnstr += "<option value=''>Freie Eingabe</option>";
        Map rollen = DB.DBConnector.getRollennamen();
        for (int i = 1; i <= rollen.size(); i++) {
           //System.out.print("Verify: " + i + ":" + rollen.get(i) + " ");
           returnstr += "<option value='" + rollen.get(i) + "'>" + rollen.get(i) + "</option>";
        }

        returnstr += "</select>";
        returnstr += "<input type='text' name='email' placeholder='Email' id='empfaenger'> ";
        
        
        Map alleUser = DB.DBConnector.getRollenEmail();
        returnstr += "<script type='text/javascript'>";

        returnstr += "admins = []; ";
        returnstr += "lehrer = []; ";
        returnstr += "eltern = []; ";
        returnstr += "personal = []; ";
        returnstr += "rektor = []; ";
        returnstr += "schueler = [];";
        int admin = 1;
        int lehrer = 1;
        int eltern = 1;
        int personal = 1;
        int rektor = 1;
        int schueler = 1;
        for (int i=1; i <= alleUser.size(); i++){
            // Abfrage welche Rolle hat E-Mail
            
            Map rolle = DB.DBConnector.getAnwenderRollen((String)alleUser.get(i));
            for ( int j=1; j <= rolle.size(); j++) {
                // Admin
                
                if (rolle.get(j).equals("Admin") ) {
                    returnstr += "admins[" + admin++ + "] = '" + alleUser.get(i) + "';";
                }
                // Lehrer
                if (rolle.get(j).equals("Lehrer") ) {
                    
                    returnstr += "lehrer[" + lehrer++ + "] = '" + alleUser.get(i) + "';";
                }
                // Eltern
                if (rolle.get(j).equals("Eltern") ) {
                    
                    returnstr += "eltern[" + eltern++ + "] = '" + alleUser.get(i) + "';";
                    
                }
                // Personal
                if (rolle.get(j).equals("Personal") ) {
                    
                    returnstr += "personal[" + personal++ + "] = '" + alleUser.get(i) + "';";
                    
                }
                // Rektor
                if (rolle.get(j).equals("Rektor") ) {
                    returnstr += "rektor[" + rektor++ + "] = '" + alleUser.get(i) + "';";
                }
                
                // Schueler
                if (rolle.get(j).equals("Schueler") ) {
                    returnstr += "schueler[" + schueler++ + "] = '" + alleUser.get(i) + "';";
                }
                
            }
            

        }
        returnstr += "console.log(admins);";
        returnstr += "</script>";
        returnstr += "<input type='text' name='betreff' placeholder='Betreff' />";
        returnstr += "<textarea name='nachricht' placeholder='Nachricht'></textarea>";
        //returnstr += "<div id='summernote' name='nachricht'></div>";
        returnstr += "<input type='submit' value='Absenden'/>";
                
        return returnstr;
    }
    
    /**
     * Methode zur Darstellung des Formulars für die SMS-Versendung
     * <br> - Logik für die Datenbankanbindung
     * <br> - Auslesen der Rollen und Verbindung mit den zugehörigen Telefonnummern
     * @return gibt den HTML-Code zum Erstellen des SMS-Formulars zurück
     */
    
    public static String getSMSForm(){
        String returnstr = "";
        returnstr += "<select id='smslist'>";
        returnstr += "<option value=''>Freie Eingabe</option>";
        Map rollen = DB.DBConnector.getRollennamen();
        for (int i = 1; i <= rollen.size(); i++) {
           //System.out.print("Verify: " + i + ":" + rollen.get(i) + " ");
           returnstr += "<option value='" + rollen.get(i) + "'>" + rollen.get(i) + "</option>";
        }

        returnstr += "</select>";
        returnstr += "<input type='text' name='sms' placeholder='Telefonnummer' id='empfaenger'> ";
        
        
        Map alleUser = DB.DBConnector.getRollenEmail();
        returnstr += "<script type='text/javascript'>";

        returnstr += "admins = []; ";
        returnstr += "lehrer = []; ";
        returnstr += "eltern = []; ";
        returnstr += "personal = []; ";
        returnstr += "rektor = []; ";
        returnstr += "schueler = [];";
        int admin = 1;
        int lehrer = 1;
        int eltern = 1;
        int personal = 1;
        int rektor = 1;
        int schueler = 1;
        for (int i=1; i <= alleUser.size(); i++){
            // Abfrage welche Rolle hat E-Mail
            Map rolle = DB.DBConnector.getAnwenderRollen((String)alleUser.get(i));
            //Map sms = DB.DBConnector.getRollenSMS((String)alleUser.get(i));
            for ( int j=1; j <= rolle.size(); j++) {
                // Admin
                if (rolle.get(j).equals("Admin") ) {

                    returnstr += "admins[" + admin++ + "] = '" + DB.DBConnector.getRollenSMS((String)alleUser.get(i)).get(1) + "';";
                }
                // Lehrer
                if (rolle.get(j).equals("Lehrer") ) {
                    Map sms = DB.DBConnector.getRollenSMS((String)alleUser.get(i));
                    returnstr += "lehrer[" + lehrer++ + "] = '" + DB.DBConnector.getRollenSMS((String)alleUser.get(i)).get(1) + "';";
                }
                // Eltern
                if (rolle.get(j).equals("Eltern") ) {
                    
                    returnstr += "eltern[" + eltern++ + "] = '" + DB.DBConnector.getRollenSMS((String)alleUser.get(i)).get(1) + "';";
                    
                }
                // Personal
                if (rolle.get(j).equals("Personal") ) {
                    
                    returnstr += "personal[" + personal++ + "] = '" + DB.DBConnector.getRollenSMS((String)alleUser.get(i)).get(1) + "';";
                    
                }
                // Rektor
                if (rolle.get(j).equals("Rektor") ) {
                    returnstr += "rektor[" + rektor++ + "] = '" + DB.DBConnector.getRollenSMS((String)alleUser.get(i)).get(1) + "';";
                }
                
                // Schueler
                if (rolle.get(j).equals("Schueler") ) {
                    returnstr += "schueler[" + schueler++ + "] = '" + DB.DBConnector.getRollenSMS((String)alleUser.get(i)).get(1) + "';";
                }
                
            }
            

        }
        returnstr += "console.log(admins);";
        returnstr += "</script>";
        returnstr += "<textarea name='nachricht' placeholder='Nachricht'></textarea>";
        //returnstr += "<div id='summernote' name='nachricht'></div>";
        returnstr += "<input type='submit' value='Absenden'/>";
        
        return returnstr;
    }
    
}
