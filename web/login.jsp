<%-- 
    Document   : register
    Created on : 14.11.2017, 19:57:03
    Author     : Christoph
--%>



<%@page import="anwender.Anwender"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Erstellt am 14.11.2017 von Christoph Stockinger
-->

<html>
    <head>
        <title>Dashboard | Schulportal</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!--// CSS //-->
        <link href="templates/thd-schulportal/css/bootstrap-grid.min.css" rel="stylesheet" type="text/css" media="all" />
        <link href="templates/thd-schulportal/css/cs-reset.css" rel="stylesheet" type="text/css" media="all" />
        <link href="templates/thd-schulportal/css/main-old.css" rel="stylesheet" type="text/css" media="all" />

    </head>
    <body>
        <header>
            <h1>Schulportal</h1>
        </header>
        <main>
            <%
                Boolean loginstatus; // not init

                String email = request.getParameter("email"); // eingegebene E-Mail-Adresse
                String password = request.getParameter("password"); // eingegebenes Passwort
                
                Map dbDataAnwenderVerify = new HashMap();
                Map<String, String>  dbDataAnwender = new HashMap<String, String> ();
                dbDataAnwenderVerify = Anwender.getVerifyData(email);
                dbDataAnwender = Anwender.getLoginData(email); // Daten zur eingegeben E-Mail-Adresse aus der DB
                
                //DB Verify data in Variablen aufteilen
                Boolean verifymail = (Boolean) dbDataAnwenderVerify.get("VERIFYSTATUS_MAIL");
                Boolean verifytel = (Boolean) dbDataAnwenderVerify.get("VERIFYSTATUS_TEL");
                Boolean verifyadmin = (Boolean) dbDataAnwenderVerify.get("VERIFYSTATUS_ADMIN");
                System.out.println(verifymail + " | " + verifytel + " | " + verifyadmin);
                // DB data in Variablen aufteilen
                String dbanrede = (String) dbDataAnwender.get("ANREDE");
                String dbvorname = (String) dbDataAnwender.get("VORNAME");
                String dbnachname = (String) dbDataAnwender.get("NACHNAME");
                String dbemail = (String) dbDataAnwender.get("EMAIL");
                String dbtelefonnummer = (String) dbDataAnwender.get("TELEFONNUMMER");
                String dbpassword = (String) dbDataAnwender.get("PASSWORT");

                if ((verifymail) && (verifytel) && (verifyadmin) ) {
                if ((dbemail.equals(email)) && (dbpassword.equals(password))) {
                    // Login erfolgreich
                    loginstatus = true; // Ändern des Loginstatus auf true
                    System.out.println("Login erfolgreich!");
                    // Create new Anwender called user
                    Anwender user = new Anwender(dbanrede, dbvorname, dbnachname, dbemail, dbtelefonnummer, dbpassword);
                    session.setAttribute("anrede", user.getAnrede());
                    session.setAttribute("vorname", user.getVorname());
                    session.setAttribute("nachname", user.getNachname());
                    session.setAttribute("email", user.getEmail());
                    session.setAttribute("telefonnummer", user.getTelefonnummer());

                } else {
                    loginstatus = false;
                    // E-Mail-Adresse Check!
                    if (dbemail.equals(email)) {
                        out.println("<p>Leider stimmt deine E-Mail-Adresse nicht!");
                    }
                    // Password Check
                    if (dbpassword.equals(password)) {
                        out.println("<p>Leider ist dein Passwort falsch!");
                    }

                    out.println("<div class='row content'><div class='col-12 col-sm-12 js_alert' id='alert'></div> <div class='col-2 col-sm-2'><!--// Rand links //--></div> <div class='col-8 col-sm-8 loginform'>");
                    out.println("<input type='email' name='email' placeholder='E-Mail-Adresse' id='email' />");
                    out.println("<input type='password' name='password' placeholder='Password' id='password' />");
                    out.println("<button type='button' class='button' onclick='login()'>Anmelden</button>");
                    out.println("</form></div><div class='col-2 col-sm-2'><!--// Rand rechts //-->");

                }
                
                // Wenn erfolgreich
                if (loginstatus) {
                    // Create Navigation
                    out.println("<nav>");
                    out.println("<p class='welcome'>Hallo " + session.getAttribute("anrede") + " " + session.getAttribute("vorname") + " " + session.getAttribute("nachname") + " | <a href='/se-schulportal/' >Abmelden</a></p>");
                    out.println("<ul>");
                    // Für Schüler

                    // Für Eltern
                    // Für Lehrer
                    // Für Personal
                    // Für Rektor
                    // Für Admins
                    out.println("<li><a href='#'>Einstellungen</a></li>");
                    
                    out.println("</ul>");
                    out.println("</nav>");

                } else {
                    out.println("<p>Leider ist etwas schief gelaufen!</p>");
                    out.println("<p>Bitte vesuche dich <a href='index.html'>hier</a> nochmal anzumelden</p>");
                }
                } else {
                    out.println("<div class='row content'>");
                    out.println("<p>Dein Account wurde noch nicht freigeschaltet!</p>");
                    out.println("</div>");
                }
            %>
            <div class="row content">


            </div>
        </main>
        <footer><p>&copy; 2017 Christoph Stockinger</p></footer>

        <!--// Javascript & jQuery //-->
        <script src="templates/thd-schulportal/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="templates/thd-schulportal/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="templates/thd-schulportal/js/func.js" type="text/javascript"></script>
    </body>
</html>
