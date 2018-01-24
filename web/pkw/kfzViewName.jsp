<%-- 
    Document   : kfzSucces
    Created on : 19.12.2017, 13:10:13
    Author     : patrickrichter
--%>

<%@page import="KfzModul.KfzModView"%>
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
             * Stellt Verbindung zum DB.Connector her.
             * Das Resultat des Scripts gibt zu einem gefundenen Eintrag des Kennzeichens in der Datenbank
             * die Email des Besitzers an.
             * @param knz das Kennzeichen des Inputs
             * @param email des eingeloggten Users
             * sind hier die zwei ausschlaggebenden Variablen.
             * Gibt einen String mit der Email des Besitzers wieder.
             */
                    
                    
                    String knz = request.getParameter("kennzeichen");
             
                 
                    try{
                        String conn = DBConnector.kfzViewDataNames(KfzModView.databasetablename, knz, email);
                        
                        if (conn.equals("Eintrag gefunden!")) {
                          
                            out.println("<h1> Eintrag gefunden!  Auto gehört zu "+email+" </h1><form><input value='Zurück zur Suche' onclick=\"window.location.href='kennzeichenViewName.jsp'\" type=button></form>"); 
                      
                            System.out.println("Eintrag gefunden!");
                        } else {
                            out.write(conn);
                            System.out.println(conn);
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
