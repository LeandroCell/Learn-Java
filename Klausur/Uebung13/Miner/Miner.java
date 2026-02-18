import java.io.*;
import java.util.*;

/**
 * Ein Bergarbeiter hat eine Wegbeschreibung zu einem Ziel, die er aus
 * einer Datei einliest und nach dieser navigiert. In der Wegbeschreibung
 * steht eine Abfolge von Richtungen, in die sie in der Methode "act" gehen
 * muss. Kann die Datei nicht gefunden werden, bleibt der Bergarbeiter
 * einfach stehen.
 *
 * Die Methode "testMiner" soll zum Testen benutzt werden.
 *
 * Der Ursprung des Koordinatensystems für die Positionen ist oben in der
 * Mitte. Die x-Achse zeigt nach rechts unten, die y-Achse nach links
 * unten. Die vier Richtungen 0-3 zeigen nach +x, +y, -x und -y.
 */
class Miner
{
    /** Die Figur, die gesteuert wird. */
    private final GameObject miner;

    // Hier weitere Attribute, falls ihr welche benötigt

    /**
     * Konstruktor der Klasse Miner.
     * @param miner Die gesteuerte Figur.
     * @param filename Der Name der Datei, in der die Wegbeschreibung
     *         gespeichert ist. In der Datei stehen einfach, durch
     *         Leerraum getrennt, die Richtungen (Parameter von
     *         "setRotation"), in die der Reihe nach gegangen werden
     *         muss.
     */
    Miner(final GameObject miner, final String filename)
    {
        this.miner = miner;

        // Datei einlesen
    }

    /**
     * Wenn die Wegbeschreibung erfolgreich gelesen werden konnte, muss
     * bei jedem Aufruf der nächste Eintrag der Wegbeschreibung als
     * Rotation gesetzt werden. Die Figur macht dann einen Schritt in
     * die entsprechende Richtung. Ist die Wegbeschreibung vollständig
     * abgearbeitet und das Ziel somit erreicht oder konnte keine
     * Wegbeschreibung eingelesen werden, muss false zurückgeliefert
     * werden.
     * @return true, wenn der Bergarbeiter einen Schritt vorwärts machen
     *         soll. false, wenn der Schatz erreicht wurde oder die
     *         Wegbeschreibung nicht lesbar war.
     */
    boolean act()
    {
        return false; // Ersetzen
    }

    /**
     * Die Testmethode. Bei richtiger Implementierung folgt der untere
     * Bergarbeiter dem Weg in der Datei "path.txt" und bleibt stehen, wenn
     * das Ziel erreicht wurde. Insbesondere sollte dann auch das Programm
     * anhalten. Die obere Bergarbeiter bewegt sich hingegen nicht.
     */
    static void testMiner()
    {
        final GameObject[] objects = {
            new GameObject(2, 8, 0, "steve"),
            new GameObject(1, 1, 0, "steve")
        };
        final Miner[] instances = {
            new Miner(objects[0], "path.txt"),
            new Miner(objects[1], "missing.txt")
        };
        final int[][] versatz = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        boolean weiter = true;
        while (weiter) {
            weiter = false;
            for (int i = 0; i < objects.length; ++i) {
                if (instances[i].act()) {
                    final GameObject object = objects[i];
                    object.setLocation(object.getX() + versatz[object.getRotation()][0],
                            object.getY() + versatz[object.getRotation()][1]);
                    weiter = true;
                }
            }
            Game.sleep(200);
        }
    }
}
