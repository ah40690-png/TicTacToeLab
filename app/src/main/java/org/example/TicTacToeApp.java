package org.example;

import java.util.Scanner;

public class TicTacToeApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("Welcome to Tic-Tac-Toe!");

        while (playAgain) {
            TicTacToeGame game = new TicTacToeGame();
            game.printBoard();

            while (!game.isGameOver()) {
                System.out.print("Your move. ");
                String input = in.nextLine().trim();

                // input validation
                int move;
                try {
                    move = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }

                if (!game.makeMove(move)) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }

                game.printBoard();
            }

            if (game.getWinner() != ' ') {
                System.out.println("Player " + game.getWinner() + " wins!");
            } else {
                System.out.println("It is a draw!");
            }

            // ask to play again
            boolean validResponse = false;
            while (!validResponse) {
                System.out.print("Would you like to play again (yes/no)? ");
                String response = in.nextLine().trim().toLowerCase();

                if (response.equals("yes")) {
                    playAgain = true;
                    validResponse = true;
                } else if (response.equals("no")) {
                    playAgain = false;
                    validResponse = true;
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("Invalid entry.");
                }
            }
        }

        in.close();
    }
}