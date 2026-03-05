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
        new GameObject(0, 0, 0, "path-e");
        new GameObject(1, 0, 0, "path-i");
        new GameObject(2, 0, 1, "path-t");
        new GameObject(3, 0, 0, "path-i");
        new GameObject(3, 0, 0, "goal");
        new GameObject(4, 0, 0, "bridge-0");
        
        new GameObject(0, 1, 0, "path-e");
        new GameObject(1, 1, 0, "path-i");
        new GameObject(2, 1, 0, "path-x");
        new GameObject(3, 1, 2, "path-e");
        new GameObject(4, 1, 3, "water-l");
        
        new GameObject(0, 2, 0, "path-e");
        new GameObject(1, 2, 0, "path-i");
        new GameObject(2, 2, 0, "path-x");
        new GameObject(3, 2, 0, "path-i");
        new GameObject(4, 2, 2, "path-e");
        
        new GameObject(0, 3, 0, "path-e");
        new GameObject(1, 3, 0, "path-i");
        new GameObject(2, 3, 2, "path-l");
        new GameObject(3, 3, 4, "water-l");
        new GameObject(4, 3, 0, "water-i");
        
        final GameObject player = new GameObject(0, 3, 0, "woman");
        final Npc npc1 = new Npc(new GameObject(3, 2, 2, "child"), 4, 1);
        final Npc npc2 = new Npc(new GameObject(0, 1, 0, "laila"), 3, 0);
        final Npc npc3 = new Npc(new GameObject(1, 0, 2, "claudius"), 3, 2);
        
        while(player.isVisible()) {
            final int key = getNextKey();
            if (key == VK_RIGHT) {
                player.setRotation(0);
                player.setLocation(player.getX() + 1, player.getY());
                playSound("step");
            }
            else if (key == VK_DOWN) {
                player.setRotation(1);
                player.setLocation(player.getX(), player.getY() + 1);
                playSound("step");
            }
            else if (key == VK_LEFT) {
                player.setRotation(2);
                player.setLocation(player.getX() - 1, player.getY());
                playSound("step");
            }
            else if (key == VK_UP) {
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