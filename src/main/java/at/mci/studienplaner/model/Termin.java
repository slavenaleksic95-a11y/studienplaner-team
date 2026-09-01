package at.mci.studienplaner.model;

public class Termin {

    private String titel;

    public Termin(String titel) {
        this.titel = titel;
    }

    public String getTitel() {
        return titel;
    }

    public String exportZeile() {
        return titel;
    }
}
