import static java.lang.Math.max;
import static java.lang.Math.abs;

/**
 * Diese Klasse definiert <Zusammenfassung ergänzen>
 *
 * @author Leandro Paolicelli
 */
class Npc extends Actor
{
    private Player player;
    private RingBuffer stepsToFollow = null;
    
    Npc(final int x, final int y, final int rotation, final String fileName, final Field field, final Player player) 
    {
        super(x, y, rotation, fileName, field);
        this.player = player;
    }
    
    @Override
    void act()
    {
        if (stepsToFollow != null) {
            stepsToFollow.push(player.getRotation());
        }
        
        if (getRotation() == 0) {
            setLocation(getX() + 1, getY());
        }
        else if (getRotation() == 1) {
            setLocation(getX(), getY() + 1);
        }
        else if (getRotation() == 2) {
            setLocation(getX() - 1, getY());
        }
        else if (getRotation() == 3) {
            setLocation(getX(), getY() - 1);
        }
        playSound("step");
        
        if (stepsToFollow != null && canWalk(stepsToFollow.peek())) {
            setRotation(stepsToFollow.pop());
        }
        else {
            stepsToFollow = null;
                
            if(!canWalk(getRotation())) {
               setRotation((getRotation() + 2) % 4);
            }
            
            if (getRotation() == 0 && player.getX() > getX() && player.getY() == getY()
                    || getRotation() == 1 && player.getX() == getX() && player.getY() > getY() 
                    || getRotation() == 2 && player.getX() < getX() && player.getY() == getY()
                    || getRotation() == 3 && player.getX() == getX() && player.getY() < getY()) {
                
                final int distance = max(abs(player.getX() - getX()), 
                        abs(player.getY() -getY()));
                
                if (distance <= 4) {
                    stepsToFollow = new RingBuffer(distance);
                    
                    for (int i = 1; i < distance; ++i) {
                        stepsToFollow.push(getRotation());
                    }
                }
            }
        }
        
        if (getX() == player.getX() && getY() == player.getY()) {
            player.setVisible(false);
            playSound("go-away");
        }  
    }
}