package com.engineer.st.exercises;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

//The objects on the board are either images or
// are drawn with the painting tools provided by the Java 2D API.
// In the next example, we draw a donut shape.
public class Board extends JPanel {

    //The painting is done inside the paintComponent() method.
    @Override
    public void paintComponent(Graphics g) {
        //It is a good programming practice to delegate the actual
        //painting to a specific method.
        super.paintComponent(g);
        drawDonut(g);
    }

    private void drawDonut(Graphics g) {
//        The Graphics2D class extends the Graphics class.
// It provides more sophisticated control over geometry,
// coordinate transformations, colour management, and text layout.
        Graphics2D g2d = (Graphics2D) g;

        //------------
//        The rendering hints are used to make the drawing smooth.
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                                                RenderingHints.VALUE_ANTIALIAS_ON);
//                                                          КАЧЕСТВО ЗНАЧЕНИЙ
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
        //-----------

        //-----------
        //We get the height and the width of the window.
        // We need them to center the donut shape on the window.
        Dimension size = getSize();
        double w = size.getWidth();
        double h = size.getHeight();
        //----------

        //Here we create the ellipse.
        Ellipse2D e = new Ellipse2D.Double(0, 0, 300, 200);
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.CYAN);
        g2d.setColor(Color.GREEN);
        g2d.setBackground(Color.black);

//        Here the ellipse is rotated 72 times to create a donut shape.
        for (double deg = 0; deg < 360; deg+=4) {
            AffineTransform at = AffineTransform.getTranslateInstance(w/2, h/2);
            at.rotate(Math.toRadians(deg));
            g2d.draw(at.createTransformedShape(e));
        }
    }

}
