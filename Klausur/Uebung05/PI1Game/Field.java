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
    
    Field(final String []field) 
    {
        this.field = field;
        
        for (int y = 0; y < field.length; y += 2) {
            for (int x = 0; x < field[y].length(); x += 2) {
                new GameObject(x/2, y/2, 0, neighborhoodToFilename[getNeighborhood(x, y)]);
            }
        }
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
        int neighborhood = 0;
        
        if (getCell(x+1, y) != ' ') {
            neighborhood += 1;
        }
        if (getCell(x, y+1) != ' ') {
            neighborhood += 2;
        }
        if (getCell(x-1, y) != ' ') {
            neighborhood += 4;
        }
        if (getCell(x, y-1) != ' ') {
            neighborhood += 8;
        }
        return neighborhood;
    }

    /** Ein Testfall, der alle Nachbarschaften enthält. */
    static void test()
    {
        new GameObject.Canvas(5, 5, 96, 96);

        // Einkommentieren, sobald Konstruktor vorhanden
        new Field(new String[] {
            "O-O-O-O  ",
            "|   |    ",
            "O O-O-O O",
            "| | | | |",
            "O-O-O-O-O",
            "| | | | |",
            "O O-O-O O",
            "    |   |",
            "O-O-O-O-O"
        });
    }
}
