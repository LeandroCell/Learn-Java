import java.util.ArrayList;
import java.util.List;

/**
 * Dies ist die Hauptklasse eines Spiels. Sie enthält die Hauptmethode, die zum
 * Starten des Spiels aufgerufen werden muss.
 *
 * @author Thomas Röfer
 */
abstract class PI1Game extends Game
{
    /** Das Spiel beginnt durch Aufruf dieser Methode. */
    static void main()
    {
        // Das Spielfeld erzeugen
        final Field field = new Field(new String[] {
            "O-O-O-O",
            "    |",
            "O-O-O-O",
            "    |",
            "O-O-O-O-O",
            "    |",
            "O-O-O"
        });
        new GameObject(3, 0, 0, "goal");
        new GameObject(4, 0, 0, "bridge");
        new GameObject(4, 1, 3, "water-l");
        new GameObject(3, 3, 0, "water-l");
        new GameObject(4, 3, 0, "water-i");

        final Player player = new Player(0, 3, 0, field);
        final List<Actor> actors = new ArrayList<>();
        actors.add(player);
        actors.add(new Walker(1, 0, 2, "claudius", field, player));
        actors.add(new Walker(0, 1, 0, "laila", field, player));
        actors.add(new Walker(3, 2, 2, "child", field, player));

        while (player.isVisible()) {
            for (final Actor actor : actors) {
                 actor.act();
            }
        }
    }
}
