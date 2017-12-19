<%-- 
    Document   : eventCalender
    Created on : 12.12.2017, 11:51:22
    Author     : peterjerger
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <title>Event-Kalender</title>
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
                <!--// CSS //-->
        <!--// CSS CS Reset //-->
        <link href="/se-schulportal/templates/thd-schulportal/css/cs-reset.css" rel="stylesheet" type="text/css" media="all">
        <!--// CSS Bootstrap Grid //-->
        <link href="/se-schulportal/templates/thd-schulportal/css/bootstrap-grid.min.css" rel="stylesheet" type="text/css" media="all">
        <!--// CSS Main //-->
        <link href="/se-schulportal/templates/thd-schulportal/css/main.css" rel="stylesheet" type="text/css" media="all">
        <!--// Bootstrap Datepicker //-->
        <link href="/se-schulportal/events/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet" type="text/css" media="all">
        <link href="/se-schulportal/events/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" media="all">
        <link href="/se-schulportal/events/bootstrap-datepicker/css/bootstrap-datepicker3.min.css.map" rel="stylesheet" type="text/css" media="all">
        <link href="/se-schulportal/events/bootstrap-datepicker/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet" type="text/css" media="all">
        <link href="/se-schulportal/events/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css" media="all">
        <link href="/se-schulportal/events/bootstrap-datepicker/css/bootstrap-datepicker.standalone.min.css.map" rel="stylesheet" type="text/css" media="all">
        <link href="/se-schulportal/events/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css.map" rel="stylesheet" type="text/css" media="all">
        <link href="/se-schulportal/events/bootstrap-datepicker/js/bootstrap-datepicker.min.js" rel="stylesheet" type="text/js" media="all">
        
    </head>
    
    <body>
        
        <div class="input-group date">
            <input type="text" class="form-control">
                <span class="input-group-addon">
                    <i class="glyphicon glyphicon-th"></i>
                </span>
        </div>
        
        <script>
            $('#sandbox-container .input-group.date').datepicker({
            language: "de",
            autoclose: true,
            todayHighlight: true,
            toggleActive: true
            });
        </script>
        
    </body>
    
</html>
