/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stundenplan;

import Modul_example.*;

/**
 *
 * @author Rodrigo und Jana 
 */
public class Stundenplan {
    
    public static String modulname = "Stundenplan";
    public static String moduldesc = ""; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.
    public static String databasetablenameFach = "Fach";
    public static String databasetablenameSlot = "Slot";
    public static String databasetablenameTage = "Tage";
    
    
    public Stundenplan() {}
    
    public static String stundenplan() {
        String output = "<ul>";
        output += "<li>  <a href='#'>Menüpunkt 1</a> </li>";
        output += "<li> <a href='#'>Menüpunkt 2</a> </li>";
       
        return output;
    }   
}
