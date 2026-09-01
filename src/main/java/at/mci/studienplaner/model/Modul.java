package at.mci.studienplaner.model;

public class Modul {

    private String kuerzel;
    private String name;
    private double ects;
    private boolean bestanden;

    public Modul(String kuerzel, String name, double ects) {
        this.kuerzel = kuerzel;
        this.name = name;
        this.ects = ects;
        this.bestanden = false;
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
}