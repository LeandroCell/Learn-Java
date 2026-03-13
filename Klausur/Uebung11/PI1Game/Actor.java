/**
 * Diese Klasse definiert <Zusammenfassung ergänzen>
 *
 * @author Leandro Paolicelli
 */
abstract class Actor extends GameObject
{
    private final Field field;
    
    Actor(final int x, final int y, final int rotation, final String fileName, final Field field)
    {
        super(x, y, rotation, fileName);
        this.field = field;
    }
    
    boolean canWalk(final int direction)
    {
        return field.hasNeighbor(getX(), getY(), direction);
    }
    
    abstract void act();
}