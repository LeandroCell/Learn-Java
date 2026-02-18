import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

/**
 * Der Rechner versteht Ausdrücke entsprechend folgender Grammatik:
 *
 * <pre>
 * or  = and , { '|' , and } ;
 * and = val , { '&' , val } ;
 * val = 'f' | 't' | '!' val | '(' or ')' ;
 * </pre>
 *
 * Beispiel: eval("(t|!t)&(f|!f)") ergibt true
 */
class Logic
{
    /**
     * Parst einen Ausdruck entsprechend der oben in der Grammatik
     * angegebenen Produktion 'or' und wertet ihn aus.
     * @param expr Der Ausdruck als Zeichenkette.
     * @return Das Ergebnis der Auswertung.
     * @throws IllegalArgumentException Der Ausdruck entspricht nicht der Grammatik.
     */
    static boolean eval(final String expr) throws IllegalArgumentException
    {
        // Erzeugt eine Warteschlange, die alle Zeichen des Ausdrucks enthält.
        // Der letzte Eintrag ist -1, um das Ende der Schlange zu markieren.
        final Queue<Integer> text = new LinkedList<>(
                IntStream.concat(expr.chars(), IntStream.of(-1))
                .boxed().toList());

        // Ausdruck auswerten
        final boolean value = or(text);

        // Nach dem Ausdruck muss das Ende-Zeichen folgen
        if (text.peek() == -1) {
            return value;
        }
        else {
            throw new IllegalArgumentException("Unerwartetes Zeichen '" + (char)(int) text.peek() + "'");
        }
    }

    private static boolean or(final Queue<Integer> text)
    {
        return false; // Ersetzen
    }

    // Weitere Methoden
}
