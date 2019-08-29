package tictactoe;

public class Game {

    public static final String B = "_";
    public static final String X = "X";
    public static final String O = "O";

    private GameEndType gameEndedType = null;
    private int stepCounter = 0;
    private final int fieldSize;
    private final String[][] field;

    private final EndOfTheGameCalculator endOfTheGameCalculator;

    public Game(EndOfTheGameCalculator endOfTheGameCalculator, int fieldSize) {
        this.fieldSize = fieldSize;
        this.endOfTheGameCalculator = endOfTheGameCalculator;
        field = new String[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                field[i][j] = Game.B;
            }
        }
    }

    public String[][] getField() {
        return field;
    }

    public void step(int x, int y) {
        if (x >= fieldSize || x < 0) {
            System.out.println("Incorrect x value");
            return;
        }
        if (y >= fieldSize || y < 0) {
            System.out.println("Incorrect y value");
            return;
        }
        if (gameEndedType != null) {
            throw new GameAlreadyEndedException();
        }
        String player = selectPlayer();

        if (checkIfFilled(field[y][x])) {
            System.out.println("Field is already filled");
            return;
        }

        field[y][x] = player;

        calculateEndOfTheGame(x, y);

        stepCounter++;

        checkDraw();
    }

    private void checkDraw() {
        if (stepCounter == fieldSize * fieldSize) {
            gameEndedType = GameEndType.DRAW;
        }
    }

    private void calculateEndOfTheGame(int x, int y) {
        if (endOfTheGameCalculator.isGameEnded(field, x, y)) {
            String player = selectPlayer();
            if (player.equals(X)) {
                gameEndedType = GameEndType.X_PLAYER;
            } else if (player.equals(O)) {
                gameEndedType = GameEndType.O_PLAYER;
            }
        }
    }

    public String selectPlayer() {
        if (stepCounter % 2 == 0) {
            return X;
        } else {
            return O;
        }
    }

    private boolean checkIfFilled(String s) {
        return !s.equals(B);
    }

    public boolean isGameEnded() {
        return gameEndedType != null;
    }

    public void printField() {
        System.out.println("    0=1=2=> X");
        for (int i = 0; i < field.length; i++) {
            System.out.print("|" + i + "| ");
            String[] row = field[i];
            for (int j = 0; j < row.length; j++) {
                System.out.print(i < field.length - 1 || !row[j].equals(Game.B) ? row[j] : " ");
                if (j < row.length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
        System.out.println("\\ /");
        System.out.println(" V");
        System.out.println(" Y");
    }

    public void printResult() {
        System.out.println(gameEndedType + " WON!");
    }
}
