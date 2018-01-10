/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Profile_edit;

import DB.DBConnector;
import static DB.DBKonstanten.DBNAME;
import static DB.DBKonstanten.PASSWORD;
import static DB.DBKonstanten.USER;
import anwender.Anwender;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    
    public static String getSubNavigation3() {
        String output = "<ul>";
        output += "<li>  <a href='/se-schulportal/profile_edit/edit.jsp'>Daten betrachten</a> </li>";
        output += "<li>  <a href='/se-schulportal/profile_edit/edit_1.jsp'>Daten bearbeiten</a> </li>";
        output += "</ul>";
        return output;
    }
    
  
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
    
    
}
