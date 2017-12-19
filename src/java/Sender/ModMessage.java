/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sender;

import Modul_example.*;

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
    
    
    
    
}
