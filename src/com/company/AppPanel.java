package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppPanel extends JPanel {
    public static final Font            BTN_FONT         = new Font( Font.SANS_SERIF, Font.BOLD, 24 );
    private             JPanel          panelButtons     = null;
    private             StringBuilder   expression       = null;
    private final       String[]        digits           = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
    private final       String[]        operators        = { "+", "-", "/", "*" };
    private final       String[]        specialOperators = { "C", "<--", "=", "(", ")", "^^" };
    private             CalculatorLogic logic            = null;

    private JTextField inputForm               = null;
    private JButton[]  operatorsButtons        = null;
    private JButton[]  digitsButtons           = null;
    private JButton[]  specialOperatorsButtons = null;

    AppPanel() {
        super();
        logic = new CalculatorLogic();
        initializeLayout();
        initializeFunctionality();
    }

    void initializeLayout() {
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        } catch ( ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e ) {
            e.printStackTrace();
        }

        this.setLayout( new BorderLayout( 4, 7 ) );
        this.setBorder( new EmptyBorder( 4, 4, 4, 4 ) );

        this.setBackground( Color.gray );

        inputForm = new JTextField( 10 );
        inputForm.setEditable( false );

        operatorsButtons = new JButton[operators.length];
        for (int i = 0; i < operators.length; i++) {
            operatorsButtons[i] = new JButton( operators[i] );
            operatorsButtons[i].setFont( BTN_FONT.deriveFont( Font.PLAIN ) );
        }

        digitsButtons = new JButton[digits.length];
        for (int i = 0; i < digits.length; i++) {
            digitsButtons[i] = new JButton( digits[i] );
            digitsButtons[i].setFont( BTN_FONT.deriveFont( Font.PLAIN ) );

        }

        specialOperatorsButtons = new JButton[specialOperators.length];
        for (int i = 0; i < specialOperators.length; i++) {
            specialOperatorsButtons[i] = new JButton( specialOperators[i] );
            specialOperatorsButtons[i].setFont( BTN_FONT.deriveFont( Font.PLAIN ) );
        }

        expression = new StringBuilder();
        panelButtons = new JPanel( new GridLayout( 4, 3, 4, 4 ) );
    }

    void initializeFunctionality() {
        for (int i = 0; i < digitsButtons.length; i++) {
            int finalI = i;
            digitsButtons[i].addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    expression.append( Integer.toString( finalI ) );
                    inputForm.setText( expression.toString() );
                }
            } );
        }

        for (int i = 0; i < operatorsButtons.length; i++) {
            int finalI = i;
            operatorsButtons[i].addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    expression.append( operators[finalI] );
                    inputForm.setText( expression.toString() );
                }
            } );
        }

        for (JButton specialOperatorsButton : specialOperatorsButtons) {
            if (specialOperatorsButton.getText().equals( "C" )) {
                specialOperatorsButton.addActionListener( new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        expression.delete( 0, expression.length() );
                        inputForm.setText( expression.toString() );
                    }
                } );
            } else if (specialOperatorsButton.getText().equals( "<--" )) {
                specialOperatorsButton.addActionListener( new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (expression.length() > 0)
                            expression.deleteCharAt( expression.length() - 1 ); // !
                        inputForm.setText( expression.toString() );
                    }
                } );
            } else if (specialOperatorsButton.getText().equals( "=" )) {
                specialOperatorsButton.addActionListener( new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String result = logic.evaluate( expression );
                        expression.delete( 0, expression.length() );
                        expression.append( result );
                        inputForm.setText( expression.toString() );
                    }
                } );
            } else if (specialOperatorsButton.getText().equals( "(" )) {
                specialOperatorsButton.addActionListener( new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        expression.append( "(" );
                        inputForm.setText( expression.toString() );
                    }
                } );
            } else if (specialOperatorsButton.getText().equals( ")" )) {
                specialOperatorsButton.addActionListener( new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        expression.append( ")" );
                        inputForm.setText( expression.toString() );
                    }
                } );
            } else if (specialOperatorsButton.getText().equals( "^^" )) {
                specialOperatorsButton.addActionListener( new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        expression.delete( 0, expression.length() );
                        expression.append( "Have a nice day!" );
                        inputForm.setText( expression.toString() );
                    }
                } );
            }
        }

        inputForm.setFont( BTN_FONT.deriveFont( Font.PLAIN ) );

        for (JButton digitsButton : digitsButtons) panelButtons.add( digitsButton, BorderLayout.CENTER );
        for (JButton operatorsButton : operatorsButtons) panelButtons.add( operatorsButton, BorderLayout.CENTER );
        for (JButton specialOperatorsButton : specialOperatorsButtons)
            panelButtons.add( specialOperatorsButton, BorderLayout.CENTER );
        this.add( panelButtons );
        this.add( inputForm, BorderLayout.NORTH );
    }
}
