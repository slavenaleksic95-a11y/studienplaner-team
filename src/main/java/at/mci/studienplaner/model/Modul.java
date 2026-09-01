package at.mci.studienplaner.model;

import java.util.ArrayList;
import java.util.List;

public class Modul {

    private String kuerzel;
    private String name;
    private double ects;
    private boolean bestanden;
    private List<Modul> voraussetzungen;
    private int semester;

    public Modul(String kuerzel, String name, double ects) {
        this(kuerzel, name, ects, 0);
    }

    public Modul(String kuerzel, String name, double ects, int semester) {
        this.kuerzel = kuerzel;
        this.name = name;
        this.ects = ects;
        this.bestanden = false;
        this.voraussetzungen = new ArrayList<>();
        this.semester = semester;
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

    public boolean istBelegbar() {
        return getOffeneVoraussetzungen().isEmpty();
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}