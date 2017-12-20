/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sender;

import Modul_example.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Christoph
 */
public class ModMessage {
    
    public static String modulname = "Nachrichten senden";
    public static String moduldesc = "Wählen Sie ob Sie eine SMS oder Email versenden wollen."; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.
    
    public ModMessage() {}
    
    public static String getSubNavigation() {
        String output = "<ul>";
        output += "<li>  <a href='/se-schulportal/messages/sms.jsp'>SMS</a> </li>";
        output += "<li> <a href='/se-schulportal/messages/email.jsp'>Email</a> </li>";
        output += "</ul>";
        return output;
    }
    
    public static String getEmailForm(){
        String returnstr = "";
        returnstr += "<select id='list'>";
        returnstr += "<option value=''>Frei Eingabe</option>";
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
        
        for (int i=1; i <= alleUser.size(); i++){
            // Abfrage welche Rolle hat E-Mail
            Map rolle = DB.DBConnector.getAnwenderRollen((String)alleUser.get(i));
            for ( int j=1; j <= rolle.size(); j++) {
                // Admin
                if (rolle.get(j).equals("Admin") ) {
                    returnstr += "admins[" + i + "] = '" + alleUser.get(i) + "';";
                }
                // Lehrer
                if (rolle.get(j).equals("Lehrer") ) {
                    returnstr += "lehrer[" + i + "] = '" + alleUser.get(i) + "';";
                }
                // Eltern
                if (rolle.get(j).equals("Eltern") ) {
                    returnstr += "eltern[" + i + "] = '" + alleUser.get(i) + "';";
                }
                // Personal
                if (rolle.get(j).equals("Personal") ) {
                    returnstr += "personal[" + i + "] = '" + alleUser.get(i) + "';";
                }
                // Rektor
                if (rolle.get(j).equals("Rektor") ) {
                    returnstr += "rektor[" + i + "] = '" + alleUser.get(i) + "';";
                }
            }

        }
        returnstr += "console.log(admins);";
        returnstr += "</script>";
        returnstr += "<input type='text' name='betreff' placeholder='Betreff' />";
        returnstr += "<textarea name='nachricht' placeholder='Nachricht'></textarea>";   
        returnstr += "<input type='submit' value='Absenden'/>";
                
        return returnstr;
    }

    
    
    /**
     * WORKFLOW
     * zuerst alle emaildaten vorladen in arrays (adminarray, lehrerarray, ...) in javascript (funct.js)
     * danach über js-fkt immer die arrayeinträge zur jeweiligen auswahl ausgeben lassen
     * Trennen mit pipe oder strichpunkt und dann splitten o.ä. im Mail-Sender
     * fertig!!
     */
    
}
