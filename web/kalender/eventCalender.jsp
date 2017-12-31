<%-- 
    Document   : eventCalender
    Created on : 12.12.2017, 11:51:22
    Author     : peterjerger
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>Event-Kalender</title>
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
                <!--// CSS //-->
        <!--// CSS CS Reset //-->
        <link href="/se-schulportal/templates/thd-schulportal/css/cs-reset.css" rel="stylesheet" type="text/css" media="all">
        <!--// CSS Bootstrap Grid //-->
        <link href="/se-schulportal/templates/thd-schulportal/css/bootstrap-grid.min.css" rel="stylesheet" type="text/css" media="all">
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
        <div class="row">
            <div class="col-2"></div>
            <div class="col-10">
                <div class="row">
                    <div class="col-6"></div>
                    <div class="col-6 menu">
                        <span class="menupunkt"><a href="#">Dashboard</a></span>
                        <span class="menupunkt"><a href="#">Abmelden</a></span>
                        <span class="menupunkt"><a href="#">Kontakt</a></span>
                    </div>
                </div>
                
                
                
                
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
                        <script>
                            function togglefunction(){
                                var zu = document.getElementById("form_hinzu");
                                zu.style.display = (zu.style.display == "table") ? "none" : "table";
                                if(document.getElementById("btn").textContent != "Schließen"){
                                    document.getElementById("btn").textContent = "Schließen";
                                } else {
                                    document.getElementById("btn").textContent = "Termin hinzufügen";
                                }
                            }
                        </script>
                    </div>
                    <div id="form_hinzu">
                        <div class="row">
                            <div class="col-12">
                                <p>Wählen Sie das gewünschte Datum aus</p>
                                <input type="text" name="datum" id="datepicker" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <p>Wählen Sie die Zeit in der das Event stattfinden soll</p>
                                <span>Von: <input type="text" name="zeitVon" id="zeitVon" /> Bis: <input type="text" name="zeitBis" id="zeitBis" /></span>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <p>Geben Sie dem Event eine Bezeichnung mit</p>
                                <input type="text" name="bezeichnung" />
                            </div>
                        </div>
                        <button type="button" onclick="inDieDB()">Erstellen</button>
                    </div>
                    
                    <% 
                        String dat = request.getParameter("datum");
                        session.setAttribute("datum", dat);
                        String zevo = request.getParameter("zeitvon");
                        session.setAttribute("zeitvon", zevo);
                        String zebi = request.getParameter("zeitbis");
                        session.setAttribute("zeitbis", zebi);
                        String bez = request.getParameter("bezeichnung");
                        session.setAttribute("bezeichnung", bez);
                    %>
                    
                    
                    <div class="row heute">
                        <div class="col-12">
                            <h3>Heutige Veranstaltungen:</h3>
                        </div>
                    
                    <div class="row termine">
                        <div class="col-12">
                            <h3>Bevorstehende Veranstaltungen:</h3>
                        </div>
                    <!-- Daten aus der Datenbank holen --->
                    <!-- Als Array(?) in Kästchen anzeigen -->
                    
                    
                    
                    
                    </div>
                </div>
            </div>
        </div>
        
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
            
    </body>
    
</html>
