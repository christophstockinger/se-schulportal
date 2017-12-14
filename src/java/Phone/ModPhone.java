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
    public static String moduldesc = "Geben Sie im oberen Textfeld den Namen der gesuchten Person ein. <br>Danach wählen Sie eine Rolle, in der gesucht werden soll."; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.
    
    public ModPhone() {}
    
    public static String getSubNavigation() {
        String output = "<ul>";
        output += "<li>  <a href='index.jsp'>Zurück zur Suche</a> </li>"; 
        output += "</ul>";
        return output;
    }
    
    // public static Map getAnwenderdataPhone(String tblname, String email) {
        // Map anwenderdata = DB.DBConnector.getAnwenderdataPhone("tblname", "email");
        
        // return anwenderdata;
    // }
    
    public static String getAnwenderdataPhoneName() {
        String anwenderdataPhone = "Pri ist schlau!";
        anwenderdataPhone += "<ul>";
        anwenderdataPhone += "<li>";
        anwenderdataPhone += "SELECT sname" + "SELECT srolle" + "FROM anwender";
        anwenderdataPhone += "</li>";
        anwenderdataPhone += "</ul>";

        return anwenderdataPhone;
    }
    
    public static String getAnwenderdataPhoneVorname() {
        String anwenderdataPhone = "";
        anwenderdataPhone += "<ul>";
        anwenderdataPhone += "<li>";
        anwenderdataPhone += "SELECT sname" + "SELECT srolle" + "FROM anwender";
        anwenderdataPhone += "</li>";
        anwenderdataPhone += "</ul>";

        return anwenderdataPhone;
    }
    
    public static String getAnwenderdataPhoneEmail() {
        String anwenderdataPhone = "";
        anwenderdataPhone += "<ul>";
        anwenderdataPhone += "<li>";
        anwenderdataPhone += "SELECT sname" + "SELECT srolle" + "FROM anwender";
        anwenderdataPhone += "</li>";
        anwenderdataPhone += "</ul>";

        return anwenderdataPhone;
    }
    
    public static String getAnwenderdataPhoneTelefon() {
        String anwenderdataPhone = "";
        anwenderdataPhone += "<ul>";
        anwenderdataPhone += "<li>";
        anwenderdataPhone += "SELECT sname" + "SELECT srolle" + "FROM anwender";
        anwenderdataPhone += "</li>";
        anwenderdataPhone += "</ul>";

        return anwenderdataPhone;
    }
    
    
    
}
