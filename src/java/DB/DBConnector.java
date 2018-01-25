 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import static DB.DBKonstanten.DBNAME;
import static DB.DBKonstanten.PASSWORD;
import static DB.DBKonstanten.USER;
import anwender.Anwender;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Meine Applikation: Datenbank Connector
 *
 * @author Coding 77 - Christoph Stockinger
 * @version v0.1
 */
public class DBConnector {

    private String databaseName;
    private String databaseUser;
    private String databasePassword;

    public DBConnector(String dbname, String user, String password) {
        this.databaseName = dbname;
        this.databaseUser = user;
        this.databasePassword = password;
    }

    /**
     * Methode baut Verbindung zu Database auf
     *
     * @return Connection-Statement
     * @throws SQLException bei Fehler der SQL-Anfrage
     * @throws ClassNotFoundException bei nicht vorhanderer Class
     */
    public Statement connect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(databaseName, databaseUser, databasePassword);
            Statement stmt = conn.createStatement();
            return stmt;
        } catch (ClassNotFoundException err) {
            System.out.println("DB-Driver nicht gefunden!");
            System.out.println(err);
            return null;
        } catch (SQLException err) {
            System.out.println("Connect nicht moeglich");
            System.out.println(err);
            return null;
        }
    }

    /**
     * Methode baut Verbindung zu Database auf
     *
     * @param out
     * @return Connection-Statement
     * @throws SQLException bei Fehler der SQL-Anfrage
     * @throws ClassNotFoundException bei nicht vorhanderer Class
     */
    public Statement connect(PrintWriter out) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schulportal", "thomas", "Dit2017+1");
            Statement stmt = conn.createStatement();
            out.println("Connection ok");
            return stmt;
        } catch (ClassNotFoundException err) {
            out.println("DB-Driver nicht gefunden!");
            out.println(err);
            return null;
        } catch (SQLException err) {
            out.println("Connect nicht moeglich");
            out.println(err);
            return null;
        }
    }

    /**
     * Methode zur Abfrage der Anwenderdaten eienr bestimmten E-Mail-Adresse
     *
     * @param tblname: Tabellenname in der angefragt werden soll
     * @param email: E-Mail, welche Daten abgefragt werden
     * @return Map mit Spaltenüberschrift und Wert. Jeweils als String.
     */
    public static Map getAnwenderdaten(String tblname, String email) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();

            ResultSet rs = statement.executeQuery("SELECT * FROM " + tblname + " WHERE email = '" + email + "'");

            Map<String, String> dbDataAnwender = new HashMap<String, String>();

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            int rownum = 0;
            while (rs.next()) {
                rownum++;
                for (int i = 0; i < cols; i++) {
                    dbDataAnwender.put((String) meta.getColumnLabel(i + 1), (String) rs.getObject(i + 1));

                }
            }

            return dbDataAnwender;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /**
     * Methode zur Abfrage der Verify-Stati ein bestimmten E-Mail-Adresse
     *
     * @param email E-Mail, welche Daten abgefragt werden
     * @return Map mit Spaltenüberschrift (String) und Boolean-Wert
     */
    public static Map getVerifyDaten(String email) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();

            ResultSet rs = statement.executeQuery("SELECT * FROM Anwenderverify WHERE anwender = '" + email + "'");

            Map dbDataAnwender = new HashMap();

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            int rownum = 0;
            while (rs.next()) {
                rownum++;
                for (int i = 1; i < cols; i++) {
                    dbDataAnwender.put(meta.getColumnLabel(i + 1), rs.getObject(i + 1));

                }
            }

            return dbDataAnwender;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /**
     * Methode zur Abfrage der Rollen eines Anwenders
     *
     * @param email E-Mail, welche Daten abgefragt werden
     * @return Map mit Integer als Laufzahl und String als Rolle
     */
    public static Map getAnwenderRollen(String email) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();

            ResultSet rs = statement.executeQuery("SELECT * FROM Rolle WHERE anwender = '" + email + "'");

            Map<Integer, String> dbDataAnwenderRolle = new HashMap<Integer, String>();

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            int rownum = 0;
            while (rs.next()) {
                rownum++;
                dbDataAnwenderRolle.put(rownum, (String) rs.getObject(1));
            }
            return dbDataAnwenderRolle;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /**
     * Methode zum Eintrag eines Anwenders
     *
     * @param tblname Die einzutragende Datenbanktabelle
     * @param anr Anrede des neuen Anwenders
     * @param vn Vorname des neues Anwenders
     * @param nn Nachname des neuen Anwenders
     * @param tel Telefonnummer des neuen Anwenders mit führender +49 (führende
     * 0 wurde entfernt)
     * @param em E-Mail-Adresse des neuen Anwenders
     * @param pw ungehashtes Passwort des neuen Anwenders
     * @return Boolean-Value <br>true: bei erfolgreichem Eintrag<br>alse: bei
     * nicht erfolgreichem Eintrag
     */
    public static Boolean writeRegistryAnwenderData(String tblname, String anr, String vn, String nn, String tel, String em, String pw) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;

        try {
            statement = javaDBConn.connect();
            statement.executeUpdate("INSERT INTO " + tblname + " (anrede, nachname, vorname, email, telefonnummer, passwort) VALUES('" + anr + "', '" + nn + "', '" + vn + "','" + em + "','" + tel + "','" + pw + "')");

            return true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    /**
     * Methode zum Eintrag eines Anwenders in der Verify-Datenbanktabelle
     *
     * @param email einzutragende E-Mail-Adresse des Anwenders (Foreign Key)
     * @param verifystatus_mail Standardwert beim Ersteintrag: false
     * @param verifystatus_tel Standardwert beim Ersteintrag: false
     * @param verifystatus_admin Standardwert beim Ersteintrag: false
     * @return Boolean-Value <br>true: bei erfolgreichem Eintrag<br>false: bei
     * nicht erfolgreichem Eintrag
     */
    public static Boolean writeAnwenderVerify(String email, Boolean verifystatus_mail, Boolean verifystatus_tel, Boolean verifystatus_admin) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();
            statement.executeUpdate("INSERT INTO Anwenderverify (anwender,verifystatus_mail,verifystatus_tel,verifystatus_admin) VALUES('" + email + "', " + verifystatus_mail + ", " + verifystatus_tel + ", " + verifystatus_admin + ")");

            return true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Methode zum Ändern des Verifystatus der E-Mail-Adresse
     *
     * @param email E-Mail-Adresse des zu ändernden Anwenders
     * @param verifystatus_mail Wird auf true gesetzt. <br> String wird für
     * Zusammenbau des SQL Statements übergeben
     * @return Boolean-Value <br>true: bei erfolgreichem Eintrag<br>false: bei
     * nicht erfolgreichem Eintrag
     */
    public static Boolean writeAnwenderVerifystatusMail(String email, String verifystatus_mail) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();
            statement.executeUpdate("Update Anwenderverify SET verifystatus_mail = " + verifystatus_mail + " WHERE anwender='" + email + "'");

            return true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Methode zum Ändern des Verifystatus der Telefonnummer
     *
     * @param email E-Mail-Adresse des zu ändernden Anwenders
     * @param verifystatus_tel Wird auf true gesetzt. <br> String wird für
     * Zusammenbau des SQL Statementsübergeben
     * @return Boolean-Value <br>true: bei erfolgreichem Eintrag<br>false: bei
     * nicht erfolgreichem Eintrag
     */
    public static Boolean writeAnwenderVerifystatusTel(String email, String verifystatus_tel) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();
            statement.executeUpdate("Update Anwenderverify SET verifystatus_tel = " + verifystatus_tel + " WHERE anwender='" + email + "'");

            return true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Methode zum Ändern des Verifystatus der Adminfreischaltung
     *
     * @param email E-Mail-Adresse des zu ändernden Anwenders
     * @param verifystatus_admin Wird auf true gesetzt. <br> String wird für
     * Zusammenbau des SQL Statements übergeben
     * @return Boolean-Value <br>true: bei erfolgreichem Eintrag<br>false: bei
     * nicht erfolgreichem Eintrag
     */
    public static Boolean writeAnwenderVerifystatusAdmin(String email, String verifystatus_admin) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();
            statement.executeUpdate("Update Anwenderverify SET verifystatus_admin = '" + verifystatus_admin + "' WHERE anwender='" + email + "'");

            return true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Methode zum Löschen der Anwenderdaten
     *
     * @param email E-Mail-Adresse des zu löschenden Anwenders
     * @return String mit Meldung des Löschstatus Wird verwendet, wenn die
     * Telefon-PIN dreimal falsch eingeben wurde
     */
    public static String deleteAnwenderdaten(String email) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();
            // Anwenderverify löschen
            statement.executeUpdate("DELETE FROM Anwenderverify  WHERE anwender='" + email + "'");
            statement.executeUpdate("DELETE FROM Anwender  WHERE email='" + email + "'");

            return "Deine Authentifizierungsdaten wurden nun aus unserer Datenbank gelöscht. Bitte versuch dich erneut mit korrekten Daten zu registrieren.";

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return "";

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return "";
        }
    }

    /**
     * Methode zur Eintragung aller Anwenderrollen eines Anwenders
     *
     * @param email E-Mail-Adresse des Anwenders (Foreing Key)
     * @param anwenderrollen ein zutragende Rollen als Map Der SQL-Befehl mit
     * der Eintragung einer Rolle wird in Abhängigkeit der Anzahl der Map
     * anwenderrollen ausgeführt und der Return-Status in jedem Durchgang
     * geupdatet
     * @return Boolean-Value <br>true: bei erfolgreichem Eintragungen<br>false:
     * bei einer nicht erfolgreichen Eintragung
     */
    public static Boolean writeAnwenderRollen(String email, Map anwenderrollen) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;

        Boolean writestatus = false;

        try {
            statement = javaDBConn.connect();
            for (int i = 0; i < anwenderrollen.size(); i++) {
                System.out.println("INSERT INTO rolle (rolle, anwender) VALUES('" + anwenderrollen.get(i) + "', '" + email + "')");
                statement.executeUpdate("INSERT INTO rolle (rolle, anwender) VALUES('" + anwenderrollen.get(i) + "', '" + email + "')");
                writestatus = true;

            }

            return writestatus;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Methode zur Ausgabe aller hinterlegter Rollen
     *
     * @return Map mit allen Rollennamen
     */
    public static Map getRollennamen() {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();

            ResultSet rs = statement.executeQuery("SELECT * FROM Rollennamen");

            Map<Integer, String> rollen = new HashMap<Integer, String>();

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            int rownum = 0;
            while (rs.next()) {
                rownum++;
                rollen.put(rownum, (String) rs.getObject(1));

            }

            return rollen;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        } catch (NullPointerException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Methode zur Löschung einer ausgewählten Rolle für einen Anwedner
     *
     * @param email der Anwender bei dem die Rolle gelöscht wird
     * @param rolle die zu löschende Rolle
     * @return String <br> true: Meldung über erfolgreiche Löschung<br>false:
     * Fehlermeldung
     */
    public static String deleteAnwenderRolle(String email, String rolle) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();
            // Anwenderverify löschen
            System.out.println("DELETE FROM Rolle  WHERE anwender='" + email + "' AND rolle='" + rolle + "'");
            statement.executeUpdate("DELETE FROM Rolle  WHERE anwender='" + email + "' AND rolle='" + rolle + "'");

            return "Die Rolle " + rolle + " für den Anwender mit der E-Mail-Adresse " + email + " wurde gelöscht.";

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return "";

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return "";
        }
    }

    /***********************************************************************/
    // Kontakte

    /**
     * Methode zur Abfrage der Anwenderdaten für alle Anwender
     *
     * @return Map mit Spaltenüberschrift und Wert. Jeweils als String.
     */

    public static Map getAnwenderdataAll() {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();
            ResultSet rs = statement.executeQuery("SELECT EMAIL FROM ANWENDER");

            Map<Integer, String> dbDataAnwender = new HashMap<Integer, String>();

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            int rownum = 0;
            while (rs.next()) {
                rownum++;
                for (int i = 0; i < cols; i++) {
                    dbDataAnwender.put(rownum, (String) rs.getObject(i + 1));
                }
            }

            return dbDataAnwender;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /**
     * Methode zur Abfrage der Anwenderdaten einer bestimmten E-Mail-Adresse mittel Name
     *
     * @param name: Name in der angefragt werden soll
     * @return Map mit Spaltenüberschrift und Wert. Jeweils als String.
     */

    public static Map getAnwenderdataName(String name) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();
            ResultSet rs = statement.executeQuery("SELECT EMAIL FROM ANWENDER WHERE upper(NACHNAME) = '" + name + "' OR upper(VORNAME) ='" + name + "'");

            Map<Integer, String> dbDataAnwender = new HashMap<Integer, String>();

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            int rownum = 0;
            while (rs.next()) {
                rownum++;
                for (int i = 0; i < cols; i++) {
                    dbDataAnwender.put(rownum, (String) rs.getObject(i + 1));
                }
            }

            return dbDataAnwender;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /**
     * Methode zur Abfrage der Anwenderdaten mittels Klasse
     *
     * @param klasse: Klasse in der angefragt werden soll
     * @return Map mit Spaltenüberschrift und Wert. Jeweils als String.
     */

    public static Map getAnwenderdataClass(String klasse) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();
            ResultSet rs = statement.executeQuery("SELECT ANWENDER FROM ROLLE WHERE ROLLE = '" + klasse + "'");

            Map<Integer, String> dbDataAnwender = new HashMap<Integer, String>();

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            int rownum = 0;
            while (rs.next()) {
                rownum++;
                for (int i = 0; i < cols; i++) {
                    dbDataAnwender.put(rownum, (String) rs.getObject(i + 1));
                }
            }

            return dbDataAnwender;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }


