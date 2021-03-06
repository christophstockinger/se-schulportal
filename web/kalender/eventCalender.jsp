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
        
        <title>Event-Kalender</title>
        
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
        <%/*
            if (loginstatus == false) {
                out.println(loginpage);
            }*/ %>
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
        <div class="row">
            <div class="col-2"></div>
            <div class="col-8">
                <div class="row" style="height: 40px;"></div>
                <div class="kalender">
                        <h1>Terminkalender</h1>
                        <hr>
                
                <div class="row heute">
                    <h3>Heutige Veranstaltungen:</h3>
                </div>    
                <div class="row termine">
                    <h3>Bevorstehende Veranstaltungen:</h3>
                        <%
                            String alleTermine = Kalender.Termine.getTermine();
                            out.print(alleTermine);
                        %>
                </div>
                    
                <%  //Verifizierung, sodass nur Rektor (bzw. andere berechtigte Personen) Termine hinzufügen können
                    /*        -----Für Testzwecke noch auskommentiert------
                    Map userrollen = new HashMap();
                    String output = "";
                    if (email != null) {
                        userrollen = DBConnector.getAnwenderRollen(email);
                        for (int i = 1; i <= userrollen.size(); i++) {
                            if ( userrollen.get(i).equals("Rektor") ) {
                                output += "<div class='row'>";
                                output += "<a href='neuerTermin.jsp'><button class='hbutton' id='btn' type='button'>Termin hinzufügen</button></a>";
                                output += "</div>";
                            }*/
                %>
                    <div class='row'></div>
                        <a href='neuerTermin.jsp'><button class='hbutton' id='btn' type='button'>Termin hinzufügen</button></a>
                    </div>
                </div>
            </div>
            <div class="col-2"></div>
        </div>
        
        
        <!--// Footer //-->
        <footer class="row">
            <div class="col-12 col-sm-6 imprint"><a href="/se-schulportal/impressum.html">Impressum</a></div>
            <div class="col-12 col-sm-6 copyright"><p>&copy 2017 THD - Peter Jerger</p></div>
        </footer>
        
        
        <script type="text/javascript">
            var dateControl = document.querySelector('input[type="date"]');
            dateControl.value = 'Datum auswählen';
            function togglefunction(){
                var hinzu = document.getElementById("form_hinzu");
                hinzu.style.display = (hinzu.style.display == "table") ? "none" : "table";
                if(document.getElementById("btn").style.display != "none"){
                    document.getElementById("btn").style.display = "none";
                } else {
                    document.getElementById("btn").style.display = "table";
                }
            }

        </script>
        
        <script src="/se-schulportal/templates/thd-schulportal/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-se-schulportal/js/func.js" type="text/javascript"></script>
            
    </body>
    
</html>
