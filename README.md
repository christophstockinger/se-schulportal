# SE Schulportal
Im Rahmen der Vorlesung Software Engineering entwicklen 10 Studenten ein Schulportal für zwei Grundschulen aus dem bayrischen Wald.
Dabei wurden folgende Module implementiert:
### Notenblatt
#### Anforderungen
Dieses Modul dient zur Erfassung von Schulnoten der Schüler für ein Schulerjahr. Zudem soll es eine Funktion für die Verwaltung, welche anschließend Zeugnisse bzw. Notenübersicht eines Schülers erstellen kann.

#### Umsetzung
Um die Schülerdaten (Klasse 1 bis 4) in das Schulportal zu importieren, nutzen wir CSV Dateien mit den Schülernamen sowie Telefonnummern. Aus diesen Daten wird dann automatische eine E-Mail-Adresse generiert.
In einem weiteren Part kann ein Lehrer eine geschriebene Prüfung erstellen und anschließend den jeweiligen Schülern der Prüfung, welcher eine Klasse zugeordnet ist, die Noten eintragen und speichern.
Im weiteren Part gibt es dann die Export-Funktion, welche eine PDF Datei mit Noten eines Schülers erstellt.

#### Probleme und ggf. Lösungsansätze
+ Nichtteilnahme eines Schülers (z. B.: wegen Krankheit): Wird als nicht teilgenommen (Status 0) eingetragen und später bei der Berechnung nicht berücksichtigt
+ Schuljahrwechsel: Es soll eine Funktion geben, welche eine Klasse in eine andere Klasse überträgt und bei den jeweiligen Schülern die Rolle ändert
+ Mehrfaches Vorkommen von Schülernamen kann zu Probleme mit gleicher nicht möglicher E-Mail-Adresse führen: Es wird eine Laufzahl beim erster gleichvorkommener E-Mail-Adresse angehängt, welche sich bei weiteren immer um eins erhöht.
+ Mit geben der Klasse beim Import. Lösung basiert nun auf einer Klassenübersicht am Anfang, wo ein Parameter mitgegeben wird und dieser auch im Form-Tag weiterverarbeitet wird und beim speichern und eintragen der Datei in die DB ausgelesen wird. (Kombination aus GET und POST)

#### Fehler
./.

---------------------------------------

### Profil-Einstellungen
#### Anforderungen

#### Umsetzung

#### Probleme und ggf. Lösungsansätze

#### Fehler

---------------------------------------

### KFZ-Kennzeichen
#### Anforderungen

#### Umsetzung

#### Probleme und ggf. Lösungsansätze

#### Fehler

---------------------------------------

### Kontakte
#### Anforderungen

#### Umsetzung

#### Probleme und ggf. Lösungsansätze

#### Fehler

---------------------------------------

### Kalender
#### Anforderungen

#### Umsetzung

#### Probleme und ggf. Lösungsansätze

#### Fehler

---------------------------------------

### Mail und SMS-Versand-Tool
#### Anforderungen

#### Umsetzung

#### Probleme und ggf. Lösungsansätze

#### Fehler

---------------------------------------

### Stundenplan
#### Anforderungen

#### Umsetzung

#### Probleme und ggf. Lösungsansätze

#### Fehler



## Github
1. Projekt klonen
> git clone https://github.com/christophstockinger/se-schulportal

2. Projekt holen
> git push

3. Projekt updaten
3.1 Hinzufügen aller Dateien
> git add *

3.2 Änderungen kommentieren
> git commit -m "Hier steht dein Text"

3.3 Änderungen auf Github.com hochladen
> git push -u origin master
> git push -u origin "Branchname"


__Jeder hat jetzt seinen eigenen Branch!__
+ Wechseln des Branches in Netbeans:
Rechtsklick auf das Projekt (schulportal) im Projects Fenster von Netbeans.
Dann bei "Git" auf "Remote" und weiter auf "Pull" zum holen.
Anschließend wieder unter "Git" auf "Branch/Tag" und "Switch to Branch".
Es öffnet sich ein Fenster dor oben bei "Select branch ..." den eigenen Branch auswählen udn auf "Switch".

+ Dateien aus dem Branch holen:
Rechtsklick auf das Projekt (schulportal) im Projects Fenster von Netbeans.
Dann bei "Git" auf "Remote" und weiter auf "Pull" zum holen.
Dann checken ob der richtige Git Repository (origin:https://github.com/christophstockinger/se-schulportal) auswählen und auf "Next" klicken. Überprüfen das der Hacken im nächsten Fenster bei dem eigenen Branch auswählt ist und auf Finish.

+ Dateien in den Branch hochladen:
Rechtsklick auf das Projekt (schulportal) im Projects Fenster von Netbeans.
Dann bei "Git" auf "Add" klicken zum hinzufügen aller Dateien. Anschließend "Commit" zum kommentieren der Änderungen (Fenster öffnet sicht und oben links einfach Nachricht schreiben und dann auf "Commit") und dann bei "Remote" und auf "Push" zum hochladen.
Dann checken ob der richtige Git Repository (origin:https://github.com/christophstockinger/se-schulportal) auswählen und auf "Next" klicken. Überprüfen das der Hacken im nächsten Fenster bei dem eigenen Branch auswählt ist und auf Finish.
Beim ersten mal kann er noch nach Zugangsdaten von Github fragen !
