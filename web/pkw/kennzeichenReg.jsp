<%-- 
    Document   : kennzeichenReg
    Created on : 12.12.2017, 14:58:07
    Author     : patrickrichter
--%>
<%@page import="Modul_example.ModExample"%>
<%@page import="anwender.Anwender"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width; initial-scale=1.0"/>

        <title>Kennzeichen hinzufügen</title>

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

        <link href="/se-schulportal/templates/thd-schulportal/css/open-ionic.min.css" rel="stylesheet" type="text/css">
        <!--// CSS Main //-->
        <link href="/se-schulportal/templates/thd-schulportal/css/main.css" rel="stylesheet" type="text/css" media="all">

        
    <!--  AUSGELAGERT IN FUNC.JS
    <script language="javascript" type="text/javascript">
        function kennzeichen(){
            
            var nkennzeichen = $('#kennzeichen').val();
            
            //Hier muss getestet werden, welcher Gruppen angekreuzt wurden
            
            //Sende an die nächste Seite
            window.location.replace("kfzSucces.jsp?kennzeichen="+nkennzeichen);
        }
        </script>
    -->
    
    </head>
    <body>
        
        <form method="get" action="kfzSucces.jsp">
        <input type="text" name="kennzeichen" placeholder="KFZ-Kennzeichen" id="kennzeichen">
        <input type="submit" value="Hinzufügen"/>
        </form>
        
        
          <!--// Javascript & jQuery //-->
        <script src="/se-schulportal/templates/thd-schulportal/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/se-schulportal/templates/thd-schulportal/js/func.js" type="text/javascript"></script>
  </body>
 
</html>