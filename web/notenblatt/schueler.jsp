<%-- 
    Document   : index
    Created on : 05.12.2017, 14:08:48
    Author     : Christoph
--%>

<%@page import="Notenblatt.Notenblatt"%>
<%@page import="anwender.Anwender"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    // Status Variable sowie String Variable für Weiterleitung auf Login-Seite
    Boolean loginstatus = (Boolean) session.getAttribute("login");
    String loginpage = "<script type='text/javascript'>window.location.replace('/schulportal/index.html');</script>";
    
    // User Variablen
    String email = "christoph.stockinger@stud.th-deg.de" ; // For Development: hardgecoded normalerweise (String) session.getAttribute("email")
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
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width; initial-scale=1.0"/>

        <title>Schülerübersicht | <% out.println(Notenblatt.modulname); %> | Schulportal</title>

        <meta name="description" content=""/>
        <meta name="author" content="Coding77 // Christoph Stockinger"/>
        <meta name="publisher" content="Coding77 // Christoph Stockinger"/>
        <meta name="copyright" content="&copy; Coding77 // Christoph Stockinger"/>
        <meta name="version" content="1.0"/>


        <!--// Favicon //-->
        <link rel="apple-touch-icon" sizes="57x57" href="/schulportal/images/favicon/apple-icon-57x57.png">
        <link rel="apple-touch-icon" sizes="60x60" href="/schulportal/images/favicon/apple-icon-60x60.png">
        <link rel="apple-touch-icon" sizes="72x72" href="/schulportal/images/favicon/apple-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="76x76" href="/schulportal/images/favicon/apple-icon-76x76.png">
        <link rel="apple-touch-icon" sizes="114x114" href="/schulportal/images/favicon/apple-icon-114x114.png">
        <link rel="apple-touch-icon" sizes="120x120" href="/schulportal/images/favicon/apple-icon-120x120.png">
        <link rel="apple-touch-icon" sizes="144x144" href="/schulportal/images/favicon/apple-icon-144x144.png">
        <link rel="apple-touch-icon" sizes="152x152" href="/schulportal/images/favicon/apple-icon-152x152.png">
        <link rel="apple-touch-icon" sizes="180x180" href="/schulportal/images/favicon/apple-icon-180x180.png">
        <link rel="icon" type="image/png" sizes="192x192" href="/schulportal/images/favicon/android-icon-192x192.png">
        <link rel="icon" type="image/png" sizes="32x32" href="/schulportal/images/favicon/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="96x96" href="/schulportal/images/favicon/favicon-96x96.png">
        <link rel="icon" type="image/png" sizes="16x16" href="/schulportal/images/favicon/favicon-16x16.png">
        <link rel="manifest" href="/schulportal/images/favicon/manifest.json">
        <meta name="msapplication-TileColor" content="#ffffff">
        <meta name="msapplication-TileImage" content="/schulportal/images/favicon/ms-icon-144x144.png">
        <meta name="theme-color" content="#ffffff">

        <!--// CSS //-->
        <!--// CSS CS Reset //-->
        <link href="/schulportal/templates/thd-schulportal/css/cs-reset.css" rel="stylesheet" type="text/css" media="all">
        <!--// CSS Bootstrap Grid //-->
        <link href="/schulportal/templates/thd-schulportal/css/bootstrap-grid.min.css" rel="stylesheet" type="text/css" media="all">

        <link href="/schulportal/templates/thd-schulportal/css/open-ionic.min.css" rel="stylesheet" type="text/css">
        <!--// CSS Main //-->
        <link href="/schulportal/templates/thd-schulportal/css/main.css" rel="stylesheet" type="text/css" media="all">


    </head>
    <body>
        <%
           // if ( loginstatus == false) {
           //     out.println(loginpage);
           // } %>
        <header class="row">
            <div class="col-2 col-sm-1 nav_burger" >
                <img data="#main_navigation" class="navicon nav_burger_image" src="/schulportal/images/icons/menu.svg" alt="Navigation öffnen" />
            </div>
            <div class="col-6 col-sm-9 brand">
                <a href="/schulportal/dashboard.jsp">
                    <img class="brand_image" src="/schulportal/images/logo/schullogo.svg" alt="Schulportal" />
                </a>
            </div>
            <div class="col-2 col-sm-1 user">
                <img data="#user_navigation" class="navicon user_image" src="/schulportal/images/user/user-dummy.svg" alt="Userbild" />
            </div>
            <div class="col-2 col-sm-1 logout">
                <a href="/schulportal/">
                    <img class="logout_image" src="/schulportal/images/icons/account-logout.svg" alt="Abmelden" />
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
        <!--// Main Modul //-->
        <main>
            <div class="row modul">
                <div class="col-12 col-sm-12 modul_headline">
                    <h2><% out.println( Notenblatt.modulname ); %></h2>
                </div>
                <div class="col-12 col-sm-12 modul_description">
                    <p><% out.println( Notenblatt.moduldesc ); %></p>
                </div>
                <nav class="col-12 col-sm-12 modul_nav">
                    <% System.out.println("E-Mail: " +  email); %>
                    <% out.println( Notenblatt.getSubNavigation( email ) ); %>
                </nav>
                <div class="col-12 col-sm-12 modul_form">
                    <form>
                        <a href="import.jsp" class="button"><img src="/schulportal/images/icons/data-transfer-upload-white.svg" alt=""/>Import</a>
                        <a href="export.jsp" class="button"><img src="/schulportal/images/icons/data-transfer-download-white.svg" alt=""/>Export</a>
                    </form>
                </div>
                <div class="col-12 col-sm-12 col-md-6 modul_table">
                    <h3>Klasse 3b</h3>
                    <table>
                        <tr>
                            <th>Anrede</th>
                            <th>Vorname</th>
                            <th>Nachname</th>
                            <th>Klasse</th>
                        </tr>
                        <tr>
                            <td>Herr</td>
                            <td>Max</td>
                            <td>Mustermann</td>
                            <td>Klasse 3b</td>
                        </tr>
                        <tr>
                            <td>Frau</td>
                            <td>Maria</td>
                            <td>Musterfrau</td>
                            <td>Klasse 3b</td>
                        </tr>
                    </table>
                </div>
                <div class="col-12 col-sm-12 col-md-6 modul_table">
                    <h3>Klasse 4a</h3>
                    <table>
                        <tr>
                            <th>Anrede</th>
                            <th>Vorname</th>
                            <th>Nachname</th>
                            <th>Klasse</th>
                        </tr>
                        <tr>
                            <td>Herr</td>
                            <td>Moritz</td>
                            <td>Mustermann</td>
                            <td>Klasse 4a</td>
                        </tr>
                        <tr>
                            <td>Frau</td>
                            <td>Martina</td>
                            <td>Musterfrau</td>
                            <td>Klasse 4a</td>
                        </tr>
                    </table>
                </div>
                
                
            </div>
        </main>
        <!--// User Navigation //-->
        <nav class="user_navi" id="user_navigation">
            <%
                user = new Anwender(anrede, vorname, nachname, email, telefonnummer, password);
                out.println("<div class='welcome'><p>Hallo " + anrede + " " + vorname + " " + nachname + "</p></div>");
                out.println( user.getUserNavigation() );
            %>
        </nav>
        
        <!--// Footer //-->
        <footer class="row">
            <div class="col-12 col-sm-6 imprint"><a href="/schulportal/impressum.html">Impressum</a></div>
            <div class="col-12 col-sm-6 copyright"><p>&copy 2017 THD - Christoph Stockinger</p></div>
        </footer>
    <!--// Javascript & jQuery //-->
        <script src="/schulportal/templates/thd-schulportal/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="/schulportal/templates/thd-schulportal/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/schulportal/templates/thd-schulportal/js/func.js" type="text/javascript"></script>

    </body>
</html>
