<%-- 
    Document   : verify
    Created on : 25.11.2017, 16:30:36
    Author     : Christoph
--%>

<%@page import="anwender.Anwender"%>
<%@page import="Verify.Verify"%>
<%@page import="DB.DBConnector"%>
<%@page import="java.util.Map"%>
<%@page import="Sender.SMSSender"%>
<%@page import="java.lang.Math.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin Verifizierung | Schulportal</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!--// CSS //-->
        <link href="/schulportal/templates/thd-schulportal/css/bootstrap-grid.min.css" rel="stylesheet" type="text/css" media="all" />
        <link href="/schulportal/templates/thd-schulportal/css/cs-reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="/schulportal/templates/thd-schulportal/css/main.css" rel="stylesheet" type="text/css" media="all" />

    </head>
    <body class='starterpager'>
        <main>
            <div class="row content">
                <div class="col-12 col-sm-12 brand">
                    <a href='/schulportal/'>
                        <img src='/schulportal/images/logo/schullogo.svg' alt='Schulname' />
                    </a>
                </div>
                <div class='col-12 col-sm-12 form_register'>
                    <h3>Verifizierung</h3>
                <%
                    // Get Parameter
                    String status = request.getParameter("status");
                    session.setAttribute("status", status);
                    String email = request.getParameter("email");
                    session.setAttribute("email", email);
                    
                    System.out.println("Status: " + status);
                    System.out.println("EMail " + email);
                    System.out.println(status == "freizugeben");
                    System.out.println(status == "delete");
                    System.out.println(status == "freigabe");

                    Map anwenderdata;
                    Map anwenderrollen;
                    // Standardausgabe
                    if (status.equals("freizugeben") ) {
                        // Get Anwenderdaten
                        anwenderdata = DBConnector.getAnwenderdaten(Anwender.databasetablename, email);
                        System.out.println("Ich bin drin!");
                        // Get Rollen
                        anwenderrollen = DBConnector.getAnwenderRollen((String) session.getAttribute("email"));


                        out.println("<form><p class='textleft'>Folgender neuer Benutzer muss freigegeben werden:</p>");
                        out.println("<p class='textleft'> Vorname: " + anwenderdata.get("VORNAME") + "</p>");
                        out.println("<p class='textleft'> Nachname: " + anwenderdata.get("NACHNAME") + "</p>");
                        out.println("<p class='textleft'> E-Mail: <span id='email'>" + anwenderdata.get("EMAIL") + "</span></p>");
                        out.println("<p class='textleft'> Telefonnummer: " + anwenderdata.get("TELEFONNUMMER") + "</p>");
                        if (anwenderrollen == null) {
                            out.println("<p class='textleft'>Leider wurden keine Rollen gefunden.</p>");
                        } else {
                            out.println("<div class='rollen'><p>Der User beantragt folgende <span id='anzahl'>" + anwenderrollen.size() + "</span> Rollen:</p>");
                            out.println("<ul>");
                            for (int i = 1; i <= anwenderrollen.size(); i++) {
                                out.println("<li>" + anwenderrollen.get(i) + "<button id='rolle-" + i + "' value=\"" + anwenderrollen.get(i) + "\" onclick=\"deleterolle('" + anwenderrollen.get(i) + "')\" class='deletebtn'><img src='/schulportal/images/icons/trash.svg' alt='Löschen' /></button></li>");
                            }
                        }
                        out.println("</ul></div>");
                        out.println("<button class='button' onclick=\"admin()\">Benutzer freigeben</button>");
                        out.println("</form>");

                    } else {

                        // Delete User Rolle
                        if (status.equals("delete")) {

                            // Delete Rolle
                            String rolle = request.getParameter("rolle");
                            String deletefeedback = DBConnector.deleteAnwenderRolle((String) session.getAttribute("email"), rolle);

                            // Get Anwenderdaten
                            anwenderdata = DBConnector.getAnwenderdaten(Anwender.databasetablename, (String) session.getAttribute("email"));

                            // Get Rollen
                            anwenderrollen = DBConnector.getAnwenderRollen((String) session.getAttribute("email"));

                            out.println("<form><p class='textleft'>Folgender neuer Benutzer muss freigegeben werden:</p>");
                            out.println("<p class='textleft'> Vorname: " + anwenderdata.get("VORNAME") + "</p>");
                            out.println("<p class='textleft'> Nachname: " + anwenderdata.get("NACHNAME") + "</p>");
                            out.println("<p class='textleft'> E-Mail: <span id='email'>" + anwenderdata.get("EMAIL") + "</span></p>");
                            out.println("<p class='textleft'> Telefonnummer: " + anwenderdata.get("TELEFONNUMMER") + "</p>");
                            if (anwenderrollen == null) {
                                out.println("<p class='textleft'>Leider wurden keine Rollen gefunden.</p>");
                            } else {
                                out.println("<div class='rollen'><p>Der User beantragt folgende <span id='anzahl'>" + anwenderrollen.size() + "</span> Rollen:</p>");
                                out.println("<ul>");
                                for (int i = 1; i <= anwenderrollen.size(); i++) {
                                    out.println("<li>" + anwenderrollen.get(i) + "<button id='rolle-" + i + "' value=\"" + anwenderrollen.get(i) + "\" onclick=\"deleterolle('" + anwenderrollen.get(i) + "')\" class='deletebtn'><img src='/schulportal/images/icons/trash.svg' alt='Löschen' /></button></li>");
                                }
                            }
                            out.println("</ul></div>");
                            out.println("<button class='button' onclick=\"admin()\">Benutzer freigeben</button>");
                            }   

                        // Freigabe User
                        if (status.equals("freigabe")) {
                            // Get Anwenderdaten
                            anwenderdata = DBConnector.getAnwenderdaten(Anwender.databasetablename, (String) session.getAttribute("email"));
                            
                            // SET VerifyAdmin in Database
                            Boolean adminverify = DBConnector.writeAnwenderVerifystatusAdmin((String) session.getAttribute("email"), "true");
                            String freigabefeedback;
                            if (adminverify) {
                            // Send Verifymail User
                                freigabefeedback = Verify.sendVerifySMSMailUser((String) anwenderdata.get("ANREDE"), (String) anwenderdata.get("VORNAME"), (String) anwenderdata.get("NACHNAME"), (String) session.getAttribute("email"));
                            } else {
                                freigabefeedback = "Leider ist etwas schief gelaufen. Bitte melde sich bei support@schulportal.de";
                            }
                           
                            out.println("<p>" + freigabefeedback + "</p>");
                        }

                    }
                %>
                </div>
            </div>
        </main>
        <footer><p>&copy; 2017 Christoph Stockinger</p></footer>

        <!--// Javascript & jQuery //-->
        <script src="/schulportal/templates/thd-schulportal/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="/schulportal/templates/thd-schulportal/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/schulportal/templates/thd-schulportal/js/func.js" type="text/javascript"></script>
    </body>
</html>