// Importieren der VK_*-Tastenkonstanten
import static java.awt.event.KeyEvent.*;

/**
 * Diese Klasse definiert <Zusammenfassung ergänzen>
 *
 * @author Leandro Paolicelli
 */
class Player extends Actor
{
    Player(final int x, final int y, final int rotation, Field field)
    {
        super(x, y, rotation, "woman", field);     
    }
    
    @Override
    void act() 
    {
        while (true) {
            final int key = getNextKey();
            if (key == VK_RIGHT && canWalk(0)) {
                setRotation(0);
                setLocation(getX() + 1, getY());
            }
            else if (key == VK_DOWN && canWalk(1)) {
                setRotation(1);
                setLocation(getX(), getY() + 1);
            }
            else if (key == VK_LEFT && canWalk(2)) {
                setRotation(2);
                setLocation(getX() - 1, getY());
            }
            else if (key == VK_UP && canWalk(3)) {
                setRotation(3);
                setLocation(getX(), getY() - 1);
            
            }
            else {
                playSound("error");
                continue;
            }
            
            playSound("step");
            sleep(200);
            break;
        }
    }
}