/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sender;

import javax.mail.Session;
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
public class MailSenderTest {
    
    public MailSenderTest() {
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
     * Test of getGMailSession method, of class MailSender.
     */
    @Test
    public void testGetGMailSession() {
        System.out.println("getGMailSession");
        String user = "mail@dit.education";
        String pass = "Mail2017+1";
        Session expResult = null;
        Session result = MailSender.getGMailSession(user, pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get1and1Session method, of class MailSender.
     */
    @Test
    public void testGet1and1Session() {
        System.out.println("get1and1Session");
        String user = "";
        String pass = "";
        MailSender instance = new MailSender();
        Session expResult = null;
        Session result = instance.get1and1Session(user, pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postMail method, of class MailSender.
     */
    @Test
    public void testPostMail() throws Exception {
        System.out.println("postMail");
        String recipient = "";
        String subject = "";
        String message = "";
        String attachFile = null;
        MailSender.postMail(recipient, subject, message, attachFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
