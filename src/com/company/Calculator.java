package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class Calculator {
    public              JFrame jFrame       = null;
    public static final int    FRAME_WIDTH  = 300;
    public static final int    FRAME_HEIGHT = 300;
    AppPanel appPanel = null;

    Calculator() {
        appPanel = new AppPanel();
        jFrame = new JFrame( "Calculator V.01" );
        jFrame.setContentPane( appPanel );

        jFrame.setSize( FRAME_WIDTH, FRAME_HEIGHT );
        jFrame.pack();
        jFrame.setResizable( false );
        jFrame.setVisible( true );
        jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        jFrame.setLocationRelativeTo( null );
    }

}
