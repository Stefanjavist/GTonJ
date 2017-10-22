package com.engineer.st.exercises;

import javax.swing.JFrame;
import java.awt.*;

//create our frame where will be components(panels)
public class Application extends JFrame {

    public Application() {
        initUI();
    }

    //create window
    private void initUI() {
        //This is the entry point of the game. Here we have the main method.
        add(new Board());
        setSize(700, 400);
        this.setEnabled(true);
        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        //We create an instance of our code example and make it visible on the screen.
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Application app = new Application();
                app.setVisible(true);
            }
        });
    }
}
