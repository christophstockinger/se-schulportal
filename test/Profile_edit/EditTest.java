/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Profile_edit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author fabianschene
 */
public class EditTest {
    
    public EditTest() {
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
     * Test of getSubNavigation method, of class Edit.
     */
    @Test
    public void testGetSubNavigation() {
        System.out.println("getSubNavigation");
        String expResult = "<ul><li> <a href='#'>Daten betrachten</a> </li><li> <a href='/se-schulportal/profile_edit/edit_1.jsp'>Daten bearbeiten</a> </li></ul>";
        String result = Edit.getSubNavigation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getSubNavigation2 method, of class Edit.
     */
    @Test
    public void testGetSubNavigation2() {
        System.out.println("getSubNavigation2");
        String expResult = "<ul><li> <a href='/se-schulportal/profile_edit/edit.jsp'>Daten betrachten</a> </li><li> <a href='#'>Daten bearbeiten</a> </li></ul>";
        String result = Edit.getSubNavigation2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getSubNavigation3 method, of class Edit.
     */
    @Test
    public void testGetSubNavigation3() {
        System.out.println("getSubNavigation3");
        String expResult = "<ul><li> <a href='/se-schulportal/profile_edit/edit.jsp'>Daten betrachten</a> </li><li> <a href='/se-schulportal/profile_edit/edit_1.jsp'>Daten bearbeiten</a> </li></ul>";
        String result = Edit.getSubNavigation3();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of updateAnwenderData method, of class Edit.
     */
    @Test
    public void testUpdateAnwenderData() {
        System.out.println("updateAnwenderData");
        String anr = "";
        String vn = "";
        String nn = "";
        String tel = "";
        String em = "";
        String pw = "";
        Boolean expResult = null;
        Boolean result = Edit.updateAnwenderData(anr, vn, nn, tel, em, pw);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
