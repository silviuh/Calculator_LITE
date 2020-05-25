package com.company;

import java.time.temporal.ChronoField;
import java.util.Stack;

public class CalculatorLogic {
    private String postfixExpression;

    private int weight(char op) {
        switch (op) {
            case '-':
            case '+': {
                return 1;
            }
            case '*':
            case '/': {
                return 2;
            }
        }
        return -1;
    }

    public CalculatorLogic() {

    }

    public String getPostfixExpression() {
        return postfixExpression;
    }

    public void setPostfixExpression(String postfixExpression) {
        this.postfixExpression = postfixExpression;
    }

    public String convertInfixToPostfix(String infixExpression) {
        StringBuilder      result = new StringBuilder();
        Stack< Character > stack  = new Stack<>();

        for (int i = 0; i < infixExpression.length(); i++) {
            char c = infixExpression.charAt( i );

            if (Character.isDigit( c )) {
                while (i < infixExpression.length() && Character.isDigit( infixExpression.charAt( i ) )) {
                    result.append( c );
                    i++;
                }
                result.append( " " );
                i--;
            }

            if (c == '(')
                stack.push( c );
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!stack.isEmpty() && weight( c ) <= weight( stack.peek() )) {
                    if (stack.peek() == '(')
                        return "Invalid Expression";
                    result.append( stack.pop() );
                }
                stack.push( c );
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result.append( stack.pop() );
                if (!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression";
                else
                    stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                return "Invalid Expression";
            result.append( stack.pop() );
        }

        return result.toString();
    }

    public Integer evaluatePostfixExpression(String expression) {
        Stack< Integer > stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt( i );
            if (c == ' ')
                continue;

            else if (Character.isDigit( c )) {
                int n = 0;
                while (Character.isDigit( c )) {
                    n = n * 10 + ( int ) ( c - '0' );
                    i++;
                    c = expression.charAt( i );
                }
                i--;
                stack.push( n );
            } else {
                int val1 = stack.pop();
                int val2 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push( val2 + val1 );
                        break;

                    case '-':
                        stack.push( val2 - val1 );
                        break;

                    case '/':
                        stack.push( val2 / val1 );
                        break;

                    case '*':
                        stack.push( val2 * val1 );
                        break;
                }
            }
        }
        return stack.pop();
    }

    public String evaluate(StringBuilder expression) {
        postfixExpression = convertInfixToPostfix( expression.toString() );
        return evaluatePostfixExpression( postfixExpression ).toString();
    }
}
