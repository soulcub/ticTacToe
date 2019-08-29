package tictactoe;

public class DirectionsManager {

    public static final double DEGREES_STEP = Math.PI / 4;

    private double degrees = 0;

    public Vector getDirection() {
        return getDirectionIterators();
    }

    public void reset() {
        degrees = 0;
    }

    public Vector next() {
        degrees += DEGREES_STEP;
        if (degrees > Math.PI / 4 * 3) {
            degrees = 0;
        }

        return getDirectionIterators();
    }

    private Vector getDirectionIterators() {
        int x = round(Math.sin(degrees));
        int y = round(Math.cos(degrees));
        return new Vector(x, y);
    }

    private int round(double number) {
        if (doubleAreEqual(number, 0.0d)) {
            return 0;
        }
        if (Double.compare(number, 0.0d) > 0) {
            return (int) Math.ceil(number);
        } else if (Double.compare(number, 0.0d) < 0) {
            return (int) Math.floor(number);
        }
        return 0;
    }

    static private boolean doubleAreEqual(double d1, double d2) {
        if (Double.compare(d1, d2) == 0) {
            return true;
        }
        return (Math.abs(d1 - d2) <= 0.001);
    }

    @Override
    public String toString() {
        return "tictactoe.EndOfTheGameCalculator{" +
                "degrees=" + degrees +
                '}';
    }

}
