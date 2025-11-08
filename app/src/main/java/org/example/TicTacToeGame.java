package org.example;

public class TicTacToeGame {
    private String[] board;
    private char currentPlayer;
    private char winner;
    private boolean gameOver;

    public TicTacToeGame() {
        this('X');
    }

    public TicTacToeGame(char startingPlayer) {
        board = new String[]{"1","2","3","4","5","6","7","8","9"};
        currentPlayer = startingPlayer;
        winner = ' ';
        gameOver = false;
    }

    public void printBoard() {
        System.out.println();
        System.out.println("  " + board[0] + "  |  " + board[1] + "  |  " + board[2]);
        System.out.println("-----+-----+-----");
        System.out.println("  " + board[3] + "  |  " + board[4] + "  |  " + board[5]);
        System.out.println("-----+-----+-----");
        System.out.println("  " + board[6] + "  |  " + board[7] + "  |  " + board[8]);
        System.out.println();
    }

    public boolean makeMove(int move) {
        if (move < 1 || move > 9) return false;

        int index = move - 1;

        if (board[index].equals("X") || board[index].equals("O")) return false;

        board[index] = String.valueOf(currentPlayer);

        if (checkWinner()) {
            winner = currentPlayer;
            gameOver = true;
        } else if (isBoardFull()) {
            gameOver = true;
        } else {
            switchPlayer();
        }
        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean isBoardFull() {
        for (String cell : board) {
            if (!(cell.equals("X") || cell.equals("O"))) return false;
        }
        return true;
    }

    private boolean checkWinner() {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };
        for (int[] pattern : winPatterns) {
            if (board[pattern[0]].equals(board[pattern[1]]) && board[pattern[1]].equals(board[pattern[2]]))
                return true;
        }
        return false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char getWinner() {
        return winner;
    }

    public String getCell(int position) {
        return board[position - 1];
    }
}
