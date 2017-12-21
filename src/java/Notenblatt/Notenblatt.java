/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Notenblatt;

import DB.DBConnector;
import anwender.Anwender;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;

/**
 * Modul Notenblatt
 *
 * @author Christoph Stockinger
 * @version 0.1
 */
public class Notenblatt implements Interfaces.IModul {

    public static final String modulname = "Notenblatt";
    public static final String moduldesc = "Online-Notenblatt zur Eintragung der Noten für verschiedene Schüler."; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.
    
    public Notenblatt() {
    }
    

    /**
     * abgewandelte Methode für die Ausgabe der Subnavigation unseres Notenblatt
     * Moduls
     *
     * @param email - dient zur Überprüfung der Berechtigungen (Rollen)
     * @return HTML-Konstrukt des Menüs
     */
    public static String getSubNavigation(String email) {
        Map userrollen = new HashMap(); // Integer und String
        String output = "";

        if (email != null) {
            userrollen = DBConnector.getAnwenderRollen(email); // For Development: Parameter email aktuell hardgecoded!

            if (userrollen.size() == 0) {
                output += "Keine Berechtigung!!";
            } else {
                for (int i = 1; i <= userrollen.size(); i++) {
                    if (userrollen.get(i).equals("Admin")) {
                        output += "<ul>";
                        output += "<li> <a href='klassen.jsp'>Klassenübersicht</a> </li>";
                        output += "<li> <a href='exam.jsp'>Probe anlegen</a> </li>";
                        // output += "<li> <a href='schuelerexport.jsp'>Schülernoten exportieren</a> </li>";
                        output += "<li> <a href='classchange.jsp'>Klassenwechsel</a> </li>";
                        output += "</ul>";
                    }
                    if (userrollen.get(i).equals("Rektor")) {
                        output += "<ul>";
                        output += "<li> <a href='klassen.jsp'>Klassenübersicht</a> </li>";
                        output += "<li> <a href='exam.jsp'>Probe anlegen</a> </li>";
                        // output += "<li> <a href='schuelerexport.jsp'>Schülernoten exportieren</a> </li>";
                        // output += "<li> <a href='classchange.jsp'>Klassenwechsel</a> </li>";
                        output += "</ul>";
                    }
                    if (userrollen.get(i).equals("Personal")) {
                        output += "<ul>";
                        output += "<li> <a href='klassen.jsp'>Klassenübersicht</a> </li>";
                        // output += "<li> <a href='exam.jsp'>Probe anlegen</a> </li>";
                        // output += "<li> <a href='schuelerexport.jsp'>Schülernoten exportieren</a> </li>";
                        output += "<li> <a href='classchange.jsp'>Klassenwechsel</a> </li>";
                        output += "</ul>";
                    }
                    if (userrollen.get(i).equals("Lehrer")) {
                        output += "<ul>";
                        // output += "<li> <a href='klassen.jsp'>Klassenübersicht</a> </li>";
                        output += "<li> <a href='exam.jsp'>Probe anlegen</a> </li>";
                        //output += "<li> <a href='schuelerexport.jsp'>Schülernoten exportieren</a> </li>";
                        // output += "<li> <a href='classchange.jsp'>Klassenwechsel</a> </li>";
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

    /**
     * Methode baut einen Select-Tag mit allen Klassen als Option-Tag zusammen
     *
     * @return String mit HTML-Konstrukt
     */
    public static String getKlassen() {
        Map alleRollen;
        String returnstr = "";

        alleRollen = DB.DBConnector.getRollennamen();

        returnstr += "<select required name='klasse' id='klasse'>";
        returnstr += "<option>Klasse auswählen</option>";
        for (int i = 1; i <= alleRollen.size(); i++) {
            if (((String) alleRollen.get(i)).contains("Klasse")) {
                returnstr += "<option value='" + alleRollen.get(i) + "'>" + alleRollen.get(i) + "</option>";
            }
        }
        returnstr += "</select>";

        return returnstr;
    }
    
    /**
     * Methode speichert alle Klassen als Map
     * @return Map mit allen Klassen
     */
    public static Map getKlassenAsMap() {
        Map alleRollen;
        Map klassen = new HashMap();

        alleRollen = DB.DBConnector.getRollennamen();


        for (int i = 1; i <= alleRollen.size(); i++) {
            if (((String) alleRollen.get(i)).contains("Klasse")) {
                klassen.put(i, alleRollen.get(i) );
            }
        }

        return klassen;
    }
    
    
    /**
     * Methode baut einen Select-Tag mit allen Klassen als Option-Tag zusammen
     * @param sename Value des Name-Atrritbut
     * @return String mit HTML-Konstrukt
     */
    public static String getKlassenSelectChange(String sename) {
        Map alleRollen;
        String returnstr = "";

        alleRollen = DB.DBConnector.getRollennamen();

        returnstr += "<select required name='" + sename + "' id='klasse'>";
        returnstr += "<option>Klasse auswählen</option>";
        for (int i = 1; i <= alleRollen.size(); i++) {
            if (((String) alleRollen.get(i)).contains("Klasse")) {
                returnstr += "<option value='" + alleRollen.get(i) + "'>" + alleRollen.get(i) + "</option>";
            }
        }
        returnstr += "<option value='entlassen'>entlassen</option>";
        returnstr += "</select>";

        return returnstr;
    }

    /**
     * Methode zur Ausgabe der Klassenübersicht
     * 
     * @return String mit HTML-Konstrukt mit Headline und Tabelle
     */
    public static String getKlassenOverview() {
        Map alleRollen;
        String returnstr = "";

        alleRollen = DB.DBConnector.getRollennamen();

        returnstr += "<h3>Klassenübersicht</h3>";

        returnstr += "<table cellpadding='0' cellspacing='0'>";

        for (int i = 1; i <= alleRollen.size(); i++) {
            if (((String) alleRollen.get(i)).contains("Klasse")) {
                returnstr += "<tr>";
                returnstr += "<td>" + alleRollen.get(i) + "</td>";
                returnstr += "<td>";
                returnstr += "<a href='schueler.jsp?klasse=" + alleRollen.get(i) + "' class='button'> <img src='se-schulportal/images/icons/brush-white.svg' alt=''/> Bearbeiten </a>";
                returnstr += "<a href='import.jsp?klasse=" + alleRollen.get(i) + "' class='button'> <img src='se-schulportal/images/icons/data-transfer-upload-white.svg' alt=''/> Import </a>";
                // returnstr += "<a href='export.jsp?klasse=" + alleRollen.get(i) + "' class='button'> <img src='se-schulportal/images/icons/data-transfer-download-white.svg' alt=''/> Export </a>";
                returnstr += "</td>";
                returnstr += "</tr>";
            }
        }

        returnstr += "</table>";

        return returnstr;
    }
    
    /**
     * Methode zur Ausgabe der Auswahlmöglichkeit der neuen Klasse
     * @return String mit HTML-Konstrukt
     */
    public static String getKlassenChange() {
        Map alleRollen;
        String returnstr = "";

        alleRollen = DB.DBConnector.getRollennamen();

        returnstr += "<h3>Klassenwechsel</h3>";

        returnstr += "<table cellpadding='0' cellspacing='0'>";

        for (int i = 1; i <= alleRollen.size(); i++) {
            if (((String) alleRollen.get(i)).contains("Klasse")) {
                returnstr += "<tr>";
                returnstr += "<td>" + alleRollen.get(i) + "</td>";
                returnstr += "<td> wird zu</td>";
                returnstr += "<td>" + getKlassenSelectChange( (String) alleRollen.get(i) ) + "</td>";
                returnstr += "</tr>";
            }
        }

        returnstr += "</table>";
        returnstr += "<button type='submit' name='classchange' value='true'>Klassenwechsel vornehmen</button>";
        return returnstr;
    }

    /**
     * Methode zur Speicherung von hochgeladenen (CSV) Dateien
     *
     * @param contentType
     * @param klasse übergibt die Klasse, welcher die Dateien zugehören
     * @param request HttpServletRequest für den Medientype des Formulars
     * @return String mit HTML-Tags (Erfolgs- oder Fehlermeldungen)
     */
    public static String writeCSVFile(String contentType, String klasse, HttpServletRequest request) {
        String returnstr = "";
        File file;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        // String filePath = "c:/apache-tomcat/webapps/data/";
        // Lokal for Development
        String filePath = "/Users/Christoph/Documents/se-schulportal/web/notenblatt/csv-data-imports/";

        // Unterverzeichnis anlegen
        String changeklasse = klasse.replaceAll(" ", "-");
        changeklasse = changeklasse.toLowerCase();
        filePath += changeklasse + "/";
        File dir = new File(filePath);

        if (dir.mkdir()) {
            System.out.println("Unterverzeichnis erstellt!");
        } else {
            System.out.println("Unterverzeichnis nicht erstellt!");
        }

        // Datei(en) auf Server speichern
        if ((contentType.indexOf("multipart/form-data") >= 0)) {

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(maxMemSize);
            // factory.setRepository(new File("c:\\temp"));
            factory.setRepository(new File("/tmp/"));
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(maxFileSize);
            try {
                List fileItems = upload.parseRequest(request);
                Iterator i = fileItems.iterator();
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();
                        file = new File(filePath + fileName);
                        fi.write(file);
                        returnstr += "<p> Die Datei der " + klasse + " wurde erfolgreich gespeichert.";

                        try {// Datei durchlafuen und in DB schreiben!
                            fileName = filePath + fileName;
                            returnstr += Notenblatt.readCsvFileAndWriteDatabase(fileName, klasse);

                        } catch (Exception er) {
                            System.out.println("Exception: " + er.getMessage());
                            returnstr = "Bei der Datenbankeintragung ist was schief gelaufen.";
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
                returnstr = "Leider ist ein Fehler beim Speichern aufgetreten. Bitte versuchen Sie es nochmal.";
            }
        }

        return returnstr;
    }

    /**
     * Methode zum Lesen der CSV Datei und Speicherung deren Daten in Database
     * (Aufruf)
     *
     * @param fileName übergibt des Speicherort der Datei
     * @param klasse übergibt die Klasse, welcher die Dateien zugehören
     * @return String mit HTML-Tags (Erfolgs- oder Fehlermeldungen)
     */
    public static String readCsvFileAndWriteDatabase(String fileName, String klasse) {
        String returnstr = "";
        LineNumberReader f;
        String line;
        String query;

        try {
            f = new LineNumberReader(new FileReader(fileName));
            while ((line = f.readLine()) != null) {

                String[] daten = line.split(";");
                System.out.print("Datenlaenge: " + daten.length + " ");

                // Variablen
                String anrede = daten[0];
                String vorname = daten[1];
                String nachname = daten[2];
                String telefonnummer = daten[3];
                String email = "";

                // Password generieren
                String password = "kafjdka";

                // E-Mail-Adresse generieren
                String tmpEmail = vorname + "." + nachname + "@schule.de";

                Boolean tmpEmailUsed = Anwender.CheckEmailInDatabase(tmpEmail);
                int i = 1;
                Boolean schleifenstatus = true;
                while (schleifenstatus) {
                    if (tmpEmailUsed == false) {
                        email = tmpEmail;
                        schleifenstatus = false;
                    } else {
                        tmpEmail = vorname + "." + nachname + i + "@schule.de";
                        tmpEmailUsed = Anwender.CheckEmailInDatabase(tmpEmail);
                        schleifenstatus = true;
                    }
                    i++;
                }

                // Anwenderdaten in DB eintragen
                Boolean dbinsertanwender, dbinsertrollen, dbinsertverify;
                if ((email == "") || (email == null)) {
                    dbinsertanwender = false;
                } else {
                    dbinsertanwender = DB.DBConnector.writeRegistryAnwenderData(Anwender.databasetablename, anrede, vorname, nachname, telefonnummer, email, password);
                }

                if (dbinsertanwender) {
                    returnstr += "<p>Der Schüler " + vorname + " " + nachname + " wurde erfolgreich erstellt!<p>";
                    // Rollen erstellen
                    Map<Integer, String> rolle = new HashMap<Integer, String>();
                    rolle.put(0, klasse); // Klasse als Rolle hinzufügen
                    rolle.put(1, "Schüler"); // Schüler als Rolle hinzufügen

                    dbinsertrollen = DB.DBConnector.writeAnwenderRollen(email, rolle);

                    if (dbinsertrollen) {
                        returnstr += "<p>Der Schüler " + vorname + " " + nachname + " hat nun folgende Rollen: " + klasse + ", Schüler<p>";
                    } else {
                        returnstr += "<p style='alert'>Die Rollen von Schüler " + vorname + " " + nachname + " wurde fehlerhaft eingetragen!</p>";
                    }

                    // Verify faken
                    dbinsertverify = DB.DBConnector.writeAnwenderVerify(email, true, true, true);

                    System.out.println("DB Insert Verify: " + dbinsertverify);
                    if (dbinsertverify) {
                        returnstr += "<p>Der Schüler" + vorname + " " + nachname + " konnte erfolgreich verifiziert werden.<p>";
                    } else {
                        returnstr += "<p style='alert'>Die Verifizierung von Schüler " + vorname + " " + nachname + " ist nicht erfolgreich erfolgt!</p>";
                    }

                } else {
                    returnstr += "<p style='alert'>Der Schüler " + vorname + " " + nachname + " konnte nicht erstellt werden!</p>";
                }

            }
            f.close();
            return returnstr;
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Datei");
            System.out.println(e.getMessage());
            returnstr = "Fehler beim Lesen der Datei";
            return returnstr;
        }

    }

    /**
     * Methode zur Ausgabe aller Schüler einer Klasse als Übersichtstabelle
     *
     * @param klasse Die Klasse wessen Schüler ausgegeben werden sollen
     * @return String mit HTML-Konstrukt als Tabelle
     */
    public static String getKlassenSchuelerOverview(String klasse) {
        String returnstr = "";
        Map klassenschueler = DB.DBConnector.getAnwenderFromRolle(klasse);

        if (klassenschueler.size() == 0) {
            returnstr += "<p>Der Klasse sind keine Schüler zugeordnet! Sie müssen erst Schüler importieren.</p>";
        } else {
            returnstr += "<table cellpadding='0' cellspacing='0'>";
            returnstr += "<tr>";
            // returnstr += "<th></th>";
            returnstr += "<th>Nr.</th>";
            returnstr += "<th>Nachname</th>";
            returnstr += "<th>Vorname</th>";
            returnstr += "</tr>";

            for (int i = 1; i <= klassenschueler.size(); i++) {
                Map schueler = DB.DBConnector.getAnwenderdaten(Anwender.databasetablename, (String) klassenschueler.get(i));

                returnstr += "<tr>";
                // returnstr += "<td><input type='checkbox' name='" + schueler.get("EMAIL") + "'/></td>";
                returnstr += "<td>" + i + "</td>";
                returnstr += "<td>" + (String) schueler.get("NACHNAME") + "</td>";
                returnstr += "<td>" + (String) schueler.get("VORNAME") + "</td>";
                returnstr += "</tr>";
            }

            returnstr += "</table>";
        }
        return returnstr;
    }

    /**
     * Methode zur Ausgabe aller Lehrer als SELECT-Auswahl
     *
     * @return String mit HTML-Konstrukt
     */
    public static String getLehrer() {
        Map lehreremails;
        String returnstr = "";

        lehreremails = DB.DBConnector.getAnwenderFromRolle("Lehrer");

        returnstr += "<select name='lehrer' id='lehrer'>";
        returnstr += "<option>Lehrer auswählen</option>";
        for (int i = 1; i <= lehreremails.size(); i++) {
            Map lehrer = DB.DBConnector.getAnwenderdaten(Anwender.databasetablename, (String) lehreremails.get(i));
            returnstr += "<option value='" + lehreremails.get(i) + "'>" + lehrer.get("NACHNAME") + " " + lehrer.get("VORNAME") + "</option>";
        }
        returnstr += "</select>";

        return returnstr;
    }

    /**
     * Methode zur Ausgabe aller Fächer als SELECT-Auswahl
     *
     * @return String mit HTML-Konstrukt
     */
    public static String getFaecher() {
        Map faecher;
        String returnstr = "";

        faecher = DB.DBConnector.getFaecher();

        returnstr += "<select required name='fach' id='fach'>";
        returnstr += "<option>Fach auswählen</option>";
        for (int i = 1; i <= faecher.size(); i++) {
            returnstr += "<option value='" + faecher.get(i) + "'>" + faecher.get(i) + "</option>";

        }
        returnstr += "</select>";

        return returnstr;
    }

    /**
     * Methode zur Ausgabe aller Prüfungsarten als SELECT-Auswahl
     *
     * @return String mit HTML-Konstrukt
     */
    public static String getExamArten() {
        String returnstr = "";
        String[] examArten = new String[3];
        examArten[0] = "schriftliche Probe";
        examArten[1] = "mündliche Probe";
        examArten[2] = "praktische Probe";

        returnstr += "<select required name='art' id='art'>";
        returnstr += "<option >Probenart auswählen</option>";
        for (int i = 0; i < examArten.length; i++) {
            returnstr += "<option value='" + examArten[i] + "'>" + examArten[i] + "</option>";
        }
        returnstr += "</select>";

        return returnstr;
    }

    /**
     * Methode zur Verarbeitung der Daten für den Datenbankeintrag
     *
     * @param klasse Klasse, in der die Prüfung absolviert wurde
     * @param fach Ausgewähltes Fach, in dem die Prüfung abgehalten wurde
     * @param art Die Art der Prüfung
     * @param lehrer Der Lehrer, welcher die Prüfung abgehalten hat
     * @param date Das Datum, ab dem die Prüfung geschrieben wurde
     * @return String mit HTM-Konstrukt, ob Eintrag erfolgreich oder nicht
     */
    public static String writeExam(String klasse, String fach, String art, String lehrer, String date) {
        String returnstr;

        Boolean dbInsertExam = DB.DBConnector.writeExamData(klasse, fach, art, lehrer, date);

        if (dbInsertExam) {
            returnstr = "<p>Die Prüfung wurde erfolgreich erstellt!</p>";
        } else {
            returnstr = "<p>Leider ist beim Erstellen der Prüfung ein Fehler unterlaufen. Versuche es erneut!</p>";
        }

        return returnstr;
    }

    /**
     * Methode zur Ausgabe aller Schüler einer Klasse als Formular für
     * Noteneintragung
     *
     * @param klasse Die Klasse wessen Schüler ausgegeben werden sollen
     * @return String mit HTML-Konstrukt als Tabelle
     */
    public static String getKlassenSchuelerForm(String klasse) {
        String returnstr = "";
        Map klassenschueler = DB.DBConnector.getAnwenderFromRolle(klasse);

        if (klassenschueler.size() == 0) {
            returnstr += "<p>Der Klasse sind keine Schüler zugeordnet! Sie müssen erst Schüler importieren.</p>";
        } else {
            returnstr += "<table cellpadding='0' cellspacing='0'>";
            returnstr += "<tr>";
            returnstr += "<th>Nr.</th>";
            returnstr += "<th>Nachname</th>";
            returnstr += "<th>Vorname</th>";
            returnstr += "<th>Note</th>";
            returnstr += "</tr>";

            for (int i = 1; i <= klassenschueler.size(); i++) {
                Map schueler = DB.DBConnector.getAnwenderdaten(Anwender.databasetablename, (String) klassenschueler.get(i));

                returnstr += "<tr>";
                returnstr += "<td>" + i + "</td>";
                returnstr += "<td>" + (String) schueler.get("NACHNAME") + "</td>";
                returnstr += "<td>" + (String) schueler.get("VORNAME") + "</td>";
                returnstr += "<td><select required name='" + (String) schueler.get("EMAIL") + "'/> <option>Note auswählen</option> <option value='1'>1</option> <option value='2'>2</option> <option value='3'>3</option> <option value='4'>4</option> <option value='5'>5</option> <option value='6'>6</option> <option value='0'>nicht teilgenommen</option> </select></td>";
                returnstr += "</tr>";
            }

            returnstr += "</table>";
        }
        return returnstr;
    }

    /**
     * Methode zur Weitergabe an den DB-Connector für Notenspeicherung
     *
     * @param examid die erstellte Prüfungs-ID
     * @param schuelernoten Die einzutragenden Schülernoten für die Prüfungs-ID
     * @return String mit HTML-Konstrukt, ob Eintrag erfolgreich oder nicht
     */
    public static String writeExamMark(int examid, Map schuelernoten) {
        String returnstr = "";
        Boolean dbInsertMark = false;
        Map<String, Integer> noten = (Map) schuelernoten;

        for (Map.Entry<String, Integer> schueler : noten.entrySet()) {
            String email = schueler.getKey();
            int note = schueler.getValue();
            dbInsertMark = DB.DBConnector.writeExamMarkSchueler(examid, note, email);
        }

        if (dbInsertMark) {
            returnstr += "<p>Die Noten wurden erfolgreich gespeichert!</p>";
        } else {
            returnstr += "<p>Beim Speichern der Noten ist ein Fehler unterlaufen, bitte überprüfen die Richtigkeit!</p>";
        }

        return returnstr;
    }
    
    /**
     * Methode zum Ändern der Noten einer Prüfung
     * @param examid ID der zu ändernden Prüfung
     * @param schuelernoten Alle Schülernoten aus der Eingabe
     * @return String mit HTML-Konstrukt, ob Eintrag erfolgreich oder nicht
     */
    public static String updateExamMark(int examid, Map schuelernoten) {
        String returnstr = "";
        Boolean dbUpdateMark = false;
        Map<String, Integer> noten = (Map) schuelernoten;

        for (Map.Entry<String, Integer> schueler : noten.entrySet()) {
            String email = schueler.getKey();
            int note = schueler.getValue();
            dbUpdateMark = DB.DBConnector.updateExamMarkSchueler(examid, note, email);
        }

        if (dbUpdateMark) {
            returnstr += "<p>Die Noten wurden erfolgreich gespeichert!</p>";
        } else {
            returnstr += "<p>Beim Speichern der Noten ist ein Fehler unterlaufen, bitte überprüfen die Richtigkeit!</p>";
        }

        return returnstr;
    }

    /**
     * Methode zur Ausgabe einer Übersicht aller Prüfungen in Abhängigkeit der
     * Rolle
     *
     * @param email E-Mail-Adresse für die Abfrage der Rollen des angemeldeten
     * Anwenders
     * @return String mit HTML-Konstrukt mit Tabelle aller Prüfungen und Bearbeitungs-Button
     */
    public static String getExamOverview(String email) {
        String returnstr = "";
        Map anwenderrollen = DB.DBConnector.getAnwenderRollen(email);

        for (int i = 1; i <= anwenderrollen.size(); i++) {
            if ((anwenderrollen.get(i)).equals("Lehrer")) {
                returnstr += "<h3>Eigene Proben</h3>";

                Map<Integer, Integer> exam = DB.DBConnector.getExamFromRolle(email);

                if (exam.isEmpty()) {
                    returnstr += "<p>Leider sind keine Prüfungen angelegt!";
                } else {
                    returnstr += "<table cellpadding='0' cellspacing='0'>";
                    returnstr += "<tr>";
                    returnstr += "<th>ID</th>";
                    returnstr += "<th>Fach</th>";
                    returnstr += "<th>Art</th>";
                    returnstr += "<th>Klasse</th>";
                    returnstr += "<th></th>";
                    returnstr += "</tr>";

                    for (int j = 1; j <= exam.size(); j++) {
                        Map examdata = DB.DBConnector.getExamDataId(exam.get(j));
                        System.out.println("Examdata: " + examdata.size());
                        returnstr += "<tr>";
                        returnstr += "<td>" + Integer.toString(exam.get(j)) + "</td>";
                        returnstr += "<td>" + examdata.get("FACH") + "</td>";
                        returnstr += "<td>" + examdata.get("ART") + "</td>";
                        returnstr += "<td>" + examdata.get("KLASSE") + "</td>";
                        returnstr += "<td><a class='button' href='exam-mark.jsp?examid=" + Integer.toString(exam.get(j)) + "'>Noten bearbeiten</a></td>";
                        returnstr += "</tr>";
                    }

                    returnstr += "</table>";
                }
            }

            if ((anwenderrollen.get(i)).equals("Admin") || (anwenderrollen.get(i)).equals("Rektor")) {
                returnstr += "<h3>Alle Proben</h3>";

                Map<Integer, Integer> exam = DB.DBConnector.getExamIdAll();
                if (exam.isEmpty()) {
                    returnstr += "<p>Leider sind keine Prüfungen angelegt!";
                } else {
                    returnstr += "<table cellpadding='0' cellspacing='0'>";
                    returnstr += "<tr>";
                    returnstr += "<th>ID</th>";
                    returnstr += "<th>Fach</th>";
                    returnstr += "<th>Art</th>";
                    returnstr += "<th>Klasse</th>";
                    returnstr += "<th></th>";
                    returnstr += "</tr>";

                    for (int j = 1; j <= exam.size(); j++) {
                        Map examdata = DB.DBConnector.getExamDataId(exam.get(j));

                        returnstr += "<tr>";
                        returnstr += "<td>" + Integer.toString(exam.get(j)) + "</td>";
                        returnstr += "<td>" + examdata.get("FACH") + "</td>";
                        returnstr += "<td>" + examdata.get("ART") + "</td>";
                        returnstr += "<td>" + examdata.get("KLASSE") + "</td>";
                        returnstr += "<td><a class='button' href='exam-mark.jsp?examid=" + Integer.toString(exam.get(j)) + "'>Noten bearbeiten</a></td>";
                        returnstr += "</tr>";
                    }

                    returnstr += "</table>";
                }
            }
        }

        return returnstr;
    }

    /**
     * Methode zur Ausgabe der akutell hinterlegten Noten einer Prüfung für die Bearbeitungsfunktion
     * @param examid Prüfungs-ID in der Datenbank
     * @param klasse Die Klasse, welche die Prüfung geschrieben hat
     * @return String mit HTML-Konstrukt mit Tabelle aller Schüler und Noten-Auswählmöglichkeit. Aktuelle Note ist vorausgewählt.
     */
    public static String getExamMarkData(int examid, String klasse) {
        String returnstr = "";
        Map examschueler = DB.DBConnector.getExamMarkSchueler(examid);

        if (examschueler.isEmpty()) {
            returnstr += "<p>Leider sind keine Noten gespeichert!</p>";
            returnstr += Notenblatt.getKlassenSchuelerForm(klasse);
            returnstr += "<button type='submit' name='examsmark'>Prüfungsnoten speichern</button>";
        } else {
            returnstr += "<table cellpadding='0' cellspacing='0'>";
            returnstr += "<tr>";
            returnstr += "<th>Nr.</th>";
            returnstr += "<th>Nachname</th>";
            returnstr += "<th>Vorname</th>";
            returnstr += "<th>Note</th>";
            returnstr += "</tr>";

            for (int i = 1; i <= examschueler.size(); i++) {
                Map schuelernote = DB.DBConnector.getExamMarksId(examid, (String) examschueler.get(i));
                Map schuelerdata = DB.DBConnector.getAnwenderdaten(Anwender.databasetablename, (String) examschueler.get(i));
                
                int mark = (Integer) schuelernote.get("NOTE");
                
                returnstr += "<tr>";
                returnstr += "<td>" + i + "</td>";
                returnstr += "<td>" + (String) schuelerdata.get("NACHNAME") + "</td>";
                returnstr += "<td>" + (String) schuelerdata.get("VORNAME") + "</td>";
                returnstr += "<td><select required name='" + (String) schuelerdata.get("EMAIL") + "'/>";
                returnstr += "<option>Note auswählen</option>";
                if (mark == 1) {
                    returnstr += "<option selected value='1'>1</option>";
                } else {
                    returnstr += "<option value='1'>1</option>";
                }
                
                if (mark == 2) {
                    returnstr += "<option selected value='2'>2</option>";
                } else {
                    returnstr += "<option value='2'>2</option>";
                }
                
                if (mark == 3) {
                    returnstr += "<option selected value='3'>3</option>";
                } else {
                    returnstr += "<option value='3'>3</option>";
                }
                
                if (mark == 4) {
                    returnstr += "<option selected value='4'>4</option>";
                } else {
                    returnstr += "<option value='4'>4</option>";
                }
                
                if (mark == 5) {
                    returnstr += "<option selected value='5'>5</option>";
                } else {
                    returnstr += "<option value='5'>5</option>";
                }
                
                if (mark == 6) {
                    returnstr += "<option selected value='6'>6</option>";
                } else {
                    returnstr += "<option value='6'>6</option>";;
                }
                
                if (mark == 0) {
                    returnstr += "<option selected value='0'>nicht teilgenommen</option>";
                } else {
                    returnstr += "<option value='0'>nicht teilgenommen</option>";
                }
                returnstr += "</select></td>";
                returnstr += "</tr>";
            }

            returnstr += "</table>";
            returnstr += "<button type='submit' name='examsmarkupdate' value='true'>Notenänerungen speichern</button>";
        }

    return returnstr ;
    }
    
    /**
     * Methode zur Weitergabe an den DB-Connector für Klassen-Rollen-Wechsel
     * @param klassenzuordnung Die neue Zuordnung mit alter Klasse wird zu neuer Klasse
     * @return String mit HTML-Konstrukt, ob Eintrag erfolgreich oder nicht
     */
    public static String updateKlassenRollen (Map klassenzuordnung) {
        String returnstr = "";
        Boolean dbUpadteRollen = false;
        Boolean dbDeleteRollen = false;
        Map<String, String> neueKlassenrollen = (Map) klassenzuordnung;

        for (Map.Entry<String, String> rollen : neueKlassenrollen.entrySet()) {
            String alteRolle = rollen.getKey();
            String  neueRolle = rollen.getValue();
            if (neueRolle.equals("entlassen")) {
                // Falls entlassen wird Rolle gelöscht
                dbDeleteRollen = DB.DBConnector.deleteRollen(alteRolle);
            } else {
                dbUpadteRollen = DB.DBConnector.updateRollen(alteRolle, neueRolle);
            }
        }

        if (dbUpadteRollen) {
            returnstr += "<p>Die Klassen konnten erfolgreich gewechselt werden</p>";
        } else {
            returnstr += "<p>Beim Speichern der Klassenwechsel ist ein Fehler unterlaufen, bitte überprüfen die Richtigkeit!</p>";
        }
        
        if (dbDeleteRollen) {
            returnstr += "<p>Die entlassen Klassen konnten erfolgreich gelöscht werden</p>";
        } else {
            returnstr += "<p>Beim Löschen der entlassenen Klassen ist ein Fehler unterlaufen, bitte überprüfen die Richtigkeit!</p>";
        }

        return returnstr;
    }
}
