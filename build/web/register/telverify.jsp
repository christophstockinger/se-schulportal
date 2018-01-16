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
                    String email = (String) request.getParameter("email");
                    String pineingabe = (String) request.getParameter("pineingabe");

                    int pin = Verify.getVerifySMSCode();
                    session.setAttribute("pin", pin);
                    System.out.println("Pin: " + pin);

                    // PIN Check
                    /* 3 Versuche für die richtige Eingabe:
                         * --> Bei Success: Rollenausgabe 
                         * --> Bei Fail: Löschen von Userdaten aus der Anweder-Database
                     */
                    // Set Startversuchcounter
                    final int startversuch = 0;

                    // Check Set Parameter
                    if (pineingabe == null) {

                        // Wenn kein Parameter gesetzt ist
                        // --> Ausgabe: Formular
                        out.println("<form>");
                        out.println("<input type='number' name='pineingabe' id='userpin'>");
                        out.println("<input type='hidden' value='" + email + "' name='email' id='email'>");
                        out.println("<button type='button' class='button' onclick=\"pin()\">Absenden</button>");
                        out.println("</form> ");
                    } else {
                        System.out.println("Pineingabe ist da!");
                        // Wenn Parameter gesetzt    
                        // Set Versuchcounter
                        if ((Integer) session.getAttribute("versuch") == 0) {
                            session.setAttribute("versuch", startversuch + 1);
                        } else {
                            System.out.println("Es gibt eine Session Versuch!");
                        }

                        System.out.println("(1) Versuch: " + session.getAttribute("versuch"));

                        // --> Check der Eingabe
                        System.out.println(session.getAttribute("pin").toString());
                        String spin = (String) session.getAttribute("pin").toString();
                        if (pineingabe.equals(spin)) {
                            System.out.println("Eingabe ist korrekt!");
                            // Korrekt
                            out.println("<p>Deine Eingabe ist richtig!</p>");
                            // Write Database 
                            Boolean verifytel = DBConnector.writeAnwenderVerifystatusTel(email, "true");
                            //Session Destroy
                            //session.invalidate();

                            if (verifytel) {
                                // Rollenauswahl
                                out.println("<form><h4>Schritt 3: Rollenauswahl</h4>");
                                out.println("<p>Bitte wähle deine Rollen aus.</p>");
                                out.println(Verify.rollenSelectionOutput());
                                out.println("<input type='hidden' value='" + email + "' name='email' id='email'>");
                                out.println("<button type='button' class='button' onclick=\"rollen()\">Speichern</button>");
                                out.println("</form>");
                            }
                        } else {
                            out.println("<p>Die Eingabe war leider nicht erfolgreich</p>");
                            // Dritter Versuch
                            if ((Integer) session.getAttribute("versuch") == 3) {
                                // Userdaten aus DB löschen
                                String deletefeedback = DBConnector.deleteAnwenderdaten(email);
                                out.println("<p>" + deletefeedback + "</p>");
                            } else {

                                // Versuch um eins erhöhen
                                session.setAttribute("versuch", ((Integer) session.getAttribute("versuch")) + 1);

                                // Sende neuen Pin
                                // GET Telefonnummer
                                Map anwenderdata = DBConnector.getAnwenderdaten(Anwender.databasetablename, (String) session.getAttribute("email"));
                                String tel = (String) anwenderdata.get("TELEFONNUMMER");

                                // Create PIN
                                Verify.setVerifySMSCode();
                                // Pin bekommen
                                pin = Verify.getVerifySMSCode();

                                // Create Message
                                String message = "Dein Authentifizierungscode für deine Handynummer " + tel + " lautet: " + pin;
                                System.out.println("Message: " + message);
                                // Send SMS                            
                                SMSSender.sendSMS(tel, message);

                                out.println("<p>Wir haben Ihnen soeben an ihre Handynummer eine SMS mit einem neuen PIN gesendet.</p>");

                                out.println("<form><h4>Schritt 3: Rollenauswahl</h4>");
                                out.println("<p>Bitte wähle deine Rollen aus.</p>");
                                out.println(Verify.rollenSelectionOutput());
                                out.println("<input type='hidden' value='" + email + "' name='email' id='email'>");
                                out.println("<button type='button' class='button' onclick=\"rollen()\">Speichern</button>");
                                out.println("</form>");
                            }

                        }
                    }

                    /*
                    

                     */
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