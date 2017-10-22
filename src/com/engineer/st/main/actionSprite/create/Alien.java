package com.engineer.st.main.actionSprite.create;

public class Alien extends Sprite {

    private final int INITIAL_X;

    public Alien(final int x, final int y, final int sizeInitail) {
        super(x, y);
        INITIAL_X = sizeInitail;
        initAlien();
    }


    private void initAlien() {
      loadImage("src/image/alien.png");
        setImageDimensions();
    }

    //Aliens return to the screen on the right side
    //after they have disappeared on the left.
    public void move() {
        if(x < 0) {
            x = INITIAL_X;
        }

        x-=1;
    }

}
