package com.codegym.games.game2048;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;

public class Game2048 extends Game{
    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];
    
    @Override
    public void initialize(){
        setScreenSize(SIDE, SIDE);
        createGame();
        
        //->last
        drawScene();
    }
    
    private void createGame(){
        createNewNumber();
        createNewNumber();
    }
    
    private void createNewNumber()
    {
        boolean isCreated = false;
        do 
        {
            int x = getRandomNumber(SIDE);
            int y = getRandomNumber(SIDE);
            if (gameField[y][x] == 0) 
            {
                gameField[y][x] = getRandomNumber(10) < 9 ? 2 : 4;
                isCreated = true;
            }
        }
        while(!isCreated);
    }
    
    private void setCellColoredNumber(int x, int y, int value)
    {
        Color color = getColorByValue(value);
        String str = value > 0? "" + value : "";
        setCellValueEx(x, y, color, str);
    }
    
    private Color getColorByValue(int value)
    {
        switch (value)
        {
            case 0:
                return Color.WHITE;
            case 2:
                return Color.RED;
            case 4:
                return Color.YELLOW;
            case 8:
                return Color.PINK;
            case 16:
                return Color.GREEN;
            case 32:
                return Color.BLUE;
            case 64:
                return Color.ORANGE;
            case 128:
                return Color.GREY;
            case 256:
                return Color.BROWN;
            case 512:
                return Color.PURPLE;
            case 1024:
                return Color.PLUM;
            case 2048:
                return Color.SALMON;
            default: 
                return Color.NONE;
        }
    }
    
    private void drawScene(){
        for(int x = 0; x < SIDE; x++){
            for(int y = 0; y < SIDE; y++)
            {
                setCellColoredNumber(x, y, gameField[y][x]);
            }
        }
    }
}


