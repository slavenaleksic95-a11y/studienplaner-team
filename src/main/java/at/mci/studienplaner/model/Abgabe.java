package at.mci.studienplaner.model;

import java.time.LocalDateTime;

public class Abgabe extends Termin {

    private LocalDateTime frist;

    public Abgabe(String titel, LocalDateTime frist) {
        super(titel);
        this.frist = frist;
    }

    public LocalDateTime getFrist() {
        return frist;
    }

    @Override
    public String exportZeile() {
        return getTitel() + ";" + frist;
    }
}