/***********************************************************************/
// Profilsettings

public static Boolean updateAnwenderData(String anr, String vn, String nn, String tel, String em, String pw) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;

        try {
            statement = javaDBConn.connect();
            statement.executeUpdate("UPDATE ANWENDER SET anrede='" + anr + "', nachname='" + nn + "', vorname='" + vn + "', telefonnummer='" + tel + "', passwort='" + pw+"' WHERE email='"+em+"'");

            return true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    /***********************************************************************/
    // Sender
    /**
     * Methode zur Abfrage der Emails einer Rolle
     * @return Map mit Integer als Laufzahl und String als Email
     */
    public static Map getRollenEmail() {

        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();

            ResultSet rs = statement.executeQuery("SELECT EMAIL FROM Anwender");

            Map<Integer, String> dbDataAnwender = new HashMap<Integer, String>();

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            int rownum = 0;
            while (rs.next()) {
                rownum++;
                for (int i = 0; i < cols; i++) {
                    dbDataAnwender.put( rownum, (String) rs.getObject(i + 1));

                }
            }

            return dbDataAnwender;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Methode zur Abfrage der Telefonnummern einer Rolle
     * @param email email des Anwenders der eine SMS bekommen soll
     * @return Map mit Integer als Laufzahl und String als Telefonnummer
     */
    public static Map getRollenSMS(String email) {

        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();

            ResultSet rs = statement.executeQuery("SELECT TELEFONNUMMER FROM Anwender WHERE EMAIL ='"+email+"'");

            Map<Integer, String> dbDataAnwender = new HashMap<Integer, String>();

            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            int rownum = 0;
            while (rs.next()) {
                rownum++;
                for (int i = 0; i < cols; i++) {
                    dbDataAnwender.put( rownum, (String) rs.getObject(i + 1));

                }
            }

            return dbDataAnwender;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /***********************************************************************/
    // KFZ
    /**
     * Methode zum Eintrag eines Anwenders
     * @param tblname Die einzutragende Datenbanktabelle
     * @param anr Anrede des neuen Anwenders
     * @param vn Vorname des neues Anwenders
     * @param nn Nachname des neuen Anwenders
     * @param tel Telefonnummer des neuen Anwenders mit führender +49 (führende 0 wurde entfernt)
     * @param em E-Mail-Adresse des neuen Anwenders
     * @param pw ungehashtes Passwort des neuen Anwenders
     * @return Boolean-Value <br>true: bei erfolgreichem Eintrag<br>alse: bei nicht erfolgreichem Eintrag
     */
    public static Boolean writeKennzeichenData(String tblname, String knz, String email) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;


        try {

            statement = javaDBConn.connect();


            statement.executeUpdate("UPDATE " + tblname + " SET Kennzeichen = '" + knz + "' WHERE email='"+email+"'");

            return true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }


   public static Boolean deleteKennzeichenData(String tblname, String knz, String email) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;


        try {

            statement = javaDBConn.connect();


            statement.executeUpdate("UPDATE " + tblname + " SET Kennzeichen = NULL WHERE kennzeichen='"+knz+"' AND email='" + email + "'");

            return true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class
                    .getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }


   public static String kfzViewData(String tblname, String knz) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;


        try {

            statement = javaDBConn.connect();
            String entry = "<h1>Eintrag gefunden!</h1><form><input value='Zurück zur Suche' onclick=\"window.location.href='kennzeichenView.jsp'\" type=button></form>";
            String noEntry = "<h1>Kein Eintrag gefunden!</h1><form><input value='Zurück zur Suche' onclick=\"window.location.href='kennzeichenView.jsp'\" type=button></form>";



           ResultSet rs = statement.executeQuery("SELECT kennzeichen from " + tblname + " WHERE kennzeichen='"+knz+"'");

            if (rs.next()) {
                return entry;
            } else {
                return noEntry;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class
                    .getName()).log(Level.SEVERE, null, ex);
           return ("class not found Error");

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class
                    .getName()).log(Level.SEVERE, null, ex);
            return ("exception error");
        }

   }

   public static String kfzViewDataNames(String tblname, String knz) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;


        try {

            statement = javaDBConn.connect();
            String entry = "Eintrag gefunden!";
            String noEntry = "<h1>Kein Eintrag gefunden!</h1><form><input value='Zurück zur Suche' onclick=\"window.location.href='kennzeichenView.jsp'\" type=button></form>";



           ResultSet rs = statement.executeQuery("SELECT kennzeichen from " + tblname + " WHERE kennzeichen='"+knz+"'");

            if (rs.next()) {
                return entry;
            } else {
                return noEntry;
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class
                    .getName()).log(Level.SEVERE, null, ex);
           return ("class not found Error");

        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class
                    .getName()).log(Level.SEVERE, null, ex);
            return ("exception error");
        }

   }

