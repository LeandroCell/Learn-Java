/**
 * Diese Klasse definiert <Zusammenfassung ergänzen>
 *
 * @author Leandro Paolicelli
 */
class Npc
{
    private final GameObject npc;
    private final int maxSteps;
    private int stepsSoFar;
    
    Npc(GameObject npc, int maxSteps, int stepsSoFar) {
        this.npc = npc;
        this.maxSteps = maxSteps;
        this.stepsSoFar = stepsSoFar;
    }
    
    void act(final GameObject player){
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
        
        stepsSoFar += 1;
        
        if(stepsSoFar == maxSteps) {
            npc.setRotation(npc.getRotation() + 2);
            stepsSoFar = 0;
        }
        
        if(npc.getX() == player.getX() && npc.getY() == player.getY()) {
            player.setVisible(false);
            npc.playSound("go-away");
        }
    }
}