/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Notenblatt;

import DB.DBConnector;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Christoph
 */
public class Notenblatt implements Interfaces.IModul {

    public static final String modulname = "Notenblatt";
    public static final String moduldesc = "Online-Notenblatt zur Eintragung der Noten für verschiedene Schüler."; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.

    public Notenblatt() {
    }

    public static String getSubNavigation(String email) {
        Map userrollen = new HashMap(); // Integer und String
        String output = "";

        if (email != null) {
            userrollen = DBConnector.getAnwenderRollen(email); // For Development: Parameter email aktuell hardgecoded!

            if (userrollen.size() == 0) {
                output += "Keine Berechtigung!!";
            } else {
                for (int i = 1; i <= userrollen.size(); i++) {
                    if ( userrollen.get(i).equals("Admin") ) {
                        output += "<ul>";
                        output += "<li> <a href='schueler.jsp'>Schülerübersicht</a> </li>";
                        output += "<li> <a href='exam.jsp'>Prüfung anlegen</a> </li>";
                        output += "<li> <a href='schuelerexport.jsp'>Schülernoten exportieren</a> </li>";
                        output += "</ul>";
                    }
                    if ( userrollen.get(i).equals("Rektor") ) {
                        output += "<ul>";
                        // output += "<li> <a href='schuelerimport.jsp'>Schüler importieren</a> </li>";
                        output += "<li> <a href='exam.jsp'>Prüfung anlegen</a> </li>";
                        // output += "<li> <a href='schuelerexport.jsp'>Schülernoten exportieren</a> </li>";
                        output += "</ul>";
                    }
                    if ( userrollen.get(i).equals("Personal") ) {
                        output += "<ul>";
                        output += "<li> <a href='schueler.jsp'>Schülerübersicht</a> </li>";
                        // output += "<li> <a href='exam.jsp'>Prüfung anlegen</a> </li>";
                        // output += "<li> <a href='schuelerexport.jsp'>Schülernoten exportieren</a> </li>";
                        output += "</ul>";
                    }
                    if ( userrollen.get(i).equals("Lehrer") ) {
                        output += "<ul>";
                        // output += "<li> <a href='schuelerimport.jsp'>Schüler importieren</a> </li>";
                        output += "<li> <a href='exam.jsp'>Prüfung anlegen</a> </li>";
                        // output += "<li> <a href='schuelerexport.jsp'>Schülernoten exportieren</a> </li>";
                        output += "</ul>";
                    }
                }
            }
        } else {
            userrollen.put(0, "Keine Berechtigungen");
            output += userrollen.get(0);
        }

        return output;
    }

    @Override
    public String getSubNavigation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void csvDataImport() {
        
    }
    
    public static String getKlassen() {
        Map alleRollen;
        String returnstr = "";
       
        alleRollen = DB.DBConnector.getRollennamen();
        
        returnstr += "<select name='klasse' id='klasse'>";
        for (int i = 1; i <= alleRollen.size(); i++) {
            if( ( (String) alleRollen.get(i) ).contains("Klasse") ) {
                System.out.println(alleRollen.get(i));
                returnstr += "<option value='" + alleRollen.get(i) + "'>" + alleRollen.get(i) + "</option>";
            }
        }
        returnstr += "</select>";
        
        return returnstr;
    }

}
