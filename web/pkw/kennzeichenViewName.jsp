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
            
                    
                    //mail = (String) session.getAttribute("email");
                    String knz = request.getParameter("kennzeichen");
             
                    // String mail = request.getParameter("email");
                    
                    // if ( (Anwender) session.getAttribute("user")!= null ) {
                    // User-Variablen mit Session-Values
                    // mail = (String) ((Anwender) session.getAttribute("user")).getEmail();
                    
                    try{
                        String conn = DBConnector.kfzViewData(KfzModView.databasetablename, knz);
                        
                        if (conn.equals("Eintrag gefunden!")) {
                          
                            out.println("<h1> Eintrag gefunden </h1><form><input value='Zurück zur Suche' onclick=\"window.location.href='kennzeichenView.jsp'\" type=button></form>"); // i.e. write the results of the method call
                      
                            System.out.println("Eintrag gefunden!");
                        } else {
                            out.write(conn);
                            System.out.println(conn);
                        }
                            
                       
                        
                        
                        
                        
                        
                        
                        // String sql = "SELECT kennzeichen FROM NUMMERNSCHILD where kennzeichen='PA XI 337'";
                        // out.println(sql);
                
                        // stmt.executeUpdate(sql);
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