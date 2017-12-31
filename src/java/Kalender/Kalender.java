/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kalender;

/**
 *
 * @author peterjerger
 */
public class Kalender {
    public static String modulname = "Terminkalender";
    public static String moduldesc = ""; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.
    
    public Kalender() {}
    
    public static String getSubNavigation() {
        String output = "<ul>";
        output += "<li>  <a href='#'>Menüpunkt 1</a> </li>";
        output += "<li> <a href='#'>Menüpunkt 2</a> </li>";
        output += "</ul>";
        return output;
    }
}
