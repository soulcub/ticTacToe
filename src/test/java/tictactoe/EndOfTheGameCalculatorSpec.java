package tictactoe;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static tictactoe.Game.B;
import static tictactoe.Game.X;

public class EndOfTheGameCalculatorSpec {

    private static final int ITEMS_IN_ROW_TO_WIN = 3;

    private EndOfTheGameCalculator calculator;

    @BeforeEach
    public void init() {
        calculator = new EndOfTheGameCalculator(ITEMS_IN_ROW_TO_WIN, new DirectionsManager());
    }

    static Stream<Arguments> fields() {
        return Stream.of(
                Arguments.of((Object) new String[][]{
                        {X, X, X},
                        {B, B, B},
                        {B, B, B}
                }),
                Arguments.of((Object) new String[][]{
                        {B, B, B},
                        {X, X, X},
                        {B, B, B}
                }),
                Arguments.of((Object) new String[][]{
                        {B, B, B},
                        {B, B, B},
                        {X, X, X}
                }),
                Arguments.of((Object) new String[][]{
                        {X, B, B},
                        {X, B, B},
                        {X, B, B}
                }),
                Arguments.of((Object) new String[][]{
                        {B, X, B},
                        {B, X, B},
                        {B, X, B}
                }),
                Arguments.of((Object) new String[][]{
                        {B, B, X},
                        {B, B, X},
                        {B, B, X}
                }),
                Arguments.of((Object) new String[][]{
                        {X, B, B},
                        {B, X, B},
                        {B, B, X}
                }),
                Arguments.of((Object) new String[][]{
                        {B, B, X},
                        {B, X, B},
                        {X, B, B}
                })
        );
    }

    @ParameterizedTest
    @MethodSource("fields")
    public void gameShouldBeEndedByTheCorrectNumberOfSameElementsInRaw(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            String[] currentRow = field[i];
            for (int j = 0; j < currentRow.length; j++) {
                if (!currentRow[j].equals(B)) {
                    assertTrue(calculator.isGameEnded(field, j, i), "x=" + j + ", y=" + i + ":");
                }
            }
        }
    }

}