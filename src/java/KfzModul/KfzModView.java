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
public class KfzModView {
    
    public static String modulname = "Kennzeichensuche";
    public static String moduldesc = "Hier können Sie überprüfen, ob ein Kennzeichen vorhanden ist."; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.
    public static String databasetablename = "Nummernschild";
    public KfzModView() {}
    
    public static String getSubNavigation() {
        String output = "<ul>";
        output += "<li>  <a href='../pkw/kennzeichenView.jsp'>Nach Kennzeichen Suchen</a> </li>";
        output += "<li> <a href='../pkw/kennzeichenViewName.jsp'>Wem gehört das Auto? </a> </li>"; 
        output += "</ul>";
        return output;
    }
    
    
    
    
}
