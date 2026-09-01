package at.mci.studienplaner.model;

import at.mci.studienplaner.data.DatenLeser;
import at.mci.studienplaner.data.RohDaten;

import java.time.LocalDateTime;

public class StudienplanFactory {

    public Studienplan laden() {
        RohDaten daten = new DatenLeser().lese("modulkatalog.csv");

        Studienplan plan = new Studienplan("Mein Studienplan");

        for (String[] m : daten.module()) {
            String kuerzel = m[0];
            String name = m[1];
            double ects = Double.parseDouble(m[2]);
            boolean bestanden = m[3].equals("x");

            Modul modul = new Modul(kuerzel, name, ects);
            modul.setBestanden(bestanden);

            plan.addModul(modul);
        }

        for (String[] a : daten.abgaben()) {
            String titel = a[0];
            LocalDateTime frist =
                    LocalDateTime.parse(a[1] + "T23:59");

            Abgabe abgabe = new Abgabe(titel, frist);
            plan.addTermin(abgabe);
        }

        return plan;
    }
}