/***********************************************************************/
// Notenblatt

/**
 * Methode zum Abfragen aller Anwender mit einer bestimmtem Rolle
 * @param rolle Die Klasse welche der Anwender als Rolle hat
 * @return Map mit Laufnummer und allen E-Mail-Adressen der Anwender, welche die Klassen-Rolle haben
 */
public static Map getAnwenderFromRolle(String rolle) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT Anwender FROM Rolle WHERE ROLLE = '" + rolle + "'");

        Map<Integer, String> anwender = new HashMap<Integer, String>();

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            System.out.println(rownum + ": " + (String) rs.getObject(1) );
            anwender.put(rownum, (String) rs.getObject(1));
        }

        return anwender;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }

}

/**
 * Methode zum Abfragen eines Anwenders mit zwei bestimmtem Eigenschaften
 * @param rolle Die Klasse welche der Anwender als Rolle hat
 * @return String der Anwenderemail
 */
public static String getAnwenderFromRolleAndAnwender(String rolle, String anwenderemail) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT Anwender FROM Rolle WHERE ROLLE = '" + rolle + "' AND Anwender ='" + anwenderemail + "'");

        String anwender = "";

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;

            anwender = (String) rs.getObject(1);
        }

        return anwender;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }

}

/**
 * Methode zur Ausgabe aller hinterlegter Fächer
 * @return Map mit allen Fächern
 */
