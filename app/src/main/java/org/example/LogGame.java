package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogGame {
    private String fileName;

    public LogGame(String fileName) {
        this.fileName = fileName;
    }

    public void displayLog(TrackPlayerStats stats) {
        System.out.println();
        System.out.println("The current log is:");
        System.out.println("Player X Wins   " + stats.getXWins());
        System.out.println("Player O Wins   " + stats.getOWins());
        System.out.println("Ties            " + stats.getTies());
        System.out.println();
    }

    public void saveToFile(TrackPlayerStats stats) {
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println("Final Tic-Tac-Toe Game Log");
            out.println("===========================");
            out.println("Player X Wins   " + stats.getXWins());
            out.println("Player O Wins   " + stats.getOWins());
            out.println("Ties            " + stats.getTies());
        } catch (IOException e) {
            System.out.println("Error writing game log to file.");
        }
    }
}
