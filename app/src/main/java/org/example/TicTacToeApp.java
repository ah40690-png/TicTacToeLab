package org.example;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TicTacToeApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean playAgain = true;
        char startingPlayer = 'X';

        TrackPlayerStats stats = new TrackPlayerStats();
        LogGame logger = new LogGame("game.txt");

        System.out.println("Welcome to Tic-Tac-Toe!");

        while (playAgain) {
            TicTacToeGame game = new TicTacToeGame(startingPlayer);
            game.printBoard();

            while (!game.isGameOver()) {
                System.out.print("What is your move? ");
                String input = in.nextLine().trim();

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

            char winner = game.getWinner();

            if (winner == 'X') {
                System.out.println("Player X wins!");
                stats.addXWin();
                startingPlayer = 'O';
            } else if (winner == 'O') {
                System.out.println("Player O wins!");
                stats.addOWin();
                startingPlayer = 'X';
            } else {
                System.out.println("It’s a draw!");
                stats.addTie();
            }

            logger.displayLog(stats);

            boolean validResponse = false;
            while (!validResponse) {
                System.out.print("Would you like to play again (yes/no)? ");
                String response = in.nextLine().trim().toLowerCase();

                if (response.equals("yes")) {
                    playAgain = true;
                    validResponse = true;
                    System.out.println("\nGreat! This time " + startingPlayer + " will go first!\n");
                } else if (response.equals("no")) {
                    playAgain = false;
                    validResponse = true;
                    System.out.println("\nWriting the game log to disk...");
                    logger.saveToFile(stats);
                    System.out.println("Please see game.txt for the final statistics!");
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("Invalid entry.");
                }
            }
        }

        in.close();
    }

   
    public static void saveGameLogForTest(int xWins, int oWins, int ties, String fileName) {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println("Final Tic-Tac-Toe Game Log");
            out.println("===========================");
            out.println("Player X Wins   " + xWins);
            out.println("Player O Wins   " + oWins);
            out.println("Ties            " + ties);
        } catch (IOException e) {
            System.out.println("Error writing test file.");
        }
    }
}