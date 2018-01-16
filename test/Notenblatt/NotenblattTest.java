/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Notenblatt;

import javax.servlet.http.HttpServletRequest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Christoph
 */
public class NotenblattTest {
    
    public NotenblattTest() {
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
     * Test of getSubNavigation method, of class Notenblatt.
     */
    @Test
    public void testGetSubNavigation_String() {
        System.out.println("getSubNavigation");
        String email = "christoph.stockinger@stud.th-deg.de";
        String expResult = "<ul><li>Berechtigungen als Admin:</li><li> <a href='klassen.jsp'>Klassenübersicht</a> </li><li> <a href='exam.jsp'>Probe anlegen</a> </li></ul><ul><li>Berechtigungen als Lehrer:</li><li> <a href='exam.jsp'>Probe anlegen</a> </li></ul>";
        String result = Notenblatt.getSubNavigation(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }


    /**
     * Test of getKlassenAsSelect method, of class Notenblatt.
     */
    @Test
    public void testGetKlassenAsSelect() {
        System.out.println("getKlassenAsSelect");
        String expResult = "<select required name='klasse' id='klasse'><option>Klasse auswählen</option><option value='Klasse 1a'>Klasse 1a</option><option value='Klasse 1b'>Klasse 1b</option></select>";
        String result = Notenblatt.getKlassenAsSelect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }


    /**
     * Test of getKlassenOverview method, of class Notenblatt.
     */
    @Test
    public void testGetKlassenOverview() {
        System.out.println("getKlassenOverview");
        String expResult = "<h3>Klassenübersicht</h3><table cellpadding='0' cellspacing='0'><tr><td>Klasse 1a</td><td><a href='schueler.jsp?klasse=Klasse 1a' class='button'> <img src='/se-schulportal/images/icons/brush-white.svg' alt=''/> Bearbeiten </a></td><td>4 Schüler</td></tr><tr><td>Klasse 1b</td><td><a href='schueler.jsp?klasse=Klasse 1b' class='button'> <img src='/se-schulportal/images/icons/brush-white.svg' alt=''/> Bearbeiten </a></td><td>9 Schüler</td></tr><tr><td>Klasse 1c</td><td><a href='import.jsp?klasse=Klasse 1c' class='button'> <img src='/se-schulportal/images/icons/data-transfer-upload-white.svg' alt=''/> Import </a></td><td>0 Schüler</td></tr><tr><td>Klasse 2a</td><td><a href='import.jsp?klasse=Klasse 2a' class='button'> <img src='/se-schulportal/images/icons/data-transfer-upload-white.svg' alt=''/> Import </a></td><td>0 Schüler</td></tr><tr><td>Klasse 2b</td><td><a href='import.jsp?klasse=Klasse 2b' class='button'> <img src='/se-schulportal/images/icons/data-transfer-upload-white.svg' alt=''/> Import </a></td><td>0 Schüler</td></tr><tr><td>Klasse 2c</td><td><a href='import.jsp?klasse=Klasse 2c' class='button'> <img src='/se-schulportal/images/icons/data-transfer-upload-white.svg' alt=''/> Import </a></td><td>0 Schüler</td></tr><tr><td>Klasse 3a</td><td><a href='import.jsp?klasse=Klasse 3a' class='button'> <img src='/se-schulportal/images/icons/data-transfer-upload-white.svg' alt=''/> Import </a></td><td>0 Schüler</td></tr><tr><td>Klasse 3b</td><td><a href='import.jsp?klasse=Klasse 3b' class='button'> <img src='/se-schulportal/images/icons/data-transfer-upload-white.svg' alt=''/> Import </a></td><td>0 Schüler</td></tr><tr><td>Klasse 3c</td><td><a href='import.jsp?klasse=Klasse 3c' class='button'> <img src='/se-schulportal/images/icons/data-transfer-upload-white.svg' alt=''/> Import </a></td><td>0 Schüler</td></tr><tr><td>Klasse 4a</td><td><a href='import.jsp?klasse=Klasse 4a' class='button'> <img src='/se-schulportal/images/icons/data-transfer-upload-white.svg' alt=''/> Import </a></td><td>0 Schüler</td></tr><tr><td>Klasse 4b</td><td><a href='import.jsp?klasse=Klasse 4b' class='button'> <img src='/se-schulportal/images/icons/data-transfer-upload-white.svg' alt=''/> Import </a></td><td>0 Schüler</td></tr><tr><td>Klasse 4c</td><td><a href='import.jsp?klasse=Klasse 4c' class='button'> <img src='/se-schulportal/images/icons/data-transfer-upload-white.svg' alt=''/> Import </a></td><td>0 Schüler</td></tr></table>";
        String result = Notenblatt.getKlassenOverview();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }




    /**
     * Test of getKlassenSchuelerOverview method, of class Notenblatt.
     */
    @Test
    public void testGetKlassenSchuelerOverview() {
        System.out.println("getKlassenSchuelerOverview");
        String klasse = "Klasse 1a";
        String expResult = "<table cellpadding='0' cellspacing='0'><tr><th>Nr.</th><th>Nachname</th><th>Vorname</th><th></th></tr><tr><td>1</td><td>Mustermann</td><td>Max</td><td><a class='button' href='schuelernoten.jsp?schueler=Max.Mustermann@schule.de'><img src='/se-schulportal/images/icons/pie-chart-white.svg' alt='' /> Notenstatistik</a></tr><tr><td>2</td><td>Musterfrau</td><td>Maria</td><td><a class='button' href='schuelernoten.jsp?schueler=Maria.Musterfrau@schule.de'><img src='/se-schulportal/images/icons/pie-chart-white.svg' alt='' /> Notenstatistik</a></tr><tr><td>3</td><td>Max</td><td>Mustermann</td><td><a class='button' href='schuelernoten.jsp?schueler=mustermann.max@schule.de'><img src='/se-schulportal/images/icons/pie-chart-white.svg' alt='' /> Notenstatistik</a></tr><tr><td>4</td><td>Maria</td><td>Musterfrau</td><td><a class='button' href='schuelernoten.jsp?schueler=musterfrau.maria@schule.de'><img src='/se-schulportal/images/icons/pie-chart-white.svg' alt='' /> Notenstatistik</a></tr></table>";
        String result = Notenblatt.getKlassenSchuelerOverview(klasse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getLehrerAsSelect method, of class Notenblatt.
     */
    @Test
    public void testGetLehrerAsSelect() {
        System.out.println("getLehrerAsSelect");
        String expResult = "<select name='lehrer' id='lehrer'><option>Lehrer auswählen</option><option value='christoph.stockinger@stud.th-deg.de'>Stockinger Christoph</option><option value='cs@christophstockinger.de'>Forstner Thomas </option></select>";
        String result = Notenblatt.getLehrerAsSelect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getFaecherAsSelect method, of class Notenblatt.
     */
    @Test
    public void testGetFaecherAsSelect() {
        System.out.println("getFaecherAsSelect");
        String expResult = "<select required name='fach' id='fach'><option>Fach auswählen</option><option value='Deutsch'>Deutsch</option><option value='Englisch'>Englisch</option><option value='Ethik'>Ethik</option><option value='Foerderunterricht'>Förderunterricht</option><option value='Heimat und Sachunterricht'>Heimat und Sachunterricht</option><option value='Kunst'>Kunst</option><option value='Mathematik'>Mathematik</option><option value='Musik'>Musik</option><option value='Religion'>Religion</option><option value='Sport'>Sport</option><option value='Werken'>Werken</option><option value='Werken und Gestalten'>Werken und Gestalten</option></select>";
        String result = Notenblatt.getFaecherAsSelect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getExamartenAsSelect method, of class Notenblatt.
     */
    @Test
    public void testGetExamartenAsSelect() {
        System.out.println("getExamartenAsSelect");
        String expResult = "<select required name='art' id='art'><option >Probenart auswählen</option><option value='muendliche Probe'>mündliche Probe</option><option value='praktische Probe'>praktische Probe</option><option value='schriftlich Probe'>schriftlich Probe</option></select>";
        String result = Notenblatt.getExamartenAsSelect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of writeExam method, of class Notenblatt.
     */
    @Test
    public void testWriteExam() {
        System.out.println("writeExam");
        String klasse = "Klasse 1a";
        String fach = "Deutsch";
        String art = "muendliche Probe";
        String lehrer = "christoph.stockinger@stud.th-deg.de";
        String date = "2018-01-16";
        String expResult = "<p>Die Prüfung wurde erfolgreich erstellt!</p>";
        String result = Notenblatt.writeExam(klasse, fach, art, lehrer, date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }


    /**
     * Test of getExamMarkFromDatabase method, of class Notenblatt.
     */
    @Test
    public void testGetExamMarkFromDatabase() {
        System.out.println("getExamMarkFromDatabase");
        int examid = 5;
        String klasse = "Klasse 1b";
        String expResult = "<table cellpadding='0' cellspacing='0'><tr><th>Nr.</th><th>Nachname</th><th>Vorname</th><th>Note</th></tr><tr><td>1</td><td>Pitagyros</td><td>Dimitri</td><td><select required name='dimitri.pitagyros@schule.de'/><option>Note auswählen</option><option selected value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='0'>nicht teilgenommen</option></select></td></tr><tr><td>2</td><td>Mittelmeier</td><td>Felix</td><td><select required name='felix.mittelmeier@schule.de'/><option>Note auswählen</option><option value='1'>1</option><option selected value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='0'>nicht teilgenommen</option></select></td></tr><tr><td>3</td><td>Lakshmü</td><td>Hermine</td><td><select required name='hermine.lakshmue@schule.de'/><option>Note auswählen</option><option value='1'>1</option><option value='2'>2</option><option selected value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='0'>nicht teilgenommen</option></select></td></tr><tr><td>4</td><td>Purzel</td><td>Jaqueline</td><td><select required name='jaqueline.purzel@schule.de'/><option>Note auswählen</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option selected value='5'>5</option><option value='6'>6</option><option value='0'>nicht teilgenommen</option></select></td></tr><tr><td>5</td><td>Benno</td><td>Maximilian</td><td><select required name='maximilian.benno@schule.de'/><option>Note auswählen</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option selected value='0'>nicht teilgenommen</option></select></td></tr><tr><td>6</td><td>Loca De Mal</td><td>Michael</td><td><select required name='michael.loca-de-mal@schule.de'/><option>Note auswählen</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option selected value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='0'>nicht teilgenommen</option></select></td></tr><tr><td>7</td><td>Müller</td><td>Mike</td><td><select required name='mike.mueller@schule.de'/><option>Note auswählen</option><option value='1'>1</option><option selected value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='0'>nicht teilgenommen</option></select></td></tr><tr><td>8</td><td>Gonzales</td><td>Pablo</td><td><select required name='pablo.gonzales@schule.de'/><option>Note auswählen</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option selected value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='0'>nicht teilgenommen</option></select></td></tr><tr><td>9</td><td>Sanchez</td><td>Pabloina</td><td><select required name='pabloina.sanchez@schule.de'/><option>Note auswählen</option><option value='1'>1</option><option selected value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='0'>nicht teilgenommen</option></select></td></tr></table><button type='submit' name='examsmarkupdate' value='true'>Notenänerungen speichern</button>";
        String result = Notenblatt.getExamMarkFromDatabase(examid, klasse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getExamStatistik method, of class Notenblatt.
     */
    @Test
    public void testGetExamStatistik() {
        System.out.println("getExamStatistik");
        int examid = 4;
        String expResult = "<h3>Probenstatistik</h3><div class='row'><div class='col-12 col-sm-12 col-md-6'><table><tr><td>Datum: </td><td>3.11.2017</td></tr><tr><td>Fach: </td><td>Werken und Gestalten</td></tr><tr><td>Art: </td><td>praktische Probe</td></tr><tr><td>Klasse: </td><td>Klasse 1b</td></tr><tr><td>Lehrer: </td><td>Herr Christoph Stockinger</td></tr><tr><td>Anzahl der teilgenommen Schüler: </td><td>9</td></tr><tr><td>Durchschnitt: </td><td>2,78</td></tr></table></div><div class='col-12 col-sm-12 col-md-6'><table><tr><th>Note </th><th>Anzahl </th><th>Prozent </th></tr><tr><td>1</td><td>1 Schüler </td><td>11 %</td></tr><tr><td>2</td><td>3 Schüler</td><td>33 %</td></tr><tr><td>3</td><td>3 Schüler</td><td>33 %</td></tr><tr><td>4</td><td>1 Schüler</td><td>11 %</td></tr><tr><td>5</td><td>1 Schüler</td><td>11 %</td></tr><tr><td>6</td><td>0 Schüler</td><td>0 %</td></tr></table></div></div><table style='margin-top: 50px;'><tr><th>Nr </th><th>Nachname </th><th>Vorname </th><th>Note </th></tr><tr><td>1</td><td>Pitagyros</td><td>Dimitri</td><td>5</td></tr><tr><td>2</td><td>Mittelmeier</td><td>Felix</td><td>3</td></tr><tr><td>3</td><td>Lakshmü</td><td>Hermine</td><td>4</td></tr><tr><td>4</td><td>Purzel</td><td>Jaqueline</td><td>2</td></tr><tr><td>5</td><td>Benno</td><td>Maximilian</td><td>2</td></tr><tr><td>6</td><td>Loca De Mal</td><td>Michael</td><td>2</td></tr><tr><td>7</td><td>Müller</td><td>Mike</td><td>1</td></tr><tr><td>8</td><td>Gonzales</td><td>Pablo</td><td>3</td></tr><tr><td>9</td><td>Sanchez</td><td>Pabloina</td><td>3</td></tr></table></div>";
        String result = Notenblatt.getExamStatistik(examid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getSchuelerStatistik method, of class Notenblatt.
     */
    @Test
    public void testGetSchuelerStatistik() {
        System.out.println("getSchuelerStatistik");
        String schueleremail = "dimitri.pitagyros@schule.de";
        String expResult = "<h3>Statistik für Dimitri Pitagyros</h3><table><tr><th>Fach</th><th>mündliche Probe</th><th>praktische Probe</th><th>schriftlich Probe</th><th>Durchschnitt</th></tr><tr><td>Deutsch</td><td></td><td></td><td></td><td>Es sind keine Prüfungen angelegt!</td></tr><tr><td>Englisch</td><td>0 | </td><td></td><td>1 | 1 | </td><td>0,67</td></tr><tr><td>Ethik</td><td></td><td></td><td></td><td>Es sind keine Prüfungen angelegt!</td></tr><tr><td>Förderunterricht</td><td></td><td></td><td></td><td>Es sind keine Prüfungen angelegt!</td></tr><tr><td>Heimat und Sachunterricht</td><td></td><td></td><td></td><td>Es sind keine Prüfungen angelegt!</td></tr><tr><td>Kunst</td><td></td><td></td><td></td><td>Es sind keine Prüfungen angelegt!</td></tr><tr><td>Mathematik</td><td></td><td></td><td></td><td>Es sind keine Prüfungen angelegt!</td></tr><tr><td>Musik</td><td></td><td></td><td></td><td>Es sind keine Prüfungen angelegt!</td></tr><tr><td>Religion</td><td></td><td></td><td></td><td>Es sind keine Prüfungen angelegt!</td></tr><tr><td>Sport</td><td></td><td></td><td></td><td>Es sind keine Prüfungen angelegt!</td></tr><tr><td>Werken</td><td></td><td></td><td></td><td>Es sind keine Prüfungen angelegt!</td></tr><tr><td>Werken und Gestalten</td><td></td><td>5 | </td><td>1 | </td><td>3,0</td></tr></table>";
        String result = Notenblatt.getSchuelerStatistik(schueleremail);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }


    /**
     * Test of getSchuelerCount method, of class Notenblatt.
     */
    @Test
    public void testGetSchuelerCount() {
        System.out.println("getSchuelerCount");
        String klasse = "Klasse 1a";
        Integer expResult = 4;
        Integer result = Notenblatt.getSchuelerCount(klasse);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
    
}
