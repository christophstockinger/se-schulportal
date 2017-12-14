<%-- 
    Document   : verify
    Created on : 25.11.2017, 16:30:36
    Author     : Christoph
--%>

<%@page import="java.util.HashMap"%>
<%@page import="Verify.Verify"%>
<%@page import="DB.DBConnector"%>
<%@page import="java.util.Map"%>
<%@page import="anwender.Anwender"%>
<%@page import="Sender.SMSSender"%>
<%@page import="java.lang.Math.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Verifizierung | Schulportal</title>
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
                    String email = request.getParameter("email");
                    // Abfrage der Anzahl an Rollen
                    int rollenanzahl = Integer.parseInt(request.getParameter("anzahl"));

                    Map<Integer, String> anwenderrollen = new HashMap<Integer, String>();

                    for (int i = 0; i < rollenanzahl; i++) {
                        anwenderrollen.put(i, (String) request.getParameter("rolle" + i));
                    }

                    Boolean rollenwrite = DBConnector.writeAnwenderRollen(email, anwenderrollen);

                    if (rollenwrite) {
                        out.println("<p>Deine Rollen wurden vermerkt und gespeichert.</p>");
                        out.println("<p>Sobald ihr Account freigeschaltet ist, bekommen Sie eine E-Mail von uns.</p>");
                        // Send E-Mail to Administration
                        Verify.sendVerifySMSMailAdmin(email, anwenderrollen);
                    } else {
                        out.println("<p>Leider ist ein Fehler unterlaufen, bitte melde dich bei support@schulsportal.de.</p>");
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