/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kalender;

import DB.DBConnector;
import DB.DBKonstanten;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Peter
 */
public class Termine implements DBKonstanten{
    private String datum;
    private String zeitvon;
    private String zeitbis;
    private String bezeichnung;
    
    public final static String databasetablename = "Termine";
    
    public Termine(String dat, String zevo, String zebi, String bez){
        this.datum = dat;
        this.zeitvon = zevo;
        this.zeitbis = zebi;
        this.bezeichnung = bez;
    }
    
    public void setDatum(String dat) {
        this.datum = dat;
    }
    
    public String getDatum() {
        return datum;
    }
    
    public void setZeitVon(String zevo) {
        this.zeitvon = zevo;
    }
    
    public String getZeitVon() {
        return zeitvon;
    }
    
    public void setZeitBis(String zebi){
        this.zeitbis = zebi;
    }
    
    public String getZeitBis() {
        return zeitbis;
    }
    
    public void setBezeichnung(String bez) {
        this.bezeichnung = bez;
    }
    
    public String getBezeichnung() {
        return bezeichnung;
    }
    
    
    
}
