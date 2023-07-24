package com.company;

// Create a snake part to place on the panel.
//
// @pharm xPos -> The current x position of the snake part on the field.
// @pharm yPos -> The current y position of the snake part on the field.
// @pharm nextLink -> The next snake part in the link.
// @pharm prevPart -> The previous snake part in the link.

public class SnakePart {

    private int xPos;
    private int yPos;
    private SnakePart nextPart;
    private SnakePart prevPart;

//    The constructor for the SnakePart class, get the current x position and y position, get the next part of the snake and the previous part of the snake.
    public SnakePart(int xPos, int yPos, SnakePart nextPart, SnakePart prevPart){
        this.xPos = xPos;
        this.yPos = yPos;
        this.nextPart = null;
        if(nextPart != null){
            setNewLast(nextPart);
        }
        if(prevPart != null){
            this.prevPart = prevPart;
        }
    }

//    Check for any collides between the head part and every other part of the snake.
//    @return -> true if any collision has found, false otherwise.
    public boolean checkForsnakeCollide(){
        if(this.nextPart != null){
            SnakePart tempPart = this.nextPart;
            while(tempPart != null){
                if(this.xPos == tempPart.xPos && this.yPos == tempPart.yPos){
                    return true;
                }
                tempPart = tempPart.nextPart;
            }
        }
        return false;
    }

//    Check for any wall collides of every snake part, if found shift the part to the other side of the panel.
    public void checkForWallCollide(int panelSize, int partSize){
        SnakePart tempPart = this;
        while(tempPart != null){
            if( (tempPart.xPos + partSize) > panelSize){
                tempPart.xPos = 0;
            }else if(tempPart.xPos < 0 ){
                tempPart.xPos = panelSize - partSize;
            }else if( (tempPart.yPos + partSize) > panelSize ){
                tempPart.yPos = 0;
            }else if( tempPart.yPos < 0){
                tempPart.yPos = panelSize - partSize;
            }
            tempPart = tempPart.getNextPart();
        }
    }

//    Move every part of the snake except of the head part to the previous part position, and the head to the xPos and yPos that the method gets.
//    @pharm xPos -> the next position for the head part.
//    @pharm yPos -> the next position for the head part.
//    @pharm lastPart -> the last part of the snake.

    public void movePart(int xPos, int yPos, SnakePart lastPart){
        if(this.nextPart != null){
            SnakePart tempPart = lastPart;
            while(tempPart.prevPart != null){
                tempPart.xPos = tempPart.prevPart.xPos;
                tempPart.yPos = tempPart.prevPart.yPos;
                tempPart = tempPart.prevPart;
            }
        }
        this.xPos = xPos;
        this.yPos = yPos;
    }
//     ****************** NEED TO CHECK ******************
//    Sets a new last part to the current part.
//    @pharm headPart -> The head part of the snake.
    public void setNewLast(SnakePart headPart){
        getLastPart(headPart).nextPart = this;
    }

//     ****************** NEED TO CHECK ******************
//    Gets the last part of the snake.
    public SnakePart getLastPart(SnakePart headPart){
        SnakePart tempPart = headPart;
        while(tempPart.nextPart != null){
            tempPart = tempPart.nextPart;
        }
        return tempPart;
    }

//    @return -> The next part of the current part.
    public SnakePart getNextPart(){
        return this.nextPart;
    }

//    @return -> The x position of the current part.
    public int getxPos() {
        return xPos;
    }

//    @return -> the y position of the current part.
    public int getyPos() {
        return yPos;
    }
}
