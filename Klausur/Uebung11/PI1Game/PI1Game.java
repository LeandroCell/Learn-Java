import java.util.ArrayList;
import java.util.List;

/**
 * Dies ist die Hauptklasse eines Spiels. Sie enthält die Hauptmethode, die zum
 * Starten des Spiels aufgerufen werden muss.
 *
 * @author Leandro Paolicelli
 */
abstract class PI1Game extends Game
{
    /** Das Spiel beginnt durch Aufruf dieser Methode. */
    static void main()
    {
        final Level level = new Level("levels/level1.txt");
        
        new GameObject(4, 1, 3, "water-l");
        new GameObject(3, 3, 4, "water-l");
        new GameObject(4, 3, 0, "water-i");
        
        
        while(level.getActors().get(0).isVisible()) {
            level.getActors().forEach(actor -> actor.act());
        }
    }
}