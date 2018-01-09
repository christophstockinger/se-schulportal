/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kalender;

import Kalender.Termine;
import DB.DBConnector;
import DB.DBKonstanten;
import static DB.DBKonstanten.DBNAME;
import static DB.DBKonstanten.PASSWORD;
import static DB.DBKonstanten.USER;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author peterjerger
 */
public class Kalender {
    public static String modulname = "Terminkalender";
    public static String moduldesc = ""; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.
    
    public static String getSubNavigation() {
        String output = "<ul>";
        output += "<li>  <a href='#'>Menüpunkt 1</a> </li>";
        output += "<li> <a href='#'>Menüpunkt 2</a> </li>";
        output += "</ul>";
        return output;
    }
    
    public String datum;
    public String zeitvon;
    public String zeitbis;
    public String bezeichnung;   
    
    public Kalender(String dat, String zevo, String zebi, String bez) {
        this.datum = dat;
        this.zeitvon = zevo;
        this.zeitbis = zebi;
        this.bezeichnung = bez;
    }
    
    public boolean saveDate(String TERMINE, String datum) {
        
        DBConnector javaDBConn;
        javaDBConn = new DBConnector(DBNAME, USER, PASSWORD);
    
        Statement statement = null;
        try {
            statement = javaDBConn.connect();
            String query = "INSERT INTO " + TERMINE + " (DAY, TIMEFROM, TIMETO, DESCRIPTION) ";
            query = query + "VALUES( " + this.datum + ", " + this.zeitvon + ", " + this.zeitbis + ", " + this.bezeichnung + ")";

            statement.executeUpdate(query);
            return true;
        }
        catch (Exception ex) {
        return false;
        }
    }
}
