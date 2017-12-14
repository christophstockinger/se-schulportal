package Interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christoph
 */
public interface IModul {
    // Modulname
    public static String modulname = "Modulname";
    
    // Modulbeschreibung
    public static String moduldesc = "Modulbeschreibung";
    
    // Subnavigation
    public abstract String getSubNavigation();
}
