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
public class KfzModViewTest {
    
    public KfzModViewTest() {
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
     * Test of getSubNavigation method, of class KfzModView.
     */
    @Test
    public void testGetSubNavigation() {
        System.out.println("getSubNavigation");
        String expResult = "<ul><li>  <a href='../pkw/kennzeichenView.jsp'>Nach Kennzeichen Suchen</a> </li><li> <a href='../pkw/kennzeichenViewName.jsp'>Wem gehört das Auto? </a> </li></ul>";
        String result = KfzModView.getSubNavigation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
