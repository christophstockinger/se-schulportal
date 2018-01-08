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
public class SMSSenderTest {
    
    public SMSSenderTest() {
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
     * Test of sendSMS method, of class SMSSender.
     */
    @Test
    public void testSendSMS() {
        System.out.println("sendSMS");
        String empfaenger = "+4915774140967";
        String message = "Hallo dies ist ein JUnit-Test";
        SMSSender.sendSMS(empfaenger, message);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    
}
