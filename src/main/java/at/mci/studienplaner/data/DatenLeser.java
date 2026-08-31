package at.mci.studienplaner.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Liest den Modulkatalog (CSV). GESCHENKT – Datei-Parsing ist kein Lernziel dieses Kurses.
 * Du musst diese Klasse nicht verändern, nur benutzen.
 *
 * Format: M;Kuerzel;Name;ECTS;x|-   VL;Titel;OrtUndZeit   AB;Titel;JJJJ-MM-TT   VOR;Vorher;Nachher
 */
public class DatenLeser {

    public RohDaten lese(String resourceName) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(resourceName);
        if (in == null) {
            throw new IllegalArgumentException("Resource nicht gefunden: " + resourceName);
        }
        List<String[]> module = new ArrayList<>();
        List<String[]> vorlesungen = new ArrayList<>();
        List<String[]> abgaben = new ArrayList<>();
        List<String[]> voraussetzungen = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String zeile;
            while ((zeile = reader.readLine()) != null) {
                zeile = zeile.trim();
                if (zeile.isEmpty() || zeile.startsWith("#")) continue;
                String[] teile = zeile.split(";");
                switch (teile[0]) {
                    case "M" -> module.add(Arrays.copyOfRange(teile, 1, 5));
                    case "VL" -> vorlesungen.add(Arrays.copyOfRange(teile, 1, 3));
                    case "AB" -> abgaben.add(Arrays.copyOfRange(teile, 1, 3));
                    case "VOR" -> voraussetzungen.add(Arrays.copyOfRange(teile, 1, 3));
                    default -> throw new IllegalStateException("Unbekannter Zeilentyp: " + teile[0]);
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return new RohDaten(module, vorlesungen, abgaben, voraussetzungen);
    }
}
