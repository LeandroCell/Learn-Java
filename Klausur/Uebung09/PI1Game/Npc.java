import static java.lang.Math.max;
import static java.lang.Math.abs;

/**
 * Diese Klasse definiert <Zusammenfassung ergänzen>
 *
 * @author Leandro Paolicelli
 */
class Npc
{
    private final GameObject npc;
    private RingBuffer stepsToFollow = null;
    private final Field field;
    
    Npc(GameObject npc, Field field) 
    {
        this.npc = npc;
        this.field = field;
    }
    
    void act(final GameObject player)
    {
        if (stepsToFollow != null) {
            stepsToFollow.push(player.getRotation());
        }
        
        if (npc.getRotation() == 0) {
            npc.setLocation(npc.getX() + 1, npc.getY());
        }
        else if (npc.getRotation() == 1) {
            npc.setLocation(npc.getX(), npc.getY() + 1);
        }
        else if (npc.getRotation() == 2) {
            npc.setLocation(npc.getX() - 1, npc.getY());
        }
        else if (npc.getRotation() == 3) {
            npc.setLocation(npc.getX(), npc.getY() - 1);
        }
        npc.playSound("step");
        
        if (stepsToFollow != null && field.hasNeighbor(npc.getX(), npc.getY(), stepsToFollow.peek())) {
            npc.setRotation(stepsToFollow.pop());
        }
        else {
            stepsToFollow = null;
                
            if(!field.hasNeighbor(npc.getX(), npc.getY(), npc.getRotation())) {
               npc.setRotation((npc.getRotation() + 2) % 4);
            }
            
            if (npc.getRotation() == 0 && player.getX() > npc.getX() && player.getY() == npc.getY()
                    || npc.getRotation() == 1 && player.getX() == npc.getX() && player.getY() > npc.getY() 
                    || npc.getRotation() == 2 && player.getX() < npc.getX() && player.getY() == npc.getY()
                    || npc.getRotation() == 3 && player.getX() == npc.getX() && player.getY() < npc.getY()) {
                
                final int distance = max(abs(player.getX() - npc.getX()), 
                        abs(player.getY() -npc.getY()));
                
                if (distance <= 4) {
                    stepsToFollow = new RingBuffer(distance);
                    
                    for (int i = 1; i < distance; ++i) {
                        stepsToFollow.push(npc.getRotation());
                    }
                }
            }
        }
        
        if (npc.getX() == player.getX() && npc.getY() == player.getY()) {
            player.setVisible(false);
            npc.playSound("go-away");
        }  
    }
}