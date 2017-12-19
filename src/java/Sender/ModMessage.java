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
        returnstr += "<select id='list' onchange='getSelectValue()'>";
        returnstr += "<option value=''>Frei Eingabe</option>";
            
        Map<String, String> rollen = new HashMap<String, String>();
                rollen = DB.DBConnector.getRollennamen();
                for (int i = 1; i <= rollen.size(); i++) {
                   //System.out.print("Verify: " + i + ":" + rollen.get(i) + " ");
                   returnstr += "<option value='" + rollen.get(i) + "'>" + rollen.get(i) + "</option>";
                }

        returnstr += "</select>";
        returnstr += "<input type='text' name='email' placeholder='Email' value='";
            /*String ValueRequired = request.getParameter('RequiredValue');
            System.out.println(ValueRequired);
            if(ValueRequired!=null){
                Map<String, String> emails = new HashMap<String, String>();
                emails = DB.DBConnector.getRollenEmail(ValueRequired);
                for (int i = 1; i <= emails.size(); i++){
                    returnstr += "emails.get(i)+'; '";
                }
            }*/
        returnstr += "'/>";
        returnstr += "<input type='text' name='betreff' placeholder='Betreff' />";
        returnstr += "<textarea name='nachricht' placeholder='Nachricht'></textarea>";   
        returnstr += "<input type='submit' value='Absenden'/>";
                
        return returnstr;
    }

    /**
     * WORKFLOW
     * zuerst alle emaildaten vorladen in arrays (adminarray, lehrerarray, ...) in javascript (funct.js)
     * danach über js-fkt immer die arrayeinträge zur jeweiligen auswahl ausgeben lassen
     * Trennen mit pipe oder strickpunkt und dann splitten o.ä. im Mail-Sender
     * fertig!!
     */
    
}
