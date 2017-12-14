<%-- 
    Document   : message
    Created on : 23.11.2017, 12:13:23
    Author     : Christoph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nachrichten senden</title>
        <style>
            main {
                width: 500px;
                margin: 0 auto;
            }
            
            #empfaengerliste {
                width: 100%;
            }
            .empfaenger {
                width: 100%;
                
            }
            
            .empfaenger input[type='checkbox'] {
                width: 3%;
                float: left;
                display: block;
                margin: 0;
                padding: 0;
            }
            .empfaenger p{
                width: 97%;
                float: left;
                display: block;
                margin: 0;
                padding: 0;
            }
            textarea {
                width: 100%;
                height: 200px;
            }
        </style>
    </head>
    <body>
        <main>
        <h1>Der Nachrichten-Sender</h1>
        <p>Du kannst hier eine Gruppe von Leuten auswählen und an diese eine E-Mail oder SMS versenden</p>
        <div id="empfaengerliste"></div>
        
        <div class="message">
            <textarea name="message" id="message"></textarea>
        </div>
        <div class="sendbuttons">
            <button type="button" onclick="mail()">E-Mail versenden</button>
            <button type="button" onclick="sms()">SMS versenden</button>
        </div>
        </main>
        <script src="templates/thd-schulportal/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            var empfaengerliste = [];
            empfaengerliste[0] = [];
            empfaengerliste[0]['name'] = "Christoph Stockinger";
            empfaengerliste[0]['tel'] = "+491601889824";
            empfaengerliste[0]['mail'] = "christoph.stockinger@stud.th-deg.de";
           empfaengerliste[1] = [];
            empfaengerliste[1]['name'] = "Thomas Forstner";
            empfaengerliste[1]['tel'] = "+4915142432074";
            empfaengerliste[1]['mail'] = "thomas.forstner2@stud.th-deg.de";
            empfaengerliste[2] = [];
            empfaengerliste[2]['name'] = "Patrick Richter";
            empfaengerliste[2]['tel'] = "+4915170000837";
            empfaengerliste[2]['mail'] = "patrick.richter@stud.th-deg.de";
            empfaengerliste[3] = [];
            empfaengerliste[3]['name'] = "Luis Graml";
            empfaengerliste[3]['tel'] = "+4915232767462";
            empfaengerliste[3]['mail'] = "luis.gram@stud.th-deg.de";
            
            var content = "";
            

            console.log(empfaengerliste.length);
            for (var i = 0; i < empfaengerliste.length; i++) {
                console.log(i);
                console.log(empfaengerliste[i]['name']);
                content += '<div class="empfaenger">';
                content += "<input type='checkbox' class='empf' name='empfaenger' value='" + empfaengerliste[i]['name'] + "'><p>" + empfaengerliste[i]['name'] + "</p>";
                content += '</div>';   
            }
          
            $('#empfaengerliste').html(content);
        
            
        
        </script>
        <script type="text/javascript">
            function mail() {
                var empfaenger = document.querySelectorAll('.empf');
                for (var j = 0; j < empfaenger.length; j++) {
                    console.log(empfaenger[j]);
                }
                
                var message = $("#message").val();
                
               window.location.replace("message.jsp);
            }
            </script>
    </body>
</html>
