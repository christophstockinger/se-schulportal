/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Profile_edit;

import anwender.Anwender;


/**
 *
 * @author Christoph
 */
public class Edit {
    
    public static String modulname = "Profilbearbeitung";
    public static String moduldesc = "Hier können Sie auf ihre Persönlichen Daten einsehen und diese ändern."; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.
    
    
    
    
    public Edit() {}
    
    
    public static String getSubNavigation() {
        String output = "<ul>";
        output += "<li>  <a href=''>Daten betrachten</a> </li>";
        output += "<li> <a href='/se-schulportal/profile_edit/edit_1.jsp'>Daten bearbeiten</a> </li>";
        output += "</ul>";
        return output;
    }
    
    public static String getSubNavigation2() {
        String output = "<ul>";
        output += "<li>  <a href='/se-schulportal/profile_edit/edit.jsp'>Daten betrachten</a> </li>";
        output += "<li> <a href=''>Daten bearbeiten</a> </li>";
        output += "</ul>";
        return output;
    }
    
    public static String getProfilData(){
        String email = "";
        String password = "";
        String anrede = "";
        String vorname = "";
        String nachname = "";
        String telefonnummer = "";
        String profilOutput;
        Anwender user = new Anwender(anrede, vorname, nachname, email, telefonnummer, password);
        profilOutput= "<form>";
        profilOutput+= "<input type='text' name='anrede' placeholder='"+anrede+"' />";
        profilOutput+= "<input type='text' name='vorname' placeholder='"+vorname+"' />";
        profilOutput+= "<input type='text' name='nachname' placeholder='"+nachname+"' />";
        profilOutput+= "<input type='text' name='email' placeholder='"+email+"' />";
        profilOutput+= "<input type='text' name='telefonnummer' placeholder='"+telefonnummer+"' />";
        profilOutput+= "</form>";
        return profilOutput;
}
    
    
}
