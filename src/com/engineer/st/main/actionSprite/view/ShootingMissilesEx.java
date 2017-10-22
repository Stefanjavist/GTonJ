package com.engineer.st.main.actionSprite.view;


import com.engineer.st.main.actionSprite.control.BoardCraft;

import javax.swing.*;
import java.awt.*;

public class ShootingMissilesEx extends JFrame {

    public ShootingMissilesEx() {
        initUI();
    }

    private void initUI() {
        add(new BoardCraft());

        setResizable(true);
        pack();

        setTitle("Shooting missile");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ShootingMissilesEx ex = new ShootingMissilesEx();
                ex.setVisible(true);
            }
        });
    }
}
