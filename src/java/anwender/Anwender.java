/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anwender;

import DB.DBConnector;
import DB.DBKonstanten;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cstockinger
 */
public class Anwender implements DBKonstanten {

    private String anrede;
    private String vorname;
    private String nachname;
    private String email;
    private String telefonnummer;
    private String password;

    public final static String databasetablename = "Anwender";

    public Anwender(String anr, String vn, String nn, String em, String tel, String pw) {
        this.anrede = anr;
        this.vorname = vn;
        this.nachname = nn;
        this.email = em;
        this.telefonnummer = tel;
        this.password = pw;
    }

    // Getter-Setter-Anrede
    public void setAnrede(String anr) {
        this.anrede = anr;
    }

    public String getAnrede() {
        return anrede;
    }

    // Getter-Setter-Vorname
    public void setVorname(String vn) {
        this.vorname = vn;
    }

    public String getVorname() {
        return vorname;
    }

    // Getter-Setter-Nachnmae
    public void setNachname(String nn) {
        this.nachname = nn;
    }

    public String getNachname() {
        return nachname;
    }

    // Getter-Setter-E-Mail
    public void setEmail(String em) {
        this.email = em;
    }

    public String getEmail() {
        return email;
    }

    // Getter-Setter-Telefonnummer
    public void setTelefonnumer(String tnr) {
        this.telefonnummer = tnr;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    // Getter-Setter-Password
    public void setPassword(String pw) {
        this.password = pw;
    }

    public String getPassword() {
        return password;
    }

    public static Map getLoginData(String email) throws SQLException {
        Map<String, String> dbDataAnwender = new HashMap<String, String>();
        dbDataAnwender = DBConnector.getAnwenderdaten(databasetablename, email);

        return dbDataAnwender;
    }

    public static Map getVerifyData(String email) {
        Map dbDataAnwenderVerify = new HashMap();
        dbDataAnwenderVerify = DBConnector.getVerifyDaten(email);

        return dbDataAnwenderVerify;
    }

    /**
     * Ausgabe der Navigation in Abhängigkeit der Rolle
     *
     * @return
     */
    public String getNavigation() {
        Map rollen = DBConnector.getAnwenderRollen(email);
        String naviOutput = "";

        if (rollen.size() == 0) {
            naviOutput += "Du hast leider keine Berechtigungen.";
        } else {
            

            for (int i = 1; i <= rollen.size(); i++) {
                naviOutput += "<h4>" + rollen.get(i) + "</h4>";
                naviOutput += "<ul>";
                // Modul PKW
                if ((rollen.get(i)).equals("Admin")) {
                    naviOutput += "<li>";
                    naviOutput += "<a href=\"/se-schulportal/pkw/\">";
                    naviOutput += "<img class=\"icon\" src=\"/se-schulportal/images/icons/location-white.svg\">";
                    naviOutput += "<span>PKW</span>";
                    naviOutput += "</a>";
                    naviOutput += "</li>";
                }

                // Notenblatt
                if ((rollen.get(i)).equals("Admin") || (rollen.get(i)).equals("Rektor") || (rollen.get(i)).equals("Personal") || (rollen.get(i)).equals("Lehrer")) {
                    naviOutput += "<li>";
                    naviOutput += "<a href=\"/se-schulportal/notenblatt/\">";
                    naviOutput += "<img class=\"icon\" src=\"/se-schulportal/images/icons/graph-white.svg\">";
                    naviOutput += "<span>Notenblatt</span>";
                    naviOutput += "</a>";
                    naviOutput += "</li>";
                }

                // Kontakte
                if ((rollen.get(i)).equals("Admin")) {
                    naviOutput += "<li>";
                    naviOutput += "<a href=\"/se-schulportal/phone/\">";
                    naviOutput += "<img class=\"icon\" src=\"/se-schulportal/images/icons/phone-white.svg\">";
                    naviOutput += "<span>Kontakte</span>";
                    naviOutput += "</a>";
                    naviOutput += "</li>";
                }

                // Kalender
                if ((rollen.get(i)).equals("Admin")) {
                    naviOutput += "<li>";
                    naviOutput += "<a href=\"/se-schulportal/kalender/eventCalender.jsp\">";
                    naviOutput += "<img class=\"icon\" src=\"/se-schulportal/images/icons/calendar-white.svg\">";
                    naviOutput += "<span>Kalender</span>";
                    naviOutput += "</a>";
                    naviOutput += "</li>";
                }
                // Sender
                if ((rollen.get(i)).equals("Admin")) {
                    naviOutput += "<li>";
                    naviOutput += "<a href=\"/se-schulportal/messages/\">";
                    naviOutput += "<img class=\"icon\" src=\"/se-schulportal/images/icons/comment-square-white.svg\">";
                    naviOutput += "<span>Sender</span>";
                    naviOutput += "</a>";
                    naviOutput += "</li>";
                }
                // Stundenplan
                if ((rollen.get(i)).equals("Admin")) {
                    naviOutput += "<li>";
                    naviOutput += "<a href=\"/se-schulportal/#\">";
                    naviOutput += "<img class=\"icon\" src=\"/se-schulportal/images/icons/comment-square-white.svg\">";
                    naviOutput += "<span>Stundenplan</span>";
                    naviOutput += "</a>";
                    naviOutput += "</li>";
                }
                naviOutput += "</ul>";
            }
            
        }

        return naviOutput;
    }

    public String getDashboard() {
        Map rollen = DBConnector.getAnwenderRollen(email);
        String dashboardOutput = "";

        if (rollen.size() == 0) {
            dashboardOutput += "<div class='col-12 col-sm-12 dashboard_modul_image'><p>Du hast leider keine Berechtigungen.</p></div>";
        } else {

            for (int i = 1; i <= rollen.size(); i++) {
                dashboardOutput += "<div class='col-12 col-sm-12 dashboard_modul_headline'><h3>" + rollen.get(i) + "</h3></div>";
                // Modul PKW
                if ((rollen.get(i)).equals("Admin")) {
                    dashboardOutput += "<div class='col-12 col-sm-6 col-md-4 col-lg-3 dashboard_modul_image'>";
                    dashboardOutput += "<a href='/se-schulportal/pkw/'>";
                    dashboardOutput += "<img src='/se-schulportal/images/module/pkw.svg' alt='PKW' />";
                    dashboardOutput += "</a>";
                    dashboardOutput += "</div>";
                }

                // Notenblatt
                if ((rollen.get(i)).equals("Admin") || (rollen.get(i)).equals("Rektor") || (rollen.get(i)).equals("Personal") || (rollen.get(i)).equals("Lehrer")) {
                    dashboardOutput += "<div class='col-12 col-sm-6 col-md-4 col-lg-3 dashboard_modul_image'>";
                    dashboardOutput += "<a href='/se-schulportal/notenblatt/'>";
                    dashboardOutput += "<img src='/se-schulportal/images/module/notenblatt.svg' alt='Notenblatt' />";
                    dashboardOutput += "</a>";
                    dashboardOutput += "</div>";
                }

                // Kontakte
                if ((rollen.get(i)).equals("Admin")) {
                    dashboardOutput += "<div class='col-12 col-sm-6 col-md-4 col-lg-3 dashboard_modul_image'>";
                    dashboardOutput += "<a href='/se-schulportal/phone/'>";
                    dashboardOutput += "<img src='/se-schulportal/images/module/kontakte.svg' alt='Kontakte' />";
                    dashboardOutput += "</a>";
                    dashboardOutput += "</div>";
                }

                // Kalender
                if ((rollen.get(i)).equals("Admin")) {
                    dashboardOutput += "<div class='col-12 col-sm-6 col-md-4 col-lg-3 dashboard_modul_image'>";
                    dashboardOutput += "<a href='/se-schulportal/kalender/eventCalender.jsp'>";
                    dashboardOutput += "<img src='/se-schulportal/images/module/kalender.svg' alt='Kalender' />";
                    dashboardOutput += "</a>";
                    dashboardOutput += "</div>";
                }
                // Mail oder SMS versenden
                if ((rollen.get(i)).equals("Admin")) {
                    dashboardOutput += "<div class='col-12 col-sm-6 col-md-4 col-lg-3 dashboard_modul_image'>";
                    dashboardOutput += "<a href='/se-schulportal/messages/'>";
                    dashboardOutput += "<img src='/se-schulportal/images/module/sender.svg' alt='Sender' />";
                    dashboardOutput += "</a>";
                    dashboardOutput += "</div>";
                }
                // Stundenplan
                if ((rollen.get(i)).equals("Admin")) {
                    dashboardOutput += "<div class='col-12 col-sm-6 col-md-4 col-lg-3 dashboard_modul_image'>";
                    dashboardOutput += "<a href='/se-schulportal/#>";
                    dashboardOutput += "<img src='/se-schulportal/images/module/stundenplan.svg' alt='Stundenplan' />";
                    dashboardOutput += "</a>";
                    dashboardOutput += "</div>";
                }
            }
        }

        return dashboardOutput;
    }

    public String getUserNavigation() {
        String userNaviOutput = "";
        userNaviOutput += "<ul>";
        userNaviOutput += "<li>";
        userNaviOutput += "<a href='profil_edit/edit.jsp'> <img class='icon' src='/se-schulportal/images/icons/person.svg'> <span>Profil bearbeiten</span> </a>";
        userNaviOutput += "</li>";
        userNaviOutput += "</ul>";

        return userNaviOutput;
    }

    
    public static Boolean CheckEmailInDatabase(String email) {
        Map tmpEmailCheck = DB.DBConnector.getAnwenderdaten(Anwender.databasetablename, email);
        
        if ((tmpEmailCheck.size() == 0)||(tmpEmailCheck == null) ) {
            return false;
        } else {
            return true;
        }
    }
    protected void finalize() {
        System.out.println("Anwender wurde zerstört!!!");
    }

}
