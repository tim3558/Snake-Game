package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Set my custom panel to be the snake field.
//
// @pharm SCREEN_SIZE -> The size of the JPanel.
// @pharm PART_SIZE -> The size of 1 snake part in the field.
// @pharm headPart -> The first snake part in the link.
// @pharm lastPart -> The end of snake part in the link.
// @pharm apple -> Control the food of the snake, position, collisions and keep tracking of the score.
// @pharm timer -> Keep the game running by reposition the snake parts, repainting the parts to the user screen,
//  and rechecking for any validations.
// @pharm delay -> The amount of time that the timer is "restarting" in milliseconds.
// @pharm myAction -> Get Input of the User, and control the movement velocity of the snake.
// @pharm lvlUp -> Track if the player has lvl up.
// @pharm counter -> Keep the lvlUp String on the panel for fixed time.
public class MyJPanel extends JPanel implements ActionListener {
    final private int SCREEN_SIZE = 500;
    final private int PART_SIZE = SCREEN_SIZE/10;

    private SnakePart headPart;
    private SnakePart lastPart;
    private Apple apple;

    private Timer timer;
    private int delay =  250;

    private MyAction myAction;

    private boolean lvlUp;
    private int counter;

//    MyJPanel constructor, sets the size of the panel, and create a new myAction, head of snake, apple and timer.
    public MyJPanel(){
        this.setPreferredSize(new Dimension(SCREEN_SIZE, SCREEN_SIZE));
        newGame();
    }

//    Create  a new head for the snake.
    private void createHead(){
        headPart = lastPart = new SnakePart(SCREEN_SIZE/2, SCREEN_SIZE/2, null, null);
    }

    private void createAction(){
        myAction = new MyAction(this, PART_SIZE);
    }
//    Create a new apple.
    private void createApple(int highScore){
        apple = new Apple(SCREEN_SIZE, PART_SIZE, highScore);
        apple.generateApple(headPart);
    }

//    Create a new timer.
    private void createTimer(){
        if(timer == null){
            timer = new Timer(delay, this);
            timer.start();
        }else{
            delay = 250;
            timer.setDelay(delay);
        }

    }

//    Paint all the components on the panel continuously, and lvlUp massage alternatively.
    public void paint(Graphics g){
        paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        paintSnake(g2D);
        paintApple(g2D);
        paintScore(g2D);
        paintHighScore(g2D);
        if(lvlUp){
            paintLvlUp(g2D);
            if(counter < 25){
                counter++;
            }else{
                lvlUp = false;
                counter = 0;
            }
        }
    }

//    paint the apple on the panel.
    private void paintApple(Graphics2D g2D){
        g2D.setPaint(Color.red);
        g2D.fillOval(apple.getxPos(), apple.getyPos(), PART_SIZE, PART_SIZE);
    }

//    Paint the snake on the panel.
    private void paintSnake(Graphics2D g2D){
        SnakePart tempPart = headPart;
        while(tempPart != null){
            g2D.setPaint((Color.BLACK));
            g2D.fillRect(tempPart.getxPos(), tempPart.getyPos(), PART_SIZE, PART_SIZE);
            g2D.setPaint((Color.gray));
            g2D.drawRect(tempPart.getxPos(), tempPart.getyPos(), PART_SIZE, PART_SIZE);
            tempPart = tempPart.getNextPart();
        }
    }

//    Paint the lvl up massage on the panel.
    private void paintLvlUp(Graphics2D g2D){
        g2D.setPaint(new Color(100,175,100));
        g2D.setFont(new Font("Vineta BT", Font.PLAIN, 23));
        g2D.drawString("Lvl Up!", 175, 30);
    }


//    Sets the paint of the highScore.
    private void paintHighScore(Graphics2D g2D){
        g2D.setPaint(Color.darkGray);
        g2D.setFont(new Font("Dubai Medium", Font.PLAIN, 25));
        g2D.drawString("High Score: " + apple.getHIGH_SCORE(), 325, 25);
    }

//    Sets the paint of the current score.
    private void paintScore(Graphics2D g2D){
        g2D.setPaint(Color.darkGray);
        g2D.setFont(new Font("Dubai Medium", Font.PLAIN, 25));
        g2D.drawString("Score: " + apple.getScore(), 25, 25);
    }

//    Keep looping while the timer is running in the timer delay speed and until the timer stops.
    @Override
    public void actionPerformed(ActionEvent e) {
//        checks if the snake have velocity.
//        If true, move the snake, check for any wall or snake or apple collisions, checks for lvlUp, and keeps repaint every new state.
        if(myAction.isMoving()){
            headPart.movePart(headPart.getxPos() + myAction.getxVel(), headPart.getyPos() + myAction.getyVel(), lastPart);
            headPart.checkForWallCollide(SCREEN_SIZE, PART_SIZE);
            if(headPart.checkForsnakeCollide()){
                newGame();
            }
            if(apple.hasEaten(headPart)){
                lastPart = new SnakePart(lastPart.getxPos(), lastPart.getyPos(),headPart, lastPart);
                lvlUp();
            }
            repaint();
        }
    }

//    Check if the player has lvl up based on the score, if true decrease the delay for harder difficulty.
    private void lvlUp(){
        if(apple.getScore() != 0 && apple.getScore() % 5 == 0 && delay > 75){
            delay = delay - 25;
            timer.setDelay(delay);
            lvlUp = true;
        }
    }

//    Restart the counter and sets lvlUp to false.
    private void resetLvlUp(){
        lvlUp = false;
        counter = 0;
    }

//    Create a new game panel, restart myAction, the snake, the apple, the timer and the lvlUp variables.
//    Also take the highScore to display in the next game.
    private void newGame(){
        int tempHigh = (apple == null) ? 0 : Math.max(apple.getScore(), apple.getHIGH_SCORE());
        createAction();
        createHead();
        createApple(tempHigh);
        createTimer();
        resetLvlUp();
    }
}
