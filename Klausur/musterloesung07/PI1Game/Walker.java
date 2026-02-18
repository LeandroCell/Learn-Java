import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * Diese Klasse definiert eine Spaziergänger:in, die dieselbe Strecke immer
 * auf und ab läuft. Dabei werden bei der Konstruktion die Startposition und
 * -richtung angegeben, sowie das Spielfeld, auf dem sie sich bewegt.
 *
 * @author Thomas Röfer
 */
class Walker
{
    private final GameObject avatar;
    private final Field field;
    private RingBuffer stepsToFollow = null;

    Walker(final GameObject avatar, final Field field)
    {
        this.avatar = avatar;
        this.field = field;
    }

    void act(final GameObject player)
    {
        // Wenn im Verfolgermodus, dann Schritt aufzeichnen
        if (stepsToFollow != null) {
            stepsToFollow.push(player.getRotation());
        }

        // Vorwärts bewegen
        if (avatar.getRotation() == 0) {
            avatar.setLocation(avatar.getX() + 1, avatar.getY());
        }
        else if (avatar.getRotation() == 1) {
            avatar.setLocation(avatar.getX(), avatar.getY() + 1);
        }
        else if (avatar.getRotation() == 2) {
            avatar.setLocation(avatar.getX() - 1, avatar.getY());
        }
        else {
            avatar.setLocation(avatar.getX(), avatar.getY() - 1);
        }

        // Sound dazu abspielen
        avatar.playSound("step");

        // Wenn im Verfolgermodus und aufgezeichneter Schritt möglich,
        // dann diesen verwenden.
        if (stepsToFollow != null && field.hasNeighbor(avatar.getX(), avatar.getY(),
                stepsToFollow.peek())) {
            avatar.setRotation(stepsToFollow.pop());
        }
        else {
            // Wir sind nicht (mehr) im Verfolgermodus
            stepsToFollow = null;

            // Umdrehen, wenn nächster Schritt nicht mehr ausführbar
            if (!field.hasNeighbor(avatar.getX(), avatar.getY(), avatar.getRotation())) {
                avatar.setRotation(avatar.getRotation() + 2);
            }

            // Wenn Spielfigur vor dieser Figur
            if (avatar.getRotation() == 0
                        && player.getX() > avatar.getX()
                        && player.getY() == avatar.getY()
                    || avatar.getRotation() == 1
                        && player.getX() == avatar.getX()
                        && player.getY() > avatar.getY()
                    || avatar.getRotation() == 2
                        && player.getX() < avatar.getX()
                        && player.getY() == avatar.getY()
                    || avatar.getRotation() == 3
                        && player.getX() == avatar.getX()
                        && player.getY() < avatar.getY()) {
                // Entfernung zur Spielfigur bestimmen.
                final int distance = max(abs(player.getX() - avatar.getX()),
                        abs(player.getY() - avatar.getY()));

                // Wenn auch in Sichtweite, dann Puffer anlegen und Weg eintragen.
                if (distance <= 4) {
                    stepsToFollow = new RingBuffer(distance);
                    for (int i = 1; i < distance; ++i) {
                        stepsToFollow.push(avatar.getRotation());
                    }
                }
            }
        }

        // Wenn gleiche Position wie Spielfigur, lasse diese verschwinden
        if (avatar.getX() == player.getX()) {
            if (avatar.getY() == player.getY()) {
                player.setVisible(false);
                avatar.playSound("go-away");
            }
        }
    }
}
