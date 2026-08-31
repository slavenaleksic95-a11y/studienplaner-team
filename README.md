# Mein Studienplaner – Starter-Repo

# Team
- Slaven Aleksic

Willkommen zum Foundation Course Software Engineering!

Dieses Repo ist absichtlich fast leer: **Ihr entwerft und baut alles selbst** – Modell, Schichtenstruktur, Logik, Tests. Geschenkt sind nur:

- `build.gradle` – Build & JUnit fertig konfiguriert
- `.github/workflows/ci.yml` – **aktive CI**: Bei jedem Push laufen die Tests auf GitHub, der grüne Haken neben dem Commit zeigt euch, dass alles durchläuft. Lokal testet ihr mit `gradlew test` (Mac/Linux: `./gradlew test`)
- `src/main/resources/modulkatalog.csv` – die Beispieldaten (Module, Termine, Voraussetzungen)
- `data/DatenLeser` – liest die CSV als Rohdaten (Parsing ist kein Lernziel)
- `export/HtmlExporter` + `export/Exportierbar` – schreibt eure Fortschrittsseite als HTML (HTML ist kein Lernziel). Der Exporter kennt eure Klassen nicht – eure Klassen unterschreiben nur seinen Vertrag (`Exportierbar`)
- `SetupTest` – euer Setup-Check
- `BACKLOG.md` – euer Product Backlog: die Stories, aus denen ihr im Sprint Planning wählt (und mindestens eine bewusst verwerft)

## Auftrag 1 – Startklar von null (gemeinsam am Morgen von Tag 1)

*Ihr müsst vor dem Kurs nichts tun – diese Schritte machen wir gemeinsam:*

1. Java (Temurin 25 LTS, adoptium.net – großer Button) + IntelliJ IDEA CE + Git installieren (Links in Sakai, „Vor dem Kurs")
2. Aus diesem Template euer gemeinsames **Team-Repository** erstellen („Use this template", Public), Teammitglied + Lektor (`Gschirr`) als Collaborators einladen
3. Repo klonen (IntelliJ: „Get from VCS"), `gradlew test` ausführen → `SetupTest` muss **grün** sein
4. Je 1 Commit: eure Namen ins README → Push → grüner CI-Haken auf GitHub

## Während des Kurses

- Eure Paketstruktur entwerfen wir gemeinsam in Station 4 – committet sie mit Begründung im README (unten ausfüllen)
- Commit-Hygiene: kleine Commits, sprechende Messages, unter eigenem Namen – eure Historie ist Teil des Arbeitsnachweises
- **Push-Regel im Team:** Es pusht immer nur ein Rechner; vor jedem Push erst `pull`, und sagt euch an, wer gerade committen will
- `DatenLeser` liefert euch Rohdaten (`String[]`) – die Verbindung zu **eurem** Modell baut ihr selbst
- Ziel für Tag 1: die erste eigene `studienplan.html` im Browser öffnen 🎉

---

## Unsere Architektur *(auszufüllen, Station 4)*

*Welche Schicht kennt welche – und warum?*

## Unsere Datenstruktur-Entscheidungen *(Station 6/7)*

*Welche Struktur nutzt ihr wofür – und warum passt sie? (Alltagssprache genügt: „oft gesucht, selten eingefügt")*

## Unser Refactoring *(Station 9)*

*Was habt ihr verbessert? (1 Satz + Commit-Link)*
