package tictactoe;

import static tictactoe.Game.B;

public class EndOfTheGameCalculator {

    private final int itemsInRowToWin;
    private final DirectionsManager directionsManager;

    public EndOfTheGameCalculator(int itemsInRowToWin, DirectionsManager directionsManager) {
        this.itemsInRowToWin = itemsInRowToWin;
        this.directionsManager = directionsManager;
    }

    public boolean isGameEnded(String[][] field, int x, int y) {

        Vector direction;

        for (int directionNumber = 0; directionNumber < 4; directionNumber++) {

            direction = directionsManager.getDirection();

            if (foundWinInDirection(field, x, y, direction)) {
                return true;
            }
            directionsManager.next();
        }

        directionsManager.reset();
        return false;
    }

    private boolean foundWinInDirection(String[][] field, int x, int y, Vector direction) {
        int numberOfX = field[0].length;
        int numberOfY = field.length;

        int deltaX = direction.getX();
        int deltaY = direction.getY();

        int maxXLeftCheckRange = Math.min(x, itemsInRowToWin - 1);
        int maxXRightCheckRange = Math.min(numberOfY - x - 1, itemsInRowToWin - 1);
        int maxYUpCheckRange = Math.min(y, itemsInRowToWin - 1);
        int maxYDownCheckRange = Math.min(numberOfX - y - 1, itemsInRowToWin - 1);

        int startX = x - (maxXLeftCheckRange * Math.abs(deltaX));
        int endX = x + (maxXRightCheckRange * Math.abs(deltaX));
        if (deltaX < 0) {
            startX += endX;
            endX = startX - endX;
            startX -= endX;
        }
        int startY = y - (maxYUpCheckRange * Math.abs(deltaY));
        int endY = y + (maxYDownCheckRange * Math.abs(deltaY));
        if (deltaY < 0) {
            startY += endY;
            endY = startY - endY;
            startY -= endY;
        }
        int cellsToCheck = Math.min(getCellsToCheck(startX, endX), getCellsToCheck(startY, endY));

        String prevItem = B;
        int itemsInRaw = 1;
        for (int i = 0; i <= cellsToCheck; i++) {
            String current = field[startY][startX];
            if (!current.equals(B)) {
                if (!prevItem.equals(current)) {
                    prevItem = current;
                    itemsInRaw = 1;
                } else {
                    itemsInRaw++;
                }
            } else {
                prevItem = B;
            }
            if (itemsInRaw == itemsInRowToWin) {
                return true;
            }
            startX += deltaX;
            startY += deltaY;
        }
        return false;
    }

    private int getCellsToCheck(int start, int end) {
        if (end != start) {
            return Math.abs(end - start);
        } else {
            return itemsInRowToWin * 2 - 1;
        }
    }
}
