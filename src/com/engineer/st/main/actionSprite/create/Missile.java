package com.engineer.st.main.actionSprite.create;


import java.util.Random;

public class Missile extends Sprite {

    private Random randomFire = new Random();
    private final int MISSILE_SPEED  = 5;

    public Missile(int x, int y) {
        super(x, y);
        initMissile();

    }

    private void initMissile(){
        loadImage("src/image/missile.png");
        setImageDimensions();
    }

    //The missile moves at constant speed.
    // When it hits the right border of the Board, it becomes invisible.
    // It is then removed from the list of missiles.
    public void move() {
        x += MISSILE_SPEED;
        if(x > randomFire.nextInt(10000)) {
            vis = false;
        }
    }
}
