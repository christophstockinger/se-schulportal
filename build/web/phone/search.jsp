<%-- 
    Document   : search
    Created on : 05.12.2017, 12:49:04
    Author     : pri
--%>


<!DOCTYPE html>
<html>
    <head> 
        <title>Telefonnummer</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">        
    </head>
    <body>
        <header>
            <h1>Telefonnummer</h1>
        </header>
        <main>
            <div class="row content">
                <div class="col-12 col-sm-12 js_alert" id="alert"></div>
                <div class="col-2 col-sm-2"><!--// Rand links //--></div>
                <div class="col-8 col-sm-8 loginform">
                    <form>
                        <input type="text" name="name" placeholder="Suche..." id="name">
                        <select id="rolle">
                            <option value="alle">Alle</option>
                            <option value="schueler">Schüler</option>
                            <option value="lehrer">Lehrer</option>
                            <option value="mitarbeiter">Mitarbeiter</option>
                        </select>
                        <button type="button" class="button" onclick="search()">Suchen</button>
                    </form>
                </div>
                <div class="col-2 col-sm-2"><!--// Rand rechts //--></div>
            </div>
        </main>
        <script src="/schulportal/templates/thd-schulportal/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        
        <script src="/schulportal/templates/thd-schulportal/js/func.js" type="text/javascript"></script>
        
        <script type="text/javascript">
            function search() {
                console.log("Geklick");
                var name = $('#name').val();
                var rolle = $('#rolle').val();
           
                var url = "aufruf.jsp?name=" + name + "&rolle=" + rolle;
                window.location.replace(url);
            }
        </script>
    </body>
</html>
