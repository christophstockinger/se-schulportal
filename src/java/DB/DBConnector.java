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

    /**
     * Methode zur Abfrage der Anwenderdaten eienr bestimmten E-Mail-Adresse
     *
     * @param tblname: Tabellenname in der angefragt werden soll
     * @param email: E-Mail, welche Daten abgefragt werden
     * @return Map mit Spaltenüberschrift und Wert. Jeweils als String.
     */
    public static Map getAnwenderdataContact(String name, String klasse) {
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);
        
      

        Statement statement = null;
        try {
            statement = javaDBConn.connect();
            ResultSet rs;
            ResultSet rs2;
            
            Map<Integer, String> dbDataAnwender = new HashMap<Integer, String>();
            
            if ((name != "") || (name != null)) {
                rs = statement.executeQuery("SELECT EMAIL FROM ANWENDER WHERE upper(NACHNAME) = '" + name + "' OR upper(VORNAME) ='" + name + "'");
            }
            
            if ((klasse != "alle") && (name != "" || name != null)) {
                
                rs = statement.executeQuery("SELECT EMAIL FROM ANWENDER WHERE NACHNAME = '" + name + "' OR upper(VORNAME) ='" + name + "'");            
                rs2 = statement.executeQuery("SELECT * FROM ANWENDER WHERE NACHNAME = '" + name + "'OR VORNAME ='" + name + "'");
            } else if ((klasse == "alle") && (name == "")) {
                rs = statement.executeQuery("SELECT * FROM ANWENDER");            
            } else {
                rs = statement.executeQuery("SELECT EMAIL FROM ANWENDER");
            }

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
}
