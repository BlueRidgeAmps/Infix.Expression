package infixexpressiongenerator;

/**
 * Course: CMSC 350-6380 Data Structures and Analysis
 * Project 2
 *
 * File: TreeNode.java 
 * Author: Jonathan Pearson 
 * Date: February 4, 2017
 * Purpose: The TreeNode class defines the abstract method operator() that is used
 *          by the AddOperator, SubOperator, MulOperator, and DivOperator classes
 *          to handle the associated operator defined in the BuildInfix class.  
 *          Each class consists of a toString() method to return the standard
 *          notation of the associated operator.
 *
 */

public abstract class TreeNode {

    // method to determine operator
    abstract public String operator();
}

// AddOperator class handles operations for "+" character in input String
class AddOperator extends TreeNode {
    // operator method to handle assembly code operator
    public String operator() {
        return "Add";
    }
    // toString() method to handle operator for infix expression
    public String toString() {
        return "+";
    }
}

// SubOperator class handles operations for "-" character in input String
class SubOperator extends TreeNode {
// operator method to handle assembly code operator
    public String operator(){
        return "Sub";
    }
    // toString() method to handle operator for infix expression
    public String toString() {
        return "-";
    }
}

// MulOperator class handles operations for "*" character in input String
class MulOperator extends TreeNode {
   // operator method to handle assembly code operator
    public String operator(){
        return "Mul";
    }
    // toString() method to handle operator for infix expression
    public String toString() {
        return "*";
    }
}

// DivOperator class handles operations for "/" character in input String
class DivOperator extends TreeNode {
    // operator method to handle assembly code operator
    public String operator(){
        return "Div";
    }
    // toString() method to handle operator for infix expression
    public String toString() {
        return "/";
    }
}
