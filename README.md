# Studienplaner

Willkommen zum Foundation Course Software Engineering!

Dieses Repo ist absichtlich fast leer: **Ihr entwerft und baut alles selbst** – bei Modell, Schichtenstruktur, Logik und Tests. Geschenkt sind nur:

- `build.gradle` & JUnit (fertig konfiguriert)
- `.github/workflows/ci.yml` für aktive CI: Bei jedem Push laufen die Tests auf GitHub, der grüne Haken neben dem Commit zeigt euch, dass alles durchläuft
- `src/main/resources/modulkatalog.csv` mit den Beispieldaten für Module, Termine und Voraussetzungen
- `data/DatenLeser` liefert Rohdaten (`String[]`) – die Verbindung zum Modell bauen wir selbst
- `export/HtmlExporter` + `export/Exportierbar` für den HTML-Export
- `SetupTest`, der das Setup überprüft
- `BACKLOG.md` für das Product Backlog

---

## Team

- Slaven Aleksic

---

## Auftrag 1 – Startklar von null (gemeinsam am Morgen von Tag 1)

Ihr müsst vor dem Kurs nichts tun – diese Schritte machen wir gemeinsam:

1. Java (Temurin 25 LTS, adoptium.net) + IntelliJ IDEA CE + Git installieren.
2. Aus diesem Template ein gemeinsames Team-Repository erstellen („Use this template“, Public) und Teammitglied + Lektor als Collaborators einladen.
3. Repo klonen und `gradlew test` ausführen → `SetupTest` muss grün sein.
4. Je 1 Commit: Namen ins README → Push → grüner CI-Haken auf GitHub.

---

## Während des Kurses

- Eure Paketstruktur entwerfen wir gemeinsam in Station 4 – committet sie mit Begründung im README.
- Commit-Hygiene: kleine Commits, sprechende Messages, unter eigenem Namen.
- Push-Regel im Team: Es pusht immer nur ein Rechner; vor jedem Push zuerst `pull`.
- `DatenLeser` liefert Rohdaten (`String[]`) – die Verbindung zum Modell baut ihr selbst.
- Ziel für Tag 1: die erste eigene HTML-Datei des Studienplans im Browser öffnen.

---

## Unsere Architektur (Station 4)

Die Anwendung ist in mehrere Bereiche aufgeteilt: `data`, `model` und `export`.

`DatenLeser` liest die CSV-Datei `modulkatalog.csv` ein und liefert `RohDaten`.

`StudienplanFactory` verwendet diese Rohdaten und erzeugt daraus die Modellobjekte wie `Studienplan`, `Modul` und `Abgabe`. Außerdem verbindet sie die Voraussetzungen zwischen den Modulen.

Das Paket `model` enthält die Fachlogik des Studienplans. Dort werden Module und Termine verwaltet, bestandene ECTS berechnet, Voraussetzungen geprüft und Module nach Semester gruppiert.

Für den Export wird das Interface `Exportierbar` verwendet. Die konkrete HTML-Erzeugung übernimmt `HtmlExporter`.

`Main` verbindet die einzelnen Teile der Anwendung, lädt den Studienplan und erstellt die HTML-Ausgabe.

Dadurch bleiben Dateneinlesen, Fachlogik und Ausgabe voneinander getrennt.

---

## Unsere Datenstruktur-Entscheidungen (Station 6/7)

- `ArrayList<Modul>` verwenden wir für die Module, weil die Elemente gespeichert und der Reihe nach durchlaufen werden.
- `ArrayList<Termin>` verwenden wir für Termine, weil auch diese der Reihe nach verarbeitet werden.
- `HashMap<String, Modul>` verwenden wir für die Suche nach einem Modul über sein Kürzel. Dadurch kann ein Modul direkt gefunden werden, ohne die komplette Modulliste durchsuchen zu müssen.
- `HashSet<Modul>` verwenden wir bei der Prüfung von Voraussetzungskreisen. Damit können bereits besuchte Module und der aktuelle Pfad effizient geprüft werden.
- `Map<Integer, List<Modul>>` verwenden wir, um Module nach Semester zu gruppieren.

---

## Unser Refactoring (Station 9)

Wir haben die Modulsuche durch eine `HashMap<String, Modul>` verbessert, sodass Module über ihr Kürzel direkt gefunden werden können, ohne jedes Mal die gesamte Modulliste zu durchsuchen.

Commit-Link: https://github.com/slavenaleksic95-a11y/studienplaner-team/commit/127814f