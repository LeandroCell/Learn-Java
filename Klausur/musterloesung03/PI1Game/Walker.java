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

    Walker(final GameObject avatar, final int maxSteps, final int stepsSoFar)
    {
        this.avatar = avatar;
        this.maxSteps = maxSteps;
        this.stepsSoFar = stepsSoFar;
    }

    void act(final GameObject player)
    {
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

        // Weiterzählen
        stepsSoFar = stepsSoFar + 1;

        // Wenn maximale Anzahl erreicht, umdrehen und Zählung neu beginnen
        if (stepsSoFar == maxSteps) {
            avatar.setRotation(avatar.getRotation() + 2);
            stepsSoFar = 0;
        }

        // Wenn gleiche Position wie Spielfigur, lasse diese verschwinden
        if (avatar.getX() == player.getX() && avatar.getY() == player.getY()) {
            player.setVisible(false);
            avatar.playSound("go-away");
        }
    }
}
