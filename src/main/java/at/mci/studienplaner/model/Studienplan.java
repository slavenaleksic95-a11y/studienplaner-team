package at.mci.studienplaner.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Studienplan {

    private String name;
    private List<Modul> module;
    private List<Termin> termine;

    public Studienplan(String name) {
        this.name = name;
        this.module = new ArrayList<>();
        this.termine = new ArrayList<>();
    }

    public void addModul(Modul modul) {
        module.add(modul);
    }

    public void addTermin(Termin termin) {
        termine.add(termin);
    }

    public String getName() {
        return name;
    }

    public List<Modul> getModule() {
        return module;
    }

    public List<Termin> getTermine() {
        return termine;
    }

    public Modul findeModul(String kuerzel) {
        for (Modul modul : module) {
            if (modul.getKuerzel().equalsIgnoreCase(kuerzel)) {
                return modul;
            }
        }
        return null;
    }

    public double berechneBestandeneEcts() {
        double ects = 0;

        for (Modul modul : module) {
            if (modul.istBestanden()) {
                ects = ects + modul.getEcts();
            }
        }

        return ects;
    }

    public List<Abgabe> getAbgabenNaechste7Tage() {
        List<Abgabe> warnliste = new ArrayList<>();

        LocalDateTime jetzt = LocalDateTime.now();
        LocalDateTime in7Tagen = jetzt.plusDays(7);

        for (Termin termin : termine) {
            if (termin instanceof Abgabe) {
                Abgabe abgabe = (Abgabe) termin;

                if (!abgabe.getFrist().isBefore(jetzt)
                        && !abgabe.getFrist().isAfter(in7Tagen)) {
                    warnliste.add(abgabe);
                }
            }
        }

        return warnliste;
    }
}