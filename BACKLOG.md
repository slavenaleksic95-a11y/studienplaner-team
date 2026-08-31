# 📦 Product Backlog: Mein Studienplaner

Euer Product Backlog für die zwei Kurstage. **Es ist bewusst zu groß, niemand soll alles schaffen.** Priorisieren ist Teil der Lektion: Im Sprint Planning wählt ihr eure Stories, schreibt zu 2–3 davon eigene Akzeptanzkriterien und **verwerft mindestens eine Story bewusst** (Begründung in einem Satz ins README).

**Szenario:** „Mein Studienplaner" verwaltet euer eigenes Studium: Module (mit ECTS und Status bestanden/offen), Termine (Vorlesungen, Abgaben) und die Voraussetzungen zwischen Modulen. Sichtbares Ergebnis: eine **HTML-Fortschrittsseite**, der HTML-Exporter ist in diesem Repo fertig enthalten, ihr müsst ihn nur mit Daten füttern. Der Modulkatalog kommt aus der mitgelieferten CSV-Datei (Loader ebenfalls fertig).

**So arbeitet ihr damit:** Jede gewählte Story wird ein **Issue** auf eurem GitHub-Board (To do / In progress / Done).

---

## 🟥 Must

- **M1 (Module und Termine als Modell):** Als Studentin möchte ich meine Module und Termine strukturiert erfassen (Vorlesung: Ort/Zeit, Abgabe: Frist), damit alles Weitere darauf aufbauen kann.
- **M2 (Modul per Kürzel finden):** Als Studentin möchte ich ein Modul über sein Kürzel finden, damit ich seine Details (Name, ECTS, Status) nachschlagen kann.
- **M3 (Fortschrittsseite mit ECTS-Stand):** Als Studentin möchte ich eine HTML-Seite mit meinen bestandenen/offenen Modulen und der ECTS-Summe, damit ich meinen Fortschritt schwarz auf weiß sehe.
- **M4 (Voraussetzungen abbilden):** Als Studentin möchte ich die Voraussetzungen zwischen Modulen abbilden („A braucht B"), damit Planungsfragen beantwortbar sind.
- **M5 (Was schaltet ein Modul frei?):** Als Studentin möchte ich sehen, welche Module ein Modul X (direkt und über Umwege) freischaltet, damit ich weiß, was davon abhängt, dass ich X bestehe.
- **M6 (Basistests):** Als Entwicklerin möchte ich zentrale Funktionen automatisiert testen, damit Änderungen nichts unbemerkt kaputt machen.

## 🟨 Should

- **S1 („Belegbar jetzt"-Check):** Als Studentin möchte ich sehen, welche Module ich sofort belegen kann (alle Voraussetzungen bestanden), damit ich mein nächstes Semester planen kann.
- **S2 (Deadline-Warnliste):** Als Studentin möchte ich alle Abgaben der nächsten 7 Tage sehen, damit ich nichts verpasse.
- **S3 (Breitere Testabdeckung):** Als Entwicklerin möchte ich auch Modell und Suche systematisch testen, damit ich Änderungen ohne Angst machen kann.

## 🟩 Could (Bonus)

- **C1 (Schnelles Nachschlagen):** Als Studentin möchte ich Module ohne Wartezeit finden, auch wenn der Katalog groß wird.
- **C2 (Studienordnungs-Check):** Als Studiengangsleitung möchte ich wissen, ob die Voraussetzungen einen Zirkel enthalten („A braucht B, B braucht A", dann kann niemand je starten).
- **C3 (Semester-Ansicht):** Als Studentin möchte ich die Fortschrittsseite nach Semestern gegliedert sehen.
- **C4 (Benchmark):** Als Entwicklerin möchte ich meine Suche messen (kleiner vs. großer Katalog), damit meine Datenstruktur-Begründung belegt ist.
