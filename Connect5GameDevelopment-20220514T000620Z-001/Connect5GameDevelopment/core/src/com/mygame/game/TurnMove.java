package com.mygame.game;

public class TurnMove {
    public static int turn; //0 - Player1, 1 - Player2
    int[][] matrixRule = Matrix.matrix;

    public TurnMove() {
        turn = 0;
    }

    public int getTurn() {
        return turn;
    }

    public void updateTurn() {
        if (turn == 0) {
            turn = 1;
        } else {
            turn = 0;
        }
    }

}