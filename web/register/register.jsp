
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
                    <h3>Registrierung</h3>
                    <%
                        String anr = request.getParameter("anrede");
                        session.setAttribute("anrede", anr);
                        String vn = request.getParameter("vorname");
                        session.setAttribute("vorname", vn);
                        String nn = request.getParameter("nachname");
                        session.setAttribute("nachname", nn);
                        String tel = request.getParameter("telefonnummer");
                        if (tel == null) {

                        } else {
                            tel = tel.substring(1, tel.length());
                            tel = "+49" + tel;
                            System.out.println(tel);
                        }
                        session.setAttribute("telefonnummer", tel);
                        String em = request.getParameter("email");
                        session.setAttribute("email", em);
                        String pw = request.getParameter("password");
                        session.setAttribute("password", pw);

                        if ((vn == null) || (nn == null) || (em == null) || (tel == null) || (pw == null) || (anr == null)) {
                            // Parameter nicht vorhanden!
                    %>

                    <form>
                        <h4>Persönliche Daten</h4>
                        <select name="anrede" class="anrede" id="anrede">
                            <option value="Frau"> Frau</option>
                            <option value="Herr"> Herr</option>
                        </select>
                        <input type="text" name="vorname" placeholder="Vorname" id="vorname" />
                        <input type="text" name="nachname" placeholder="Nachname" id="nachname" />
                        <input type="text" name="telefonnumer" placeholder="Handynummer" id="telefonnummer" />
                        <h4>Anmeldedaten</h4>
                        <input type="email" name="email" placeholder="E-Mail-Adresse" id="email" />
                        <input type="password" name="password" placeholder="Password" id="password" />

                        <button type="button" class="button" onclick="register()">Registrieren</button>
                    </form>
                    <%
                        } else {
                            // Parameter vorhanden!
                            anr = (String) session.getAttribute("anrede");
                            vn = (String) session.getAttribute("vorname");
                            nn = (String) session.getAttribute("nachname");
                            tel = (String) session.getAttribute("telefonnummer");
                            em = (String) session.getAttribute("email");
                            pw = (String) session.getAttribute("password");

                            // Database Insert
                            Boolean dbinsert = DBConnector.writeRegistryAnwenderData(Anwender.databasetablename, anr, vn, nn, tel, em, pw);
                            Boolean dbverifyinsert = DBConnector.writeAnwenderVerify(em, false, false, false);
                            // Für Testzwecke!!!
                            // Boolean dbinsert = true;
                            // Boolean dbverifyinsert = true;
                            if (dbinsert) {
                                out.println("<p>Deine Daten konnten erfolgreich verarbeitet werden.</p>");

                                // Mailversand.
                                if (dbverifyinsert) {
                                    // Mail versenden
                                    String feedbackverifymail = Verify.sendVerfifyMail(anr, vn, nn, em);

                                    // Feedback
                                    out.println("<p>" + feedbackverifymail + "</p>");
                                } else {
                                    // Falls Verify-Einträge nicht in DB geschrieben wurden!
                                    System.out.println("Verify Eintrag Error!");
                                }
                            } else {
                                // Falls User-Einträge nicht in DB geschrieben wurden!
                                out.println("<p>Leider ist bei der Datenverarbeitung ein Fehler unterlaufen! Bitte versuche dich erneut zu registrieren.</p>");
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