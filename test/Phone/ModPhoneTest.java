/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Phone;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Precha
 */
public class ModPhoneTest {
    
    public ModPhoneTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSubNavigation method, of class ModPhone.
     */
    @Test
    public void testGetSubNavigation() {
        System.out.println("getSubNavigation");
        String expResult = "<ul><li>  <a href='index.jsp'>Zurück zur Suche</a> </li></ul>";
        String result = ModPhone.getSubNavigation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getAnwenderdataContacts method, of class ModPhone.
     */
    @Test
    public void testGetAnwenderdataContacts() {
        System.out.println("getAnwenderdataContacts");
        String name = "Christoph";
        String klasse = "alle";
        String expResult = "<table><tr><th>Name</th><th>Vorname</th><th>E-Mail</th><th>Telefonnummer</th></tr><tr><td>Stockinger</td><td>Christoph</td><td>christoph.stockinger@stud.th-deg.de</td><td>02156168228</td></tr></table>";
        String result = ModPhone.getAnwenderdataContacts(name, klasse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    
}
