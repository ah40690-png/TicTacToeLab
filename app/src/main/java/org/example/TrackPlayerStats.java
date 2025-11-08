package org.example;

public class TrackPlayerStats {
    private int xWins;
    private int oWins;
    private int ties;

    public void addXWin() { xWins++; }
    public void addOWin() { oWins++; }
    public void addTie() { ties++; }

    public int getXWins() { return xWins; }
    public int getOWins() { return oWins; }
    public int getTies() { return ties; }
}