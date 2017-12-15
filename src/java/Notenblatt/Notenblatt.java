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
                    if (userrollen.get(i).equals("Admin")) {
                        output += "<ul>";
                        output += "<li> <a href='klassen.jsp'>Klassenübersicht</a> </li>";
                        output += "<li> <a href='schueler.jsp'>Schülerübersicht</a> </li>";
                        output += "<li> <a href='exam.jsp'>Prüfung anlegen</a> </li>";
                        output += "<li> <a href='schuelerexport.jsp'>Schülernoten exportieren</a> </li>";
                        output += "</ul>";
                    }
                    if (userrollen.get(i).equals("Rektor")) {
                        output += "<ul>";
                        // output += "<li> <a href='schuelerimport.jsp'>Schüler importieren</a> </li>";
                        output += "<li> <a href='exam.jsp'>Prüfung anlegen</a> </li>";
                        // output += "<li> <a href='schuelerexport.jsp'>Schülernoten exportieren</a> </li>";
                        output += "</ul>";
                    }
                    if (userrollen.get(i).equals("Personal")) {
                        output += "<ul>";
                        output += "<li> <a href='schueler.jsp'>Schülerübersicht</a> </li>";
                        // output += "<li> <a href='exam.jsp'>Prüfung anlegen</a> </li>";
                        // output += "<li> <a href='schuelerexport.jsp'>Schülernoten exportieren</a> </li>";
                        output += "</ul>";
                    }
                    if (userrollen.get(i).equals("Lehrer")) {
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
            if (((String) alleRollen.get(i)).contains("Klasse")) {
                returnstr += "<option value='" + alleRollen.get(i) + "'>" + alleRollen.get(i) + "</option>";
            }
        }
        returnstr += "</select>";

        return returnstr;
    }

    public static String getKlassenOverview() {
        Map alleRollen;
        String returnstr = "";

        alleRollen = DB.DBConnector.getRollennamen();

        returnstr += "<h3>Klasseübersicht</h3>";

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
                            Notenblatt.readCsvFileAndWriteDatabase(fileName, klasse);

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
                System.out.println("TMP-E-Mail:" + tmpEmail);

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
                    System.out.println("e-Mail:" + email);
                }

                System.out.println(anrede + " " + vorname + " " + nachname + " | Tel: " + telefonnummer + " | E-Mail" + email + " | Password" + password);

                // Anwenderdaten in DB eintragen
                Boolean dbinsertanwender, dbinsertrollen, dbinsertverify;
                if ((email == "") || (email == null)) {
                    dbinsertanwender = false;
                } else {
                    dbinsertanwender = DB.DBConnector.writeRegistryAnwenderData(Anwender.databasetablename, anrede, vorname, nachname, email, telefonnummer, password);
                }

                System.out.println("DB Insert Anwender: " + dbinsertanwender);
                if (dbinsertanwender) {
                    returnstr += "<p>Der Schüler" + vorname + " " + nachname + "wurde erfolgreich erstellt!<p>";
                    // Rollen erstellen
                    Map<Integer, String> rolle = new HashMap<Integer, String>();
                    rolle.put(0, klasse); // Klasse als Rolle hinzufügen
                    rolle.put(1, "Schüler"); // Schüler als Rolle hinzufügen

                    dbinsertrollen = DB.DBConnector.writeAnwenderRollen(email, rolle);

                    System.out.println("DB Insert Rollen: " + dbinsertrollen);
                    if (dbinsertrollen) {
                        returnstr += "<p>Der Schüler" + vorname + " " + nachname + "hat nun folgende Rollen: " + klasse + ", Schüler<p>";
                    } else {
                        returnstr += "<p style='alert'>Die Rollen von Schüler" + vorname + " " + nachname + "wurde fehlerhaft eingetragen!</p>";
                    }

                    // Verify faken
                    dbinsertverify = DB.DBConnector.writeAnwenderVerify(email, true, true, true);

                    System.out.println("DB Insert Verify: " + dbinsertverify);
                    if (dbinsertverify) {
                        returnstr += "<p>Der Schüler" + vorname + " " + nachname + "konnte erfolgreich verifiziert werden.<p>";
                    } else {
                        returnstr += "<p style='alert'>Die Verifizierung von Schüler" + vorname + " " + nachname + "ist nicht erfolgreich erfolgt!</p>";
                    }

                } else {
                    returnstr += "<p style='alert'>Der Schüler" + vorname + " " + nachname + "konnte nicht erstellt werden!</p>";
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
}
