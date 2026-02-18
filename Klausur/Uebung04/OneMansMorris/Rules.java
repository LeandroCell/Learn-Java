import static java.lang.Math.abs; // Absolutwert
import static java.lang.Math.max; // Maximum zweier Zahlen

class Rules
{
    private final int centerX;
    private final int centerY;

    Rules(final int x, final int y)
    {
        centerX = x;
        centerY = y;
    }

    boolean isLegal(final GameObject object, final int dirX, final int dirY)
    {
        if (dirX == 0 && dirY == 0) {
            return false;
        }

        if (dirX != 0 && dirY != 0) {
            return false;
        }

        if (abs(dirX) > 1 || abs(dirY) > 1) {
            return false;
        }

        final int fromLevel = maxAbs(object.getX(), object.getY());
        final int toLevel = maxAbs(object.getX() + dirX, object.getY() + dirY);
        if (fromLevel == toLevel) {
            return true;
        }

        if (object.getX() != centerX && object.getX() != centerY) {
            return false;
        }

        final int outerLevel = maxAbs(fromLevel, toLevel);
        return outerLevel == 2;
    }

    private int maxAbs(final int x, final int y)
    {
        return max(abs(x - centerX), abs(y - centerY));
    }
}
