// Importieren der VK_*-Tastenkonstanten
import static java.awt.event.KeyEvent.*;

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
        Field field = new Field(new String[] {
            "O-O-O-O  ",
            "    |    ",
            "O-O-O-O  ",
            "    |    ",
            "O-O-O-O-O",
            "    |    ",
            "O-O-O    "
        });
        
        new GameObject(3, 0, 0, "goal");
        new GameObject(4, 0, 0, "bridge-0");
        
        new GameObject(4, 1, 3, "water-l");
        
        new GameObject(3, 3, 4, "water-l");
        new GameObject(4, 3, 0, "water-i");
        
        final GameObject player = new GameObject(0, 3, 0, "woman");
        final Npc npc1 = new Npc(new GameObject(3, 2, 2, "child"), field);
        final Npc npc2 = new Npc(new GameObject(0, 1, 0, "laila"), field);
        final Npc npc3 = new Npc(new GameObject(1, 0, 2, "claudius"), field);
        
        while(player.isVisible()) {
            final int key = getNextKey();
            if (key == VK_RIGHT && field.hasNeighbor(player.getX(), player.getY(), 0)) {
                player.setRotation(0);
                player.setLocation(player.getX() + 1, player.getY());
                playSound("step");
            }
            else if (key == VK_DOWN && field.hasNeighbor(player.getX(), player.getY(), 1)) {
                player.setRotation(1);
                player.setLocation(player.getX(), player.getY() + 1);
                playSound("step");
            }
            else if (key == VK_LEFT && field.hasNeighbor(player.getX(), player.getY(), 2)) {
                player.setRotation(2);
                player.setLocation(player.getX() - 1, player.getY());
                playSound("step");
            }
            else if (key == VK_UP && field.hasNeighbor(player.getX(), player.getY(), 3)) {
                player.setRotation(3);
                player.setLocation(player.getX(), player.getY() - 1);
                playSound("step");
            }
            else {
                playSound("error");
                continue;
            }
            npc1.act(player);
            npc2.act(player);
            npc3.act(player);
        }
    }
}