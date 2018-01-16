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
    public static String moduldesc = "Im oberen Feld können Sie die Klasse wählen. <br>Im unteren Feld können Sie nach spezifisch nach Namen suchen."; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.

    public ModPhone() {
    }

    public static String getSubNavigation() {
        String output = "<ul>";
        output += "<li>  <a href='index.jsp'>Zurück zur Suche</a> </li>";
        output += "</ul>";
        return output;
    }

    public static String getAnwenderdataContacts(String name, String klasse) {

        Map allContacts, allName, allClass;

        String anwenderdata = "";

        anwenderdata += "<table>";
        anwenderdata += "<tr>";
        anwenderdata += "<th>Name</th>" + "<th>Vorname</th>" + "<th>E-Mail</th>" + "<th>Telefonnummer</th>";
        anwenderdata += "</tr>";

        // Klasse = alle und Name = leer
        if ((klasse.equals("alle")) && ((name.equals("")))) {
            allContacts = DB.DBConnector.getAnwenderdataAll();

            if (allContacts.size() > 0) {
                for (int i = 1; i <= allContacts.size(); i++) {

                    // DB-Aufruf mit E-Mail-Addresse um User-Daten zu bekommen
                    String email = (String) allContacts.get(i);
                    Map userdata = DB.DBConnector.getAnwenderdaten(Anwender.databasetablename, email);

                    anwenderdata += "<tr>";

                    anwenderdata += "<td>" + (String) userdata.get("NACHNAME") + "</td>";
                    anwenderdata += "<td>" + (String) userdata.get("VORNAME") + "</td>";
                    anwenderdata += "<td>" + (String) userdata.get("EMAIL") + "</td>";
                    anwenderdata += "<td>" + (String) userdata.get("TELEFONNUMMER") + "</td>";

                    anwenderdata += "</tr>";
                }
            } else {
                anwenderdata += "<tr><td colspan='4'>Keine Einträge gefunden.</td></tr>";
            }

        } else {

            // Klasse = alle und Name = gesetzt
            if (((!name.equals("")) || (!name.equals(""))) && (klasse.equals("alle"))) {
                allName = DB.DBConnector.getAnwenderdataName(name.toUpperCase());

                if (allName.size() > 0) {
                    for (int i = 1; i <= allName.size(); i++) {

                        // DB-Aufruf mit E-Mail-Addresse um User-Daten zu bekommen
                        String email = (String) allName.get(i);
                        Map userdata = DB.DBConnector.getAnwenderdaten(Anwender.databasetablename, email);

                        anwenderdata += "<tr>";

                        anwenderdata += "<td>" + (String) userdata.get("NACHNAME") + "</td>";
                        anwenderdata += "<td>" + (String) userdata.get("VORNAME") + "</td>";
                        anwenderdata += "<td>" + (String) userdata.get("EMAIL") + "</td>";
                        anwenderdata += "<td>" + (String) userdata.get("TELEFONNUMMER") + "</td>";

                        anwenderdata += "</tr>";
                    }
                } else {
                    anwenderdata += "<tr><td colspan='4'>Keine Einträge gefunden.</td></tr>";
                }

            } else {

                // Klasse = spezifische Klasse und Name = leer
                if (!klasse.equals("alle") && (name.equals(""))) {
                    allClass = DB.DBConnector.getAnwenderdataClass(klasse);

                    if (allClass.size() > 0) {
                        for (int i = 1; i <= allClass.size(); i++) {

                            // DB-Aufruf mit E-Mail-Addresse um User-Daten zu bekommen
                            String email = (String) allClass.get(i);
                            Map userdata = DB.DBConnector.getAnwenderdaten(Anwender.databasetablename, email);

                            anwenderdata += "<tr>";

                            anwenderdata += "<td>" + (String) userdata.get("NACHNAME") + "</td>";
                            anwenderdata += "<td>" + (String) userdata.get("VORNAME") + "</td>";
                            anwenderdata += "<td>" + (String) userdata.get("EMAIL") + "</td>";
                            anwenderdata += "<td>" + (String) userdata.get("TELEFONNUMMER") + "</td>";

                            anwenderdata += "</tr>";
                        }
                    } else {
                        anwenderdata += "<tr><td colspan='4'>Keine Einträge gefunden.</td></tr>";
                    }

                } else {

                    // Klasse = spezifische Klasse und Name = gesetzt
                    if (!klasse.equals("alle") && (!name.equals(""))) {
                        allClass = DB.DBConnector.getAnwenderdataClass(klasse);
                        allName = DB.DBConnector.getAnwenderdataName(name.toUpperCase());

                        if (allClass.size() > 0) {
                            for (int i = 1; i <= allClass.size(); i++) {
                                String emailclass = (String) allClass.get(i);

                                if (allName.size() > 0) {
                                    for (int j = 1; j <= allName.size(); j++) {
                                        String emailname = (String) allName.get(j);

                                        if (emailclass.equals(emailname)) {
                                            
                                            // DB-Aufruf mit E-Mail-Addresse um User-Daten zu bekommen
                                            String email = emailclass;
                                            Map userdata = DB.DBConnector.getAnwenderdaten(Anwender.databasetablename, email);

                                            anwenderdata += "<tr>";

                                            anwenderdata += "<td>" + (String) userdata.get("NACHNAME") + "</td>";
                                            anwenderdata += "<td>" + (String) userdata.get("VORNAME") + "</td>";
                                            anwenderdata += "<td>" + (String) userdata.get("EMAIL") + "</td>";
                                            anwenderdata += "<td>" + (String) userdata.get("TELEFONNUMMER") + "</td>";

                                            anwenderdata += "</tr>";
                                        }
                                    }
                                    
                                } else {
                                    anwenderdata += "<tr><td colspan='4'>Keine Einträge gefunden.</td></tr>";
                                    break;
                                }
                            }
                            
                        } else {
                            anwenderdata += "<tr><td colspan='4'>Keine Einträge gefunden.</td></tr>";
                        }

                    } else {
                        anwenderdata += "<tr><td colspan='4'>Keine Einträge gefunden.</td></tr>";
                    }
                }
            }
        }

        anwenderdata += "</table>";

        return anwenderdata;
    }
}
