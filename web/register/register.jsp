
<%@page import="Sender.SMSSender"%>
<%@page import="Sender.MailSender"%>
<%@page import="javax.mail.Session"%>
<%-- 
    Document   : register
    Created on : 14.11.2017, 19:57:03
    Author     : Christoph
--%>
<%@page import="DB.DBConnector"%>
<%@page import="anwender.Anwender"%>
<%@page import="Verify.Verify"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Erstellt am 14.11.2017 von Christoph Stockinger
-->
<html>
    <head>
        <title>Registrierung | Schulportal</title>
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
                    <h3>Registrierung</h3>
                    <%
                        String setButton = request.getParameter("register");
                        if ( Boolean.parseBoolean( (String) setButton) ) {
                        String anr = request.getParameter("anrede");
                        String vn = request.getParameter("vorname");
                        String nn = request.getParameter("nachname");
                        String tel = request.getParameter("telefonnummer");
                        String em = request.getParameter("email");
                        String pw = request.getParameter("password");
                        
                        out.println( Verify.anwenderRegister(anr, vn, nn, tel, em, pw) );
                        
                        } else {

                    %>

                    <form method="GET" action="register.jsp" >
                        <h4>Persönliche Daten</h4>
                        <select name="anrede" class="anrede" id="anrede">
                            <option value="Frau"> Frau</option>
                            <option value="Herr"> Herr</option>
                        </select>
                        <input required type="text" name="vorname" placeholder="Vorname" id="vorname" />
                        <input required type="text" name="nachname" placeholder="Nachname" id="nachname" />
                        <input required type="text" name="telefonnummer" placeholder="Handynummer" id="telefonnummer" />
                        <h4>Anmeldedaten</h4>
                        <input required type="email" name="email" placeholder="E-Mail-Adresse" id="email" />
                        <input required type="password" name="password" placeholder="Password" id="password" />

                        <button type="submit" class="button" name="register" value="true">Registrieren</button>
                    </form>
                    <%
                        }
%>
                </div>
            </div>
        </main>
        <!--// Footer //-->
        <footer class="row">
            <div class="col-12 col-sm-6 imprint"><a href="/se-schulportal/impressum.html">Impressum</a></div>
            <div class="col-12 col-sm-6 copyright"><p>&copy 2017 THD - Christoph Stockinger</p></div>
        </footer>

        <!--// Javascript & jQuery //-->
        <script src="/se-schulportal/templates/thd-schulportal/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/func.js" type="text/javascript"></script>
    </body>
</html>