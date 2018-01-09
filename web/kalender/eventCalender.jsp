<%-- 
    Document   : eventCalender
    Created on : 12.12.2017, 11:51:22
    Author     : peterjerger
--%>

<%@page import="Kalender.Termine"%>
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
