/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
console.log("Start");

window.addEventListener("load", function() {
    console.log("window Lister start");
    main_user_navigation();
    console.log("window Lister endet");
}, false);

// Erstellung der URL für Login
function login() {
    var email = $('#email').val();
    var password = $('#password').val();
    //Sende an die nächste Seite
    if (email !== "" || password !== "") {
        window.location.replace("dashboard.jsp?email=" + email + "&password=" + password);
    } else {
        $('#alert').html("<p>Bitte fülle alle Felder aus!</p>");
    }
}

// Erstellung der URL für Registrierung
function register() {
    var anrede = $('#anrede').val();
    var vorname = $('#vorname').val();
    var nachname = $('#nachname').val();
    var telefonnummer = $('#telefonnummer').val();
    var email = $('#email').val();
    var password = $('#password').val();
    var url = "register.jsp?anrede=" + anrede + "&vorname=" + vorname + "&nachname=" + nachname + "&telefonnummer=" + telefonnummer + "&email=" + email + "&password=" + password;
    window.location.replace(url);
}

// Erstellung der URL für PIN Validation
function pin() {
    var email = $('#email').val();
    var pin = $('#userpin').val();
    window.location.replace("telverify.jsp?email=" + email + "&pineingabe=" + pin);
}

function rollen() {
    var url = "rollenverify.jsp?";
    var email = $('#email').val();
    url += "email=" + email;
    var userrollen = [];
    var a = document.querySelectorAll(".checkbox");
    // Check ob Rolle checked
    for (var i = 0; i < a.length; i++) {
        if (a[i].checked) {
            // Push into Array
            userrollen.push($("#" + a[i].getAttribute('id')).val());
        }
    }

    // Anzahl der Rollen
    url += "&anzahl=" + userrollen.length;
    // Anhängen der URL
    for (var j = 0; j < userrollen.length; j++) {
        url += "&rolle" + j + "=" + userrollen[j];
    }

    window.location.replace(url);
}

function deleterolle(rolle) {
    var email = $('#email').html();
    var url = "adminverify.jsp?email=" + email + "&status=delete&rolle=" + rolle;
    window.location.replace(url);
}


function admin() {
    var email = $('#email').html();
    var url = "adminverify.jsp?email=" + email + "&status=freigabe";
    window.location.replace(url);
}

// Main Navigation-Animation
function main_user_navigation() {
    
    document.addEventListener("click", function (event) {
        if(!event.target.classList.contains('navicon')) {
            return false;
        } else {
            var content = event.target.getAttribute("data");
            if (!content) {
                return false;
            } else {
                if (content == "#main_navigation") {
                    document.querySelector(content).classList.toggle("open-left");
                }
                
                if (content == "#user_navigation") {
                    document.querySelector(content).classList.toggle("open-right");
                }
                
                event.preventDefault;
                return true;
            }
        }
    })
}

function csvImport() {
    
}

function loadEmail(){
        
}


console.log("Finish!");

// Table sortieren?
// mit jQuery: http://tablesorter.com/docs/
// mit JS: https://www.w3schools.com/howto/howto_js_sort_table.asp
