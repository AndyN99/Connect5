package com.mygame.game;

public class Matrix {
    public static int[][] matrix; // 0 - default, 1 - Player1, 2 - Player2
    public Matrix()
    {
        matrix = new int[15][15];
    }
    public void updateMatrix(int row , int col, int turn)
    {
        if(turn == 0)
        {
            matrix[row][col] = 1;
        }else
        {
            matrix[row][col] = 2;
        }
    }
    public boolean checkVictory()
    {
        if(vertical5() || horizontal5() || ascendDiagonal5() || descendDiagonal5() )
        {
            return true;
        }
        return false;
    }
    public boolean vertical5()
    {
        int x = TurnMove.turn + 1;

        for(int r = 0; r < 15; r++)
        {
            int counter = 0;
            for(int c = 0; c < 15; c++)
            {
                if(matrix[r][c] == x)
                {
                    counter++;
                }
                if(counter >= 5)
                {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean horizontal5()
    {
        int x = TurnMove.turn + 1;
        for(int c = 0; c < 15; c++)
        {
            int counter = 0;
            for(int r = 0; r < 15; r++)
            {
                if(matrix[r][c] == x)
                {
                    counter++;
                }
                if(counter >= 5)
                {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean ascendDiagonal5()
    {
        int x = TurnMove.turn + 1;
        for(int r = 0; r < 11; r++)
        {
            for(int c = 0; c < 11; c++)
            {
                int count = 0;
                for(int i = 0; i < 5; i++)
                {
                    if(matrix[r+i][c+i] == x)
                    {
                        count++;
                    }
                    if(count >= 5)
                    {
                        return  true;
                    }
                }

            }
        }
        return false;
    }
    public boolean descendDiagonal5()
    {
        int x = TurnMove.turn + 1;
        for(int r = 0 ; r < 11; r++)
        {
            for(int c = 4 ; c < 15; c++)
            {
                int count = 0;
                for(int i = 0; i < 5; i++)
                {
                    if(matrix[r+i][c-i] == x)
                    {
                        count++;
                    }
                    if(count >= 5)
                    {
                        return  true;
                    }
                }

            }
        }
        return false;
    }
    public int getMatrixIndex(int row, int col)
    {
        return matrix[row][col];
    }
}
