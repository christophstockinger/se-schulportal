<%-- 
    Document   : changeSuccess
    Created on : Jan 17, 2018, 11:32:26 AM
    Author     : rodrigoscheipel
--%>

<%@page import="Stundenplan.Stundenplan"%>
<%@page import="DB.DBConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            
            String plan = request.getParameter("p");
            /*String fach2 = request.getParameter("d2");
            String fach3 = request.getParameter("d3");
            String fach4 = request.getParameter("d4");
            String fach5 = request.getParameter("d5");
            String fach6 = request.getParameter("d6");
            String fach7 = request.getParameter("d7");
            String fach8 = request.getParameter("d8");
            String fach9 = request.getParameter("d9");
            String fach10 = request.getParameter("d10");
            */
            String tage[] = plan.split("]");
            for(int ii = 0; ii < tage.length;ii++){
                String tag2[] = tage[ii].split(",");
                String s= "c";
                if(-1 != tag2[1].indexOf("c")){
                    String zeile= tag2[2];
                    String reihe= tag2[3];
                    String fach= tag2[5];
                    
                   /* try{
                    
                    Boolean dbinsert = DBConnector.writeScheduleSlot(Stundenplan.databasetablenameSlot, zeile, reihe);
                    
                    } catch(Exception e){
                        System.out.println(e);
                    }
                    } */
                     int c=0;
                }
                // int b = 0;
            }
            // int a = 0;
             
         %>
        
        <p>Stundenplan erfolgreich aktuallisiert</p>
        
        
        
        
    </body>
</html>
