package infixexpressiongenerator;

/**
 * Course: CMSC 350-6380 Data Structures and Analysis
 * Project 2
 *
 * File: OperatorNode.java 
 * Author: Jonathan Pearson 
 * Date: February 4, 2017
 * Purpose: The OperatorNode class defines operations of defined operators from
 *          the BuildInfix class.  This class uses the Node interface and the 
 *          infixExpression() method assembles the operands and operators into
 *          an infix expression.
 *
 */

public class OperatorNode implements Node {

    // define class variables
    private final Node right, left;
    private final TreeNode operator;

    // class constructor
    public OperatorNode(Node right, Node left, TreeNode operator) {
        this.right = right;
        this.left = left;
        this.operator = operator;
    }

    // infixExpression() method to return a String of the 
    // expression in infix notation
    public String infixExpression() {
        String leftValue = left.infixExpression();
        String rightValue = right.infixExpression();

        return "(" + leftValue + " " + operator + " "
                + rightValue + ")";
    }
}
