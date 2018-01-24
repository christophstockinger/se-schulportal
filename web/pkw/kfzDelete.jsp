<%-- 
    Document   : kfzSucces
    Created on : 19.12.2017, 13:10:13
    Author     : patrickrichter
--%>

<%@page import="KfzModul.KfzMod"%>
<%@page import="DB.DBConnector"%>
<%@page import="anwender.Anwender"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
    /**
     * Überprüft, ob user eingeloggt ist.
     * Falls nicht, leitet auf Index zurück.
     * <p> 
     * Wenn der User eingeloggt ist, holt es sich aus der Session die Attribute
     * wie Email, Anrede, Name, Vorname, usw. des Userszur weiteren Nutzung. 
     * 
     * 
     */
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <%  
            /**
             * Funktion, die Verbindung mit dem DB-Connector aufbaut, und die Variablen
             * @param knz das zu löschende Kennzeichen übergibt. 
             * @param email stellt sicher, dass der der eingeloggte Benutzer nur sein eigenes Kennzeichen löschen kann.
             * Den Tabellennamen bekommen wir durch die KfzMod.java datei.
             * 
             * Wenn dbinsert die Daten der Email gefunden hat (SQL abfrage in DBConnector) 
             * wird der String sqlSuccess ausgegeben.
             * Falls keine Übereinstimmung vorhanden ist (ein User also ein Kennzeichen löschen will, 
             * das nicht auf seine Email registriert ist)
             * gibt es eine Fehlermeldung und der Eintrag wird nicht aus der Datenbank gelöscht.
             */
            
                     String sqlSuccess = "<h1>Sie haben Ihr Kennzeichen Erfolgreich gelöscht!</h1><form><input value='Zurück zur Kennzeichen-Konfiguration' onclick=\"window.location.href='kfzModul.jsp'\" type=button></form>";
                     String sqlFail = "<h1>Kennzeichen konnte nicht gelöscht werden! Vergewissern Sie sich, dass sie es richtig geschrieben haben!</h1><form><input value='Zurück zur Kennzeichen-Konfiguration' onclick=\"window.location.href='kfzModul.jsp'\" type=button></form>";
                   
                     // Variable knz durch vorheriges Formular
                    String knz = request.getParameter("kennzeichen");
                  
                    
                    try{
                       
                        
                        // Verbindung zum DB Connector
                        // Variable email wird von der Seite übergeben (siehe script oben).
                        
                        Boolean dbinsert = DBConnector.deleteKennzeichenData(KfzMod.databasetablename, knz, email);
                        
                    
                        if (dbinsert == true){
                        out.println(sqlSuccess);
                        } else {
                         
                            out.println(sqlFail);
                        }
                   
                        
                }   catch(Exception e){
                        System.out.println(e);
                }
                %>
        

        
        
        
                
               <!--// Javascript & jQuery //-->
        <script src="/se-schulportal/templates/thd-schulportal/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/func.js" type="text/javascript"></script> 
    </body>
</html>
