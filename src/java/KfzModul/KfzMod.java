/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KfzModul;



/**
 *
 * @author Christoph
 */
public class KfzMod {
    
    public static String modulname = "Kennzeichenbearbeitung";
    public static String moduldesc = "Hier können Sie Ihr Autokennzeichen bearbeiten."; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.
    public static String databasetablename = "Nummernschild";
    public KfzMod() {}
    
    public static String getSubNavigation() {
        String output = "<ul>";
        output += "<li>  <a href='../pkw/kennzeichenReg.jsp'>Kennzeichen hinzufügen</a> </li>";
        output += "<li>  <a href='../pkw/kennzeichenLook.jsp'>Kennzeichen betrachten</a> </li>";
        output += "<li> <a href='../pkw/kennzeichenDel.jsp'>Kennzeichen löschen</a> </li>";
        output += "</ul>";
        return output;
    }
    
    
    
    
}
