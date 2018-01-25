/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stundenplan;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rodrigoscheipel
 */
public class StundenplanTest {
    
    public StundenplanTest() {
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
     * Test of stundenplan method, of class Stundenplan.
     */
    @Test
    public void testStundenplan() {
        System.out.println("stundenplan");
        String expResult = "<ul><li>  <a href='#'>Menüpunkt 1</a> </li><li> <a href='#'>Menüpunkt 2</a> </li>";
        String result = Stundenplan.stundenplan();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
