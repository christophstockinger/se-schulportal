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
    
    public static String modulname = "Modulbeispiel";
    public static String moduldesc = "Hier könnte eventuell noch ein Text zur Modulerklärung stehen. <br>Muss aber nicht sein"; // Zeilenumbruch mit <br>-Tag erzeugen, da es in einen <p>-Tag gerendert wird.
    
    public KfzMod() {}
    
    public static String getSubNavigation() {
        String output = "<ul>";
        output += "<li>  <a href='../pkw/kennzeichenReg.jsp'>Kennzeichen hinzufügen</a> </li>";
        output += "<li> <a href='#'>Menüpunkt 2</a> </li>";
        output += "</ul>";
        return output;
    }
    
    
    
    
}
