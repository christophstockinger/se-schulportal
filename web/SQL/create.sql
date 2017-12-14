/* 
   Beispielrealisierung zum Tests der Basis Anwendungsfälle
   Beachten Sie, wenn hier etwas nicht funktioniert, kann das in der Applikation auch nicht funktionieren

   Author: GWinterfeldt
   Version: 0.1 

*/
/*
	Hier bauen wir die Testdatenbank auf. Wenn Sie ihre alten Daten nicht überschreiben wollen, nutzen Sie if 
	not exists statemenets
*/

/**
	 Basis Anwender ohne Adresse
*/

create Database schule;

CREATE TABLE anwender (
  name varchar(200)  NOT NULL,
  vorname varchar(200)  NOT NULL,
  email varchar(200) NOT NULL,
  telenummmer varchar(20) NOT NULL,
  passwort varchar(200) NOT NULL
  PRIMARY KEY (email)
);

/*
    Tabelle aktualisieren
*/
ALTER TABLE anwender sdfsdaf varchar (255);

/*
	Rollen, die wir verteilen können
*/
CREATE TABLE rollennamen (
    rollenname varchar(100) not null,
    PRIMARY KEY(rollenname)
);

/*
	n2n Beziehung, jeder darf mehrere Rollen haben und jede Rolle wird von mehreren besetzt
*/
CREATE TABLE rolle (
   rolle varchar(100)  NOT NULL,
   anwender varchar(200)  NOT NULL,
    FOREIGN KEY (anwender) REFERENCES anwender(email),
    FOREIGN KEY (rolle) REFERENCES rollennamen(rollenname)
);


/*
	Inhalte, die verteilt werden sollen. Später sollte man vielleicht den Kanal mit speichern?  	
*/
CREATE TABLE information (
   infotitel varchar(200)  NOT NULL,
   infotext varchar(32000)   NOT NULL,
   author varchar(200) NOT NULL,
    FOREIGN KEY (author) REFERENCES anwender(email)
);


/*
	Testen, ob Daten geladen werden können
*/
INSERT INTO anwender (name, vorname, email, telenum) VALUES(
'Dreiner', 'Thomas','Thomas.dreier','01728652312');


INSERT INTO rollennamen (rollenname) VALUES('Lehrer'), ('Klasse 1A'), ('Klasse1b'), ('Eltern'), ('Schueler');

INSERT INTO rolle(rolle,anwender) VALUES('Lehrer', 'Thomas.dreier');

INSERT INTO information(infotitel,infotext,author) VALUES('Erste Geschichte','Eine erste Geschichte, die ich immer schon erhlen wollte','Thomas.dreier');

/*
	Realisierung der Wesentlichen Abfragen im Vorfeld!
*/
SELECT anwender.name, anwender.vorname
FROM anwender
LEFT JOIN rolle ON rolle.rolle = 'Lehrer';

SELECT * FROM rollennamen;
