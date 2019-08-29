import java.util.Scanner;

import tictactoe.DirectionsManager;
import tictactoe.EndOfTheGameCalculator;
import tictactoe.Game;

public class Main {
    public static void main(String[] args) {
        DirectionsManager directionsManager = new DirectionsManager();
        EndOfTheGameCalculator endOfTheGameCalculator = new EndOfTheGameCalculator(3, directionsManager);
        Game game = new Game(endOfTheGameCalculator, 3);

        Scanner s = new Scanner(System.in);

        while (!game.isGameEnded()) {
            game.printField();

            System.out.println(game.selectPlayer() + "'s turn. Your x,y: ");

            int x = s.nextInt();
            int y = s.nextInt();

            game.step(x, y);
        }
        game.printField();
        game.printResult();
    }
}
