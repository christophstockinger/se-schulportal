<%-- 
    Document   : index
    Created on : 05.12.2017, 14:08:48
    Author     : Precha Sae-Heng
--%>

<%@page import="Phone.ModPhone"%>
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
    password = (String) ((Anwender) session.getAttribute("user")).getEmail();
    anrede = (String) ((Anwender) session.getAttribute("user")).getEmail();
    vorname = (String) ((Anwender) session.getAttribute("user")).getEmail();
    nachname = (String) ((Anwender) session.getAttribute("user")).getEmail();
    telefonnummer = (String) ((Anwender) session.getAttribute("user")).getEmail();
    
    } else {
        loginstatus = false;
    }
    
    %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width; initial-scale=1.0"/>

        <title>Suche Kontakte</title>

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

        <link href="/se-schulportal/templates/thd-schulportal/css/open-ionic.min.css" rel="stylesheet" type="text/css">
        <!--// CSS Main //-->
        <link href="/se-schulportal/templates/thd-schulportal/css/main.css" rel="stylesheet" type="text/css" media="all">


    </head>
    <body>
        <%
      //      if ( loginstatus == false) {
      //          out.println(loginpage);
      //      } 
        %>
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
        <!--// Main Modul //-->
        <main>
            <div class="row modul">
                <div class="col-12 col-sm-12 modul_headline">
                    <h2><% out.println(ModPhone.modulname); %></h2>
                </div>
                <div class="col-12 col-sm-12 modul_description">
                    <p><% out.println(ModPhone.moduldesc); %></p>
                </div>
                <nav class="col-12 col-sm-12 modul_nav">
                    
                </nav>
                <div class="col-12 col-sm-12 modul_form">
                    <form action="aufruf.jsp" method="GET">
                        <h4>Suchen in Klassen</h4>
                        <br>
                        <h6>Klasse:</h6>
                        <select name="klasse" id="klasse">
                            <option value="alle">Alle Klassen</option>
                            <option value="Klasse&#32;1a">1a</option>
                            <option value="Klasse&#32;1b">1b</option>
                            <option value="Klasse&#32;1c">1c</option>
                            <option value="Klasse&#32;2a">2a</option>
                            <option value="Klasse&#32;2b">2b</option>
                            <option value="Klasse&#32;2c">2c</option>
                            <option value="Klasse&#32;3a">3a</option>
                            <option value="Klasse&#32;3b">3b</option>
                            <option value="Klasse&#32;3c">3c</option>
                            <option value="Klasse&#32;4a">4a</option>
                            <option value="Klasse&#32;4b">4b</option>
                            <option value="Klasse&#32;4c">4c</option>
                        </select>                    
                        
                        <br>
                        <br>
                        <br>    
                        
                        <h4>Suchen nach Namen</h4>
                        <br>
                        <h6>Name:</h6>
                        <input type="text" name="name" placeholder="Geben Sie hier den Namen ein..." id="name"/>
                        
                        <br>
                        <br>
                        <br>
                        
                       <button type="submit" class="button">Suchen</button>
                    </form>
                </div>             
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
    <!--
        <script type="text/javascript">
            function search() {
                console.log("Geklick");
                var name = $('#name').val();
                var klasse = $('#klasse').val();
           
                var url = "aufruf.jsp?name=" + name + "&rolle=" + klasse;
                console.log(url);
                window.location.replace(url);
            }
        </script>     
    -->
        <script src="/se-schulportal/templates/thd-schulportal/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/func.js" type="text/javascript"></script>

    </body>
</html>
