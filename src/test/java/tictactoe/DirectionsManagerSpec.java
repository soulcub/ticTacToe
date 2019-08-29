package tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionsManagerSpec {

    @Test
    public void shouldInitByDirectionToRight() {
        DirectionsManager endOfTheGameCalculator = new DirectionsManager();

        assertEquals(new Vector(0, 1), endOfTheGameCalculator.getDirection());
    }

    @Test
    public void shouldChangeDirectionToTheNextAndReturnToTheFirst() {
        DirectionsManager endOfTheGameCalculator = new DirectionsManager();

        Vector next = endOfTheGameCalculator.next();
        assertEquals(new Vector(1, 1), next);
        next = endOfTheGameCalculator.next();
        assertEquals(new Vector(1, 0), next);
        next = endOfTheGameCalculator.next();
        assertEquals(new Vector(1, -1), next);
        next = endOfTheGameCalculator.next();
        assertEquals(new Vector(0, 1), next);
    }

    @Test
    public void shouldResetDirection() {
        DirectionsManager endOfTheGameCalculator = new DirectionsManager();

        Vector next = endOfTheGameCalculator.next();
        assertEquals(new Vector(1, 1), next);

        endOfTheGameCalculator.reset();
        assertEquals(new Vector(0, 1), endOfTheGameCalculator.getDirection());
    }

}