public static Map getFaecher() {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT * FROM faecher");

        Map<Integer, String> rollen = new HashMap<Integer, String>();

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            rollen.put(rownum, (String) rs.getObject(1));

        }

        return rollen;

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex.getMessage());
        return null;

    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex.getMessage());
        return null;
    } catch (NullPointerException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex.getMessage());
        return null;
    }
}

/**
 * Methode zum Eintragen einer Prüfung
 * @param klasse Klasse in der, die Prüfung geschrieben wurde
 * @param fach Fach in dem, die Prüfung geschrieben wurde
 * @param art Die Art der Prüfung
 * @param lehrer Der Lehrer, der die Prüfung geschrieben hat
 * @param date Das Datum an dem die Prüfung geschrieben wurde
 * @return Boolean: <br> true: erfolgreicher Eintrag <br> false: nicht erfolgreicher Eintrag
 */
public static Boolean writeExamData(String klasse, String fach, String art, String lehrer, String date) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;

    try {
        statement = javaDBConn.connect();
        statement.executeUpdate("INSERT INTO pruefung (lehrer, art, klasse, fach, datum) VALUES ('" + lehrer + "', '" + art + "','" + klasse + "','" + fach + "','" + date + "')");

        return true;

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class
                .getName()).log(Level.SEVERE, null, ex);
        return false;

    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}

