package at.mci.studienplaner.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Studienplan {

    private String name;
    private List<Modul> module;
    private List<Termin> termine;
    private Map<String, Modul> moduleNachKuerzel;

    public Studienplan(String name) {
        this.name = name;
        this.module = new ArrayList<>();
        this.termine = new ArrayList<>();
        this.moduleNachKuerzel = new HashMap<>();
    }

    public void addModul(Modul modul) {
        module.add(modul);
        moduleNachKuerzel.put(modul.getKuerzel().toUpperCase(), modul);
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
        return moduleNachKuerzel.get(kuerzel.toUpperCase());
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

    /*
     * M5:
     * Findet alle Module, die durch ein bestimmtes Modul
     * direkt oder indirekt freigeschaltet werden.
     */
    public List<Modul> getFreigeschalteteModule(Modul startModul) {
        List<Modul> ergebnis = new ArrayList<>();
        Set<Modul> besucht = new HashSet<>();

        findeFreigeschalteteModule(startModul, ergebnis, besucht);

        return ergebnis;
    }

    private void findeFreigeschalteteModule(
            Modul startModul,
            List<Modul> ergebnis,
            Set<Modul> besucht) {

        if (!besucht.add(startModul)) {
            return;
        }

        for (Modul modul : module) {
            if (modul.getVoraussetzungen().contains(startModul)) {

                if (!ergebnis.contains(modul)) {
                    ergebnis.add(modul);
                }

                findeFreigeschalteteModule(modul, ergebnis, besucht);
            }
        }
    }

    public boolean hatVoraussetzungsZirkel() {
        Set<Modul> besucht = new HashSet<>();
        Set<Modul> aktuellerPfad = new HashSet<>();

        for (Modul modul : module) {
            if (hatZirkel(modul, besucht, aktuellerPfad)) {
                return true;
            }
        }

        return false;
    }

    private boolean hatZirkel(
            Modul modul,
            Set<Modul> besucht,
            Set<Modul> aktuellerPfad) {

        if (aktuellerPfad.contains(modul)) {
            return true;
        }

        if (besucht.contains(modul)) {
            return false;
        }

        besucht.add(modul);
        aktuellerPfad.add(modul);

        for (Modul voraussetzung : modul.getVoraussetzungen()) {
            if (hatZirkel(voraussetzung, besucht, aktuellerPfad)) {
                return true;
            }
        }

        aktuellerPfad.remove(modul);

        return false;
    }

    public Map<Integer, List<Modul>> getModuleNachSemester() {
        Map<Integer, List<Modul>> moduleNachSemester = new HashMap<>();

        for (Modul modul : module) {
            moduleNachSemester
                    .computeIfAbsent(modul.getSemester(), k -> new ArrayList<>())
                    .add(modul);
        }

        return moduleNachSemester;
    }
}