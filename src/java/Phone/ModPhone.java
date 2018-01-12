/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Phone;

import java.util.Map;
import anwender.Anwender;

/**
 *
 * @author Christoph
 */
public class ModPhone {

    public static String modulname = "Kontakt";
    public static String moduldesc = "Geben Sie im oberen Textfeld den Namen der gesuchten Person ein. <br>Danach wählen Sie die entsprechende Rolle, in der gesucht werden soll."; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.

    public ModPhone() {
    }

    public static String getSubNavigation() {
        String output = "<ul>";
        output += "<li>  <a href='index.jsp'>Zurück zur Suche</a> </li>";
        output += "</ul>";
        return output;
    }

    public static String getAnwenderdataContacts(String name, String klasse) {

        Map allPhoneContacts = DB.DBConnector.getAnwenderdataContact(name.toUpperCase(), klasse);

        String anwenderdata = "";

        anwenderdata += "<table>";
            anwenderdata += "<tr>";
            anwenderdata += "<th>Name</th>" + "<th>Vorname</th>" + "<th>E-Mail</th>" + "<th>Telefonnummer</th>";
            anwenderdata += "</tr>";

            for (int i = 1; i <= allPhoneContacts.size(); i++) {

            // DB-Aufruf mit E-Mail-Addresse um User-Daten zu bekommen
            String email = (String) allPhoneContacts.get(i);
            Map userdata = DB.DBConnector.getAnwenderdaten(Anwender.databasetablename, email);

            anwenderdata += "<tr>";

            anwenderdata += "<td>" + (String) userdata.get("NACHNAME") + "</td>";
            anwenderdata += "<td>" + (String) userdata.get("VORNAME") + "</td>";
            anwenderdata += "<td>" + (String) userdata.get("EMAIL") + "</td>";
            anwenderdata += "<td>" + (String) userdata.get("TELEFONNUMMER") + "</td>";

            anwenderdata += "</tr>";
            }

        anwenderdata += "</table>";

        return anwenderdata;
    }

}
