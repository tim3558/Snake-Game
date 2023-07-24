package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;

//Checks for any action performed from the user, and update snake velocity accordingly.
//
//@pharm SPEED -> The speed of the snake velocity for implementing on the x or y axis's.
//@pharm xVel -> The velocity of the snake on the x axis.
//@pharm yVel -> The velocity of the snake on the y axis.
//@pharm upAction -> Check if the user has clicked the up button.
//@pharm downAction -> Check if the user has clicked the down button.
//@pharm rightAction -> Check if the user has clicked the right button.
//@pharm leftAction -> Check if the user has clicked the left button.\
//@pharm exitAction -> Check if the user has clicked the escape button.

public class MyAction {
    private final int SPEED;
    private int xVel;
    private int yVel;

    Action upAction, downAction, leftAction, rightAction, exitAction;

//  MyAction constructor.

    public MyAction(MyJPanel panel, int SPEED){

        this.SPEED = SPEED;
        this.xVel = this.yVel = 0;

        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new LeftAction();
        rightAction = new RightAction();
        exitAction = new ExitAction();
        setUpActionMap(panel);

    }

//    Sets an input map and action map to the keys, and adds them to the panel.
    private void setUpActionMap(MyJPanel panel){
        panel.getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction");
        panel.getActionMap().put("upAction", upAction);

        panel.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");
        panel.getActionMap().put("downAction", downAction);

        panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
        panel.getActionMap().put("rightAction", rightAction);

        panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
        panel.getActionMap().put("leftAction", leftAction);

        panel.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "exitAction");
        panel.getActionMap().put("exitAction", exitAction);

    }

//    Checks if the snake is moving or static.
//    @return -> true if the snake is static.
    public boolean isMoving(){
        if(xVel != 0 || yVel != 0){
            return true;
        }
        return false;
    }

//    @return -> The x velocity of the snake.
    public int getxVel() {
        return xVel;
    }

//    @return -> The y velocity of the snake.
    public int getyVel() {
        return yVel;
    }

//    Check if the user has clicked the up arrow.
//    If event has performed, and if the snake is not moving up or down, update the velocity for up movement.
    private class UpAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(yVel == 0){
                xVel = 0;
                yVel = -1 * SPEED;
            }
        }
    }


//    Check if the user has clicked the down arrow.
//    If event has performed, and if the snake is not moving up or down, update the velocity for down movement.
    private class DownAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(yVel == 0){
                xVel = 0;
                yVel = 1 * SPEED;
            }
        }
    }


//    Check if the user has clicked the right arrow.
//    If event has performed, and if the snake is not moving right or left, update the velocity for right movement.
    private class RightAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(xVel == 0){
                xVel = 1 * SPEED;
                yVel = 0;
            }
        }
    }


//    Check if the user has clicked the left arrow.
//    If event has performed, and if the snake is not moving right or left, update the velocity for left movement.
    private class LeftAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (xVel == 0) {
                xVel = -1 * SPEED;
                yVel = 0;
            }
        }
    }

//    Check if the user has clicked the escape button.
//    If event has performed, exit from the game and close the frame.
    private class ExitAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
