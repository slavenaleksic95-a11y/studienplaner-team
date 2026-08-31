package at.mci.studienplaner;

import at.mci.studienplaner.data.DatenLeser;
import at.mci.studienplaner.data.RohDaten;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Setup-Check (Vorbereitungspaket): Wenn dieser Test bei dir GRÜN läuft,
 * funktionieren JDK, Build, JUnit und die Beispieldaten. Nicht löschen.
 */
class SetupTest {

    @Test
    void beispieldatenSindLesbar() {
        RohDaten daten = new DatenLeser().lese("modulkatalog.csv");
        assertEquals(7, daten.module().size());
        assertEquals(2, daten.vorlesungen().size());
        assertEquals(2, daten.abgaben().size());
        assertEquals(7, daten.voraussetzungen().size());
    }
}