/**
 * Methode zur Abfrage der Prüfungs-ID
 * @param klasse Klasse in der die Prüfung geschrieben wurde
 * @param fach Fach in der die Prüfung geschrieben wurde
 * @param art Art der geschriebenen Prüfung
 * @param lehrer Lehrer, der die Prüfung geschrieben hat
 * @param date Datum, an dem die Prüfung geschrieben wurde
 * @return Integer mit der Prüfungs-ID
 */
public static Integer getExamId(String klasse, String fach, String art, String lehrer, String date) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT id FROM pruefung WHERE klasse='" + klasse + "' AND fach='" + fach + "'  AND art='" + art + "' AND lehrer='" + lehrer + "' AND datum='" + date + "'");

        int id = 0;

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        if (cols == 0) {
            id = 0;
        } else {
            while (rs.next()) {
                rownum++;
                id = (Integer) rs.getObject(1);
            }
        }

        return id;

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex.getMessage());
        return 0;

    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex.getMessage());
        return 0;
    } catch (NullPointerException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex.getMessage());
        return 0;
    }
}

/**
 * Methode zur Eintragung aller Schülernoten einer Prüfung in die Datenbank
 * @param examid Prüfungs-ID
 * @param note Note des Schüler
 * @param email E-Mail-Adresse des Schülers
 * @return Boolean mit true für erfolgreichen Eintrag und false mit falschem Eintrag
 */
