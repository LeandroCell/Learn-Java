import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * Diese Klasse definiert eine Spaziergänger:in, die dieselbe Strecke immer
 * auf und ab läuft. Dabei werden bei der Konstruktion die Startposition und
 * -richtung angegeben, die Länge der Strecke in Schritten sowie die Anzahl
 * der Schritte, die zum Start bereits auf dieser Strecke zurückgelegt
 * wurden.
 *
 * @author Thomas Röfer
 */
class Walker
{
    private final GameObject avatar;
    private final int maxSteps;
    private int stepsSoFar;
    private RingBuffer stepsToFollow = null;

    Walker(final GameObject avatar, final int maxSteps, final int stepsSoFar)
    {
        this.avatar = avatar;
        this.maxSteps = maxSteps;
        this.stepsSoFar = stepsSoFar;
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

        // Wenn im Verfolgermodus, dann aufgezeichneten Schritt verwenden
        if (stepsToFollow != null) {
            avatar.setRotation(stepsToFollow.pop());
        }
        else {
            // Weiterzählen
            stepsSoFar = stepsSoFar + 1;

            // Wenn maximale Anzahl erreicht, umdrehen und Zählung neu beginnen
            if (stepsSoFar == maxSteps) {
                avatar.setRotation(avatar.getRotation() + 2);
                stepsSoFar = 0;
            }

            // Ist Spielfigur vor dieser Figur?
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
                // Entfernung zur Spielfigur bestimmen
                final int distance = max(abs(player.getX() - avatar.getX()),
                        abs(player.getY() - avatar.getY()));

                // Wenn auch in Sichtweite, dann Puffer anlegen und Weg eintragen
                if (distance <= 4) {
                    stepsToFollow = new RingBuffer(distance);
                    for (int i = 1; i < distance; ++i) {
                        stepsToFollow.push(avatar.getRotation());
                    }
                }
            }
        }

        // Wenn gleiche Position wie Spielfigur, lasse diese verschwinden
        if (avatar.getX() == player.getX() && avatar.getY() == player.getY()) {
            player.setVisible(false);
            avatar.playSound("go-away");
        }
    }
}
