public class Game {

    private boolean gameEnded;
    private int stepCounter = 0;
    private final String[][] field = {{"", "", ""}, {"", "", ""}, {"", "", ""}};


    public String[][] getField() {
        return field;
    }

    public void step(int x, int y) {
        String player = selectPlayer();

        checkIfFilled(field[x][y]);

        field[x][y] = player;

        calculateEndOfTheGame();
        if (gameEnded) {
            throw new GameAlreadyEndedException();
        }

        stepCounter++;
    }

    private void calculateEndOfTheGame() {
        for (int i = 0; i < field.length; i++) {
            String[] row = field[i];
            if (!row[0].equals("") && row[0].equals(row[1]) && row[1].equals(row[2])) {
                gameEnded = true;
            }
        }
    }

    private String selectPlayer() {
        String player;
        if (stepCounter % 2 == 0) {
            player = "X";
        } else {
            player = "O";
        }
        return player;
    }

    private void checkIfFilled(String s) {
        if (!s.equals("")) {
            throw new AlreadyFilledFieldException();
        }
    }
}
