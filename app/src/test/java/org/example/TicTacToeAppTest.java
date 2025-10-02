package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeAppTest {

    @Test
    public void testValidMove() {
        TicTacToeGame game = new TicTacToeGame();
        assertTrue(game.makeMove(1));
    }

    @Test
    public void testInvalidMoveOutOfRange() {
        TicTacToeGame game = new TicTacToeGame();
        assertFalse(game.makeMove(10));
    }

    @Test
    public void testInvalidMoveAlreadyTaken() {
        TicTacToeGame game = new TicTacToeGame();
        assertTrue(game.makeMove(1));
        assertFalse(game.makeMove(1)); 
    }

    @Test
    public void testWinningConditionRow() {
        TicTacToeGame game = new TicTacToeGame();
        game.makeMove(1); 
        game.makeMove(4); 
        game.makeMove(2); 
        game.makeMove(5); 
        game.makeMove(3); 
        assertTrue(game.isGameOver());
        assertEquals('X', game.getWinner());
    }

    @Test
    public void testDrawCondition() {
        TicTacToeGame game = new TicTacToeGame();
        
        game.makeMove(1); 
        game.makeMove(2); 
        game.makeMove(3); 
        game.makeMove(5); 
        game.makeMove(4); 
        game.makeMove(6); 
        game.makeMove(8); 
        game.makeMove(7); 
        game.makeMove(9); 
        assertTrue(game.isGameOver());
        assertEquals(' ', game.getWinner());
    }
}