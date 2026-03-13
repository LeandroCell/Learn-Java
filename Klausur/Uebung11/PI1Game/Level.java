import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Diese Klasse definiert <Zusammenfassung ergänzen>
 *
 * Player: <, >, ^, v
 * Laila: l, L, i, I
 * Claudius: c, C, k, K
 * Child: s, S, z, Z
 * Ziel: G
 * Brücke: b, B
 * @author Leandro Paolicelli
 */
class Level
{
    private final Field field;
    private final List<Actor> actors = new ArrayList<>();
    private final List<GameObject> gameObjects = new ArrayList<>();

    Level(final String fileName)
    {
        final List<String> lines = new ArrayList<String>();
        try (final InputStream input = Game.Jar.getInputStream(fileName);
             final Reader reader = new InputStreamReader(input);
             final BufferedReader stream = new BufferedReader(reader)) {
            String line;
            while ((line = stream.readLine()) != null) {
                lines.add(line);
            }
        }
        catch (final FileNotFoundException e) {
            throw new IllegalArgumentException("Level '" + fileName 
                    + "' wurde nicht gefunden.");
        }
        catch (final IOException e) {
            throw new IllegalArgumentException("Fehler beim Lesen des Levels '" 
                    + fileName + "'.");
        }
    
        field = new Field(lines.toArray(new String[lines.size()]));
        
        final Player player = new Player(-1, 0, 0, field);
        actors.add(player);
        
        for (int y = 0; y < lines.size(); y += 2) {
            for (int x = 0; x < lines.get(y).length(); x += 2) {
                final int index = "<>^vlLiIcCkKsSzZGbBO "
                        .indexOf(lines.get(y).charAt(x));
                
                if (index == -1) {
                    throw new IllegalArgumentException("Unbekanntes Symbol '" + 
                            lines.get(y).charAt(x) + "' in Level '" + fileName +
                            "' in Zeile '" + (y + 1) + " Spalte " + (x + 1) + " gefunden.");
                }
                else if (index < 4) {
                        // Es darf nur eine Spielfigur geben.
                    if (player.getX() != -1) {
                        throw new IllegalArgumentException("Zweite Spielfigur in Level '"
                                + fileName + "', Zeile " + (y + 1) + ", Spalte " 
                                + (x + 1) + " gefunden.");
                    }
                    
                    player.setLocation(x / 2, y / 2);
                    player.setRotation(index);
                }
                else if (index < 16) {
                    // Spaziergänger:innen platzieren, sind nach Bild und Rotationen
                    // geordnet.
                    final String[] images = {"laila", "claudius", "child"};
                    final Actor actor = new Npc(x / 2, y / 2, index % 4,
                            images[index / 4 - 1], field, player);
                    actors.add(actor);
                    gameObjects.add(actor);
                }
                else if (index < 19) {
                        // Ziel und Brückensymbole einfügen
                        final String[] images = {"goal", "bridge-0", "bridge-1"};
                        gameObjects.add(new GameObject(x / 2, y / 2, 0, images[index - 16]));
                }
            }
        }
        
        if (player.getX() == -1) {
            throw new IllegalArgumentException("Keine Spielfigur in Level '" + fileName + "' gefunden");
        }
    }
    List<Actor> getActors() 
    {
        return actors;
    }
    
    void hide()
    {
        for (final GameObject gameObject : gameObjects) {
            gameObject.setVisible(false);
        }
        field.hide();
    }
}