package at.mci.studienplaner.model;

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
}