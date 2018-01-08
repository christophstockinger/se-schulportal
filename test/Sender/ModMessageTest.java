/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sender;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mwitzlsperger
 */
public class ModMessageTest {
    
    public ModMessageTest() {
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
     * Test of getSubNavigation method, of class ModMessage.
     */
    @Test
    public void testGetSubNavigation() {
        System.out.println("getSubNavigation");
        String expResult = "";
        String result = ModMessage.getSubNavigation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmailForm method, of class ModMessage.
     */
    @Test
    public void testGetEmailForm() {
        System.out.println("getEmailForm");
        String expResult = "";
        String result = ModMessage.getEmailForm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSMSForm method, of class ModMessage.
     */
    @Test
    public void testGetSMSForm() {
        System.out.println("getSMSForm");
        String expResult = "";
        String result = ModMessage.getSMSForm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
