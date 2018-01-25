<%-- 
    Document   : index
    Created on : 05.12.2017, 14:08:48
    Author     : Christoph
--%>

<%@page import="Stundenplan.Stundenplan"%>
<%@page import="anwender.Anwender"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<!--// 
Know-How:
Ionic Icons: https://useiconic.com/open/ 
//-->
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
    password = (String) ((Anwender) session.getAttribute("user")).getPassword();
    anrede = (String) ((Anwender) session.getAttribute("user")).getAnrede();
    vorname = (String) ((Anwender) session.getAttribute("user")).getVorname();
    nachname = (String) ((Anwender) session.getAttribute("user")).getNachname();
    telefonnummer = (String) ((Anwender) session.getAttribute("user")).getTelefonnummer();
    
    } else {
        loginstatus = false;
    }
    
    %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width; initial-scale=1.0"/>

        <title>Startseite | Modul_Example | Schulportal</title>

        <meta name="description" content=""/>
        <meta name="author" content="Coding77 // Christoph Stockinger"/>
        <meta name="publisher" content="Coding77 // Christoph Stockinger"/>
        <meta name="copyright" content="&copy; Coding77 // Christoph Stockinger"/>
        <meta name="version" content="1.0"/>


        <!--// Favicon //-->
        <link rel="apple-touch-icon" sizes="57x57" href="/se-schulportal/images/favicon/apple-icon-57x57.png">
        <link rel="apple-touch-icon" sizes="60x60" href="/se-schulportal/images/favicon/apple-icon-60x60.png">
        <link rel="apple-touch-icon" sizes="72x72" href="/se-schulportal/images/favicon/apple-icon-72x72.png">
        <link rel="apple-touch-icon" sizes="76x76" href="/se-schulportal/images/favicon/apple-icon-76x76.png">
        <link rel="apple-touch-icon" sizes="114x114" href="/se-schulportal/images/favicon/apple-icon-114x114.png">
        <link rel="apple-touch-icon" sizes="120x120" href="/se-schulportal/images/favicon/apple-icon-120x120.png">
        <link rel="apple-touch-icon" sizes="144x144" href="/se-schulportal/images/favicon/apple-icon-144x144.png">
        <link rel="apple-touch-icon" sizes="152x152" href="/se-schulportal/images/favicon/apple-icon-152x152.png">
        <link rel="apple-touch-icon" sizes="180x180" href="/se-schulportal/images/favicon/apple-icon-180x180.png">
        <link rel="icon" type="image/png" sizes="192x192" href="/se-schulportal/images/favicon/android-icon-192x192.png">
        <link rel="icon" type="image/png" sizes="32x32" href="/se-schulportal/images/favicon/favicon-32x32.png">
        <link rel="icon" type="image/png" sizes="96x96" href="/se-schulportal/images/favicon/favicon-96x96.png">
        <link rel="icon" type="image/png" sizes="16x16" href="/se-schulportal/images/favicon/favicon-16x16.png">
        <link rel="manifest" href="/se-schulportal/images/favicon/manifest.json">
        <meta name="msapplication-TileColor" content="#ffffff">
        <meta name="msapplication-TileImage" content="/se-schulportal/images/favicon/ms-icon-144x144.png">
        <meta name="theme-color" content="#ffffff">

        <!--// CSS //-->
        <!--// CSS CS Reset //-->
        <link href="/se-schulportal/templates/thd-schulportal/css/cs-reset.css" rel="stylesheet" type="text/css" media="all">
        <!--// CSS Bootstrap Grid //-->
        <link href="/se-schulportal/templates/thd-schulportal/css/bootstrap-grid.min.css" rel="stylesheet" type="text/css" media="all">

        
      
        
        
        <!--// CSS Main //-->
        <link href="/se-schulportal/templates/thd-schulportal/css/main.css" rel="stylesheet" type="text/css" media="all">
         <link href="/se-schulportal/templates/thd-schulportal/css/style.css" rel="stylesheet" type="text/css" media="all">

        
        


    </head>
    <body>
        <%
            //if ( loginstatus == false) {
            //    out.println(loginpage);
            // } %>
        <header class="row">
            <div class="col-2 col-sm-1 nav_burger" >
                <img data="#main_navigation" class="navicon nav_burger_image" src="/se-schulportal/images/icons/menu.svg" alt="Navigation öffnen" />
            </div>
            <div class="col-6 col-sm-9 brand">
                <a href="/se-schulportal/dashboard.jsp">
                    <img class="brand_image" src="/se-schulportal/images/logo/schullogo.svg" alt="Schulportal" />
                </a>
            </div>
            <div class="col-2 col-sm-1 user">
                <img data="#user_navigation" class="navicon user_image" src="/se-schulportal/images/user/user-dummy.svg" alt="Userbild" />
            </div>
            <div class="col-2 col-sm-1 logout">
                <a href="/se-schulportal/">
                    <img class="logout_image" src="/se-schulportal/images/icons/account-logout.svg" alt="Abmelden" />
                </a>
            </div>
        </header>
        <!--// Main Navigation //-->
        <nav class="main_navi" id="main_navigation" >
           <%
                user = new Anwender(anrede, vorname, nachname, email, telefonnummer, password);
                out.println(user.getNavigation());
            %>
        </nav>
        <!--// Main Modul //-->
        <main>
            <div class="row modul">
                <div class="col-12 col-sm-12 modul_headline">
                    <h2><% out.println(Stundenplan.modulname ); %></h2>
                </div>
                <div class="col-12 col-sm-12 modul_description">
                    <p><% out.println(Stundenplan.moduldesc ); %></p>
                </div>
                <nav class="col-12 col-sm-12 modul_nav">
                <!-- CODE HIER -->	
		<!-- tables inside this DIV could have draggable content -->
		<div id="redips-drag">
			<table id="table1">
				<colgroup><col width="100"/><col width="50"/><col width="100"/><col width="100"/><col width="100"/><col width="100"/><col width="100"/></colgroup>
				<tr>
                    <td class="redips-mark">Faecher</td>
                    <td class="redips-mark">Zeit</td>
					<td class="redips-mark">Montag</td>
					<td class="redips-mark">Dienstag</td>
					<td class="redips-mark">Mittwoch</td>
					<td class="redips-mark">Donnerstag</td>
					<td class="redips-mark">Freitag</td>
				</tr>
				<tr style="background-color: #eee">
					<td><div id="d1" class="redips-drag t1 redips-clone d1">Deutsch</div></td>
					<td class="redips-mark">07:35<br>08:20</td>
					<td></td>
					<td></td>
					<td></td>
                    <td></td>
                    <td></td>
				</tr>
				<tr>
					<td><div id="d2" class="redips-drag t1 redips-clone d2">Englisch</div></td>
					<td class="redips-mark">08:20<br>09:05</td>
					<td></td>
					<td></td>
					<td></td>
                    <td></td>
                    <td></td>
				</tr>
				<tr style="background-color: #eee">
					<td><div id="d3" class="redips-drag t1 redips-clone d3">Mathematik</div></td>
					<td class="redips-mark">09:05<br>09:30</td>
					<td class="redips-mark lunch" colspan="5">Pause</td>
					
				</tr>
				<tr>
					<td><div id="d4" class="redips-drag t1 redips-clone d4">Ethik</div></td>
					<td class="redips-mark">09:30<br>10:15</td>
					<td></td>
					<td></td>
					<td></td>
                    <td></td>
                    <td></td>
				</tr>
                <tr>
					<td><div id="d5" class="redips-drag t1 redips-clone d5">Kunst</div></td>
					<td class="redips-mark">10:15<br>11:00</td>
					<td></td>
					<td></td>
					<td></td>
                    <td></td>
                    <td></td>
				</tr>
				<tr style="background-color: #eee">
					<td><div id="d6" class="redips-drag t1 redips-clone d6">HSU</div></td>
					<td class="redips-mark">11:00<br>11:05</td>
					<td class="redips-mark lunch" colspan="5">Pause</td>
					
					<!-- <td><div id="d8" class="redips-drag t1"><img id="smile_img" src="/wp-includes/images/smilies/icon_smile.gif"/></div></td> -->
				</tr>
                <tr>
					<td><div id="d7" class="redips-drag t1 redips-clone d7">Sport</div></td>
					<td class="redips-mark">11:05<br>11:50</td>
					<td></td>
					<td></td>
					<td></td>
                    <td></td>
                    <td></td>
				</tr>
                <tr>
					<td><div id="d8" class="redips-drag t1 redips-clone d8">Foerderung</div></td>
					<td class="redips-mark">11:50<br>12:35</td>
					<td></td>
					<td></td>
					<td></td>
                    <td></td>
                    <td></td>
				</tr>
                <tr>
					<td><div id="d9" class="redips-drag t1 redips-clone d9">Musik</div></td>
					<td class="redips-mark">12:35</td>
					<td class="redips-mark lunch" colspan="5">Mittag</td>
					
				</tr>
                <tr>
					<td><div id="d10" class="redips-drag t1 redips-clone d10">Werken</div></td>
					
				</tr>
			</table>
			
			<table id="table3">
				<colgroup><col width="100"/><col width="100"/><col width="100"/><col width="100"/><col width="100"/></colgroup>
				<tr style="background-color: #eee">
					<td id="message" class="redips-mark" title="You can not drop here">Table3</td>
					<!-- jump to smile image -->
					<td class="redips-trash" title="Trash">Trash</td>
					
				</tr>
				
			</table>
			<!--<div><input type="button" value="Save1" class="button" onclick="save('plain')" title="Send content to the server (it will only show accepted parameters)"/><span class="message_line">Save content of the first table (plain query string)</span></div>-->
			<div><input method="get" type="button" value="Save2" class="button" onclick="save('json')" title="Send content to the server (it will only show accepted parameters)"/><span class="message_line">Speichern(JSON format)</span></div>
			<!--<div><input type="radio" name="drop_option" class="checkbox" onclick="setMode(this)" value="multiple" title="Enabled dropping to already taken table cells" checked="true"/><span class="message_line">Enable dropping to already taken table cells</span></div>
			<div><input type="radio" name="drop_option" class="checkbox" onclick="setMode(this)" value="single" title="Disabled dropping to already taken table cells"/><span class="message_line">Disable dropping to already taken table cells</span></div>
			<div><input type="radio" name="drop_option" class="checkbox" onclick="setMode(this)" value="switch" title="Switch content"/><span class="message_line">Switch content</span></div>
			<div><input type="radio" name="drop_option" class="checkbox" onclick="setMode(this)" value="switching" title="Switching content continuously"/><span class="message_line">Switching content continuously</span></div>
			<div><input type="radio" name="drop_option" class="checkbox" onclick="setMode(this)" value="overwrite" title="Overwrite content"/><span class="message_line">Overwrite content</span></div>
			<div><input type="checkbox" class="checkbox" onclick="toggleDeleteCloned(this)" title="Remove cloned object if dragged outside of any table" checked="true"/><span class="message_line">Remove cloned element if dragged outside of any table</span></div>
			<div><input type="checkbox" class="checkbox" onclick="toggleConfirm(this)" title="Confirm delete"/><span class="message_line">Confirm delete</span></div>
			<div><input type="checkbox" class="checkbox" onclick="toggleDragging(this)" title="Enable dragging" checked="true"/><span class="message_line">Enable dragging</span></div>			
		</div>-->

                
                
                
                
                
                
                
                
                
                
                
                
                
                
                </nav>
                
              <!--  <div class="col-12 col-sm-12 modul_table">
                    <h3>Stundenplan</h3>
                    <% out.println(Stundenplan.stundenplan() ); %>
                </div>
            </div>
        </main>
        
        <nav class="user_navi" id="user_navigation">
            <%
                user = new Anwender(anrede, vorname, nachname, email, telefonnummer, password);
                out.println("<div class='welcome'><p>Hallo " + anrede + " " + vorname + " " + nachname + "</p></div>");
                out.println(user.getUserNavigation());
            %>
        </nav>
        -->
        <!--// Footer //-->
        <footer class="row">
            <div class="col-12 col-sm-6 imprint"><a href="/se-schulportal/impressum.html">Impressum</a></div>
            <div class="col-12 col-sm-6 copyright"><p>&copy 2017 THD - Christoph Stockinger</p></div>
        </footer>
    
        <script src="/se-schulportal/templates/thd-schulportal/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/func.js" type="text/javascript"></script>
        
		
		
		
		
              
	<script type="text/javascript" src="/se-schulportal/templates/thd-schulportal/js/redips-drag-min.js"></script>
        <script type="text/javascript" src="/se-schulportal/templates/thd-schulportal/js/script.js"></script>
        
     
        <script>
            var button = document.getElementById('save');
            
            button.addEventListener('click', function() {
                save('json');
            });
        </script>
	

    </body>
</html>
