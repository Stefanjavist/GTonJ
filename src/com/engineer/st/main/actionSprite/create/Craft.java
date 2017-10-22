package com.engineer.st.main.actionSprite.create;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Craft extends Sprite {
    private int dx, dy;
    //All the missiles fired by the spacecraft are stored in the missiles list.
    private ArrayList<Missile> missiles;

    public Craft(int x, int y) {
        super(x, y);
        initCraft();
    }

    private void initCraft() {
        missiles = new ArrayList<>();
        loadImage("src/image/craft.png");
        setImageDimensions();
    }

    //These x and y values are used in the paintComponent() method to draw
    //the image of the sprite.
    public void move() {
        x += dx;
        y += dy;

        if(x < 2) {
            x = 1;
        }

        if(y < 2){
            y = 1;
        }
    }

    public ArrayList getMissiles() {
        return missiles;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT) {
            dx = -1;
        }
        if(key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }

        if(key == KeyEvent.VK_SPACE){
            fire();
        }
    }

//The fire() method creates a new Missile object and adds it to the missiles ArrayList.
    private void fire() {
        missiles.add(new Missile(width +x, height+y ));
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        //When we release the left cursor key, we set the dx variable to zero.
        //The spacecraft will stop moving.
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }

    }

}
