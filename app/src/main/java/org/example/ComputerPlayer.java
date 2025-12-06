package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer {

    public int chooseMove(TicTacToeGame game) {
        char ai = game.getCurrentPlayer();
        char opponent = (ai == 'X') ? 'O' : 'X';

        List<Integer> available = getAvailable(game);


        if (countMoves(game) == 0) {
            int[] corners = {1, 3, 7, 9};
            return corners[new Random().nextInt(corners.length)];
        }


        if (countMoves(game) == 1 && isFree(game, 5)) {
            return 5;
        }


        for (int pos : available) {
            if (wouldWin(game, pos, ai)) {
                return pos;
            }
        }

       
        for (int pos : available) {
            if (wouldWin(game, pos, opponent)) {
                return pos;
            }
        }

       
        return available.get(new Random().nextInt(available.size()));
    }

    private List<Integer> getAvailable(TicTacToeGame game) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            String c = game.getCell(i);
            if (!c.equals("X") && !c.equals("O")) {
                list.add(i);
            }
        }
        return list;
    }

    private boolean isFree(TicTacToeGame game, int pos) {
        if (pos < 1 || pos > 9) return false;
        String c = game.getCell(pos);
        return !(c.equals("X") || c.equals("O"));
    }

    private int countMoves(TicTacToeGame game) {
        int c = 0;
        for (int i = 1; i <= 9; i++) {
            String v = game.getCell(i);
            if (v.equals("X") || v.equals("O")) c++;
        }
        return c;
    }

    private boolean wouldWin(TicTacToeGame game, int pos, char symbol) {
        String old = game.getCell(pos);
        game.setCellForSimulation(pos, symbol);
        boolean win = game.checkWinnerForSimulation();
        game.restoreCellAfterSimulation(pos, old);
        return win;
    }
}