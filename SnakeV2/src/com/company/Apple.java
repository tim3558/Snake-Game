package com.company;

import java.util.Random;

// Create a apple object to place on the panel.
//
// @pharm xPos -> The current x position of the apple on the field.
// @pharm yPos -> The Current y position of the apple on the field.
// @pharm score -> The current score (how much apples has been eaten).
// @pharm SCREEN_SIZE -> The size of the field.
// @pharm APPLE_SIZE -> The size of the apple.
// @pharm HIGH_SCORE -> The last high score.
public class Apple {

    private int xPos;
    private int yPos;
    private int score;
    private final int SCREEN_SIZE;
    private final int APPLE_SIZE;
    private final int HIGH_SCORE;


//    The apple constructor,sets the score to zero and gets the panel size, the apple size and the last high score.
    public Apple(int screenSize, int appleSize, int highScore){
        SCREEN_SIZE = screenSize;
        APPLE_SIZE = appleSize;
        HIGH_SCORE = highScore;
        score = 0;
    }

//     Check for and collisions between the head of the snake and the current apple on the field, if true reposition thr apple and update the score.
//     @return true -> If collision has found return true.
    public boolean hasEaten(SnakePart headPart){
        if(headPart.getxPos() == xPos && headPart.getyPos() == yPos){
            score++;
            generateApple(headPart);
            return true;
        }
        return false;
    }

//    @return -> The last high score.
    public int getHIGH_SCORE(){
        return HIGH_SCORE;
    }

//    @return -> The current score.
    public int getScore(){
        return score;
    }

//    Generate a random x position and random y position, check if the the apple is collide with the snake, if true call the method again.
    public void generateApple(SnakePart headPart){
        SnakePart tempPart = headPart;
        xPos = randNum();
        yPos = randNum();
        while(tempPart != null){
            if(xPos == tempPart.getxPos() && yPos == tempPart.getyPos()){
                generateApple(headPart);
            }
            tempPart = tempPart.getNextPart();
        }
    }

//    generate a new random by the size of the field.
    private int randNum(){
        Random rand = new Random();
        int temp = rand.nextInt(SCREEN_SIZE);
        while(temp % APPLE_SIZE != 0){
            temp--;
        }
        return temp;
    }

//    Getter for xPos.
    public int getxPos() {
        return xPos;
    }

//    Getter for yPos.
    public int getyPos() {
        return yPos;
    }
}