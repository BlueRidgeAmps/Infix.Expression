package infixexpressiongenerator;

/**
 * Course: CMSC 350-6380 Data Structures and Analysis
 * Project 2
 *
 * File: Node.java 
 * Author: Jonathan Pearson 
 * Date: February 4, 2017
 * Purpose: This class defines the interface called Node.  It allows for multiple
 *          classes to use the same method to conduct a different operation.
 *
 */

public interface Node {
    
    // define method to be used by OperatorNode
    // and OperandNode classes
    public String infixExpression();

}
