package org.example;

public class TicTacToeGame {
    private String[] board;
    private char currentPlayer;
    private char winner;
    private boolean gameOver;

    public TicTacToeGame() {
        board = new String[]{"1","2","3","4","5","6","7","8","9"};
        currentPlayer = 'X';
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
        if (move < 1 || move > 9) {
            return false;
        }
        int index = move - 1;
        if (board[index].equals("X") || board[index].equals("O")) {
            return false;
        }

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
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }

   private boolean isBoardFull() {
    for (int i = 0; i < board.length; i++) {
        if (!(board[i].equals("X") || board[i].equals("O"))) {
            return false;
        }
    }
    return true;
}

   private boolean checkWinner() {
    
    if (board[0].equals(board[1]) && board[1].equals(board[2])) {
        return true;
    }
    
    if (board[3].equals(board[4]) && board[4].equals(board[5])) {
        return true;
    }
    
    if (board[6].equals(board[7]) && board[7].equals(board[8])) {
        return true;
    }
    
    if (board[0].equals(board[3]) && board[3].equals(board[6])) {
        return true;
    }
    
    if (board[1].equals(board[4]) && board[4].equals(board[7])) {
        return true;
    }
    
    if (board[2].equals(board[5]) && board[5].equals(board[8])) {
        return true;
    }
    
    if (board[0].equals(board[4]) && board[4].equals(board[8])) {
        return true;
    }
   
    if (board[2].equals(board[4]) && board[4].equals(board[6])) {
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
}