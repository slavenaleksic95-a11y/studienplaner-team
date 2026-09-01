package at.mci.studienplaner.model;

import java.util.ArrayList;
import java.util.List;

public class Modul {

    private String kuerzel;
    private String name;
    private double ects;
    private boolean bestanden;
    private List<Modul> voraussetzungen;

    public Modul(String kuerzel, String name, double ects) {
        this.kuerzel = kuerzel;
        this.name = name;
        this.ects = ects;
        this.bestanden = false;
        this.voraussetzungen = new ArrayList<>();
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public String getName() {
        return name;
    }

    public double getEcts() {
        return ects;
    }

    public boolean istBestanden() {
        return bestanden;
    }

    public void setBestanden(boolean bestanden) {
        this.bestanden = bestanden;
    }

    public void addVoraussetzung(Modul modul) {
        voraussetzungen.add(modul);
    }

    public List<Modul> getVoraussetzungen() {
        return voraussetzungen;
    }

    public List<Modul> getOffeneVoraussetzungen() {
        List<Modul> offen = new ArrayList<>();

        for (Modul modul : voraussetzungen) {
            if (!modul.istBestanden()) {
                offen.add(modul);
            }
        }

        return offen;
    }
}