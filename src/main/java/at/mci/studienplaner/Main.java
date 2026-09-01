package at.mci.studienplaner;

import at.mci.studienplaner.export.Exportierbar;
import at.mci.studienplaner.export.HtmlExporter;
import at.mci.studienplaner.model.Modul;
import at.mci.studienplaner.model.Studienplan;
import at.mci.studienplaner.model.StudienplanFactory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // Studienplan aus der CSV-Datei laden
        Studienplan plan = new StudienplanFactory().laden();

        // Listen für bestandene und offene Module
        List<Exportierbar> bestandeneModule = new ArrayList<>();
        List<Exportierbar> offeneModule = new ArrayList<>();

        double gesamtEcts = 0;

        // Alle Module durchgehen
        for (Modul modul : plan.getModule()) {

            gesamtEcts += modul.getEcts();

            Exportierbar exportModul = new Exportierbar() {
                @Override
                public String exportZeile() {
                    return modul.getKuerzel()
                            + " - "
                            + modul.getName()
                            + " ("
                            + modul.getEcts()
                            + " ECTS)";
                }
            };

            if (modul.istBestanden()) {
                bestandeneModule.add(exportModul);
            } else {
                offeneModule.add(exportModul);
            }
        }

        // Bereits bestandene ECTS berechnen
        double bestandeneEcts = plan.berechneBestandeneEcts();

        // Kennzahl für die HTML-Seite
        List<String> kennzahlen = new ArrayList<>();

        kennzahlen.add(
                bestandeneEcts
                        + " von "
                        + gesamtEcts
                        + " ECTS bestanden"
        );

        // Abschnitte der HTML-Seite
        Map<String, List<? extends Exportierbar>> abschnitte =
                new LinkedHashMap<>();

        abschnitte.put("Bestandene Module", bestandeneModule);
        abschnitte.put("Offene Module", offeneModule);

        // HTML-Exporter
        HtmlExporter exporter = new HtmlExporter();

        // Datei wird im Hauptordner des Projekts erstellt
        Path datei = Path.of("fortschritt.html");

        exporter.schreibeSeite(
                "Mein Studienplan",
                kennzahlen,
                abschnitte,
                datei
        );

        System.out.println(
                "HTML-Datei erstellt: " + datei.toAbsolutePath()
        );
    }
}