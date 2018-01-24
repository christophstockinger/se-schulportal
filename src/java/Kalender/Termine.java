/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kalender;

import DB.DBConnector;
import java.text.ParseException;
import java.util.HashMap;

/**
 *@param DBdate das Datum im SimpleDateFormat aus der Datenbank
 * @param DBzevo die Startzeit eines Events aus der Datenbank
 * @param DBzebi die Endzeit eines Events aus der Datenbank
 * @param DBbez die Erläuterung des Events aus der Datenbank
 * @param DbTermine die Hashmap mit allen Daten des zugehörigen SQL-Befehls
 * 
 * @author Peter
 */
public class Termine implements Interfaces.IModul{
    private String DBdate;
    private String DBzevo;
    private String DBzebi;
    private String DBbez;
    
    public final static String databasetablename = "Termine";
    
    public Termine (){}
    
    public Termine(String dbdate, String dbzevo, String dbzebi, String dbbez){
        this.DBdate = dbdate;
        this.DBzevo = dbzevo;
        this.DBzebi = dbzebi;
        this.DBbez = dbbez;
    }
    
    //Getter-Setter der Datums aus der DB
    public void setDBdate(String dbdate){
        this.DBdate = dbdate;
    }
    
    public String getDBdate(){
        return this.DBdate;
    }
    
    //Getter-Setter der Startzeit aus der DB
    public void setDBzevo(String dbzevo){
        this.DBzevo = dbzevo;
    }
    
    public String getDBzevo(){
        return this.DBzevo;
    }
    
    //Getter-Setter der Endzeit aus der DB
    public void setDBzebi(String dbzebi){
        this.DBzebi = dbzebi;
    }
    
    public String getDBzebi(){
        return this.DBzebi;
    }
    
    //Getter-Setter der Event-Erläuterung aus der DB
    public void setDBbez(String dbbez){
        this.DBbez = dbbez;
    }
    
    public String getDBbez(){
        return this.DBbez;
    }
    
        //HashMap auslesen und weitere HTML-Struktur mitgeben
    
        public static String getTermine() throws ParseException {
        HashMap alleTermine;
        String returnstr = "";
       
        alleTermine = DBConnector.GetDBTermine(databasetablename);
        
        returnstr += "<div class='row TerminContainer' name='termin' id='termin'>";
        for (int i = 1; i <= alleTermine.size(); i++) {
            if(alleTermine != null) {
                System.out.println(alleTermine.get(i));
                returnstr += "<div class='col-12 EinTermin'>" + alleTermine.get(i) + "</div>";
            }
        }
        returnstr += "</div>";
        
        return returnstr;
    }

    @Override
    public String getSubNavigation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
}



