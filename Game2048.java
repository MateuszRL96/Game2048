package com.codegym.games.game2048;

import com.codegym.engine.cell.*;

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
    
    @Override
    public void onKeyPress(Key key){
        if (key == Key.UP)
        {
            moveUp();
        }
        else if (key == Key.DOWN)
        {
            moveDown();
        }
        else if (key == Key.RIGHT)
        {
            moveRight();
        }
        else if (key == Key.LEFT)
        {
            moveLeft();
        }
        else 
        {
            return;
        }
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
    
    private void moveUp(){
        
    }
    private void moveLeft(){
        boolean newNumber = false;
        for(int[] row : gameField)
        {
            boolean compressed = compressRow(row);
            boolean merged = mergeRow(row);
            if (merged){
                compressRow(row);
            }
            if(compressed || merged){
                newNumber = true;
            }
        }
        if(newNumber){
            createNewNumber();
        }
    }
    private void moveRight(){
        
    }
    private void moveDown(){
        
    }
    
    private boolean compressRow(int[] row)
    {
        int insertPosition = 0;
        boolean result = false;
        for(int x = 0; x < SIDE; x++)
        {
            if(row[x] > 0)
            {
                if(x != insertPosition)
                {
                    row[insertPosition] = row[x];
                    row[x] = 0;
                    result = true;
                }
            insertPosition++;
            }
        }
        return result;
    }
    
    private boolean mergeRow(int[] row)
    {
        boolean result = false;
        for(int i = 0; i < row.length - 1; i++)
        {
            if(row[i] != 0 && row[i] == row[i + 1])
            {
                row[i] += row[i + 1];
                row[i + 1] = 0;
                result = true;
            }
        }
        return result;
    }
    
    private void drawScene()
    {
        for(int x = 0; x < SIDE; x++)
        {
            for(int y = 0; y < SIDE; y++)
            {
                setCellColoredNumber(x, y, gameField[y][x]);
            }
        }
    }
}


