package com.engineer.st.main.actionSprite.create;


import javax.swing.*;
import java.awt.*;

//The Sprite class shares common code from the Missile and Craft classes.
public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean vis;
    private Image image;


    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        this.vis = true;
    }


    protected void setImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected final void loadImage(String s) {
        ImageIcon ii = new ImageIcon(s);
        image = ii.getImage();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }

    //The getBounds() method returns the bounding rectangle of the sprite image.
    //We need the bounds in collision detection.
    public Rectangle getBound() {
        return new Rectangle(x, y, width, height);
    }
}
