package infixexpressiongenerator;

/**
 * Course: CMSC 350-6380 Data Structures and Analysis
 * Project 2
 *
 * File: OperandNode.java 
 * Author: Jonathan Pearson 
 * Date: February 4, 2017
 * Purpose: The OperandNode class defines operations of defined operands from
 *          the BuildInfix class.  This class uses the Node interface and the 
 *          infixExpression() method to return the operand value that is used
 *          in the operatorNode class to assemble an infix expression.
 *
 */

public class OperandNode implements Node{
    
    // define class variable
    private final String value;
    
    // class constructor
    public OperandNode(String value){
        this.value = value;
    }
    
    // infixExpression() method to handle operand varable
    public String infixExpression(){
        return value;
    }
}
