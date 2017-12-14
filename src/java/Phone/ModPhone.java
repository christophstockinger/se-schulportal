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
    
    // public static Map getAnwenderdataPhone(String tblname, String email) {
        // Map anwenderdata = DB.DBConnector.getAnwenderdataPhone("tblname", "email");
        
        // return anwenderdata;
    // }
    
    public static String getAnwenderdataPhoneName() {
        String anwenderdataName = "Pri ist schlau!";
        anwenderdataName += "<ul>";
        anwenderdataName += "<li>";
        anwenderdataName += "SELECT sname" + "SELECT srolle" + "FROM anwender";
        anwenderdataName += "</li>";
        anwenderdataName += "</ul>";

        return anwenderdataName;
    }
    
    public static String getAnwenderdataPhoneVorname() {
        String anwenderdataVorname = "";
        anwenderdataVorname += "<ul>";
        anwenderdataVorname += "<li>";
        anwenderdataVorname += "SELECT sname" + "SELECT srolle" + "FROM anwender";
        anwenderdataVorname += "</li>";
        anwenderdataVorname += "</ul>";

        return anwenderdataVorname;
    }
    
    public static String getAnwenderdataPhoneEmail() {
        String anwenderdataEmail = "";
        anwenderdataEmail += "<ul>";
        anwenderdataEmail += "<li>";
        anwenderdataEmail += "SELECT sname" + "SELECT srolle" + "FROM anwender";
        anwenderdataEmail += "</li>";
        anwenderdataEmail += "</ul>";

        return anwenderdataEmail;
    }
    
    public static String getAnwenderdataPhoneTelefon() {
        String anwenderdataTelefon = "";
        anwenderdataTelefon += "<ul>";
        anwenderdataTelefon += "<li>";
        anwenderdataTelefon += "SELECT sname" + "SELECT srolle" + "FROM anwender";
        anwenderdataTelefon += "</li>";
        anwenderdataTelefon += "</ul>";

        return anwenderdataTelefon;
    }
    
    
    
}
