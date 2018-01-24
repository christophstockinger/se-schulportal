<%-- 
    Document   : eventCalender
    Created on : 12.12.2017, 11:51:22
    Author     : peterjerger
--%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.time.LocalDate;"%>
<%@page import="anwender.Anwender"%>
<%@page import="DB.DBConnector"%>
<%@page import="Kalender.Termine"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Status Variable sowie String Variable für Weiterleitung auf Login-Seite
    Boolean loginstatus = (Boolean) session.getAttribute("login");
    String loginpage = "<script type='text/javascript'>window.location.replace('/se-schulportal/index.html');</script>";
    
    // User Variablen
    String email = "";
    String password = "";
    String anrede = "";
    String vorname = "";
    String nachname = "";
    String telefonnummer = "";
    Anwender user;
    
    if ( (Anwender) session.getAttribute("user")!= null ) {
    // User-Variablen mit Session-Values
    email = (String) ((Anwender) session.getAttribute("user")).getEmail();
    password = (String) ((Anwender) session.getAttribute("user")).getPassword();
    anrede = (String) ((Anwender) session.getAttribute("user")).getAnrede();
    vorname = (String) ((Anwender) session.getAttribute("user")).getVorname();
    nachname = (String) ((Anwender) session.getAttribute("user")).getNachname();
    telefonnummer = (String) ((Anwender) session.getAttribute("user")).getTelefonnummer();
    
    } else {
        loginstatus = false;
    }
    
    %>