public static Boolean writeExamMarkSchueler(int examid, int note, String email) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;

    try {
        statement = javaDBConn.connect();
        statement.executeUpdate("INSERT INTO pruefungsnoten (note, pruefung, email) VALUES (" + note + ", " + examid + ",'" + email + "')");

        return true;

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class
                .getName()).log(Level.SEVERE, null, ex);
        return false;

    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class
                .getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}

/**
 * Methode zur Änderung aller Schülernoten einer Prüfung in der Datenbank
 * @param examid Prüfungs-ID
 * @param note Note des Schüler
 * @param email E-Mail-Adresse des Schülers
 * @return Boolean mit true für erfolgreichen Eintrag und false mit falschem Eintrag
 */
public static Boolean updateExamMarkSchueler(int examid, int note, String email) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;

    try {
        statement = javaDBConn.connect();
        System.out.println ("UPDATE pruefungsnoten SET note = " + note + " WHERE email = '" + email + "' AND pruefung = " + examid + "");
        statement.executeUpdate("UPDATE pruefungsnoten SET note = " + note + " WHERE email = '" + email + "' AND pruefung = " + examid + "");

        return true;

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class
                .getName()).log(Level.SEVERE, null, ex);
        return false;

    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class
                .getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}


/**
 * Methode um Prüfungs-Ids eines Lehrers zu bekommen
 * @param email E-Mail-Adresse des angemeldeteten Lehrers
 * @return Map mit allen Prüfung-Ids eines Lehrers
 */
public static Map getExamFromRolle (String email) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT ID  FROM Pruefung WHERE Lehrer = '" + email + "'");

        Map<Integer, Integer> exam = new HashMap<Integer, Integer>();

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            exam.put(rownum, (Integer) rs.getObject(1));
        }

        return exam;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}

/**
 * Methode um ID aller Prüfungen zu bekommen
 * @return Map mit allen Prüfungs-Ids
 */
public static Map getExamIdAll () {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT ID  FROM Pruefung");

        Map<Integer, Integer> exam = new HashMap<Integer, Integer>();

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            exam.put(rownum, (Integer) rs.getObject(1));
        }

        return exam;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}

/**
 * Methode um Informationen zur einer speziellen Prüfung zu bekommen
 * @param examid Die spezielle Prüfung
 * @return Map mit den Informationen als Key die Spaltenüberschrift
 */
public static Map getExamDataId (int examid) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT ART, KLASSE, FACH, LEHRER FROM Pruefung WHERE id = " + examid + "");

        Map<String, String> examdata = new HashMap<String, String>();

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            for (int i = 0; i < cols; i++) {
                examdata.put( (String) meta.getColumnLabel(i + 1), (String) rs.getObject(i + 1));
            }
        }

        return examdata;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}

/**
 * Methode um das Fach einer bestimmten Prüfung zu erhalten
 * @param examid Id der angefragten Prüfung
 * @return String mit dem Fachnamen
 */
public static String getExamFachWithId (int examid) {
   DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT FACH FROM Pruefung WHERE id = " + examid + "");

        String examfach = "";

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            for (int i = 0; i < cols; i++) {
                examfach = (String) rs.getObject(i + 1);
            }
        }

        return examfach;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}

/**
 *
 * @param examid
 * @return
 */
