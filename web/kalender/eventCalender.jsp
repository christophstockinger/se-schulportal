<%-- 
    Document   : eventCalender
    Created on : 12.12.2017, 11:51:22
    Author     : peterjerger
--%>
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
        
        <!---// Datepicker //--->
        <link href="/se-schulportal/kalender/jquery-ui.css" rel="stylesheet" type="text/css" media="all">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link href="/resources/demos/style.css" rel="stylesheet" type="text/css" media="all">
        <script src="/se-schulportal/kalender/datepicker-jquery.js" type="text/javascript"></script>
        <script src="/se-schulportal/kalender/jquery-ui.js" type="text/javascript"></script>
  
        <!---// Timepicker //--->
        <link href="/se-schulportal/kalender/jquery.timepicker.min.css" rel="stylesheet" type="text/css" media="all">
        <script src="/se-schulportal/kalender/jquery.timepicker.min.js" type="text/javascript"></script>
        
        
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
        <div class="row">
            <div class="col-2"></div>
            <div class="col-10">
                <div class="row" style="height: 40px;"></div>
                <div class="row">
                    <div class="col-12 ">
                        <h1>Terminkalender</h1>
                    </div>
                </div>
                
                <div class="row heute">
                    <div class="col-12">
                        <h3>Heutige Veranstaltungen:</h3>
                        <% 
                        SimpleDateFormat today = new SimpleDateFormat("yyyy.MM.dd");
                                
                            
                            
                            %>
                        <!-- While-Schleife (bzw. do-while-Schleife) mit datum==date (evtl. Halbstündlich erhöhen) -->
                    </div>
                    
                    <div class="row termine">
                        <div class="col-12">
                            <h3>Bevorstehende Veranstaltungen:</h3>
                        </div>
                    </div>
                    <!-- Daten aus der Datenbank holen --->
                    <!-- Als Array(?) oder for-Schleife mit "datum" > date und in Kästchen anzeigen -->
                    
                </div>
                
                <!--- Event hinzufügen --->
                <!--- Versteckt und mit buttonklick togglebar -->
                <!--- Datum, Zeit und Bezeichnung in Datenbank legen -->
                <div class="hinzu">
                    <div class="row">
                        <button id="btn" type="button" onclick="togglefunction()">Termin hinzufügen</button>
                        
                    </div>
                    <form method="get" action="eventCalender.jsp">
                    <div id="form_hinzu">
                        <div class="row">
                            <div class="col-12 datum">
                                <p class="schrift">Wählen Sie das gewünschte Datum aus</p>
                                <input type="date" name="datum" value="2017-01-01" id="datum" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12 zeit">
                                <p class="schrift">Wählen Sie die Zeit in der das Event stattfinden soll</p>
                                <div class="row">
                                    <div class="col-2">Von: </div><div class="col-10"><input type="time" name="zeitVon" id="zeitVon" /></div>
                                </div>
                                <div class="row">
                                    <div class="col-2">Bis: </div><div class="col-10"><input type="time" name="zeitBis" id="zeitBis" /></div>
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
                        <span><button method="get" type="submit" id="inDieDB">Erstellen</button><button type="button" id="close" onclick="togglefunction()">Schließen</button></span>
                    </div>
                    </form>
                    
                  <%
                      
                        final String tblname = "Termine";
                        
                        String datum = request.getParameter("datum");
                        
                        if (datum != null){
                            LocalDate dat = LocalDate.parse(datum);
                            System.out.println(dat);
                        
                        
                        String zevo = request.getParameter("zeitVon");
                        String zebi = request.getParameter("zeitBis");                  
                        String bez = request.getParameter("bezeichnung");
                        
                        /*
                        
                        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                        Date parsed = format.parse("20110210");
                        java.sql.Date sql = new java.sql.Date(parsed.getTime());
                        
                        
                        String parsezevo = "zevo";
                        DateFormat zevof = new SimpleDateFormat("hh:mm a");
                        Date newzevo = zevof.parse(parsezevo);
                        System.out.println(newzevo);
                        
                        String parsezebi = "zebi";
                        DateFormat zebif = new SimpleDateFormat("hh:mm a");
                        Date newzebi = zebif.parse(parsezebi);
                        System.out.println(newzebi);
                        
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
                        LocalDate parsedate = LocalDate.parse(dat, dateFormat);
                        Date dat = Date.valueOf(parsedate);

                        System.out.println(dat);*/
                        
                        
                        Boolean dbinsert = DBConnector.DBTermine(tblname, dat, zevo, zebi, bez);
                        
                        if (dbinsert){
                            if (dat != null && zevo != null && zebi != null && bez != null){
                            out.println("Der Termin wurde erfolgreich hinzugefügt <br />");
                            out.println("Das Event: " + bez + " findet am " + dat + " statt, beginnt am " + zevo + " und endet um " + zebi);
                            }
                        } else {
                            out.println("Etwas ist schiefgelaufen");
                             out.println("Das Event: " + bez + " findet am " + dat + " statt, beginnt am " + zevo + " und endet um " + zebi);
                        }
                        
                       /* Boolean dboutput = DBConnector.GetDBTermine(tblname, dat, zevo, zebi, bez);
                        if(dboutput){
                            
                        } else {
                            
                        }*/
                        
                        }
                  %>
                        
                    
            </div>
        </div>
        </div>
            
        
        
        <script type="text/javascript">
            $( function() {
              $( "#datepicker" ).datepicker({
                  dateFormat: "yyyy.mm.dd"
                });
                
                $("#zeitVon").timepicker({
                    timeFormat: "G:i"
                });
                
                $("#zeitBis").timepicker({
                    timeFormat: "G:i"
                });
            });
        </script>  
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
