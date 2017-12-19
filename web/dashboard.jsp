<%-- 
    Document   : dashboard
    Created on : 05.12.2017, 14:08:48
    Author     : Christoph
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="anwender.Anwender"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<!--// 
Know-How:
Ionic Icons: https://useiconic.com/open/ 
//-->
<%
    // Status Variablen sowie String Variable für Weiterleitung auf Login-Seite
    Boolean loginstatus; // not init
    Boolean verify;
    String loginpage = "<script type='text/javascript'>window.location.replace('/se-schulportal/index.html');</script>";

    // User-Variablen
    String email = request.getParameter("email"); // eingegebene E-Mail-Adresse
    String password = request.getParameter("password"); // eingegebenes Passwort
    String anrede = "";
    String vorname = "";
    String nachname = "";
    String telefonnummer = "";
    Anwender user;

    // Maps für Verifizierungsdaten und DB-Daten des Users
    Map dbDataAnwenderVerify = new HashMap();
    Map<String, String> dbDataAnwender = new HashMap<String, String>();
    dbDataAnwenderVerify = Anwender.getVerifyData(email);
    dbDataAnwender = Anwender.getLoginData(email); // Daten zur eingegeben E-Mail-Adresse aus der DB

    //DB Verify data in Variablen aufteilen
    Boolean verifymail = (Boolean) dbDataAnwenderVerify.get("VERIFYSTATUS_MAIL");
    Boolean verifytel = (Boolean) dbDataAnwenderVerify.get("VERIFYSTATUS_TEL");
    Boolean verifyadmin = (Boolean) dbDataAnwenderVerify.get("VERIFYSTATUS_ADMIN");

    if ((verifymail == null) || (verifytel == null) || (verifyadmin == null)) {
        verify = false;
    } else {
        verify = (verifymail) && (verifytel) && (verifyadmin);
    }

    // DB data in Variablen aufteilen
    String dbanrede = (String) dbDataAnwender.get("ANREDE");
    String dbvorname = (String) dbDataAnwender.get("VORNAME");
    String dbnachname = (String) dbDataAnwender.get("NACHNAME");
    String dbemail = (String) dbDataAnwender.get("EMAIL");
    String dbtelefonnummer = (String) dbDataAnwender.get("TELEFONNUMMER");
    String dbpassword = (String) dbDataAnwender.get("PASSWORT");

    // Check ob Verifizierung abgeschlossen
    if (verify) {
        // Erfolgreich
        // Setzen des Loginstatus wenn E-Mail und Password mit DB übereinstimmen
        loginstatus = (dbemail.equals(email)) && (dbpassword.equals(password));

        // Create a new User
        user = new Anwender(dbanrede, dbvorname, dbnachname, dbemail, dbtelefonnummer, dbpassword);
        // User Attribute global zur Verfügung zu stellen
        anrede = user.getAnrede();
        vorname = user.getVorname();
        nachname = user.getNachname();
        telefonnummer = user.getTelefonnummer();
        email = user.getEmail();

        session.setAttribute("user", user);

    } else {
        loginstatus = false;
    }

    session.setAttribute("login", loginstatus);
    // For Development!!
    session.setAttribute("email", email);
%>


<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width; initial-scale=1.0"/>

        <title>Dashboard | Schulportal</title>

        <meta name="description" content=""/>
        <meta name="author" content="Coding77 // Christoph Stockinger"/>
        <meta name="publisher" content="Coding77 // Christoph Stockinger"/>
        <meta name="copyright" content="&copy; Coding77 // Christoph Stockinger"/>
        <meta name="version" content="1.0"/>


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
        <!--// CSS Main //-->
        <link href="/se-schulportal/templates/thd-schulportal/css/main.css" rel="stylesheet" type="text/css" media="all">


    </head>
    <body>
        <%
            if (loginstatus == false) {
                out.println(loginpage);
            } %>
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
                <%
                    out.println("<img data='#user_navigation' class='navicon user_image' src='/se-schulportal/images/user/user-dummy.svg' alt='Userbild' />");
                %>
            </div>
            <div class="col-2 col-sm-1 logout">
                <a href='/se-schulportal/'>
                    <img class='logout_image' src='/se-schulportal/images/icons/account-logout.svg' alt='Abmelden' />
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
        <!--// Main Dashboard //-->
        <main>
            <div class="row dashboard">
                <%
                    user = new Anwender(anrede, vorname, nachname, email, telefonnummer, password);
                    out.println(user.getDashboard());
                %>
            </div>
        </main>
        <!--// User Navigation //-->
        <nav class="user_navi" id="user_navigation">
            <%
                user = new Anwender(anrede, vorname, nachname, email, telefonnummer, password);
                out.println("<div class='welcome'><p>Hallo " + anrede + " " + vorname + " " + nachname + "</p></div>");
                out.println(user.getUserNavigation());
            %>
        </nav>

        <!--// Footer //-->
        <footer class="row">
            <div class="col-12 col-sm-6 imprint"><a href="/se-schulportal/impressum.html">Impressum</a></div>
            <div class="col-12 col-sm-6 copyright"><p>&copy 2017 THD - Christoph Stockinger</p></div>
        </footer>
        <!--// Javascript & jQuery //-->
        <script src="/se-schulportal/templates/thd-schulportal/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/func.js" type="text/javascript"></script>

    </body>
</html>