public static Date getExamDateFromId (int examid) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT DATUM FROM Pruefung WHERE id = " + examid + "");

        Date examdate = new Date();

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            examdate = (Date) rs.getObject(1);
        }

        return examdate;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}

/**
 * Methode um eine Note einer Schülers in einer Prüfung zur erhalten
 * @param examid ID der Prüfung
 * @param email E-Mail des Schülers
 * @return Map mit der Note
 */
public static Map getExamMarksId(int examid, String email) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT note FROM pruefungsnoten WHERE pruefung = " + examid + " AND email='" + email + "'");

        Map examdata = new HashMap();

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            for (int i = 0; i < cols; i++) {
                Class typ = ( rs.getObject( i + 1 ) ).getClass();
                    examdata.put( (String) meta.getColumnLabel(i + 1), (Integer) rs.getObject( i + 1 ));
            }
        }

        return examdata;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}

/**
 * Methode um die alle Schüler zu erhalten, welche eine Prüfung geschrieben haben
 * @param examid ID der Prüfung
 * @return Map mit allen E-Mail-Adressen der Schüler, welche die Prüfung geschrieben haben
 */
public static Map getExamMarkSchueler(int examid) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT EMAIL FROM Pruefungsnoten WHERE pruefung = " + examid + "");

        Map<Integer, String> examdata = new HashMap<Integer, String>();

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            for (int i = 0; i < cols; i++) {
                examdata.put( rownum, (String) rs.getObject( i + 1 ));
            }
        }

        return examdata;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}

/**
 * Methode zur Berechnung der Gesamtanzahl der Anwender mit einer bestimmte Berechtigung
 * @param rolle Die Berechtigung der Anwender
 * @return Integer mit der Gesamtanzahl.
 */
public static Integer getAnwenderCount(String rolle) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT COUNT(ANWENDER) FROM rolle WHERE rolle = '" + rolle + "'");

        int anwenderCount = 0;

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            for (int i = 0; i < cols; i++) {
                anwenderCount = (Integer) rs.getObject( i + 1 );
            }
        }

        return anwenderCount;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}

/**
 * Methode zur Berechnung aller an einer Prüfung wirklich teilgenommenen Schüler
 * @param examid Id der abgefragten Prüfung
 * @return Integer mit der Zahl der teilgenommenen Schüler
 */
public static Integer getExamAttendersCount(int examid) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT COUNT(EMAIL) FROM pruefungsnoten WHERE pruefung = " + examid + " AND NOT note = 0 ");

        int examAttendersCount = 0;

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            for (int i = 0; i < cols; i++) {
                examAttendersCount = (Integer) rs.getObject( i + 1 );
            }
        }

        return examAttendersCount;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}

/**
 * Methode für die Berechnung der Gesamtzahl, wie viele Schüler eine Note haben
 * @param examid ID der abgefragten Prüfung
 * @param note Die entsprechende Note
 * @return
 */
public static Integer getExamMarkCount(int examid, int note) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT COUNT(EMAIL) FROM pruefungsnoten WHERE pruefung = " + examid + " AND note = " + note);

        int examMarkCount = 0;

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            for (int i = 0; i < cols; i++) {
                examMarkCount = (Integer) rs.getObject( i + 1 );
            }
        }

        return examMarkCount;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}

/**
 * Methode zur Ausgabe aller hinterlegter Prüfungsarten
 * @return Map mit allen Prüfungsarten
 */
public static Map getExamArten() {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT art FROM pruefungsarten");

        Map<Integer, String> arten = new HashMap<Integer, String>();

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            arten.put(rownum, (String) rs.getObject(1));

        }

        return arten;

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex.getMessage());
        return null;

    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex.getMessage());
        return null;
    } catch (NullPointerException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex.getMessage());
        return null;
    }
}

/**
 * Methode um alle Prüfungen eines Schülers zu erhalten
 * @param schueleremail E-Mail-Addresse eines Schülers
 * @return Map mit allen Prüfungen
 */
public static Map getExamIdFromSchueler(String schueleremail) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT pruefung FROM pruefungsnoten WHERE email = '" + schueleremail + "'");

        Map<Integer, Integer> examids = new HashMap<Integer, Integer>();

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            examids.put( rownum , (Integer) rs.getObject( 1 ) );

        }

        return examids;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}

/**
 * Methode um die Art einer bestimmten Prüfung zu erhalten
 * @param examid ID der angefragten Prüfung
 * @return String mit der Prüfungsart
 */
