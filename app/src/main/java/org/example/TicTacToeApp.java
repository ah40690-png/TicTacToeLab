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

            
            int selection = 0;
            while (selection < 1 || selection > 3) {
                System.out.println();
                System.out.println("What kind of game would you like to play?");
                System.out.println("1. Human vs Human");
                System.out.println("2. Human vs Computer (human goes first!)");
                System.out.println("3. Computer vs Human (computer goes first!)");
                System.out.print("How do you want to go about this game? ");
                String selInput = in.nextLine().trim();
                try {
                    selection = Integer.parseInt(selInput);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid selection. Try again.");
                }
            }

            boolean humanVsHuman = (selection == 1);
            boolean humanVsComputer = (selection == 2);
            boolean computerVsHuman = (selection == 3);

            if (computerVsHuman) {
                startingPlayer = 'X'; 
            } else {
                startingPlayer = 'X'; 
            }

            TicTacToeGame game = new TicTacToeGame(startingPlayer);
            game.printBoard();

           
            if (computerVsHuman && game.getCurrentPlayer() == 'X') {
                System.out.println("Affirmative. The computer shall go first.");
            } else if (humanVsComputer || humanVsHuman) {
                
            }

            while (!game.isGameOver()) {
                char current = game.getCurrentPlayer();

                boolean isComputerTurn = false;
                if (humanVsComputer) {
                    
                    if (current == 'O') isComputerTurn = true;
                } else if (computerVsHuman) {
                    
                    if (current == 'X') isComputerTurn = true;
                } 

                if (isComputerTurn) {
                  
                    game.makeComputerMove();
                    game.printBoard();
                    continue;
                }

               
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
                    System.out.println("\nGreat! This time " + startingPlayer + " will go first.\n");
                } else if (response.equals("no")) {
                    playAgain = false;
                    validResponse = true;
                    System.out.println("\nWriting the game log to disk...");
                    logger.saveToFile(stats);
                    System.out.println("Please see game.txt for final statistics.");
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("Invalid input.");
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
