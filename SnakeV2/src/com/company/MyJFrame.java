package com.company;

import javax.swing.*;

// Set my custom JFrame, and create a custom JPanel to be the field for the snake to move on.
public class MyJFrame extends JFrame {
    public MyJFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new MyJPanel());
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
