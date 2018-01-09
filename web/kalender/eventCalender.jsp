<%-- 
    Document   : eventCalender
    Created on : 12.12.2017, 11:51:22
    Author     : peterjerger
--%>
<%@page import="Modul_example.ModExample"%>
<%@page import="anwender.Anwender"%>
<%@page import="Kalender.Termine"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>





<html>
    <head>
        
        <title>Event-Kalender</title>
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
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
                
                
                
                <div class="row">
                    <div class="col-12 ">
                        <h1>Terminkalender</h1>
                    </div>
                </div>
                
                <!--- Event hinzufügen --->
                <!--- Versteckt und mit buttonklick togglebar -->
                <!--- Datum, Zeit und Bezeichnung in Datenbank legen -->
                <div class="hinzu">
                    <div class="row">
                        <button id="btn" type="button" onclick="togglefunction()">Termin hinzufügen</button>
                        
                    </div>
                    <div id="form_hinzu">
                        <div class="row">
                            <div class="col-12 datum">
                                <p class="schrift">Wählen Sie das gewünschte Datum aus</p>
                                <input type="text" name="datum" id="datepicker" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12 zeit">
                                <p class="schrift">Wählen Sie die Zeit in der das Event stattfinden soll</p>
                                <div class="row">
                                    <div class="col-2">Von: </div><div class="col-10"><input type="text" name="zeitVon" id="zeitVon" /></div>
                                </div>
                                <div class="row">
                                    <div class="col-2">Bis: </div><div class="col-10"><input type="text" name="zeitBis" id="zeitBis" /></div>
                                </div>
                            </div>
                            <br/>
                        </div>
                        <div class="row">
                            <div class="col-12 bezeichnung">
                                <p class="schrift">Geben Sie dem Event eine Bezeichnung mit</p>
                                <input type="text" name="bezeichnung" />
                            </div>
                            <br/>
                        </div>
                        <span><button type="button" id="inDieDB" onclick="inDieDB()">Erstellen</button><button type="button" id="close" onclick="togglefunction()">Schließen</button></span>
                    </div>
                   
                    
                    
                    <div class="row heute">
                        <div class="col-12">
                            <h3>Heutige Veranstaltungen:</h3>
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
            </div>
        
        
        
        <!--// Footer //-->
        <footer class="row">
            <div class="col-12 col-sm-6 imprint"><a href="/schulportal/impressum.html">Impressum</a></div>
            <div class="col-12 col-sm-6 copyright"><p>&copy 2017 THD - Christoph Stockinger</p></div>
        </footer>
    <!--// Javascript & jQuery //-->
        <script src="/schulportal/templates/thd-schulportal/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="/schulportal/templates/thd-schulportal/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/schulportal/templates/thd-schulportal/js/func.js" type="text/javascript"></script>
        <script>
            $( function() {
              $( "#datepicker" ).datepicker({
                  dateFormat: "dd.mm.yy"
                });
                
                $("#zeitVon").timepicker({
                    timeFormat: "G:i"
                });
                
                $("#zeitBis").timepicker({
                    timeFormat: "G:i"
                });
            });
        </script>  
        <script>
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
            
    </body>
    
</html>
