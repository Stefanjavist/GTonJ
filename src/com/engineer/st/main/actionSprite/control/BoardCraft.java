package com.engineer.st.main.actionSprite.control;

import com.engineer.st.main.actionSprite.create.Alien;
import com.engineer.st.main.actionSprite.create.Craft;
import com.engineer.st.main.actionSprite.create.Missile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class BoardCraft extends JPanel implements ActionListener{

    private final int ICRAFT_X = 60;
    private final int ICRAFT_Y = 40;
    private Timer timer;
    private Craft craft;
    private final int DELAY = 15;
    //collision detection variables
    private boolean ingame;
    private ArrayList<Alien> aliens;
    private final int B_WIDTH = 700;//является и размером рамки
    private final int B_HEIGHT = 500;

    //These are the initial positions of alien ships.
    private Random random = new Random();
    //arr[i][j]
    private final int I_ARR = 1;//quantity of aliens
    private final int J_ARR = 2;//position

    private int[][] position;

    private int[][] posArray() {
        position = new int[I_ARR][J_ARR];
        for (int i = 0; i < I_ARR; i++) {
            for (int j = 1; j >= 0; j--) {
                int x = random.nextInt(B_WIDTH) + B_WIDTH;
                int y = random.nextInt(B_HEIGHT);
                if(j == 1) {
                    position[i][j] = y;
                }else{
                    position[i][j] = x;
                }
            }
        }
        return position;

    }

    public BoardCraft() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());//add listener for keys
        // class TAdapter ext KeyEvent and hear event from "craft.pressed("KeyEvent" e){}"
        setFocusable(true);//Sets the focusable state of this Component to the specified value. This
        //value overrides the Component's default focusability.
        setBackground(Color.black);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));//set size our board

        ingame = true;

        craft = new Craft(ICRAFT_X, ICRAFT_Y);

        initAliens();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    //The initAliens() method creates a list of alien objects.
    //The aliens take their initial positions from the pos array.
    private void initAliens() {
        aliens = new ArrayList<>();

        for (int[] p : posArray()) {
            aliens.add(new Alien(p[0], p[1], B_WIDTH));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(ingame) {

            drawObjects(g);

        } else{

            drawGameOver(g);

        }
        //ensure that the necessary native libraries are loaded
        Toolkit.getDefaultToolkit().sync();
    }

    //In the drawObjects() method, we draw the craft and all the available missiles.
    private void drawObjects(Graphics g) {
        if(craft.isVisible()) {
            g.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);

        }

        ArrayList<Missile> ms = craft.getMissiles();

        for (Missile m : ms) {
            if(m.isVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }

        }

        ArrayList<Alien> a = aliens;

        for (Alien al:a) {
            if(al.isVisible()){
                g.drawImage(al.getImage(), al.getX(), al.getY(), this);
            }
        }


        g.setColor(Color.RED);
        g.drawString("Aliens:"+aliens.size(), 5, 10);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
//        ArrayList ms = craft.getMissiles();
//        for (Object m1 : ms ) {
//            Missile m = (Missile) m1;
//            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
//        }
    }

    private void drawGameOver(Graphics g) {

        String msgGG = "Game Over";
        String msgWin = "You win";
        Font small= new Font("Helvetica", Font.ITALIC, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.RED);
        g.setFont(small);
        
        if(aliens.isEmpty() && craft.isVisible()){
        g.drawString(msgWin,(B_WIDTH - fm.stringWidth(msgWin)) / 2 , B_HEIGHT / 2);

        }else {
            g.drawString(msgGG, (B_WIDTH - fm.stringWidth(msgGG)) / 2, B_HEIGHT / 2);
        }
    }

    //The actionPerformed() method is called every DELAY ms.
    //We move the sprite and repaint the board.
    @Override
    public void actionPerformed(ActionEvent e) {
        inGame();

        updateCraft();
        updateMissiles();
        updateAliens();

        checkCollision();

        repaint();
    }

    private void inGame() {
        if(!ingame) {
            timer.stop();
        }

    }

    private void updateCraft() {
        if(craft.isVisible()) {
            craft.move();
        }
    }

    //In the updateMissiles() method we parse all missiles from the missiles list.
    //Depending on what the isVisible() method returns, we either move the missile or remove it from the container.
    private void updateMissiles() {
        ArrayList<Missile> ms = craft.getMissiles();

        for (int i = 0; i < ms.size(); i++) {

            Missile m = ms.get(i);

            if(m.isVisible()) {

                m.move();

            } else{

                ms.remove(i);
            }
        }
    }

    private void updateAliens() {
        if(aliens.isEmpty()) {
            ingame = false;
            return;
        }

        for (int i = 0; i < aliens.size(); i++) {

            Alien a = aliens.get(i);

            if(a.isVisible()) {

                a.move();

            } else {

                aliens.remove(i);

            }

        }
    }

    private void checkCollision() {
        Rectangle r3 = craft.getBound();

        for (Alien alien : aliens) {
            Rectangle r2 = alien.getBound();

            if(r3.intersects(r2)){
                craft.setVisible(false);
                alien.setVisible(false);
                ingame = false;
            }
        }

        ArrayList<Missile> ms = craft.getMissiles();

        for (Missile m : ms) {
            Rectangle r1 = m.getBound();

            for (Alien a : aliens) {
                Rectangle r2 = a.getBound();

                if(r1.intersects(r2)){
                    m.setVisible(false);
                    a.setVisible(false);
                }
            }
        }
    }



    //In the Board class we listen for key events.
    //The overridden methods of the KeyAdapter class delegate the processing to the methods of the Craft class.
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
}

