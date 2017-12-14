<%-- 
    Document   : verify
    Created on : 25.11.2017, 16:30:36
    Author     : Christoph
--%>

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
        <link href="/se-schulportal/templates/thd-schulportal/css/bootstrap-grid.min.css" rel="stylesheet" type="text/css" media="all" />
        <link href="/se-schulportal/templates/thd-schulportal/css/cs-reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="/se-schulportal/templates/thd-schulportal/css/main.css" rel="stylesheet" type="text/css" media="all" />

    </head>
    <body class='starterpager'>
        <main>
            <div class="row content">
                <div class="col-12 col-sm-12 brand">
                    <a href='/se-schulportal/'>
                        <img src='/se-schulportal/images/logo/schullogo.svg' alt='Schulname' />
                    </a>
                </div>
                <div class='col-12 col-sm-12 form_register'>
                    <h3>Verifizierung</h3>
                    <%
                        String email = request.getParameter("email");
                        session.setAttribute("email", email);
                        Boolean verifymail = DBConnector.writeAnwenderVerifystatusMail((String) session.getAttribute("email"), "true");

                        if (verifymail) {
                            // E-Mail Verify successful
                            out.println("<p>Deine E-Mail-Adresse ist nun authentifiziert.</p>");

                            // Telefonnummer Verify
                            // Powered by Nexmo
                            out.println("<form><h4>Telefon-Authentifizierung</h4>");

                            // GET Telefonnummer
                            Map anwenderdata = DBConnector.getAnwenderdaten(Anwender.databasetablename, (String) session.getAttribute("email"));
                            String tel = (String) anwenderdata.get("telefonnummer");

                            // Create PIN
                            Verify.setVerifySMSCode();
                            // Pin bekommen
                            int pin = Verify.getVerifySMSCode();

                            // Create Message
                            String message = "Dein Authentifizierungscode für deine Handynummer " + tel + " lautet: " + pin;
                            System.out.println("Message: " + message);
                            // Send SMS                            
                            SMSSender.sendSMS(tel, message);

                            out.println("<p>Wir haben Ihnen soeben an ihre Handynummer eine SMS mit einem PIN gesendet.</p>");

                            out.println("<input type='number' name='pineingabe' id='userpin'>");
                            out.println("<input type='hidden' value='" + email + "' name='email' id='email'>");
                            out.println("<button type='button' class='button' onclick=\"pin()\">Absenden</button>");
                            out.println("</form>");

                            session.setAttribute("versuch", 0);

                        }
                    %>
                </div>
            </div>
        </main>
        <footer><p>&copy; 2017 Christoph Stockinger</p></footer>

        <!--// Javascript & jQuery //-->
        <script src="/se-schulportal/templates/thd-schulportal/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/func.js" type="text/javascript"></script>
    </body>
</html>