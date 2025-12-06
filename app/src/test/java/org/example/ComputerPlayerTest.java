package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComputerPlayerTest {

    @Test
    public void testComputerMakesValidMove() {
        TicTacToeGame game = new TicTacToeGame('O');
        ComputerPlayer cpu = new ComputerPlayer();

        int move = cpu.chooseMove(game);

        assertTrue(move >= 1 && move <= 9);
    }
}