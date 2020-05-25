package com.company;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
        // CalculatorMainFrame calculatorMainFrame = new CalculatorMainFrame();
    }
}
