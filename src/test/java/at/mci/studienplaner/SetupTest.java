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

        Modul planModul = plan.findeModul("PROJ");

        assertNotNull(planModul);
        assertFalse(planModul.istBelegbar());
    }

    @Test
    void deadlineWarnlisteKannErstelltWerden() {
        Studienplan plan = new StudienplanFactory().laden();

        List<Abgabe> abgaben = plan.getAbgabenNaechste7Tage();

        assertNotNull(abgaben);
    }

    @Test
    void sucheBeiKleinemUndGrossemKatalogMessen() {
        Studienplan klein = new Studienplan("Klein");

        for (int i = 0; i < 100; i++) {
            klein.addModul(
                    new Modul("M" + i, "Modul " + i, 5.0)
            );
        }

        Studienplan gross = new Studienplan("Gross");

        for (int i = 0; i < 100000; i++) {
            gross.addModul(
                    new Modul("M" + i, "Modul " + i, 5.0)
            );
        }

        long startKlein = System.nanoTime();
        klein.findeModul("M99");
        long endeKlein = System.nanoTime();

        long startGross = System.nanoTime();
        gross.findeModul("M99999");
        long endeGross = System.nanoTime();

        long zeitKlein = endeKlein - startKlein;
        long zeitGross = endeGross - startGross;

        System.out.println("Kleiner Katalog: " + zeitKlein + " ns");
        System.out.println("Großer Katalog: " + zeitGross + " ns");

        assertNotNull(klein.findeModul("M99"));
        assertNotNull(gross.findeModul("M99999"));
    }
}