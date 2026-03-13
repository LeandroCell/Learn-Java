import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Diese Klasse repräsentiert ein Spielfeld. Ihr Konstruktor bekommt dieses als
 * String-Array übergeben.
 */
class Field
{
    /**
     * Die Dateinamen der Bodengitterelemente, die direkt mit einer
     * Rotation 0 verwendet werden können. Der Index ergibt sich
     * aus der Summe der folgenden Zahlen:
     * 1: In Richtung 0 (+1, 0) gibt es eine Verbindung.
     * 2: In Richtung 1 (0, +1) gibt es eine Verbindung.
     * 4: In Richtung 2 (-1, 0) gibt es eine Verbindung.
     * 8: In Richtung 3 (0, -1) gibt es eine Verbindung.
     */
    private static final String[] neighborhoodToFilename = {
        "grass",
        "path-e-0",
        "path-e-1",
        "path-l-0",
        "path-e-2",
        "path-i-0",
        "path-l-1",
        "path-t-1",
        "path-e-3",
        "path-l-3",
        "path-i-1",
        "path-t-0",
        "path-l-2",
        "path-t-3",
        "path-t-2",
        "path-x"
    };
    
    private final String[] field;
    
    /** Die Liste aller erzeugten Spielobjekte. */
    private final List<GameObject> gameObjects = new ArrayList<>();
    
    Field(final String[] field)
    {
        this.field = field;
        
        IntStream.iterate(0, y -> y < field.length, y -> y + 2)
                .forEach(y -> IntStream.iterate(0, x -> x < field[y].length(), x -> x +2)
                .forEach(x -> gameObjects.add(new GameObject(x / 2, y / 2, 0, neighborhoodToFilename[getNeighborhood(x, y)]))));
    }
    
    private char getCell(final int x, final int y)
    {
        if (x >= 0 && y >= 0 && y < field.length && x < field[y].length()) {
            return field[y].charAt(x);
        }
        else {
            return ' ';
        }
    }
    
    private int getNeighborhood(final int x, final int y)
    {
        int[][] neighbors = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        
        return IntStream.range(0, neighbors.length)
                .filter(direction -> getCell(x + neighbors[direction][0], y + neighbors[direction][1]) != ' ')
                .map(direction -> 1 << direction)
                .reduce(0, (a, b) -> a | b);
    }
    
    boolean hasNeighbor(final int x, final int y, final int direction) 
    {
        return (getNeighborhood(x*2, y*2) & (1 << direction)) != 0;
    }

    void hide()
    {
        for (final GameObject gameObject : gameObjects) {
            gameObject.setVisible(false);
        }
    }
}