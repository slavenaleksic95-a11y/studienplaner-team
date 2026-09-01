package at.mci.studienplaner.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Vorlesung extends Termin {

    private String ort;
    private LocalDate datum;
    private LocalTime uhrzeit;

    public Vorlesung(String titel, String ort, LocalDate datum, LocalTime uhrzeit) {
        super(titel);
        this.ort = ort;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
    }

    public String getOrt() {
        return ort;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public LocalTime getUhrzeit() {
        return uhrzeit;
    }

    @Override
    public String exportZeile() {
        return getTitel() + ";" + ort + ";" + datum + ";" + uhrzeit;
    }
}