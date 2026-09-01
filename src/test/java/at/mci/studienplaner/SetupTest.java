package at.mci.studienplaner;

import at.mci.studienplaner.model.Abgabe;
import at.mci.studienplaner.model.Modul;
import at.mci.studienplaner.model.Studienplan;
import at.mci.studienplaner.model.StudienplanFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SetupTest {

    @Test
    void modulKannGefundenWerden() {
        Studienplan plan = new StudienplanFactory().laden();

        Modul modul = plan.findeModul("PROG1");

        assertNotNull(modul);
        assertEquals("PROG1", modul.getKuerzel());
    }

    @Test
    void bestandeneEctsWerdenBerechnet() {
        Studienplan plan = new StudienplanFactory().laden();

        double ects = plan.berechneBestandeneEcts();

        assertEquals(10.0, ects);
    }

    @Test
    void voraussetzungenWerdenGeladen() {
        Studienplan plan = new StudienplanFactory().laden();

        Modul prog2 = plan.findeModul("PROG2");

        assertNotNull(prog2);
        assertFalse(prog2.getVoraussetzungen().isEmpty());
    }

    @Test
    void offeneVoraussetzungenWerdenErkannt() {
        Studienplan plan = new StudienplanFactory().laden();

        Modul proj = plan.findeModul("PROJ");

        assertNotNull(proj);
        assertFalse(proj.getOffeneVoraussetzungen().isEmpty());
    }

    @Test
    void modulIstBelegbarWennVoraussetzungenBestandenSind() {
        Studienplan plan = new StudienplanFactory().laden();

        Modul prog2 = plan.findeModul("PROG2");

        assertNotNull(prog2);
        assertTrue(prog2.istBelegbar());
    }

    @Test
    void modulIstNichtBelegbarWennVoraussetzungenOffenSind() {
        Studienplan plan = new StudienplanFactory().laden();

        Modul proj = plan.findeModul("PROJ");

        assertNotNull(proj);
        assertFalse(proj.istBelegbar());
    }

    @Test
    void deadlineWarnlisteKannErstelltWerden() {
        Studienplan plan = new StudienplanFactory().laden();

        List<Abgabe> abgaben = plan.getAbgabenNaechste7Tage();

        assertNotNull(abgaben);
    }
}