<!DOCTYPE html>
<html>
    <head>
        
        <title>Termin hinzufügen</title>
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <!--// Favicon //-->
        <link rel="apple-touch-icon" sizes="57x57" href="/se-schulportal/images/favicon/apple-icon-57x57.png">
        <link rel="apple-touch-icon" sizes="60x60" href="/se-schulportal/images/favicon/apple-icon-60x60.png">
        <link rel="apple-touch-icon" sizes="72x72" href="/se-schulportal/images/favicon/apple-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="76x76" href="/se-schulportal/images/favicon/apple-icon-76x76.png">
        <link rel="apple-touch-icon" sizes="114x114" href="/se-schulportal/images/favicon/apple-icon-114x114.png">
        <link rel="apple-touch-icon" sizes="120x120" href="/se-schulportal/images/favicon/apple-icon-120x120.png">
        <link rel="apple-touch-icon" sizes="144x144" href="/se-schulportal/images/favicon/apple-icon-144x144.png">
        <link rel="apple-touch-icon" sizes="152x152" href="/se-schulportal/images/favicon/apple-icon-152x152.png">
        <link rel="apple-touch-icon" sizes="180x180" href="/se-schulportal/images/favicon/apple-icon-180x180.png">
        <link rel="icon" type="image/png" sizes="192x192" href="/se-schulportal/images/favicon/android-icon-192x192.png">
        <link rel="icon" type="image/png" sizes="32x32" href="/se-schulportal/images/favicon/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="96x96" href="/se-schulportal/images/favicon/favicon-96x96.png">
        <link rel="icon" type="image/png" sizes="16x16" href="/se-schulportal/images/favicon/favicon-16x16.png">
        <link rel="manifest" href="/se-schulportal/images/favicon/manifest.json">
        <meta name="msapplication-TileColor" content="#ffffff">
        <meta name="msapplication-TileImage" content="/se-schulportal/images/favicon/ms-icon-144x144.png">
        <meta name="theme-color" content="#ffffff">
        
            <!--// CSS //-->
        <!--// CSS CS Reset //-->
        <link href="/se-schulportal/templates/thd-schulportal/css/cs-reset.css" rel="stylesheet" type="text/css" media="all">
        <!--// CSS Bootstrap Grid //-->
        <link href="/se-schulportal/templates/thd-schulportal/css/bootstrap-grid.min.css" rel="stylesheet" type="text/css" media="all">

        <link href="/se-schulportal/templates/thd-schulportal/css/open-ionic.min.css" rel="stylesheet" type="text/css">
        <!--// CSS Main //-->
        <link href="/se-schulportal/templates/thd-schulportal/css/main.css" rel="stylesheet" type="text/css" media="all">
        <link href="/se-schulportal/kalender/eventCSS.css" rel="stylesheet" type="text/css" media="all">
        
        
    </head>
    
    <body>
        <header class="row">
            <div class="col-2 col-sm-1 nav_burger" >
                <img data="#main_navigation" class="navicon nav_burger_image" src="/se-schulportal/images/icons/menu.svg" alt="Navigation öffnen" />
            </div>
            <div class="col-6 col-sm-9 brand">
                <a href="/se-schulportal/dashboard.jsp">
                    <img class="brand_image" src="/se-schulportal/images/logo/schullogo.svg" alt="Schulportal" />
                </a>
            </div>
            <div class="col-2 col-sm-1 user">
                <img data="#user_navigation" class="navicon user_image" src="/se-schulportal/images/user/user-dummy.svg" alt="Userbild" />
            </div>
            <div class="col-2 col-sm-1 logout">
                <a href="/se-schulportal/">
                    <img class="logout_image" src="/se-schulportal/images/icons/account-logout.svg" alt="Abmelden" />
                </a>
            </div>
        </header>
        <!--// Main Navigation //-->
        <nav class="main_navi" id="main_navigation" >
           <%
                user = new Anwender(anrede, vorname, nachname, email, telefonnummer, password);
                out.println(user.getNavigation());
            %>
        </nav>
                        <!--- Event hinzufügen --->
                <!--- Datum, Zeit und Bezeichnung in Datenbank legen -->
                <div class="row ">
                    <div class="col-2"></div>
                    <div class="col-8">
                    <div class="row" style="height: 40px;"></div>
                        <div class="hinzu">
                            <h1>Termin hinzufügen</h1>
                            <hr>
                            <form class="THinzu" method="get" action="neuerTermin.jsp">
                                <div class="row">
                                    <div class="col-12 datum">
                                        <p class="schrift">Wählen Sie das gewünschte Datum aus</p>
                                        <input type="date" name="datum" id="datum" />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12 zeit">
                                        <p class="schrift">Wählen Sie die Zeit in der das Event stattfinden soll</p>
                                        <div class="row">
                                            <div class="col-1">Von: </div><div class="col-11"><input type="time" name="zeitVon" id="zeitVon" /></div>
                                        </div>
                                        <div class="row">
                                            <div class="col-1">Bis: </div><div class="col-11"><input type="time" name="zeitBis" id="zeitBis" /></div>
                                        </div>
                                    </div>
                                    <br/>
                                </div>
                                <div class="row">
                                    <div class="col-12 bezeichnung">
                                        <p class="schrift">Geben Sie dem Event eine Bezeichnung mit</p>
                                        <input type="text" name="bezeichnung" id="bezeichnung" />
                                    </div>
                                    <br/>
                                </div>
                                <button class="formbutton" method="get" type="submit" id="inDieDB">Erstellen</button><a href="eventCalender.jsp"><button class="formbutton" type="button" id="close">Zurück</button></a>
                           
                            </form>
                                <%

                                    //Tabellennamen übergeben
                                    String databasetablename = "Termine";
                                    
                                    //Zur Fehlervermeidung zuerst das Datum auslesen
                                    String datum = request.getParameter("datum");
                                    
                                    if (datum != null){
                                        String zevo = request.getParameter("zeitVon");
                                        String zebi = request.getParameter("zeitBis");                  
                                        String bez = request.getParameter("bezeichnung");

                                        //An DB übergeben
                                        Boolean dbinsert = DBConnector.DBTermine(databasetablename, datum, zevo, zebi, bez);

                                        if (dbinsert){ //Wenn alle Parameter erfolgreich übergeben, dann in DB lagern und zurück zum Kalender
                                            if (datum != null && zevo != null && zebi != null && bez != null){
                                            out.println("Der Termin wurde erfolgreich hinzugefügt <br />");
                                            out.println("Das Event: " + bez + " findet am " + datum + " statt, beginnt um " + zevo + " und endet um " + zebi);
                                            }
                                        response.sendRedirect("eventCalender.jsp");
                                        } else { //Bei Fehler wird Benutzer informiert, sonst nichts
                                            out.println("Etwas ist schiefgelaufen. Versuchen Sie es erneut");
                                        }

                                    }

                                %>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
        
                                <!--// Footer //-->
        <footer class="row">
            <div class="col-12 col-sm-6 imprint"><a href="/se-schulportal/impressum.html">Impressum</a></div>
            <div class="col-12 col-sm-6 copyright"><p>&copy 2017 THD - Peter Jerger</p></div>
        </footer>
                                
                                        <!--// Javascript & jQuery //-->
        <script src="/se-schulportal/templates/thd-schulportal/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/func.js" type="text/javascript"></script>
        
    </body>
</html>
