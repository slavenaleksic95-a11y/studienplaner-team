package at.mci.studienplaner.export;

/**
 * Vertrag: Alles, was als Zeile auf der HTML-Seite erscheinen kann. (Station 5: Interface)
 * Der Exporter kennt NUR diesen Vertrag – nie deine Domänenklassen.
 * Deine Klassen implementieren dieses Interface, damit der Exporter sie ausgeben kann.
 */
public interface Exportierbar {
    String exportZeile();
}
