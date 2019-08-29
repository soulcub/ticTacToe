package tictactoe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static tictactoe.Game.B;
import static tictactoe.Game.O;
import static tictactoe.Game.X;

public class GameSpec {

    private Game game;
    private EndOfTheGameCalculator calculator = mock(EndOfTheGameCalculator.class);

    @BeforeEach
    public void init() {
        game = new Game(calculator, 3);
    }

    @Test
    public void mustGenerateTicTacToeField() {
        int expectedSize = 3;

        String[][] field = game.getField();

        assertEquals(field.length, expectedSize);
        for (int i = 0; i < field.length; i++) {
            int actual = field[i].length;
            assertEquals(expectedSize, actual);
        }
    }

    @Test
    public void mustGenerateAllFieldsEmptyStrings() {
        String[][] field = game.getField();

        for (int i = 0; i < field.length; i++) {
            String[] row = field[i];
            for (int j = 0; j < row.length; j++) {
                assertEquals(B, field[i][j]);
            }
        }
    }

    @Test
    public void mustSetSymbolByCoords() {
        String expected = X;

        game.step(1, 1);

        String[][] field = game.getField();
        String actual = field[1][1];
        assertEquals(expected, actual);
    }

    @Test
    public void mustChangePlayerAfterMove() {
        game.step(1, 1);
        game.step(1, 2);

        String[][] field = game.getField();

        assertEquals(X, field[1][1]);
        assertEquals(O, field[1][2]);
    }

    @Test
    public void mustNotOverrideExistingSteps() {
        Assertions.assertThrows(AlreadyFilledFieldException.class, () -> {
            game.step(1, 1);
            game.step(1, 1);
        });

    }

    @Test
    public void mustNotSetOutOfBoundsOfField() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            game.step(5, 5);
        });
    }


    @Test
    public void mustNotAbleToDoMoveAfterEndOfTheGame() {
        when(calculator.isGameEnded(game.getField(), 0, 0)).thenReturn(true);

        game.step(0, 0);

        assertTrue(game.isGameEnded());

        Assertions.assertThrows(GameAlreadyEndedException.class, () -> {
            game.step(1, 1);
        });

    }

}