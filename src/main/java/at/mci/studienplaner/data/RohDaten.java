package at.mci.studienplaner.data;

import java.util.List;

/**
 * Rohdaten aus der CSV-Datei – bewusst OHNE Modellklassen.
 * Dein Modell entwirfst du selbst (Station 3–5) und verbindest es dann hiermit.
 *
 * module:          [Kuerzel, Name, ECTS, "x" (bestanden) oder "-" (offen)]
 * vorlesungen:     [Titel, OrtUndZeit]
 * abgaben:         [Titel, Frist als JJJJ-MM-TT]
 * voraussetzungen: [Vorher, Nachher]  → „Vorher muss bestanden sein, bevor Nachher belegbar ist"
 */
public record RohDaten(
        List<String[]> module,
        List<String[]> vorlesungen,
        List<String[]> abgaben,
        List<String[]> voraussetzungen) {
}
