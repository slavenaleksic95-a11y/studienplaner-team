package at.mci.studienplaner.export;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * Schreibt die Fortschrittsseite als HTML-Datei.
 * (Im Kurs GESCHENKT – HTML ist kein Lernziel. Der Exporter kennt nur den
 * Vertrag {@link Exportierbar}, nie die Domänenklassen: Station 4, Abhängigkeitsrichtung.)
 */
public class HtmlExporter {

    /**
     * @param titel      Seitenüberschrift
     * @param kennzahlen kurze Zeilen für den Kopf der Seite (z.B. "15 von 40 ECTS bestanden")
     * @param abschnitte Abschnittsüberschrift → Einträge (Reihenfolge bleibt erhalten)
     * @param zielDatei  wohin die Seite geschrieben wird
     */
    public Path schreibeSeite(String titel, List<String> kennzahlen,
                              Map<String, List<? extends Exportierbar>> abschnitte, Path zielDatei) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html lang=\"de\">\n<head>\n<meta charset=\"UTF-8\">\n");
        html.append("<title>").append(escape(titel)).append("</title>\n");
        html.append("<style>\n");
        html.append("body { font-family: system-ui, sans-serif; max-width: 720px; margin: 2rem auto; padding: 0 1rem; color: #1a2330; }\n");
        html.append("h1 { border-bottom: 3px solid #00518e; padding-bottom: .3rem; }\n");
        html.append("h2 { color: #00518e; margin-top: 2rem; }\n");
        html.append(".kennzahl { background: #eef4fa; border-left: 4px solid #00518e; padding: .5rem .8rem; margin: .4rem 0; }\n");
        html.append("ul { line-height: 1.7; }\n");
        html.append("</style>\n</head>\n<body>\n");
        html.append("<h1>").append(escape(titel)).append("</h1>\n");
        for (String kennzahl : kennzahlen) {
            html.append("<p class=\"kennzahl\">").append(escape(kennzahl)).append("</p>\n");
        }
        for (Map.Entry<String, List<? extends Exportierbar>> abschnitt : abschnitte.entrySet()) {
            html.append("<h2>").append(escape(abschnitt.getKey())).append("</h2>\n<ul>\n");
            for (Exportierbar eintrag : abschnitt.getValue()) {
                html.append("<li>").append(escape(eintrag.exportZeile())).append("</li>\n");
            }
            html.append("</ul>\n");
        }
        html.append("</body>\n</html>\n");

        try {
            Files.writeString(zielDatei, html.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return zielDatei;
    }

    private String escape(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
