/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KfzModul;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patrickrichter
 */
public class KfzModTest {
    
    public KfzModTest() {
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
     * Test of getSubNavigation method, of class KfzMod.
     */
    @Test
    public void testGetSubNavigation() {
        System.out.println("getSubNavigation");
        String expResult = "<ul><li>  <a href='../pkw/kennzeichenReg.jsp'>Kennzeichen hinzufügen</a> </li><li> <a href='../pkw/kennzeichenDel.jsp'>Kennzeichen löschen</a> </li></ul>";
        String result = KfzMod.getSubNavigation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
