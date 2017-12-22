/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  cstockinger
 * Created: 14.11.2017
 */


CREATE TABLE anwender (
    anrede varchar(10)  NOT NULL,    
  nachname varchar(200)  NOT NULL,
  vorname varchar(200)  NOT NULL,
  email varchar(200) NOT NULL,
  telefonnummer varchar(20) NOT NULL,
  passwort varchar(200) NOT NULL,
  PRIMARY KEY (email)
);




CREATE TABLE rollennamen (
    rollenname varchar(100) not null,
    PRIMARY KEY(rollenname)
);

CREATE TABLE rolle (
   rolle varchar(100)  NOT NULL,
   anwender varchar(200)  NOT NULL,
    FOREIGN KEY (anwender) REFERENCES anwender(email),
    FOREIGN KEY (rolle) REFERENCES rollennamen(rollenname)
);


CREATE TABLE anwenderverify (
   anwender varchar(200)  NOT NULL,
verifystatus_mail Boolean  NOT NULL,
verifystatus_tel Boolean  NOT NULL,
verifystatus_admin Boolean  NOT NULL,
    FOREIGN KEY (anwender) REFERENCES anwender(email)
);

CREATE TABLE information (
   infotitel varchar(200)  NOT NULL,
   infotext varchar(32000)   NOT NULL,
   author varchar(200) NOT NULL,
    FOREIGN KEY (author) REFERENCES anwender(email)
);



INSERT INTO anwender (anrede, nachname, vorname, email, telefonnummer, passwort) VALUES('Herr', 'Forstner', 'Thomas','thomas.forstner2@stud.th-deg.de','01523856852','123thomas654');
INSERT INTO anwender (anrede, nachname, vorname, email, telefonnummer, passwort) VALUES('Herr', 'Stockinger', 'Christoph','christoph.stockinger@stud.th-deg.de','02156168228','123christoph654');


INSERT INTO rollennamen (rollenname) VALUES('Lehrer'), ('Eltern'), ('Schüler'), ('Rektor'), ('Personal'),('Admin'),('Klasse 1a'),('Klasse 1b'), ('Klasse 1c'), ('Klasse 2a'),('Klasse 2b'),('Klasse 2c'), ('Klasse 3a'),('Klasse 3b'),('Klasse 3c'), ('Klasse 4a'),('Klasse 4b'), ('Klasse 4c');

INSERT INTO rolle(rolle,anwender) VALUES('Admin', 'thomas.forstner2@stud.th-deg.de');
INSERT INTO rolle(rolle,anwender) VALUES('Admin', 'christoph.stockinger@stud.th-deg.de');

INSERT INTO information(infotitel,infotext,author) VALUES('Erste Geschichte','Eine erste Geschichte, die ich immer schon erhlen wollte','christoph.stockinger@stud.th-deg.de');

INSERT INTO anwenderverify (anwender, verifystatus_mail, verifystatus_tel, verifystatus_admin) VALUES('christoph.stockinger@stud.th-deg.de', false, false,false);

/* Mail verifizieren */
UPDATE anwenderverify SET verifystatus_mail = true WHERE anwender = 'christoph.stockinger@stud.th-deg.de';
/* Telfon verifizieren */
UPDATE anwenderverify SET verifystatus_tel = true WHERE anwender = 'christoph.stockinger@stud.th-deg.de';
/* Admin verifiziert */
UPDATE anwenderverify SET verifystatus_admin = true WHERE anwender = 'christoph.stockinger@stud.th-deg.de';


DELETE FROM Anwenderverify  WHERE anwender='email@mail.de'
DELETE FROM Anwender  WHERE email='email@mail.de'
DELETE FROM Rolle  WHERE anwender='email@mail.de' AND rolle='rollename'
DELETE FROM Rollennamen WHERE Rollenname = 'rollenname'

SELECT anwender.nachname, anwender.vorname FROM anwender LEFT JOIN rolle ON rolle.rolle = 'Admin';

SELECT * FROM rollennamen;


/* Notenblatt */


/* Fächer DB */
CREATE TABLE faecher (
  fach varchar(100) NOT NULL,
    PRIMARY KEY (fach) 
);

INSERT INTO faecher (fach) VALUES('Mathematik');
INSERT INTO faecher (fach) VALUES('Deutsch');
INSERT INTO faecher (fach) VALUES('Englisch');
INSERT INTO faecher (fach) VALUES('Heimat und Sachunterricht');
INSERT INTO faecher (fach) VALUES('Werken');
INSERT INTO faecher (fach) VALUES('Kunst');
INSERT INTO faecher (fach) VALUES('Religion');
INSERT INTO faecher (fach) VALUES('Sport');
INSERT INTO faecher (fach) VALUES('Musik');
INSERT INTO faecher (fach) VALUES('Förderunterricht');
INSERT INTO faecher (fach) VALUES('Ethik');
INSERT INTO faecher (fach) VALUES('Werken und Gestalten');

SELECT fach FROM faecher;


/* Prüfungsarten DB */
CREATE TABLE pruefungsarten(
    art varchar(100) NOT NULL,
    PRIMARY KEY (art)
);

INSERT INTO pruefungsarten (art) VALUES('schriftlich Probe');
INSERT INTO pruefungsarten (art) VALUES('mündliche Probe');
INSERT INTO pruefungsarten (art) VALUES('praktische Probe');

SELECT art FROM pruefungsarten;


/* Prüfung DB */
CREATE TABLE pruefung (
  id int(100) NOT NULL AUTO_INCREMENT,
  lehrer varchar(200) NOT NULL,
  art varchar(100) NOT NULL,
  klasse varchar(100) NOT NULL,
  fach varchar(100) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (fach) REFERENCES faecher(fach),
    FOREIGN KEY (klasse) REFERENCES rollennamen(rollenname),
    FOREIGN KEY (lehrer) REFERENCES anwender(email),
    FOREIGN KEY (art) REFERENCES pruefungsarten(art)
);

CREATE TABLE pruefungsnoten (
  note int NOT NULL,
  pruefung int NOT NULL,
  email varchar (200) NOT NULL,
    PRIMARY KEY (pruefung, email),
    FOREIGN KEY (email) REFERENCES anwender(email),
    FOREIGN KEY (pruefung) REFERENCES pruefung(id)
);



INSERT INTO pruefung(lehrer, art, klasse, fach) VALUES ('thomas.forstner2@stud.th-deg.de', 'Schulaufgabe','Klasse 4b','Mathematik');

SELECT * FROM `faecher`;
SELECT fach FROM faecher;
SELECT * FROM faecher WHERE fach='Deutsch';

INSERT INTO `faecher`(`fach`) VALUES ('Rechtschreibung');

UPDATE `faecher` SET `fach`='Rechtschreiben' WHERE fach='Rechtschreibung';

DELETE FROM `faecher` WHERE fach='Rechtschreiben';

/* FÜR Derby */
CREATE TABLE pruefung (
  id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  lehrer varchar(200) NOT NULL,
  art varchar(100) NOT NULL,
  klasse varchar(100) NOT NULL,
  fach varchar(100) NOT NULL,
  datum date NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (fach) REFERENCES faecher(fach),
    FOREIGN KEY (klasse) REFERENCES rollennamen(rollenname),
    FOREIGN KEY (lehrer) REFERENCES anwender(email),
    FOREIGN KEY (art) REFERENCES pruefungsarten(art)
);


/* Reset Id auf 1 */
ALTER TABLE pruefung ALTER COLUMN id RESTART WITH 1
