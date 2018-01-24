/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;


import java.text.SimpleDateFormat;
import java.io.PrintWriter;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Peter
 */
public class DBConnectorTest {
    
    public DBConnectorTest() {
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
     * Test of DBTermine method, of class DBConnector.
     */
    @Test
    public void testDBTermine() {
        System.out.println("DBTermine");
        String tblname = "";
        String datum = null;
        String zevo = "";
        String zebi = "";
        String bez = "";
        Boolean expResult = null;
        Boolean result = DBConnector.DBTermine(tblname, datum, zevo, zebi, bez);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
