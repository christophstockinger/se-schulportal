/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Phone;

import java.util.Map;

/**
 *
 * @author Christoph
 */
public class ModPhone {
    
    public static String modulname = "Kontakt";
    public static String moduldesc = "Geben Sie im oberen Textfeld den Namen der gesuchten Person ein. <br>Danach wählen Sie die entsprechende Rolle, in der gesucht werden soll."; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.
    
    public ModPhone() {}
    
    public static String getSubNavigation() {
        String output = "<ul>";
        output += "<li>  <a href='index.jsp'>Zurück zur Suche</a> </li>"; 
        output += "</ul>";
        return output;
    }
    
    /*public static Map getAnwenderdataPhone() {
         
         Map anwenderdata = DB.DBConnector.getAnwenderdataPhone("ANWENDER");
        
         return anwenderdata;
    }*/
    
    public static String getAnwenderdataPhone(String name, String rolle) {
        
        
        
        String anwenderdata = "";
        
        anwenderdata += "<table>";
        anwenderdata += "<tr>";
        anwenderdata += "<th>Name</th>" + "<th>Vorname</th>" + "<th>E-Mail</th>" + "<th>Telefonnummer</th>";
        anwenderdata += "</tr>";
        anwenderdata += "<tr>";
        anwenderdata += "<td>" + "hallo" + "</td>" + "<td>" + "whats" + "</td>" + "<td>" + "whats" + "</td>" + "<td>" + "whats" + "</td>";
        anwenderdata += "</tr>";
        anwenderdata += "</table>";

        return anwenderdata;
    }
 
}