public static String getExamArtFromId (int examid) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT art FROM pruefung WHERE id = " + examid );

        String art = "";

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            art = (String) rs.getObject( 1 );

        }

        return art;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}

/**
 * Methode um die Note eines Schülers (Anwenders) für eine bestimmte Prüfung zu erhalten
 * @param examid ID der bestimmten Prüfung
 * @param schueleremail Die E-Mail-Addresse des Schülers
 * @return Note als Integer
 */
public static Integer getExamMarkOfExamIdFromSchueler(int examid, String schueleremail) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {
        statement = javaDBConn.connect();

        ResultSet rs = statement.executeQuery("SELECT note FROM pruefungsnoten WHERE pruefung = " + examid + " AND email = '" + schueleremail + "'");

        int note = 0;

        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        int rownum = 0;
        while (rs.next()) {
            rownum++;
            note = (Integer) rs.getObject( 1 );

        }

        return note;
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        return null;
    }
}


/***********************************************************************/
// Notenblatt

/**
 *
 * @param databasetablename der Tabellennamen, von eventCalender.jsp mitgegeben
 * @param datum das Datum aus der Frontend-Form (eventCalender.jsp)
 * @param zevo die Startzeit eines Events aus der Frontend-Form
 * @param zebi die Endzeit eines Events aus der Frontend-Form
 * @param bez die Erläuterung zu einem Event aus der Frontend-Form
 *
 * @return gibt true/false zurück, darauf wird zugegriffen um einen "Erfolgreich-, bzw. Fehlgeschlagen-Text auszugeben"
 */

public static Boolean dbTermine(String databasetablename, String datum, String zevo, String zebi, String bez) {
    DBConnector javaDBConn;
    javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

    Statement statement = null;
    try {                           //Nur in DB lagern wenn alle Parameter vorhanden
        statement = javaDBConn.connect();
        if (datum != null && zevo != null && zebi != null && bez != null){
        statement.executeUpdate("INSERT INTO " + databasetablename + "(DATUM, ZEITVON, ZEITBIS, BEZEICHNUNG) VALUES ('" + datum + "', '" + zevo + "', '" + zebi + "','" + bez + "')");
        }
        return true;

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Anwender.class
                .getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex.getMessage());

        return false;

    } catch (SQLException ex) {
        Logger.getLogger(DBConnector.class
                .getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex.getMessage());

        return false;
    }

}

/** TERMINKALENDER
 *
 * @param databasetablename Tabellenname von Termine.java mitgegeben
 * @return
 * SQL Befehl wandelt das Datum in eine String um,
 * gibt vorsichtshalber die Reihenzahl zusätzlich mit
 * und ordnet die Einträge aufsteigend dem Datum nach.
 *
 * @throws java.text.ParseException Exception für Datum-Parse
 */

    public static HashMap getDBTermine(String databasetablename) throws ParseException {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);

        Statement statement = null;
        try {
            statement = javaDBConn.connect();

            ResultSet rs = statement.executeQuery("SELECT CAST (DATUM AS VARCHAR(20)), ZEITVON, ZEITBIS, BEZEICHNUNG FROM " + databasetablename + " WHERE DATUM >= CURRENT_DATE ORDER BY DATUM");
            //ResultSet rs = statement.executeQuery("SELECT ZEITVON, ZEITBIS, BEZEICHNUNG FROM TERMINE ORDER BY DATUM");
            //ResultSet rs = statement.executeQuery("SELECT * FROM (SELECT ROW_NUMBER() OVER () AS R, Termine.* FROM Termine) AS TR WHERE R <= " + cols + " AND DATUM >= CURRENT_DATE ORDER BY DATUM");

            HashMap<Integer, String> termine = new HashMap<Integer, String>();

            //HashMap anlegen, Einzelne Reihen mit whileschleife auslesen und HTML-Grundstruktur mitgeben
            ResultSetMetaData meta = rs.getMetaData();
            int cols = meta.getColumnCount();
            int rownum = 0;
            while (rs.next()) {
                rownum++;
                termine.put(rownum, "<h4 class='DBdatum'>" + (String) rs.getObject(1) + "</h4><h3 class='DBbezeichnung'>" + (String) rs.getObject(4) +  "</h3> Von: " + (String) rs.getObject(2) + " bis " + (String) rs.getObject(3));
            }

        return termine;


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Anwender.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }


    }



}
