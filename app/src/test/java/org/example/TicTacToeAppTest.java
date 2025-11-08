package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

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

    @Test
    public void testStartingPlayerConstructor() {
        TicTacToeGame game = new TicTacToeGame('O');
        game.makeMove(1);
        assertEquals("O", game.getCell(1));
    }

    @Test
    public void testSaveGameLogCreatesFile() {
        String testFile = "test_game.txt";
        TicTacToeApp.saveGameLogForTest(1, 2, 0, testFile);

        File file = new File(testFile);
        assertTrue(file.exists());

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String firstLine = reader.readLine();
            assertTrue(firstLine.contains("Final Tic-Tac-Toe Game Log"));
            reader.close();
        } catch (IOException e) {
            fail("Could not read file");
        }

        file.delete();
    }
}